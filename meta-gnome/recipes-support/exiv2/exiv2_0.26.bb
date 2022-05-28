SUMMARY = "Exif, Iptc and XMP metadata manipulation library and tools"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=625f055f41728f84a8d7938acc35bdc2"

DEPENDS = "zlib expat"

SRC_URI = "http://www.exiv2.org/releases/${BPN}-${PV}-trunk.tar.gz"
SRC_URI[md5sum] = "5399e3b570d7f9205f0e76d47582da4c"
SRC_URI[sha256sum] = "c75e3c4a0811bf700d92c82319373b7a825a2331c12b8b37d41eb58e4f18eafb"

inherit cmake gettext

S = "${WORKDIR}/${BPN}-trunk"

INSANE_SKIP:${PN} = "useless-rpaths"

do_install:preend() {
    install -d ${D}${mandir}
}
