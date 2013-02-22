#
# A property holding a name of the file containing an id of the cancelled child job
#
if [[ -z "$MIF_CANCELLED_JOB_ID_FILE_NAME" ]]; then
   MIF_CANCELLED_JOB_ID_FILE_NAME=".MIF-cancelledJobId"
fi

#
# gets SGE Job Id from a file containing qsub output
#
function captureSgeJobIdFromQsubOutput {
	grep -E -o 'Your job [[:digit:]]+' "$1" | sed 's/Your job //g'
}
#
# gets SGE Job name from a file containing qsub output
#
function captureSgeJobNameFromQsubOutput {
	grep -o "\".*\"" "$1" | sed 's/"//g'
}
#
# retrieves a start time from lst file
#
function captureStartTime {
     if [[ -f $1 ]]; then
        tmpDate=$(head -n 1 $1)
        echo $(date --date="$tmpDate" "+%s")"000" #converting to milliseconds
     else 
         echo "0"
     fi
}
#
# checks if given token can be found at the end of the line, if so it returns true (0)
#
function isTokenFoundInTheLastLine {
    if (($(tail -n 2 "$1" | grep "$2" | wc -l) == 1)); then
        echo 1
    else 
        echo 0
    fi
}
#
# retrieves a end time from lst file
#
function captureEndTime {
    if [[ -f $1 ]]; then
        finishedTokensCount=$(isTokenFoundInTheLastLine "$1" "Finished")
        if (( $finishedTokensCount != 1 )); then
            echo "0"
        else
            tmpDate=$(tail -n 4 "$1" | head -n 1)
            echo  $(date --date="$tmpDate" "+%s")"000" #converting to milliseconds
        fi
    else 
        echo "0"
    fi
}
#
# retrieves a end time from lst file
#
function getCurrentLstFileName {
    if [[ -d $1 ]]; then
        echo  $(find "$1" -noleaf -type f -name "*.lst" -print | sort | head -n 1)
    else 
        echo "N/A"
    fi
}

#
# get base model lst file
#
function getBaseModelLstFile {
    psnOutputDirectory=$1
    echo "$psnOutputDirectory/base_modelfit_dir1/NM_run1/psn.lst"
}

#
# computes status of a run
#
function computeStatus {
    runWorkingDir=$1
    runName=$2
    duration=$3
    treshold=$(($4 * 10))
    modelEntriesCountInBootstrapLog=$(countNumberOfMessages "$runWorkingDir/../../bootstraplog1.csv" "$runName")
    if (( modelEntriesCountInBootstrapLog > 0 )); then
        echo "Completed"
        return 0;
    fi
    if (( treshold < duration)); then
        echo "Hanging"
    else
        echo "Running"
    fi
}

#
# returns duration in milliseconds
#
function retrieveParentModelDuration {
    bsOutputDirectory=$1
    startTime=$(captureStartTime "$bsOutputDirectory/orig_modelfit_dir1/NM_run1/psn.lst")
    endTime=$(captureEndTime "$bsOutputDirectory/orig_modelfit_dir1/NM_run1/psn.lst")
    echo $(($endTime - $startTime))
}
#
# builds a NONMEM job descriptor
# key-value csv file formatted
#
function createNonmemJobDescriptor {
    runWorkingDir=$1
    qsubOutputFileName=$2
    nonmemOutputFileName=$3
    parentModelDuration=$4
    jobIdFile="$runWorkingDir/$qsubOutputFileName"
    runOnSge=1
    if [[ -f $jobIdFile ]]; then
        jobId=$(captureSgeJobIdFromQsubOutput "$jobIdFile")
        jobName=$(captureSgeJobNameFromQsubOutput "$jobIdFile")
    else 
        jobId=""
        jobName=""
        runOnSge=0
    fi
    runName=$(basename $runWorkingDir)
    modFile="$runWorkingDir/psn.mod"
    modFileName=$(basename "$modFile")
    outputFile="$runWorkingDir/$nonmemOutputFileName"
    extFile="$runWorkingDir/psn.ext"
    startTime=$(captureStartTime "$runWorkingDir/psn.lst")
    endTime=$(captureEndTime "$runWorkingDir/psn.lst")
    description="$jobName run with NM"
    status="Pending"
#cancelled jobs
    cancelledJobIdFile="$runWorkingDir/$MIF_CANCELLED_JOB_ID_FILE_NAME"
    if [[ -f $cancelledJobIdFile && $runOnSge ]]; then
        cancelledJobId=$(captureSgeJobIdFromQsubOutput "$cancelledJobIdFile")
        if (($cancelledJobId==$jobId)); then
            status="Cancelled"
        fi
    fi
#retries
    retriesSegment=""
    
    if [[ -n "$retries" ]] && (( retries > 0 )); then
        count=0
        if [[ -d "$runWorkingDir" ]]; then
            count=$(find "$runWorkingDir" -name "psn-*.lst" | wc -l)
            if [[ -n "$count" ]] && (( count > 0 )); then
               (( count = count - 1 ))
            fi
        fi
        retriesSegment=" ($count/$retries)"
    fi
    

    if (( startTime == 0 )) || [[ $status = "Cancelled" ]]; then
        duration=0
        objectiveFunctionValue=""
        estimationMethod=""
        summaryMessage="STARTUP$retriesSegment"
    else
        estIndex=$(getCurrentEstimationMethodIndexFromExt "$extFile")
        objectiveFunctionValue=$(getObjectiveFunctionValueForRunningNONMEM "$modFile" "$extFile" "$outputFile")
    
        if (( endTime == 0 )); then 
            endTime=$(date +%s)"000"
        fi
        duration=$((endTime - startTime))

        estimationMethod=$(getEstimationMethod "$modFile" "$estIndex")
        status=$(computeStatus "$runWorkingDir" "$jobName" $duration $parentModelDuration)
        if [[ "$objectiveFunctionValue" = "No OBJV" ]]; then
            summaryMessage="STARTUP$retriesSegment"
        else
            summaryMessage="$estimationMethod: $objectiveFunctionValue$retriesSegment"
        fi
    fi
    echo "jobId:$jobId;runName:$jobName;description:$description;modFileName:$modFileName;runWorkingDir:$runWorkingDir;startTime:$startTime;outputFile:$nonmemOutputFileName;objectiveFunctionValue:$objectiveFunctionValue;estimationMethod:$estimationMethod;status:$status;duration:$duration;summaryMessage:$summaryMessage" 
}
#
# builds a key-value csv formatted file containing NONMEM child jobs descriptors  
#
function getNonmemJobsDescriptors {
    #bootstrap output directory (e.g. 'bs')
    rootDirectory=$1 
    if [ -z $2 ]; then
        childNonmemRunWorkingDirectoryNamePattern="NM_run*"
    else 
        childNonmemRunWorkingDirectoryNamePattern=$2
    fi
    if [ -z $3 ]; then
        qsubOutputFileName="JobId"
    else 
        childNonmemRunWorkingDirectoryNamePattern=$3
    fi
    if [ -z $4 ]; then
        nonmemOutputFileName="OUTPUT"
    else 
        nonmemOutputFileName=$4
    fi

    retries=0
    for param in $(cat "$rootDirectory/command.txt"); do
        case $param in
           -retries=*) retries=$(echo $param | sed -r -e "s/-retries=([0-9]+)[^0-9]*/\1/") ;;
        esac
    done
    
    parentModelDuration=$(retrieveParentModelDuration "$rootDirectory")
    find "$rootDirectory/modelfit_dir1/" -noleaf -type d -name "$childNonmemRunWorkingDirectoryNamePattern" -print | while read dir; do echo $(createNonmemJobDescriptor "$dir" "$qsubOutputFileName" "$nonmemOutputFileName" $parentModelDuration $retries); done
}
#
# builds a list of SGE Child jobs ids
#
function getListOfPsNSGEChildJobsIds {
    rootDirectory=$1
    qsubOutputFileName="JobId"
    childNonmemRunWorkingDirectoryNamePattern="NM_run*"
    childJobsIds=$(find $rootDirectory -noleaf -type f -name "$qsubOutputFileName" -print | while read file; do printf "$(captureSgeJobIdFromQsubOutput "$file" "$childNonmemRunWorkingDirectoryNamePattern" "$qsubOutputFileName") "; done)
    childJobsIds=$(echo "$childJobsIds" | sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//')
    echo "$childJobsIds"
}
