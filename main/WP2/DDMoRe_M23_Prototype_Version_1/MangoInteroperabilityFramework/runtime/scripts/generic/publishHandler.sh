#!/bin/bash

# Note that obviously by the time we are invoked, mango_initialise.sh and mango_copier.sh
# are expected to be on the PATH

source mango_initialise.sh "$0" "$JOBID" "$@"

logMetrics "$JOBID" "$0" "START SCRIPT"

logRaw "=========================================================================================
=========================================================================================
=========================================================================================
========================================================================================="

our_pattern="\..*"
if [[ -n "$DIR_IGNORE_PATTERN" ]]; then
    DIR_IGNORE_PATTERN="$our_pattern,$DIR_IGNORE_PATTERN"
else
    DIR_IGNORE_PATTERN="$our_pattern"
fi
export DIR_IGNORE_PATTERN

mango_copier.sh "$@"

logMetrics "$JOBID" "$0" "END SCRIPT"
