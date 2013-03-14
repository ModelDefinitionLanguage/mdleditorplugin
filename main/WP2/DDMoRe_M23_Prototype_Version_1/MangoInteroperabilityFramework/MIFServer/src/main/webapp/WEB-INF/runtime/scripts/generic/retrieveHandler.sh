#!/bin/bash

# Note that obviously by the time we are invoked, mango_initialise.sh
# is expected to be on the PATH

# Also note: Initially this script took -c arguments which said what file
# to copy.  Unfortunately with some runs there are many hundreds of files,
# which blew the argument length and caused the script to fail.  Now the
# files to copy are read from the standard input.

source mango_initialise.sh $0 "$JOBID" "$@"

logMetrics "$JOBID" "$0" "START SCRIPT"

hiddenDir=
sourceDir=
targetDir=
modifiedListFileName=
copyToOutputDir=true

i=0

while getopts 'h:s:l:d:c:' arg "$@"; do
  case $arg in
    h)  hiddenDir="$OPTARG" ;;
    s)  sourceDir="$OPTARG" ;;
    d)  targetDir="$OPTARG" ;;
    l)  modifiedListFileName="$OPTARG" ;;
    c)  copyToOutputDir=$OPTARG ;;
    ?)  logErrorAndExit 127 "retrieveHandler.sh saw unknown flag $OPTARG" ;;
  esac
done

let x=OPTIND-1
shift $x

# Preconditions
#
[[ -z $hiddenDir ]] && logErrorAndExit 128 "$0 expected argument -h with hidden MIF directory path"
[[ -z $sourceDir ]] && logErrorAndExit 128 "$0 expected argument -s with source directory path"
[[ ! -d $sourceDir ]] && logErrorAndExit 128 "$0 -s argument $sourceDir: directory is expected to exist"
[[ -z $targetDir ]] && logErrorAndExit 128 "$0 expected argument -d with request output folder"
[[ ! -d $targetDir ]] && logErrorAndExit 128 "$0 -d argument $targetDir: directory is expected to exist"
[[ -z $modifiedListFileName ]] && logErrorAndExit 128 "$0 -l argument expected"

logMetrics "$JOBID" "$0" "START STEP1"

# Step 1
# Create the hidden/secret MIF directory with no special modes (the current umask will have to do)
#
if [[ ! -d $hiddenDir ]]; then
    log "$0: mkdir -p $hiddenDir"
    if ! mkdir -p "$hiddenDir"; then
        logErrorAndExit 31 "$0: FATAL ERROR: Failed to create hidden directory $hiddenDir"
    fi
fi

logMetrics "$JOBID" "$0" "END STEP1"
logMetrics "$JOBID" "$0" "START STEP2"

# Step 2
# Move the grid output and error files into the hidden/secret MIF directory with no special modes.
# Perhaps we should move these files rather than copying them to avoid recopying down below?
# no we should not, otherwise users cannot see them in related files once job is finished
#
if $copyToOutputDir; then
      cp -r "$sourceDir/.MIF/"* "$hiddenDir"
fi

logMetrics "$JOBID" "$0" "END STEP2"
logMetrics "$JOBID" "$0" "START STEP3"

# Step 3
# Create the file containing the list of copied files, in the hidden/secret MIF directory
#
log "$0: Creating the modified list file $modifiedListFileName"
modifiedListFile=$hiddenDir/$modifiedListFileName
> $modifiedListFile


logMetrics "$JOBID" "$0" "END STEP3"
logMetrics "$JOBID" "$0" "START STEP4"

# Step 4
# Copy files from the grid shared directory to the MIF output directory. Remember that the
# relative filenames are ON OUR STANDARD INPUT to avoid "arg list too long" messages.
#
while read relativePath
do
    sourcePath="$sourceDir/$relativePath"
    targetPath="$targetDir/$relativePath"
    targetParentPath=$(dirname "$targetPath")
    if $copyToOutputDir; then
        if [[ ! -d $targetParentPath ]]; then
            log "$0: mkdir -p $targetParentPath"
            if ! mkdir -p "$targetParentPath"; then
                logErrorAndExit 32 "$0: FATAL ERROR: $targetParentPath does not exist and cannot be created"
            fi
        fi
        if [[ ! -w "$targetParentPath" ]]; then
            logErrorAndExit 33 "$0: FATAL ERROR: $targetParentPath exists and is not writable"
        fi

        if [[ -e "$targetPath" && ! -w "$targetPath" ]]; then
            logErrorAndExit 34 "$0: FATAL ERROR: $targetPath exists and is not writable"
        fi
        # log "$0: cp $sourcePath $targetPath"
        cp "$sourcePath" "$targetPath"
    fi

    # as we are going, we keep the list of updated files up to date
    # log "$0: adding \"$targetPath\" to $modifiedListFile"
    echo "$targetPath" >> "$modifiedListFile"
done

logMetrics "$JOBID" "$0" "END STEP4"
logMetrics "$JOBID" "$0" "END SCRIPT"
