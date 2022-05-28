# Put python library into a separate package and avoid dragging in python as RDEPENDS
PACKAGES =+ "${PN}-python"
FILES:${PN}-python = "${libdir}/python${PYTHON_BASEVERSION}"
RDEPENDS:${PN}:remove = "${PYTHON_PN}-core"
