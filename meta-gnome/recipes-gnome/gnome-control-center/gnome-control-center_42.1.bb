SUMMARY = "GNOME Settings"
DESCRIPTION = "GNOME Settings is GNOME's main interface for configuration of various aspects of your desktop"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=75859989545e37968a99b631ef42722e"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gettext vala upstream-version-is-even bash-completion features_check

DEPENDS = " \
    gdk-pixbuf-native \
    colord-gtk \
    udisks2 \
    upower \
    polkit \
    pulseaudio \
    accountsservice \
    gsettings-desktop-schemas \
    gnome-settings-daemon \
    gnome-desktop \
    modemmanager \
    networkmanager \
    network-manager-applet \
    gnome-bluetooth \
    libnma \
    libgtop \
    libgudev \
    gsound \
    libpwquality \
    ibus \
    libadwaita \
"

REQUIRED_DISTRO_FEATURES += " pulseaudio systemd x11"

SRC_URI += "file://0001-Add-meson-option-to-pass-sysroot.patch"
SRC_URI += " file://0001-Add-options-for-Citadel-and-disabling-GOA-CUPS.patch"
SRC_URI[archive.sha256sum] = "fb30aff90fbe1d2ad56107d6e9f5f8a4a3aaf3635bbd88920d75b568bb776785"


#
# Extra options have been added to meson_options.txt to make some components of the control
# center optional.  One reason is that these components drag in heavy dependencies, and some
# of these dependencies have not been packaged and tested yet.
#
# Gnome Online Accounts support
#
#      EXTRA_OEMESON += "-Donline_accounts=true"
#      DEPENDS += "grilo gnome-online-accounts webkitgtk rest"
#
# Printer Panel
#
#      EXTRA_OEMESON += "-Dcups=true"
#      DEPENDS += "cups samba"  (only smbclient needed from samba)
#
# User Accounts Panel
#
#      EXTRA_OEMESON += "-Duser_accounts=true"
#      DEPENDS += "accountsservice krb5"
#
# Citadel
#
#      This option disables some things in gnome-control-center such as certain  
#      panels that are not used in Citadel, defaults to true in meson_options.   
#      To re-enable these things, set the option to false:
#
#      EXTRA_OEMESON += "-Dcitadel=false"
#

#SRC_URI += " file://0001-Add-options-for-Citadel-and-disabling-GOA-CUPS.patch"

EXTRA_OEMESON = " \
    -Doe_sysroot=${STAGING_DIR_HOST} \
"

do_install:append() {
	# If polkit is setup fixup permissions and ownership
    if [ -d ${D}${datadir}/polkit-1/rules.d ]; then
        chmod 700 ${D}${datadir}/polkit-1/rules.d
        chown polkitd:root ${D}${datadir}/polkit-1/rules.d
    fi
}
FILES:${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/gnome-shell \
    ${datadir}/metainfo \
"

FILES:${PN}-dev += "${datadir}/gettext"

RDEPENDS:${PN} += "gsettings-desktop-schemas"

