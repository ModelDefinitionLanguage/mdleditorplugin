#!${SHELL}

export PATH="${PSN_SGECONNECTOR_UTILS}:${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "psnbootstrapsgeconnector/running-summary-message.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "psnbootstrapsgeconnector/running-summary-message.ftl" "START SCRIPT"
source "psn_functions.fn.sh"

TFILE=$(generateTmpFileName)

echo "${bsinfo}" > $TFILE 

BootstrapParser.py -r "${job.getPsNOutputDir()}" -i $TFILE

logMetrics "${job.getJobId()}" "psnbootstrapsgeconnector/running-summary-message.ftl" "END SCRIPT"
