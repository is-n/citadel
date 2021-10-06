require gtk4.inc

MAJ_VER = "${@oe.utils.trim_version("${PV}", 2)}"

SRC_URI = "http://ftp.gnome.org/pub/gnome/sources/gtk/${MAJ_VER}/gtk-${PV}.tar.xz \
          "

SRC_URI[sha256sum] = "e0a1508f441686c3a20dfec48af533b19a4b2e017c18eaee31dccdb7d292505b"

S = "${WORKDIR}/gtk-${PV}"

LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2 \
                    file://gtk/gtk.h;endline=25;md5=1d8dc0fccdbfa26287a271dce88af737 \
                    file://gdk/gdk.h;endline=25;md5=c920ce39dc88c6f06d3e7c50e08086f2 \
                    file://tests/testgtk.c;endline=25;md5=49d06770681b8322466b52ed19d29fb2"
