FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

RDEPENDS:${PN}:remove = "systemd-serialgetty"
RDEPENDS:${PN}:remove = "volatile-binds"
RDEPENDS:${PN}:remove = "update-rc.d"

ALTERNATIVE:${PN}:remove = "resolv-conf"

GROUPADD_PARAM:${PN} += "-r wheel; -r kvm; -r render"
PACKAGECONFIG = "\
    efi acl ldconfig pam usrmerge rfkill backlight binfmt hostnamed localed logind machined myhostname \
    nss polkit randomseed seccomp timedated utmp timesyncd kmod sysusers gshadow cryptsetup \
"

do_install:append() {
    rm -f ${D}${sysconfdir}/tmpfiles.d/00-create-volatile.conf
    ln -s rescue.target ${D}${systemd_unitdir}/system/kbrequest.target
}
