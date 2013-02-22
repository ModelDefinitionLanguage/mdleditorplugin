#!${SHELL}

export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
QSUB_PARAMETERS=""
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "common/qsub.ftl" ${job.getJobId()}

logMetrics "${job.getJobId()}" "common/qsub.ftl" "START SCRIPT"

${MANGO_SGE_QSUB} $QSUB_PARAMETERS  -N "MIF-${job.jobId}" -u ${job.userName} -e "${job.getGridJobDirectoryMifHiddenDir()}/${sgeJobStdErrFileName}" -o "${job.getGridJobDirectoryMifHiddenDir()}/${sgeJobStdOutFileName}" -wd "${job.gridJobCurrentWorkingDirectory}" "${job.getGridJobDirectoryMifHiddenDir()}/MIF-submitter.sh"

logMetrics "${job.getJobId()}" "common/qsub.ftl" "END SCRIPT"
