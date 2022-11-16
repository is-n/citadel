SUMMARY = "Makes power profiles handling available over D-Bus."
DESCRIPTION = "power-profiles-daemon offers to modify system behaviour based upon user-selected power profiles."
HOMEPAGE = "https://gitlab.freedesktop.org/hadess/power-profiles-daemon"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://gitlab.freedesktop.org/hadess/power-profiles-daemon/-/archive/${PV}/${BPN}-${PV}.tar.gz"
SRC_URI[sha256sum] = "4c01dc51cd734403af868cff2d13923c83076c477d7f02c601f3a5127dd82a18"

DEPENDS = "polkit upower"
inherit meson systemd gtk-doc features_check
REQUIRED_DISTRO_FEATURES = "polkit systemd"
GTKDOC_MESON_OPTION = "gtk_doc"

SYSTEMD_SERVICE:${PN} = "power-profiles-daemon.service"

FILES:${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/polkit-1 \
"

do_install:append () {
    # remove unneeded python utility
    rm ${D}${bindir}/powerprofilesctl
    rm -rf ${D}${bindir}
}
