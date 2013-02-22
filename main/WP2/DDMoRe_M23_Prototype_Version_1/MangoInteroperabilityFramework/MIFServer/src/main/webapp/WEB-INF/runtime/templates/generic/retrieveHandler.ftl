#!${SHELL}

<#--
  Retrieve Handler Freemarker Template

  SHELL                      preferred execution shell
  SUBMIT_HOST_PREAMBLE       any series of variable assignments and commands that you want executed before the real work starts
  REQUEST_ATTRIBUTE_BLOCK    variables from the execution request attributes from the navigator execution profile
  MANGO_UTILS                directory containing the mango utility shell scripts
  CONNECTOR_UTILS            directory containing the publishHandler.sh script, which is connector specific
  mifOutputDirectory         MIF request output directory, i.e. where we are writing to
  mifHiddenDirectory         absolute path of the hidden .MIF directory
  mifModifiedListFileName    name of the file (not an absolute path) of the file which will contain a list of files copied by us
  gridOutputFileName         grid output file name - not its absolute path
  gridErrorFileName          grid error file name - not its absolute path
  gridJobFinalStatusFileName final status of command output file name - not its absolute path
  job                        the (now dead) job
-->

export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "generic/retrieveHandler.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "generic/retrieveHandler.ftl" "START SCRIPT"

TMP=${job.getGridJobDirectoryMifHiddenDir()}/MIF.list

if [[ ! -f $TMP ]]; then
    echo "generic/retrieveHandler.ftl: FATAL ERROR: Cannot locate MIF.list file" >&2
    exit 1
fi

# We need to switch this off, otherwise the grep below can cause the script to bomb out if no matching
# pattern is found
set +e

logMetrics "${job.getJobId()}" "generic/retrieveHandler.ftl" "START IGNORE"

if [[ -n "$FILE_IGNORE_PATTERN" || -n "$DIR_IGNORE_PATTERN" ]]; then
    log "retrieveHandler.ftl: File ignore pattern: $FILE_IGNORE_PATTERN"
    log "retrieveHandler.ftl: Dir ignore pattern: $DIR_IGNORE_PATTERN"
    originalCount=$(cat $TMP | wc -l)
    log "retrieveHandler.ftl: Original list of files has $originalCount entries"
    ignorePattern=$(constructIgnorePattern "" "$FILE_IGNORE_PATTERN", "$DIR_IGNORE_PATTERN")
    grep -E -v "$ignorePattern" $TMP > $TMP.1
    newCount=$(cat $TMP.1 | wc -l)
    log "retrieveHandler.ftl: Modified list now has $newCount entries (original $originalCount)"
    mv $TMP.1 $TMP
else
    log "retrieveHandler.ftl: Have neither FILE_IGNORE_PATTERN nor DIR_IGNORE_PATTERN"
fi

logMetrics "${job.getJobId()}" "generic/retrieveHandler.ftl" "END IGNORE"
logMetrics "${job.getJobId()}" "generic/retrieveHandler.ftl" "START RETRIEVE"
<#if job.runInRequestDirectory >
cat "$TMP" | grep -v 'MIF.list' | retrieveHandler.sh -h ${mifHiddenDirectory} \
    -s ${sharedDirectory} -l ${mifModifiedListFilename} \
    -d ${mifOutputDirectory} -c false
<#else >
cat $TMP | grep -v 'MIF.list' | retrieveHandler.sh -h ${mifHiddenDirectory} \
    -s ${sharedDirectory} -l ${mifModifiedListFilename} \
    -d ${mifOutputDirectory} -c true
</#if>


logMetrics "${job.getJobId()}" "generic/retrieveHandler.ftl" "END RETRIEVE"
logMetrics "${job.getJobId()}" "generic/retrieveHandler.ftl" "END SCRIPT"
