
S = "${WORKDIR}${@source_path(d)}"
SRC_URI = "${@source_uri(d)}"


def source_path(d):
    var = d.getVar("EXTERNAL_TREE_VAR")
    tree_path = d.getVar(var)

    if tree_path:
        return tree_path
    else:
        return "/git"

def source_uri(d):
    var = d.getVar("EXTERNAL_TREE_VAR")
    tree_path = d.getVar(var)

    if tree_path:
        return "file://" + tree_path
    else:
        return d.getVar("GIT_URI")

# Set debug build if $EXTERNAL_TREE_VAR is set for faster builds
DEBUG_BUILD = "${@debug_build(d)}"

def debug_build(d):
    var = d.getVar("EXTERNAL_TREE_VAR")
    tree_path = d.getVar(var)
    if tree_path:
        return "1"
    else:
        return "0"
