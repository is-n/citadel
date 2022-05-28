SUMMARY = "GNOME javascript bindings based on the Spidermonkey javascript engine"
HOMEPAGE = "https://wiki.gnome.org/Projects/Gjs"

LICENSE = "MIT & LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=8dcea832f6acf45d856abfeb2d51ec48"

inherit gnomebase gettext gobject-introspection meson 

GIR_MESON_OPTION = ""

DEPENDS = "glib-2.0 gobject-introspection gobject-introspection-native cairo gtk+3 mozjs-91 glib-2.0-native"

EXTRA_OEMESON = " -Dskip_dbus_tests=true -Dskip_gtk_tests=true -Dinstalled_tests=false"
CFLAGS:append = " -include ${STAGING_INCDIR}/mozjs-91/js/RequiredDefines.h"

SRC_URI += " file://0001-Fix-tests-that-cannot-run.patch"
SRC_URI[archive.sha256sum] = "4b0629341a318a02374e113ab97f9a9f3325423269fc1e0b043a5ffb01861c5f"

RDEPENDS:${PN} += "libmozjs-91"

FILES:${PN}-dbg += "${datadir}/gjs-1.0/lsan ${datadir}/gjs-1.0/valgrind"
