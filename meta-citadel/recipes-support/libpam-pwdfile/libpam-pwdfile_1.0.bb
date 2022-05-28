SUMMARY = "PAM pwdfile library"
SECTION = "libs"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://pam_pwdfile.c;beginline=1;endline=39;md5=02765d16df913d327ffd4a2ef499baf4"

SRC_URI = "git://github.com/tiwe-de/libpam-pwdfile.git;protocol=https;branch=master"
SRCREV = "8f0e412b48178c00abd023917dd2c9050ee89c18"

S = "${WORKDIR}/git"

DEPENDS = "libpam libxcrypt"
inherit lib_package pkgconfig

FILES:${PN} += "${libdir}/security/pam_pwdfile.so"

do_compile_class() {
    oe_runmake CC_FOR_BUILD="${BUILD_CC}" PAM_LIB_DIR=${libdir}/security
}

do_install() {
    oe_runmake install DESTDIR=${D} PAM_LIB_DIR=${libdir}/security
}
