SUMMARY = "GUsb is a GObject wrapper for libusb1"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

DEPENDS = "glib-2.0 libusb1"

inherit meson gobject-introspection gtk-doc gettext vala

SRC_URI = "git://github.com/hughsie/libgusb.git;branch=master;protocol=https"
SRCREV = "ff9c606d9d72c7dd369ad220d92729556faaaf0c"
S = "${WORKDIR}/git"
