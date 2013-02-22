#!${SHELL}

<#--
  Prepare Handler Freemarker Template

  SHELL                  preferred execution shell
  SUBMIT_HOST_PREAMBLE   any series of variable assignments and commands that you want executed before the real work starts
  REQUEST_ATTRIBUT_BLOCK various stuff from the execution request attributes coming from the execution profile in Navigator
  MANGO_UTILS            the directory containing the mango utility shell scripts
  CONNECTOR_UTILS        the directory containing the publishHandler.sh script, which is connector specific
  sgeSharedLocation      the grid shared directory we are copying to
  job                    the current job
-->

export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "psnsgeconnector/validatePsNOutputs.ftl" ${job.getJobId()}

logMetrics "${job.getJobId()}" "psnsgeconnector/validatePsNOutputs.ftl" "START SCRIPT"

mifGridOutputFileName='${job.getRequestOutputDirectoryStdOutStreamFile()}'
mifGridErrorFileName='${job.getRequestOutputDirectoryStdErrStreamFile()}'

mifFatalErrorsCount=$(countNumberOfMessages $mifGridErrorFileName "Fatal Error:")

if (( $mifFatalErrorsCount >= 1 )); then
	mifGridErrorFileContent=$(getFileContent $mifGridErrorFileName)
    logMetrics "${job.getJobId()}" "psnsgeconnector/validatePsNOutputs.ftl" "END SCRIPT"
    logErrorAndExit 128 "Fatal Error found in PsN output\n $mifGridErrorFileContent"
fi

logMetrics "${job.getJobId()}" "psnsgeconnector/validatePsNOutputs.ftl" "END SCRIPT"

exit 0
