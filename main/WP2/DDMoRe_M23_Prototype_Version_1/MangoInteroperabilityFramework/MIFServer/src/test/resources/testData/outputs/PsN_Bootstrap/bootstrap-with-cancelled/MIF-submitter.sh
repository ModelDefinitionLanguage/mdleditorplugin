#!/bin/bash

# REQUEST_ATTRIBUTE_BLOCK
QSUB_PARAMETERS="-q dev.q@navtst-nonmem.mango.local"; export QSUB_PARAMETERS

# GRID_HOST_PREAMBLE
source /usr/global/grid/bin/sge.sh
SGE_INITIALISATION_SCRIPT_PATH=/runtime/scripts/common/sge.sh; export SGE_INITIALISATION_SCRIPT_PATH


if [[ ! -f "scm_test.mod" ]]; then
    echo "$0: \"scm_test.mod\": No such file" >&2
    exit 1
fi

if [[ -n "bootstrap" && "bootstrap" = /* && ! -x "bootstrap" ]]; then
    echo "$0: executable \"bootstrap\": No such file" >&2
    exit 2
fi

bootstrap -run_on_sge -samples=10 -threads=5 -directory=bs -crash_restarts=0 "scm_test.mod"


