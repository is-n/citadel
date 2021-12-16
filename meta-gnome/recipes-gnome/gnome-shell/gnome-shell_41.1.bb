LICENSE = "GPLv2" 
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

REQUIRED_DISTRO_FEATURES = "x11 systemd pam"

ERROR_QA_remove = "unknown-configure-option"

SRCREV = "d85bd654a3c830a8c3982286c1876321c96faf7d"

EXTERNAL_TREE_VAR="CITADEL_GNOME_SHELL_PATH"
GIT_URI = "gitsm://github.com/brl/gnome-shell.git;branch=citadel;protocol=https"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gettext gobject-introspection gobject-introspection-data features_check bash-completion external-tree



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
