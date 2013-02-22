
# Collected Mango functions
# This file can be sourced from whereever you like and will provide a common set of lovingly crafted
# functions for doing indescribable things to the files resulting from NONMEM runs.
#

#
# Finds a list file in the given directory
#
function findLstFile {
    find "$1" -type f -name "*.lst" -noleaf -print
}

#
# Return true (i.e. zero exit status) if the file specified by the first parameter does
# *NOT* exist, writing all other parameters to the standard error in the process.  Probably
# best to look at how this is invoked, when it will make more sense.
#
function invalidFile {
    typeset file="$1"
    shift
    if [[ ! -f "$file" ]]; then
        echo "$@" >&2
        return 0
    fi
    return 1
}

#
# Get the rounded objective function from the .lst file (passed in as the first parameter).
# This will have a value of 0 if it cannot be determined.
#
function getObjectiveFunctionValue {
    local objv
    if [[ -f $1 ]]; then
        objv=$(grep '#OBJV' "$1" | tail -1 | sed -r -e 's/[^-0-9.]*(-?[0-9\.]+)[^0-9]*/\1/')
    fi
    if [[ -n $objv ]]; then
        printf "%.3f\n" $objv
    else
        echo "No OBJV"
    fi
}


#
# Get the rounded objective function from the Ext file (passed in as the first parameter).
# This will have a value of "No OBJV" if it cannot be determined (ext file does not exist or it is empty)
# $1 - ext file 
#
function getCurrentObjectiveFunctionValueFromExt {
    local objv
    if [[ -f $1 ]]; then
        objv=$(grep -v -E "(ITERATION|TABLE|-100000000[0-9])" "$1" | tail -1 | sed -r -e 's/^\s+//' | sed -r -e 's/\s+/ /g' | tr ' ' '\n' | tail -1)
    fi
    if [[ -n $objv ]]; then
        printf "%.3f\n" $objv
    else
        echo "No OBJV"
    fi
}

#
# Get objective function value for  nonmem run being processed
# $1 - ctl file 
# $2 - ext file
# $3 - OUTPUT file 
#
function getObjectiveFunctionValueForRunningNONMEM {
        objectiveFunctionValue=$(getCurrentObjectiveFunctionValueFromExt "$2")
        if [[ -z "$objectiveFunctionValue" ]] || [[ "No OBJV" = "$objectiveFunctionValue" ]]; then
            isMaxEval0=$(isMAXEVAL_0 "$1" "$estIndex")
            if (( isMaxEval0 )); then
                log "Is MAXEVAL=0"
                objectiveFunctionValue=$(getObjectiveFunctionValue "$3")
            else
                log "objectiveFunctionValue=\"No OBJV\""
                objectiveFunctionValue="No OBJV"
            fi
        fi
        echo "$objectiveFunctionValue"
}



#
# Get the index of the current estimation method from Ext file (passed in as the first parameter).
# This will have a value of "0" if it cannot be determined (ext file does not exist or it is empty)
# $1 - ext file 
#
function getCurrentEstimationMethodIndexFromExt {
    local estNo=0
    if [[ -f $1 ]]; then
        estNo=$(grep -E "(TABLE)" "$1" | tail -1 | sed -r -e 's/TABLE NO.\s+([0-9]+).+/\1/')
    fi
    
    if [[ ! -n $estNo ]]; then
        estNo=0
    fi
    echo $estNo
}

#
# captures all the blocks of with a given name from the control file
# each block is returned in a single line
# $1 - block name (without '$')
# $2 - ctl file
function captureBlocksFromCtlFile {
    ctlFile=$(cat "$2")
    blocks=$(echo -e "$ctlFile\n\$" | cut -f1 -d';' | sed -n '/[\$]'$1'/{p;:a;n;/[\$]/{/[\$]'$1'/p;b};p;b}' | grep -v '^$')
    local firstLine=1
    echo -e "$blocks" | while read line; 
    do
        newLine=$(echo "$line" | grep "^\$$1" | wc -l)
        if (( $newLine > 0 )); then 
            if (( $firstLine==0 )); then 
            	echo -e ""
            fi
            echo -e "$line" | tr '\n' ' '
        else 
            echo -e "$line" | tr '\n' ' '
        fi;
        firstLine=0
    done
    echo -e ""
}

# Get the estimation method from the control file.
# This has a default of "FO".
# $1 - control file path
# $2 - index of the estimation statement ('-1' or > number of est blocks - last, 0 or empty=1)
function getEstimationMethod {
    invalidFile "$1" "No such control file \"$1\"" && return 10
    local em=FO
    estStatement=$(getEstimationStatement "$1" "$2")
    if echo "$estStatement" | grep METH > /dev/null; then
        em=$(echo "$estStatement" | sed -r -e 's/.*METH(OD)?=([^ ]+[ ]+(INTER)?).*/\2/' -e 's/^1 /FOCE /' -e 's/ +$//')
    fi
    echo "$em"
}

# Checks if the estimation is of MAXEVAL=0
# $1 - control file path
# $2 - index of the estimation statement ('-1' or > number of est blocks - last, 0 or empty=1)
function isMAXEVAL_0 {
    invalidFile "$1" "No such control file \"$1\"" && return 10
    estStatement=$(getEstimationStatement "$1" "$2")
    if echo "$estStatement" | grep 'MAXEVAL=0' > /dev/null; then
        echo "1"
    else 
        echo "0"
    fi
}

#
# Get the estimation statement from the control file
# This has a default of "FO".
# $1 - control file path
# $2 - index of the estimation method ('-1' or > number of est blocks - last, 0 or empty=1)
function getEstimationStatement {
    invalidFile "$1" "No such control file \"$1\"" && return 10
    local em=FO
    if grep "^\$EST" "$1" > /dev/null; then
        estIndex=$2
        estBlocks=$(captureBlocksFromCtlFile "EST" "$1")
        noOfBlocks=$(echo "$estBlocks" | wc -l)
        if [[ -z estIndex ]] || (( 0 == estIndex )); then
            estIndex=1
        else
            if (( -1 == estIndex )) || (( $noOfBlocks < estIndex )); then
                estIndex=$noOfBlocks
            fi
        fi
        local last
        last=$(echo "$estBlocks" | head -n $estIndex | tail -1)
        echo "$last"
    fi
}

#
# Get the termination status from the lst file (passed in as the first parameter).  Its the
# line after the #TERM tag and does not include the leading digit(s)
#
function getStatus {
    invalidFile "$1" "No such lst file \"$1\"" && return 11
    grep -A1 "#TERM" "$1" | tail -1 | sed -r -e "s/^[0-9]+//" -e "s/^ +//"
}

#
# Get the covariance from the control file (passed in as the first parameter) and/or the lst file
# (passed as the second parameter). The rules are rather complicated.
#
function getCovariance {
    invalidFile "$1" "No such control file \"$1\"" && return 10
    invalidFile "$2" "No such lst file \"$2\"" && return 11
    typeset covariance=$(grep '$COV' "$1")
    if [[ -z $covariance ]]; then
        echo 'No $COV'
        return
    fi
    case $covariance in
      *\;*\$COV*)
        echo 'No $COV'
        return ;;
    esac
    typeset seoe="STANDARD ERROR OF ESTIMATE"
    if grep "$seoe" "$1" > /dev/null || grep "$seoe" "$2" > /dev/null; then
        echo '$COV successful'
        return
    fi
    echo '$COV failed'
}

#
# Get the problem (as given by the $PROB line) from the control file (passed in as the first parameter).
#
function getProblem {
    invalidFile "$1" "No such control file \"$1\"" && return 10
    grep "^\$PROB" "$1" | head -1 | sed -r -e 's/\$PROB([^ ]*) (.*)/\2/'
}

#
# Checks if the given lst file represents simulation.
#
function isSIM {
    invalidFile "$1" "No such control file \"$1\"" && return 10
    sim=$(grep "^\$SIM" "$1" | wc -l)
    if (( $sim > 0 )); then 
        echo 1
        return
    fi
    echo 0
}

#
# Captures number of SIM subproblems found in ctl file
# Returns
# 1 - if SIM found but NSUB not specified
# 0 - if SIM not found
# NSUB value 
#
function getNumberOfSIMSubProblems {
    invalidFile "$1" "No such control file \"$1\"" && return 10
    local nsub=1
    if grep "^\$SIM" "$1" > /dev/null; then
        local last
        last=$(captureBlocksFromCtlFile "SIM" "$1" | tail -1)
        if echo "$last" | grep SUB > /dev/null; then
            nsub=$(echo "$last" | sed -r -e 's/.*((NSUB(PROB(LEM)?S)?)|(SUBPROB(LEM)?S))=([0-9]+)[ ]*/\7/')
        fi
    else 
        nsub=0
    fi
    echo "$nsub"
}
#
# Get the number of currently processed subproblem  
# This will have a value of 1 if it cannot be determined.
#
function getCurrentSubProblemNumber {
    invalidFile "$1" "No such OUTPUT file \"$1\"" && return 10
    local subprob
    if [[ -f "$1" ]]; then
        subprob=$(grep 'SUBPROBLEM NO.:' "$1" | tail -1 | sed -r -e 's/.*SUBPROBLEM NO\.:[\ ]+([0-9]+)[^0-9]*/\1/')
    fi
    if [[ -n $subprob ]]; then
        echo $subprob
    else
        echo "1"
    fi
}

#
# Get the data file (as given by the $DATA line) from the control file (passed in as the first parameter).
#
function getDataFile {
    invalidFile "$1" "No such control file \"$1\"" && return 10
    grep "^\$DATA" "$1" | head -1 | sed -r -e 's/\$DATA ([^ ]+) .*/\1/'
}

#
# Get the last updated time from the youngest file in the specified directory or below it
#
function getLastUpdatedFileTimeInSeconds {
    typeset dir="$1"
    if [[ ! -d "$dir" ]]; then
        echo "$dir: No such directory"
        return 58
    fi
    find $dir -type f -exec stat -c "%X" {} \; -noleaf -print | sort -nr | head -1
}


function createSummaryMessageForCompletedNONMEMRun {
    nonmemRunWorkingDirectory="$1"
    nonmemModelFile="$2"
    nonmemLstFileName="$3"

    LST_FILEPATH="$nonmemRunWorkingDirectory/$nonmemLstFileName"
    CONTROL_FILEPATH="$nonmemRunWorkingDirectory/$nonmemModelFile"
    
    IS_SIM=$(isSIM "$CONTROL_FILEPATH" "$LST_FILEPATH")
    
    if (( $IS_SIM )); then
        echo "Simulation Completed"
        return
    fi
    
    OBJECTIVE_FUNCTION_VALUE=0
    TERM_STATUS_TEXT=""
    if [[ -f "$LST_FILEPATH" ]]; then
        OBJECTIVE_FUNCTION_VALUE=$(getObjectiveFunctionValue "$LST_FILEPATH")
        TERM_STATUS_TEXT=$(getStatus "$LST_FILEPATH")
    fi
    
    ESTIMATION_METHOD=
    PROBLEM=
    DATAFILE=
    if [[ -f "$CONTROL_FILEPATH" ]]; then
        ESTIMATION_METHOD=$(getEstimationMethod "$CONTROL_FILEPATH")
        PROBLEM=$(getProblem "$CONTROL_FILEPATH")
        DATAFILE=$(getDataFile "$CONTROL_FILEPATH")
    fi
    
    COVARIANCE=$(getCovariance "$CONTROL_FILEPATH" "$LST_FILEPATH")
    
    echo "$ESTIMATION_METHOD:$OBJECTIVE_FUNCTION_VALUE;$TERM_STATUS_TEXT;$COVARIANCE"
}
