#!${SHELL}

export PATH="${PSN_SGECONNECTOR_UTILS}:${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "nonmemsgeconnector/qdel.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "nonmemsgeconnector/qdel.ftl" "START SCRIPT"

source "psn_functions.fn.sh"

${MANGO_SGE_QDEL} ${job.getSGEJobID()}

mifChildJobsIds=$(getListOfPsNSGEChildJobsIds "${job.gridJobCurrentWorkingDirectory}")

if [[ ! -z $mifChildJobsIds ]]; then
	${MANGO_SGE_QDEL} $mifChildJobsIds
fi

logMetrics "${job.getJobId()}" "nonmemsgeconnector/qdel.ftl" "END SCRIPT"
