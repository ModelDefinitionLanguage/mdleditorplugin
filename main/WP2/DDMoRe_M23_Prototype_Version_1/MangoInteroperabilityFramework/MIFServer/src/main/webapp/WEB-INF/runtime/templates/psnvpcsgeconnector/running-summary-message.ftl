#!${SHELL}

export PATH="${PSN_SGECONNECTOR_UTILS}:${NONMEMSGE_CONNECTOR_UTILS}:${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "psnvpcsgeconnector/running-summary-message.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "psnvpcsgeconnector/running-summary-message.ftl" "START SCRIPT"

VPCParser.py -r "${job.getPsNOutputDir()}"
logMetrics "${job.getJobId()}" "psnvpcsgeconnector/running-summary-message.ftl" "END SCRIPT"
