#!/bin/bash

# Note that obviously by the time we are invoked, mango_initialise.sh and mango_copier.sh
# are expected to be on the PATH

source mango_initialise.sh "$0" "$JOBID" "$@"

logMetrics "$JOBID" "$0" "START SCRIPT"
mango_copier.sh "$@"
logMetrics "$JOBID" "$0" "END SCRIPT"
