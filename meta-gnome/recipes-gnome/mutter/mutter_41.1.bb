SUMMARY = "Window and compositing manager based on Clutter"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = " \
    xserver-xorg-cvt-native \
    libsm \
    virtual/libx11 \
    libxi \
    glib-2.0 \
    libwacom \
    xcomposite \
    libinput \
    libcanberra \
    mesa \
    upower \
    xserver-xorg \
    gtk+3 \
    gdk-pixbuf \
    cairo \
    pango \
    gsettings-desktop-schemas \
    json-glib \
    gnome-desktop \
    gnome-settings-daemon \
    libxtst \
    libxkbfile \
    xinerama \
    graphene \
    graphene-native \
    wayland-protocols \
    wayland-native \
    zenity \
    gobject-introspection \
    gobject-introspection-native \
"

RDEPENDS_${PN} += " xserver-xorg-xwayland zenity"

SRCREV = "7cab502754edef977215bdfc9f8dfa0849a426f3"

EXTERNAL_TREE_VAR="CITADEL_MUTTER_PATH"
GIT_URI = "git://github.com/brl/mutter.git;branch=citadel;protocol=https"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gobject-introspection gettext upstream-version-is-even features_check external-tree

# x11 is still mandatory - see meson.build
REQUIRED_DISTRO_FEATURES = "x11 systemd"

# systemd can be replaced by libelogind (not available atow - make systemd
# mandatory distro feature)
LOGIND ?= "systemd"


EXTRA_OEMESON += " \
    -Dremote_desktop=false \
    -Dtests=false \
    -Dprofiler=false \
    -Dcogl_tests=false \
    -Dlibwacom=true \
    -Dxwayland_initfd=disabled \
    -Dxwayland_path=/usr/bin/Xwayland \
    -Dgles2_libname=libGLESv2.so.2 \
"

MUTTER_API_NAME = "mutter-9"

do_install_append() {
    # Add gir links in standard paths. That makes dependents life much easier
    # to find them
    install -d ${D}${datadir}/gir-1.0
    for gir_full in `find ${D}${libdir}/${MUTTER_API_NAME} -name '*.gir'`; do
        gir=`basename "$gir_full"`
        ln -sr "${D}${libdir}/${MUTTER_API_NAME}/$gir" "${D}${datadir}/gir-1.0/$gir"
    done
}

PACKAGES =+ "${PN}-tests"

FILES_${PN} += " \
    ${datadir}/gnome-control-center \
    ${libdir}/${MUTTER_API_NAME}/lib*${SOLIBS} \
    ${libdir}/${MUTTER_API_NAME}/*.typelib \
    ${libdir}/${MUTTER_API_NAME}/plugins \
"

FILES_${PN}-tests += " \
    ${datadir}/installed-tests \
    ${datadir}/${MUTTER_API_NAME}/tests \
    ${libexecdir}/installed-tests/${MUTTER_API_NAME} \
"

FILES_${PN}-dev += " \
    ${libdir}/${MUTTER_API_NAME}/*.gir \
    ${libdir}/${MUTTER_API_NAME}/lib*.so \
"


