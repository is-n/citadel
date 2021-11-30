SUMMARY = "GNOME disk utility"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SECTION = "x11/gnome"

DEPENDS = " \
    gtk+3 \
    libdvdread \
    libcanberra \
    libnotify \
    libsecret \
    libpwquality \
    libhandy \
    udisks2 \
    libxml2-native \
"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gtk-icon-cache gettext features_check upstream-version-is-even mime-xdg

REQUIRED_DISTRO_FEATURES = "x11"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"

# As soon as elogind is of interest this needs rework: meson option is combo
PACKAGECONFIG[systemd] = "-Dlogind=libsystemd,-Dlogind=none,systemd"

EXTRA_OEMESON += "-Dman=false"

def gnome_verdir(v):
    return oe.utils.trim_version(v, 1)

SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive \
           file://0001-Don-t-display-ignored-disks.patch \
           "
SRC_URI[archive.sha256sum] = "8743c98fd656062ef862933efe30c5be4c6b322ec02eee154ec70d08ed0895df"

FILES_${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/dbus-1 \
"

