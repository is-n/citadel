LICENSE = "GPLv2" 
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

REQUIRED_DISTRO_FEATURES = "x11 systemd pam"

ERROR_QA_remove = "unknown-configure-option"


GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gsettings gettext gobject-introspection gobject-introspection-data features_check bash-completion

def gnome_verdir(v):
    return oe.utils.trim_version(v, 1)

SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive \
           file://0001-do-not-use-python-path-from-build-environment.patch \
           file://0001-Remove-calendar-server-fix-build.patch \
           file://0001-Remove-log-out-label-from-power-off-in-status-UI.patch \
           file://0001-Disabled-calendar-events-from-user-session.patch \
           file://0002-Citadel-Gnome-Shell-changes.patch \
           "

SRC_URI[archive.sha256sum] = "52f971e85140e5de74b9369ef6656e49ce95af1f232fc1e0df1f046129ab4f65"


DEPENDS = " \
    libxml2-native \
    sassc-native \
    gtk4 \
    mutter \
    gcr \
    gjs \
    mozjs78 \
    network-manager-applet \
    gnome-autoar \
    polkit \
    libcroco \
    startup-notification \
    ibus \
    gsettings-desktop-schemas \
"

RDEPENDS_${PN} = "gsettings-desktop-schemas librsvg-gtk"

FILES_${PN} += "\
    ${datadir}/dbus-1 \
    ${datadir}/xdg-desktop-portal \
    ${datadir}/gnome-control-center  \
    ${datadir}/bash-completion/completions/gnome-extensions \
    ${systemd_user_unitdir} \
"

PACKAGES =+ "${PN}-tools"
FILES_${PN}-tools += "\
    /usr/bin/gnome-shell-perf-tool \
    /usr/bin/gnome-shell-extension-tool \
"

do_compile_prepend() {
    rm -f ${B}/data/theme/*.css
}

do_configure_append () {
    MUTTER_DIR="/usr/lib/mutter"
    sed --in-place=.old1 "s;=${MUTTER_DIR};=${PKG_CONFIG_SYSROOT_DIR}${MUTTER_DIR};" ${B}/build.ninja
}

EXTRA_OEMESON += "-Dman=false -Dsystemd=true -Dnetworkmanager=true -Dextensions_app=false -Dtests=false"
