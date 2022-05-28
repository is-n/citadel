SUMMARY = "GNOME Display Manager"
LICENSE="GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = " \
    dconf-native \
    itstool-native \
    gtk+3 \
    glib-2.0 \
    accountsservice \
    libcanberra \
    libxinerama \
    libpam \
    plymouth \
"

REQUIRED_DISTRO_FEATURES = "x11 systemd pam"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gsettings gobject-introspection gettext systemd useradd upstream-version-is-even features_check

SRC_URI[archive.sha256sum] = "5738c4293a9f5a80d4a6e9e06f4d0df3e9f313ca7b61bfb4d8afaba983e200dc"

SRC_URI += "file://gdm.conf"

GIR_MESON_OPTION = ""

FILES:${PN} += " \
    ${libdir}/systemd/system/gdm.service \
    ${libdir}/systemd/user \
    ${base_libdir}/security/pam_gdm.so \
    ${datadir}/gnome-session/sessions \
    ${datadir}/dconf/profile \
    /run/gdm/greeter \
"

do_install:append() {
    install -d ${D}${sysconfdir}/default/volatiles
    echo "d gdm gdm 755 ${localstatedir}/run/gdm/greeter none" > ${D}${sysconfdir}/default/volatiles/99_gdm
    rm ${D}${sysconfdir}/gdm/custom.conf
    rm -rf ${D}/run/gdm
    rm ${D}${libdir}/udev/rules.d/61-gdm.rules
    install -m 644 ${WORKDIR}/gdm.conf ${D}${sysconfdir}/gdm/custom.conf
}

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --home /var/lib/gdm -u 21 -s /bin/false --user-group gdm"

SYSTEMD_SERVICE:${PN} = "${BPN}.service"

EXTRA_OEMESON = "\
    -Dplymouth=enabled \
    -Dxdmcp=disabled \
    -Ddefault-pam-config=openembedded \
    -Dpam-mod-dir=${base_libdir}/security \
    -Dgdm-xsession=true\
"

PACKAGES += "${PN}-help"
FILES:${PN}-help = "${datadir}/help"
