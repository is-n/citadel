SUMMARY = "A library for bits of crypto UI and parsing etc"
DESCRIPTION = "GCR is a library for displaying certificates, and crypto UI, \
accessing key stores. It also provides the viewer for crypto files on the \
GNOME desktop."
HOMEPAGE = "https://gitlab.gnome.org/GNOME/gcr"
BUGTRACKER = "https://gitlab.gnome.org/GNOME/gcr/issues"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=55ca817ccb7d5b5b66355690e9abc605"

DEPENDS = "p11-kit glib-2.0 libgcrypt gnupg-native \
           ${@bb.utils.contains('GI_DATA_ENABLED', 'True', 'libxslt-native', '', d)}"

CACHED_CONFIGUREVARS += "ac_cv_path_GPG='gpg2'"

CFLAGS += "-D_GNU_SOURCE"

GNOMEBASEBUILDCLASS = "meson"
GTKDOC_MESON_OPTION = "gtk_doc"
inherit gnomebase gtk-icon-cache gtk-doc features_check upstream-version-is-even vala gobject-introspection gettext mime mime-xdg
UPSTREAM_CHECK_REGEX = "[^\d\.](?P<pver>\d+\.(?!9\d+)(\d*[02468])+(\.\d+)+)\.tar"

SRC_URI = "https://download.gnome.org/sources/gcr/3.92/gcr-3.92.0.tar.xz;name=archive"
SRC_URI += " file://0001-Don-t-require-ssh-binaries-for-build.patch"
SRC_URI[archive.sha256sum] = "896abf8e1db0f40eb28073f364f36a72385ac8abf8cd1362b1016e97721ff518"

S="${WORKDIR}/gcr-${PV}"

PACKAGECONFIG ??= " \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'gtk', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'gtk', '', d)} \
"
PACKAGECONFIG[gtk] = "-Dgtk4=true,-Dgtk4=false,gtk4"

FILES:${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/gcr-3 \
"

# http://errors.yoctoproject.org/Errors/Details/20229/
ARM_INSTRUCTION_SET:armv4 = "arm"
ARM_INSTRUCTION_SET:armv5 = "arm"
ARM_INSTRUCTION_SET:armv6 = "arm"

EXTRA_OEMESON += "--cross-file ${WORKDIR}/meson-${PN}.cross"
EXTRA_OEMESON += "-Dssh_agent=false"
do_write_config:append() {
    cat >${WORKDIR}/meson-${PN}.cross <<EOF
[binaries]
gpg2 = '${bindir}/gpg2'
EOF
}
