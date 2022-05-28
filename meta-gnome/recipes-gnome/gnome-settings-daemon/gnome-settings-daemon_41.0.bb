
SUMMARY = "A set of daemons that manage and provide various parameters to applications"
HOMEPAGE = "https://wiki.gnome.org/Initiatives/Wayland/gnome-settings-daemon"
LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
                    file://COPYING.LIB;md5=fbc093901857fcd118f065f900982c24"

DEPENDS = "pango gnome-desktop xorgproto libnotify fontconfig libgudev libxext wayland glib-2.0 libxi libx11 libwacom libxtst gsettings-desktop-schemas intltool-native gtk+3 polkit upower lcms glib-2.0-native wayland colord geoclue libcanberra geocode-glib libgweather pulseaudio networkmanager"

FILES:${PN} += "\
    ${systemd_user_unitdir} \
    ${libdir}/gnome-settings-daemon-41 \
"

FILES:${PN}-staticdev += "${libdir}/gnome-settings-daemon-3.0/libgsd.a"


GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gobject-introspection gettext meson-exe-wrapper

GIR_MESON_OPTION = ""

SRC_URI[archive.sha256sum] = "e6ca6361fbd1deab2de1a1e390d4f14167cf47b1c547dbb8b65a5d89e9663884"
SRC_URI += "file://0001-disable-power-tests-and-sharing.patch"

EXTRA_OEMESON += "\
    --buildtype=release \
    -Dcups=false \
    -Dsmartcard=false \
    -Dwwan=false \
    -Dusb-protection=false \
    -Dsystemd=true \
"

