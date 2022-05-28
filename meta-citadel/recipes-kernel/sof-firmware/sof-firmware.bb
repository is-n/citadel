DESCRIPTION = "SOF firmware files for use with Linux kernel"
HOMEPAGE = "https://github.com/thesofproject"
SECTION = "kernel"

SRCREV = "082fb0673f8488717c431801e4bc061d0dc2051c"
SRC_URI = "git://github.com/thesofproject/sof-bin.git;branch=main;protocol=https;destsuffix=sof-firmware"

S = "${WORKDIR}"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://sof-firmware/LICENCE.Intel;md5=54b4f1a2dd35fd85bc7a1d4afa731b78"

PV = "1.9"

FILES:${PN} += "/usr/lib/firmware/intel/*"

inherit allarch update-alternatives

do_install() {
        install -v -d  ${D}/usr/lib/firmware/intel/sof
        install -v -d  ${D}/usr/lib/firmware/intel/sof-tplg
        cp -R ${S}/sof-firmware/v1.9.x/sof-v1.9/* ${D}/usr/lib/firmware/intel/sof
        cp -R ${S}/sof-firmware/v1.9.x/sof-tplg-v1.9/* ${D}/usr/lib/firmware/intel/sof-tplg
}
