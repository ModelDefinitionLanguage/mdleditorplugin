#!${SHELL}

export PATH="${PSN_SGECONNECTOR_UTILS}:${NONMEMSGE_CONNECTOR_UTILS}:${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "generic/retrieveChildJobsDetails.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "generic/retrieveChildJobsDetails.ftl" "START SCRIPT"

source nonmem_functions.fn.sh
source "psn_functions.fn.sh"

getNonmemJobsDescriptors "${job.getPsNOutputDir()}"
logMetrics "${job.getJobId()}" "psnscmsgeconnector/completed-summary-message.ftl" "END SCRIPT"
