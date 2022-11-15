SUMMARY = "Window navigation construction toolkit"
LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gobject-introspection gettext features_check upstream-version-is-even

DEPENDS = " \
    colord \
    geocode-glib \
    gcr \
    gnome-desktop \
    libgweather4 \
    lcms \
    libcanberra \
    geoclue \
    libnotify \
    upower \
    libwacom \
    pulseaudio \
    virtual/libx11 \
"

# all these are mandatory
REQUIRED_DISTRO_FEATURES = "x11 polkit pulseaudio systemd gobject-introspection-data"
GIR_MESON_OPTION = ""

SRC_URI[archive.sha256sum] = "3513bb24fc6f8181667223a64a067534fdccf3bf66326a9403d38b0f0d6013d0"
SRC_URI += "file://0001-disable-power-tests-and-sharing.patch"

PACKAGECONFIG ??= " \
    nm \
    alsa gudev \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
"
PACKAGECONFIG[alsa] = "-Dalsa=true,-Dalsa=false,alsa-lib"
PACKAGECONFIG[cups] = "-Dcups=true,-Dcups=false,cups"
PACKAGECONFIG[gudev] = "-Dgudev=true,-Dgudev=false,libgudev"
PACKAGECONFIG[nm] = "-Dnetwork_manager=true,-Dnetwork_manager=false,networkmanager"
PACKAGECONFIG[smartcard] = "-Dsmartcard=true,-Dsmartcard=false,nss"
PACKAGECONFIG[wayland] = "-Dwayland=true,-Dwayland=false,wayland"

FILES:${PN} += " \
    ${systemd_user_unitdir} \
    ${libdir}/gnome-settings-daemon-43/libgsd.so \
"

EXTRA_OEMESON += "\
    -Dwwan=false \
    -Dusb-protection=false \
    -Dsystemd=true \
"
