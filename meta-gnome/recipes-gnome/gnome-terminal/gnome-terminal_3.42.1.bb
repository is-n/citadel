SUMMARY = "GNOME terminal emulator"
HOMEPAGE = "https://wiki.gnome.org/Apps/Terminal"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

DEPENDS = " \
    glib-2.0-native \
    intltool-native \
    yelp-tools-native \
    desktop-file-utils-native \
    gtk+3 \
    gsettings-desktop-schemas \
    vte \
    dconf \
    libpcre2 \
    docbook-xsl-stylesheets-native \
"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gsettings gnome-help gettext itstool upstream-version-is-even

SRC_URI[archive.sha256sum] = "c319b1405501b8c7693e616f48eced41695d2e786148ca5f9e27bc7d98f4aeb1"

EXTRA_OEMESON = "-Ddocs=false -Dnautilus_extension=false -Dsearch_provider=false"

FILES_${PN} += "\
    ${systemd_user_unitdir} \
    ${datadir}/dbus-1/services \
    ${datadir}/glib-2.0/schemas \
    ${datadir}/metainfo \
"

FILES_${PN}-doc += "\
    ${datadir}/help \
"
