#!/bin/bash

# Usage:
#   Usage is rather arcane, for instance, if you specify a -c flag at any stage, all the other flags
#   are ignored.
#   -d dir                 Nominate the destination directory as "dir"
#   -c from-f1 to-f1       Copy the file "from-f1" to "to-f1", creating any intermediate directories
#                          in the path of "to-f1" as necessary
#   -s dir                 Nominate the source directory as "dir"
#   -i ignore.pattern      Specify a regexp with which to ignore entries - files or directories
#   -l filesListing File containing list of files that should be copied

# Note that obviously by the time we are invoked, mango_initialise.sh
# is expected to be on the PATH

source mango_initialise.sh "$0" "$JOBID" "$@"

logArgs $0 "$@"
logMetrics "$JOBID" "$0" "START SCRIPT"

# scan for the use of the -c flag which overrides all other flags
#
cflag=0
otherflag=
for arg; do
    case $arg in
      -c)  cflag=1 ;;
      -*)  otherflag="$arg" ;;
    esac
done

if (( cflag )) && [[ -n $otherflag ]]; then
    logErrorAndExit 127 "$0: Bad usage: other flags used with -c flag"
fi

if (( cflag )); then
    # Deal with the -c flags here

    log "$0: Dealing with -c usage"

    while (( $# > 0 )); do
      case $1 in
        -c) # Copy an existing file to the specified target, creating missing directories in the target path
            # as necessary
            logMetrics "$JOBID" "$0" "START (-c) cp $fromFile $toFile"
            shift
            if (( $# < 2 )); then
                logErrorAndExit 127 "$0: Bad usage: -c and less than two args"
            else
                fromFile="$1"
                shift
                toFile="$1"
                shift
            fi
            toBase=$(dirname "$toFile")
            if [[ ! -d $toBase ]]; then
                log "$0: (-c) mkdir -p $toBase"
                mkdir -p "$toBase"
            fi
            log "$0: (-c) cp $fromFile $toFile"
            cp "$fromFile" "$toFile"
            logMetrics "$JOBID" "$0" "END (-c) cp $fromFile $toFile"
            ;;
        -*)
            # Not sure how this can happen since we have already tested for it above
            #
            logErrorAndExit 128 "$0: Bad usage: -c used, but unknown flag found $1"
            ;;
        *)
            logErrorAndExit 128 "$0: Bad usage: Unknown argument found $1 while processing -c args"
            ;;
      esac
    done

    exit
fi

log "$0: Dealing with non -c usage"

# Since we know we do not have -c, it is safe to use getopts
#
destDir=
sourceDir=
ignorePattern=
filesListing=
while getopts 'd:s:i:l:' arg "$@"; do
  case $arg in
    d)  destDir="$OPTARG" ;;
    s)  sourceDir="$OPTARG" ;;
    i)  ignorePattern="$OPTARG" ;;
    l)  filesListing="$OPTARG" ;;
    ?)  error=1 ;;
  esac
done

let x=OPTIND-1
shift $x

# we must have at least source and destination directories, and they must make sense
#
if [[ -z $destDir ]]; then
    logErrorAndExit 127 "$0: Destination directory not specified with -d flag"
fi

if [[ ! -d $destDir ]]; then
    logErrorAndExit 127 "$0: Destination directory \"$destDir\" does not exist"
fi

if [[ -z $sourceDir ]]; then
    logErrorAndExit 127 "$0: Source directory not specified with -s flag"
fi

if [[ ! -d $sourceDir ]]; then
    logErrorAndExit 127 "$0: Source directory \"$sourceDir\" does not exist"
fi

# we need to add the secret/hidden .MIF directory into the mix
#
if [[ -z "$ignorePattern" ]]; then
    ignorePattern="/\.MIF/"
else
    ignorePattern="$ignorePattern|/\.MIF/"
fi

# Now add the file and directory ignore patterns into the mix too
#
ignorePattern=$(constructIgnorePattern "$ignorePattern" "$FILE_IGNORE_PATTERN" "$DIR_IGNORE_PATTERN")

#
# Now copy files from the source directory to the destination directory, taking into account the
# ignore pattern
#
logMetrics "$JOBID" "$0" "START copyFileStructure f $sourceDir $destDir $ignorePattern" "$filesListing"
copyFileStructure "f" "$sourceDir" "$destDir" "$ignorePattern" "$filesListing"
logMetrics "$JOBID" "$0" "END copyFileStructure f $sourceDir $destDir $ignorePattern" "$filesListing"

ex=$?
log "resourceCopier.sh: the value returned from copyFileStructure is $ex"
logMetrics "$JOBID" "$0" "END SCRIPT"

exit 0
