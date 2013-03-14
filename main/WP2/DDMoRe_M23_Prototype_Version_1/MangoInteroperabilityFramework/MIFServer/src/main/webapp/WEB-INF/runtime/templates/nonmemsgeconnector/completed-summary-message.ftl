#!${SHELL}

# Completed Summary Message Template: This gathers and assembles text prettily for completed PSN runs.

<#--
  SHELL                     the preferred execution shell (full path)
  SUBMIT_HOST_PREAMBLE      any series of variable assignments and commands that you want executed before the real work starts
  REQUEST_ATTRIBUTE_BLOCK   values from the request attributes which have come from the navigator execution profile
  MANGO_UTILS               the directory containing the mango utility shell scripts
  NONMEMSGE_CONNECTOR_UTILS the directory containing utilities specific to NONMEM/PsN
  job                       the current job
-->

export PATH="${NONMEMSGE_CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source mango_initialise.sh "nonmemsgeconnector/completed-summary-message.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "nonmemsgeconnector/completed-summary-message.ftl" "START SCRIPT"

source nonmem_functions.fn.sh
set +o pipefail

CONTROL_FILEPATH="${job.getGridJobDirectory()}/${job.getExecutionFile()}"
LST_FILEPATH="${job.getGridJobCurrentWorkingDirectory()}/modelfit_dir1/NM_run1/psn.lst"
if [[ ! -f $LST_FILEPATH ]]; then
    NEW_LST_FILEPATH="${job.getGridJobCurrentWorkingDirectory()}/${job.getRunLstFileName()}"
    if [[ ! -f "$NEW_LST_FILEPATH" ]]; then
       logErrorAndExit 100 "nonmemsgeconnector/completed-summary-message.ftl cannot find LST file $LST_FILEPATH or $NEW_LST_FILEPATH"
    fi
    LST_FILEPATH="$NEW_LST_FILEPATH"
fi

OBJECTIVE_FUNCTION_VALUE=0
TERM_STATUS_TEXT=""
ESTIMATION_METHOD=""
PROBLEM=""
DATAFILE=""
STATUS=""
#
# Simulation 
#
IS_SIM=$(isSIM "$CONTROL_FILEPATH" "$LST_FILEPATH")
    
if (( $IS_SIM )); then
    STATUS="Simulation Completed"
else
#
# Estimation
#
    OBJECTIVE_FUNCTION_VALUE=$(getObjectiveFunctionValue "$LST_FILEPATH")
    TERM_STATUS_TEXT=$(getStatus "$LST_FILEPATH")
    
    if [[ -f "$CONTROL_FILEPATH" ]]; then
        ESTIMATION_METHOD=$(getEstimationMethod "$CONTROL_FILEPATH" "-1")
        PROBLEM=$(getProblem "$CONTROL_FILEPATH")
        DATAFILE=$(getDataFile "$CONTROL_FILEPATH")
    else
        logErrorAndExit 101 "nonmemsgeconnector/completed-summary-message.ftl cannot find CTL file $CONTROL_FILEPATH"
    fi
    
    COVARIANCE=$(getCovariance "$CONTROL_FILEPATH" "$LST_FILEPATH")
    STATUS="$ESTIMATION_METHOD:$OBJECTIVE_FUNCTION_VALUE;$TERM_STATUS_TEXT;$COVARIANCE"
fi

DURATION=0
SUBMITTED="${job.getSGEStartTime()}"
if [[ -n "$SUBMITTED" && "$SUBMITTED" != "-1" ]]; then
    now="$(date +%s)"000
    (( DURATION = now - SUBMITTED ))
fi

LAST_UPDATED=$(getLastUpdatedFileTimeInSeconds "${job.getGridJobCurrentWorkingDirectory()}")

echo "username: ${job.getRequestUserName()}"
echo "gridjobid: ${job.getSGEJobID()}"
echo "controlfilename: ${job.getExecutionFile()}"
echo "requestdirectory: ${job.getRequestInputDirectory()}" # This will not be parsed
echo "gridJobDirectory: ${job.getGridJobDirectory()}" # This will not be parsed
echo "workingDirectory: ${job.getGridJobCurrentWorkingDirectory()}"
echo "problem: $PROBLEM"
echo "datafilename: $DATAFILE"
echo "duration: $DURATION"
echo "status: $STATUS"
echo "starttime: $SUBMITTED"
echo "lastupdated: $LAST_UPDATED"000

logMetrics "${job.getJobId()}" "nonmemsgeconnector/completed-summary-message.ftl" "END SCRIPT"
