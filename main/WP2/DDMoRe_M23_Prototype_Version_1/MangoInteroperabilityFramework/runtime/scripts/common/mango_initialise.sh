
# This script is SOURCED by every important script we run.
# YOU MUST ENSURE there are NO syntax errors in this file
# or literally EVERYTHING will fail
#
# Fortunately there are no preambles inserted into this, so
# there is less chance of something set in navigator (via an
# application profile etc.) of upsetting it

set -e

# Enable debugging while installing and testing.  Don't forget to remove
# this when everything is working, otherwise the /var/log/mif/shell.log
# file will grow without limit.
#
export MANGO_LOGGING=true

# The templates set the job id as the second parameter (the
# name of the template being the first parameter).
# This allows us to relate logged messages back to the job.
#
export JOBID="$2"

source mango_functions.fn.sh

log "$1"
logMetrics "$JOBID" "$0" "SCRIPT START"

umask ${UMASK:-002}

# See if a separate SGE initialisation script has been set up.  If so,
# invoke that one in preference to the "default" one in "script/commons".
#
if [[ -f $SGE_INITIALISATION_SCRIPT_PATH && -x $SGE_INITIALISATION_SCRIPT_PATH ]]; then
    log "source special SGE script: $SGE_INITIALISATION_SCRIPT_PATH"
    source $SGE_INITIALISATION_SCRIPT_PATH
else
    log "source default sge.sh"
    source sge.sh
fi

logMetrics "$JOBID" "$0" "END SCRIPT"
