#!${SHELL}

export PATH="${PSN_SGECONNECTOR_UTILS}:${NONMEMSGE_CONNECTOR_UTILS}:${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "psnvpcsgeconnector/completed-summary-message.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "psnvpcsgeconnector/completed-summary-message.ftl" "START SCRIPT"
source nonmem_functions.fn.sh
source "psn_functions.fn.sh"

LST_FILEPATH="${job.getPsNOutputDir()}/NM_run1/psn.lst"
OBJECTIVE_FUNCTION_VALUE=0
if [[ -f "$LST_FILEPATH" ]]; then
    OBJECTIVE_FUNCTION_VALUE=$(getObjectiveFunctionValue "$LST_FILEPATH")
fi

CONTROL_FILEPATH="${job.getGridJobDirectory()}/${job.getExecutionFile()}"
ESTIMATION_METHOD=
if [[ -f "$CONTROL_FILEPATH" ]]; then
    ESTIMATION_METHOD=$(getEstimationMethod "$CONTROL_FILEPATH")
fi

echo "$ESTIMATION_METHOD: $OBJECTIVE_FUNCTION_VALUE"

logMetrics "${job.getJobId()}" "psnvpcsgeconnector/completed-summary-message.ftl" "END SCRIPT"
