DESCRIPTION = "Tiny, lightning fast, feature-packed file manager"
SUMMARY = "Small command line file manager"
HOMEPAGE = "https://github.com/jarun/nnn"
SECTION = "base"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ee18574190d1b9d7ce45445955af2c9b"

DEPENDS = "ncurses readline"
inherit pkgconfig


SRCREV = "3f58f6111c95a38f2bfbdde92c42bf54edeb5927"
SRC_URI = "git://github.com/jarun/nnn.git;protocol=https;branch=master" 
S = "${WORKDIR}/git"

EXTRA_OEMAKE = "'PREFIX=/usr'"

do_install () {
    oe_runmake 'DESTDIR=${D}' install
}

