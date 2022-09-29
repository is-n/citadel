HOMEPAGE = "https://snwh.org/paper"
LICENSE = "CC-BY-SA-4.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=8335471e369a410f354d5179fdcf0195 \
                    file://LICENSE;md5=4a312f184ff2ceafe9466cbb7ea43f4e"

SRC_URI = "git://github.com/snwh/paper-icon-theme.git;protocol=https;branch=master \
                file://index.theme \
                "
SRCREV = "aa3e8af7a1f0831a51fd7e638a4acb077a1e5188"

S = "${WORKDIR}/git"

inherit meson allarch 

FILES:${PN} = "${datadir}/icons"

# Add an index.theme to /usr/share/icons/default that inherits Paper cursors
do_install:append() {
    mkdir -p ${D}${datadir}/icons/default
    install -m 644 ${WORKDIR}/index.theme ${D}${datadir}/icons/default/index.theme
}

