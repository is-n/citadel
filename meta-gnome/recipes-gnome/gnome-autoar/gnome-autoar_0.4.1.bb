SUMMARY = "GNOME archive library"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = " \
    gtk+3 \
    libarchive \
"

GNOMEBASEBUILDCLASS = "meson"
GIR_MESON_ENABLE_FLAG = "enabled"
GTKDOC_MESON_OPTION = "gtk_doc"
inherit gnomebase gobject-introspection gtk-doc vala

SRC_URI[archive.sha256sum] = "646bd50ebad92d91c1be89097a15364156157442cac1471ded7ecb27d9a9150e"

do_compile:prepend() {
    export GIR_EXTRA_LIBS_PATH="${B}/gnome-autoar/.libs"
}
