SUMMARY = "GNOME desktop-wide GSettings schemas"
HOMEPAGE = "http://live.gnome.org/gsettings-desktop-schemas"
BUGTRACKER = "https://bugzilla.gnome.org/"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "glib-2.0 intltool-native"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gsettings gettext gobject-introspection upstream-version-is-even

EXTRA_OEMESON = "-Dintrospection=true"

def gnome_verdir(v):
    return oe.utils.trim_version(v, 1)

SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive"

SRC_URI[archive.sha256sum] = "5d5568282ab38b95759d425401f7476e56f8cbf2629885587439f43bd0b84bbe"

