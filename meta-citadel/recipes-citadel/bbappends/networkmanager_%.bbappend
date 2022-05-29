# Append recipe from meta-openembedded/meta-networking
FILESEXTRAPATHS:prepend := "${THISDIR}/networkmanager:"

SRC_URI += "\
    file://NetworkManager.conf \
    file://watch-resolvconf.path \
    file://watch-resolvconf.service \
"

SYSTEMD_SERVICE:${PN} += "watch-resolvconf.path"

do_install:append() {
    install -m 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/
    install -m 644 ${WORKDIR}/watch-resolvconf.path ${D}${systemd_system_unitdir}
    install -m 644 ${WORKDIR}/watch-resolvconf.service ${D}${systemd_system_unitdir}
}
PACKAGECONFIG = "nss systemd polkit wifi iwd vala"
