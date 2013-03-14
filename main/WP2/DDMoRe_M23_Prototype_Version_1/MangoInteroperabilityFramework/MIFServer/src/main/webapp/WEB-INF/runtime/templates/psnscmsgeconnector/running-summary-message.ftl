#!${SHELL}

export PATH="${PSN_SGECONNECTOR_UTILS}:${NONMEMSGE_CONNECTOR_UTILS}:${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "psnscmsgeconnector/running-summary-message.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "psnscmsgeconnector/running-summary-message.ftl" "START SCRIPT"
source nonmem_functions.fn.sh
source "psn_functions.fn.sh"

SCMParser.py -r "${job.getGridJobDirectory()}" -o "${job.getPsNOutputDir()}" -l "${job.getPsNSCMLogFile()}"
logMetrics "${job.getJobId()}" "psnscmsgeconnector/running-summary-message.ftl" "END SCRIPT"
