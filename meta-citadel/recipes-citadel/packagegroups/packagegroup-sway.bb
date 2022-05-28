
PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup


RDEPENDS:${PN} = "\
    wlroots \
    sway \
    xcb-util-xrm \
    swaybg \
    swaylock \
    grim \
    slurp \
    wdisplays \
    polkit-gnome \
    gtk-layer-shell \
    sgmenu \
"

