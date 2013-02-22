#!${SHELL}

<#--
  Prepare Handler Freemarker Template

  SHELL                   preferred execution shell
  SUBMIT_HOST_PREAMBLE    any series of variable assignments and commands that you want executed before the real work starts
  REQUEST_ATTRIBUTE_BLOCK variables from the execution request attributes all the way from Navigator's execution profile
  MANGO_UTILS             the directory containing the mango utility shell scripts
  CONNECTOR_UTILS         the directory containing the publishHandler.sh script, which is connector specific
  sgeSharedLocation       the grid shared directory we are copying to
  job                     the current job
-->

export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "generic/prepareHandler.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "generic/prepareHandler.ftl" "START SCRIPT"

<#if ! job.runInRequestDirectory >
prepareHandler.sh \
    -d "${job.getGridJobDirectory()}"\
    -R "${job.getRequestInputDirectory()}" "${job.getGridJobDirectory()}"
</#if>

hiddenDir="${job.getGridJobDirectoryMifHiddenDir()}"

if [[ ! -d $hiddenDir ]]; then
    log "$0: mkdir -p $hiddenDir"
    if ! mkdir -p "$hiddenDir"; then
        logErrorAndExit 31 "$0: FATAL ERROR: Failed to create hidden directory $hiddenDir"
    fi
fi

logMetrics "${job.getJobId()}" "generic/prepareHandler.ftl" "END SCRIPT"

exit 0
