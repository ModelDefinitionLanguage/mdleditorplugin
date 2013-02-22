#!${SHELL}

# Running Summary Message Template: This gathers data and assembles data for running PsN jobs.
#
# On running stage the #OBFV is not available in the lst is not available, here the objective function from current iteration
# should be used.
#
# the script should use OUTPUT instead of lst (lst is updated after NONMEM completes)
#
# the script needs to handle both PsN execute and NMFE, for PsN execute has different directory structure, see
# /MIFServer/src/test/resources/testData/outputs/NONMEM/warfar-completed-psn-parallel
#

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

source mango_initialise.sh "nonmemsgeconnector/running-summary-message.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "nonmemsgeconnector/running-summary-message.ftl" "START SCRIPT"

source nonmem_functions.fn.sh
set +o pipefail
set +e

<#if "NMFE" = job.executionRequest.getType()>
ISPsN=0
JOB_CWD="${job.getGridJobCurrentWorkingDirectory()}"
OUTPUT_FILEPATH="$JOB_CWD/OUTPUT"
CTL_FILE_NAME="${job.getExecutionFileName()}"
RUN_NAME=$(echo "$CTL_FILE_NAME" | cut -f1 -d '.')
EXT_FILEPATH="$JOB_CWD/$RUN_NAME.ext"
</#if>

<#if "PsN_EXEC" = job.executionRequest.getType()>
ISPsN=1
JOB_CWD="${job.getPsNOutputDir()}"
OUTPUT_FILEPATH="$JOB_CWD/NM_run1/OUTPUT"
EXT_FILEPATH="$JOB_CWD/NM_run1/psn.ext"
</#if>

PsN_Extra=""
RETRIES=
if (( ISPsN )); then
    for param in ${job.executionRequest.getExecutionParameters()!}; do
        case $param in
           -retries=*) RETRIES=$(echo $param | sed -r -e "s/-retries=([0-9]+)[^0-9]*/\1/") ;;
        esac
    done
fi

# The next block of lines are dedicated to attempting to find the path of the output
# file, which may be called OUTPUT or may be a *.lst
#
# This, as far as I can tell, is the default:
CONTROL_FILEPATH="${job.getGridJobExecutionFile()}"

# If in "retries" mode (i.e. retries is > 0), then work out how far through we are.
#
if [[ -n "$RETRIES" ]] && (( RETRIES > 0 )); then
    COUNT=0
    if [[ -d "$JOB_CWD/NM_run1" ]]; then
        COUNT=$(find "$JOB_CWD/NM_run1" -name "psn-*.lst" | wc -l)
        if [[ -n "$COUNT" ]] && (( COUNT > 0 )); then
           (( COUNT = COUNT - 1 ))
        fi
    fi
    PsN_Extra=" ($COUNT/$RETRIES)"
fi

OBJECTIVE_FUNCTION_VALUE="No OBJV"

ESTIMATION_METHOD=""
PROBLEM=""
DATAFILE=""

#
# Simulation 
#
NSUB=$(getNumberOfSIMSubProblems "$CONTROL_FILEPATH")

if (( $NSUB > 0 )); then
    nsub=$(getCurrentSubProblemNumber "$OUTPUT_FILEPATH") 
    STATUS="Simulating ($nsub of $NSUB)"
else 
    # Estimation
    #
    if [[ -f "$CONTROL_FILEPATH" ]] && [[ -f "$OUTPUT_FILEPATH" ]] && [[ -f "$EXT_FILEPATH" ]]; then
        estIndex=$(getCurrentEstimationMethodIndexFromExt "$EXT_FILEPATH")
        
        OBJECTIVE_FUNCTION_VALUE=$(getObjectiveFunctionValueForRunningNONMEM "$CONTROL_FILEPATH" "$EXT_FILEPATH" "$OUTPUT_FILEPATH")
        ESTIMATION_METHOD=$(getEstimationMethod "$CONTROL_FILEPATH" "$estIndex")
        PROBLEM=$(getProblem "$CONTROL_FILEPATH")
        DATAFILE=$(getDataFile "$CONTROL_FILEPATH")
        STATUS="$ESTIMATION_METHOD:$OBJECTIVE_FUNCTION_VALUE$PsN_Extra"
    else
        log "DO NOT have required files for generating status"
        STATUS="STARTUP$PsN_Extra"
    fi
fi

DURATION=0
SUBMITTED="${job.getSGEStartTime()}"
if [[ -n "$SUBMITTED" && "$SUBMITTED" != "-1" ]]; then
    now="$(date +%s)"000
    (( DURATION = now - SUBMITTED ))
fi

LAST_UPDATED=$(getLastUpdatedFileTimeInSeconds ${job.getGridJobCurrentWorkingDirectory()})

echo "username: ${job.getRequestUserName()}"
echo "gridjobid: ${job.getSGEJobID()}"
echo "controlfilename: ${job.getExecutionFile()}"
echo "requestdirectory: ${job.getRequestInputDirectory()}"
echo "problem: $PROBLEM"
echo "datafilename: $DATAFILE"
echo "duration: $DURATION"
echo "status: $STATUS"
log "status: $STATUS"
echo "starttime: $SUBMITTED"
echo "lastupdated: $LAST_UPDATED"000

logMetrics "${job.getJobId()}" "nonmemsgeconnector/running-summary-message.ftl" "END SCRIPT"
