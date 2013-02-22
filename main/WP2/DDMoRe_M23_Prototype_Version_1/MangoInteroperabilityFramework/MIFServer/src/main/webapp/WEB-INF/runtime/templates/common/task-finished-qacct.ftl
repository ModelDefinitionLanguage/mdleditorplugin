#!${SHELL}

export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "common/task-finished-qacct.ftl" ${job.getJobId()}

logMetrics "${job.getJobId()}" "common/task-finished-qacct.ftl" "START SCRIPT"

#
# Echoing qacct file to make sure that qacct output is not empty, See bug https://bugs.mango-solutions.com/mantis/view.php?id=9738
#
FILE="${job.getRequestOutputDirectoryMifHiddenDir()}/${sgeJobFinalStatusFileName}"
cat $FILE

logMetrics "${job.getJobId()}" "common/task-finished-qacct.ftl" "END SCRIPT"

# If the file is zero length, something has gone wrong.  Indicate this with a non zero exit status.
if [[ ! -s $FILE ]]; then
   exit 5
fi

exit 0
