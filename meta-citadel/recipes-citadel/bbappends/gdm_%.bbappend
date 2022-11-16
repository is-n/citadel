FILESEXTRAPATHS:prepend := "${THISDIR}/gdm:"

INSANE_SKIP:${PN}-src = "buildpaths"

SRC_URI += "file://gdm.conf"
DEPENDS += "plymouth"

do_install:append() {
    install -d ${D}${sysconfdir}/default/volatiles
    echo "d gdm gdm 755 ${localstatedir}/run/gdm/greeter none" > ${D}${sysconfdir}/default/volatiles/99_gdm
    rm ${D}${sysconfdir}/gdm/custom.conf
    rm ${D}${libdir}/udev/rules.d/61-gdm.rules
    install -m 644 ${WORKDIR}/gdm.conf ${D}${sysconfdir}/gdm/custom.conf
}

EXTRA_OEMESON = " \
    -Dplymouth=enabled \
    -Ddefault-pam-config=openembedded \
    -Dpam-mod-dir=${base_libdir}/security \
"
