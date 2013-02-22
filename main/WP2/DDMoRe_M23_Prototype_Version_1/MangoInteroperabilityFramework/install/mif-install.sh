#!/bin/bash

##########################################################################################
# Install MIF
##########################################################################################
#
export MIF_WAR=MIF.war
export MIF_CONTEXT_XML=mif-context.xml
export MIF_CONFIG_DIR=/etc/sysconfig/mif
export MIF_PROPERTIES=$MIF_CONFIG_DIR/mif.properties
export MIF_LOG_DIR=/var/log/mif

##########################################################################################
# Functions
##########################################################################################
#
function verifyDirectory {
    if [[ -d "$1" ]]; then
       return 0
    else
       echo "The directory \"$1\" does not exist" >&2
       return 1
    fi
}

function verifyUser {
    if grep "^$1:" /etc/passwd > /dev/null; then
       return 0
    elif getent passwd "$1" > /dev/null; then
       return 0
    else
       echo "Cannot locate the user \"$1\"" >&2
    fi
    return 1
}

function verifyGroup {
    if grep "^$1:" /etc/group > /dev/null; then
       return 0
    elif getent group | grep "$1" > /dev/null; then
       return 0
    else
       echo "Cannot locate the group \"$1\"" >&2
    fi
    return 1
}

function verifyFile {
    local file="$1"
    local type="$2"

    case $type in
      f)  if [[ -f "$file" ]]; then
              return 0
          else 
              echo "The file \"$file\" does not exist" >&2
          fi ;;
      x)  if [[ -f "$file" && -x "$file" ]]; then
              return 0
          elif [[ ! -f "$file" ]]; then
              echo "The file \"$file\" does not exist" >&2
          else
              echo "The file \"$file\" is not executable" >&2
          fi ;;
    esac
    return 1
}

# Not much we can do to check the path to a file or directory that doesn't exist.
# What we do, however, is to check the path is absolute and that the top level
# directory exists
#
function verifyPath {
    local path="$1"
    if [[ "$path" != /* ]]; then
        echo "The path must be absolute" >&2
        return 1
    fi
    return 0
}

##########################################################################################
# make a directory path, or check each part of it if it already exists for group and other
# accessibility
#
function mkpath {
    local path="$1"

    if [[ "$path" == "/" ]]; then
        return 0
    else
        if ! mkpath $(dirname "$path"); then
            return 1
        fi
    fi

    if [[ ! -d "$path" ]] && ! mkdir "$path"; then
        echo "mkdir $path failed"
        return 1
    fi        

    if [[ -d "$path" ]]; then
        if [[ ! -x "$path" || ! -r "$path" ]]; then
            echo "Error: $path is not accessible"
            return 1
        fi
        local mode=$(stat -c "%a" "$path")
        set -- $(echo $mode | sed -r -e 's/(.)(.)(.)$/\1 \2 \3/')
        local user=$1
        local group=$2
        local others=$3
        if (( group != 5 && group != 7 )); then
            echo "WARNING: $path is not accessible by group (mode $mode)"
        fi
        if (( others != 5 && others != 7 )); then
            echo "WARNING: $path is not accessible by others (mode $mode)"
        fi
    fi
    return 0
}

##########################################################################################
# Get a value from the .last file
##########################################################################################
function getLast {
   local name="$1"
   grep "^$name=" $INSTALL_LAST 2> /dev/null | tail -1 | sed -r -e 's/[^=]+=//'
}

##########################################################################################
# Put a value into the .last file for next time.
##########################################################################################
function putLast {
   local name="$1"
   local value="$2"
   echo "$name=$value" >> $INSTALL_LAST
}

##########################################################################################
# get a variety of things by prompting the user and looping if we don't like the users
# response.
# Parameters:
# 1.  The type tells us what we're expecting unless empty in which case we'll take anything.
# 2.  The prompt to use while annoying the user
# 3.  Any default value, if appropriate
# 4.  The value that the user typed the last time they were asked this question
#
function get {
    local type="$1"
    local prompt="Please specify the $2"
    local default="$3"
    local lastname="$4"
    local lastvalue=
    local reply=

    # this is where we pick up the value used last time
    if [[ -n "$lastname" ]]; then
        lastvalue=$(getLast $lastname)
        if [[ -n "$lastvalue" ]]; then
            default="$lastvalue"
        fi
    fi
    if [[ -n "$default" ]]; then
        history -s "$default"
    fi

    while true; do
        echo -n "$prompt: " >&2
        read -e reply
        if [[ -n "$reply" ]]; then
            case $type in
              d)    if verifyDirectory "$reply"; then
                        break
                    fi ;;
              u)    if verifyUser "$reply"; then
                        break
                    fi ;;
              f|x)  if verifyFile "$reply" "$type"; then
                        break
                    fi ;;
              g)    if verifyGroup "$reply"; then
                        break
                    fi ;;
              p)    if verifyPath "$reply"; then
                        break
                    fi ;;
              ?*)   echo "Internal error, unknown type $type" >&2 ;;
              *)    # no checking here
                    break ;;
            esac
        fi
    done

    # save for next time
    history -s "$reply"
    history -w "$INSTALL_HISTORY"

    echo "$reply"
}

##########################################################################################
# set the property with the specified name to the specified value in the specified file
##########################################################################################
#
function setproperty {
    local name="$1"
    local value="$2"
    local file="$3"

    if [[ ! -f $file ]]; then
        echo "Can't write property $name to $file, No such file"
        return 1
    fi

    sed -i "/$name=/d" $file

    echo "$name=$value" >> $file
}

##########################################################################################
# Initial checks
##########################################################################################
if [[ ! -f "$MIF_WAR" ]]; then
    echo "Cannot find $MIF_WAR, please run in the same directory as the war file was extracted to"
    exit 2
fi

if [[ ! -f "$MIF_CONTEXT_XML" ]]; then
    echo "Cannot find $MIF_CONTEXT_XML, please run in the same directory as the war file was extracted to"
    exit 2
fi

# The days of us getting an absolute path in $0 are over...
#
export INSTALL_DIR=$(cd $(dirname $0); /bin/pwd)
export INSTALL_LAST=$INSTALL_DIR/.last
export INSTALL_HISTORY=$INSTALL_DIR/.history

##########################################################################################
# Make the history/editing work.
##########################################################################################
set -o emacs
if [[ -f $INSTALL_HISTORY ]]; then
    history -r $INSTALL_HISTORY
fi

##########################################################################################
# Process command line arguments.
# Not sure what these should be yet.
##########################################################################################
error=0
verbose=0
while getopts 'v' arg "$@"; do
  case $arg in
    v)  verbose=1 ;;
    ?)  error=1 ;;
  esac
done

let x=OPTIND-1
shift $x

if (( error )); then
    echo "Usage: $(basename $0) [-v]"
    echo "-v   possibly enable verbose, meaningless debugging"
    exit 3
fi


##########################################################################################
# Check the environment, we only need to do this once, so only do this if we've never ever
# been here before.
##########################################################################################
checkedBefore=$(getLast ENVIRONMENT_CHECK)

if [[ -z "$checkedBefore" ]]; then
    
    echo "Environment check..."

    # Step 1: check the bash version, must be 3.2 or above
    #
    INSTALLED_BASH_VERSION=$(bash --version | head -1 | perl -ne "print if s/.* version ([0-9.]+).*$/\1/")
    case $INSTALL_BASH_VERSION in
      [1|2].*|3.[0|1].*)
            echo "Your version of bash ($INSTALLED_BASH_VERSION) is too old"
            exit 9 ;;
    esac

    # Step 2: check the python version
    #
    INSTALLED_PYTHON_VERSION=$(python -V 2>&1 | perl -ne "print if s/^([^0-9])+([0-9.]+).*/\2/")
    case $INSTALLED_PYTHON_VERSION in
      1.*|2.[0|1|2|3].*)
            echo "Your version of python ($INSTALLED_PYTHON_VERSION) is too old"
            exit 9 ;;
    esac

    # Step 3: There are a whole bunch of commands we rely on, but don't care about their version.
    #         Some of these aren't really worth checking, because they're part of the woodwork,
    #         but since we're here...
    #
    LIST=/tmp/.list.$$
    cat <<-'%' > $LIST
	cp
	mv
    cpio
    find
    touch
    mkdir
    grep
    date
    stat
    cat
    ls
    wc
    chmod
    tail
    sort
    head
    basename
    dirname
%
    errors=0
    for command in $(< $LIST); do
        echo -n "Checking $command... "
        if [[ ! -x /bin/$command && ! -x /usr/bin/$command ]]; then
            echo "*FAIL*"
            errors=1
        else
            echo "PASS"
        fi
    done
    if (( errors )); then
        exit 9
    fi

    # Step 4: The last step is to check for particular flags we need to particular
    #         commands.
    #
    DUMMY=/tmp/.empty.$$
    mkdir $DUMMY
    DEEPER=$DUMMY/a/b/c/d/e

    LIST=/tmp/.list.$$
    cat <<-% > $LIST
	echo -n "find accepts the -newer option..." && find $DUMMY -newer $LIST -print > /dev/null 2>&1
	echo -n "find accepts the -noleaf option..." && find $DUMMY -noleaf -print > /dev/null 2>&1
	echo -n "mkdir accepts the -p option..." && mkdir -p $DEEPER  > /dev/null 2>&1 && [[ -d "$DEEPER" ]]
	echo -n "grep accepts the -E option..." && grep -E "root|bin" /etc/passwd > /dev/null 2>&1
	echo -n "grep accepts the \":digit:\" syntax..." && grep "[:digit:]" /etc/passwd > /dev/null 2>&1
	echo -n "grep accepts the -A option..." && grep -A5 "root" /etc/passwd > /dev/null 2>&1
	echo -n "date accepts the necessary formatting flags..." && date +"%s %H:%M %m-%d" > /dev/null 2>&1
	echo -n "date accepts the --date flag..." && date --date="$(date)" > /dev/null 2>&1
	echo -n "stat accepts the -c flag and %X formatting flag..." && stat -c "%X" /etc/passwd > /dev/null 2>&1
	echo -n "sed accepts the -r flag and backreferences..." && cat /etc/passwd | sed -r -e "s/([^:]+).*/\1/" > /dev/null 2>&1
	echo -n "sed accepts the \":space:\" syntax..." && cat /etc/passwd | sed -e "s/[[:space:]]/X/g" > /dev/null 2>&1
	echo -n "ls accepts the 1 and d flags..." && ls -1d > /dev/null 2>&1
	echo -n "tail accepts the -n flag..." && cat /etc/passwd | tail -n 3 > /dev/null 2>&1
%
    while read cmd; do
        if eval "$cmd"; then
            echo "PASS"
        else
            echo "*FAIL*"
            errors=1
        fi
    done < $LIST

    if (( errors )); then
        exit 9
    fi

    putLast ENVIRONMENT_CHECK "y"
fi

##########################################################################################
# The bits we can't do, unless we're running as root, we get these over first as they
# will cause us to fail utterly
##########################################################################################
if [[ ! -w "$INSTALL_DIR" ]]; then
    echo "The install directory $INSTALL_DIR is not writeable"
    exit 3
fi

if ! mkpath "$MIF_CONFIG_DIR"; then
    echo "Failed to create $MIF_CONFIG_DIR, please see your system administrator"
    exit 4
fi

if ! mkpath "$MIF_LOG_DIR"; then
    echo "Failed to create $MIF_LOG_DIR, please see your system administrator"
    exit 5
fi

if [[ ! -f $MIF_PROPERTIES ]]; then
    if ! touch $MIF_PROPERTIES; then
        echo "Failed to create $MIF_PROPERTIES, please see your system administrator"
        exit 6
    fi
fi

MIF_SHARE_DIRECTORY=$(get "p" "MIF share directory" "" "MIF_SHARE_DIRECTORY_LAST")
if ! mkpath "$MIF_SHARE_DIRECTORY"; then
    echo "Failed to create $MIF_SHARE_DIRECTORY, please see your system administrator"
    exit 7
fi

GRID_SHARE_DIRECTORY=$(get "p" "Grid share directory" "" "GRID_SHARE_DIRECTORY_LAST")
if ! mkpath "$GRID_SHARE_DIRECTORY"; then
    echo "Failed to create $GRID_SHARE_DIRECTORY, please see your system administrator"
    exit 8
fi

MIF_WORKING_DIRECTORY=$(get "p" "MIF working directory" "$INSTALL_DIR/working" "MIF_WORKING_DIR_LAST")
if ! mkpath "$MIF_WORKING_DIRECTORY"; then
    echo "Failed to create $MIF_WORKING_DIRECTORY, please see your system administrator"
    exit 9
fi

MIF_RUNTIME_DIRECTORY=$(get "d" "MIF runtime directory" "$INSTALL_DIR/runtime" "MIF_RUNTIME_DIR_LAST")
if ! mkpath "$MIF_RUNTIME_DIRECTORY"; then
    echo "Failed to create $MIF_RUNTIME_DIRECTORY, please see your system administrator"
    exit 10
fi

SGE_SCRIPT_PATH=$(get "x" "SGE initialisation script" "" "SGE_SCRIPT_PATH_LAST")

##########################################################################################
# Backup files
##########################################################################################
now=$(date +"%Y%m%d-%H:%M:%S")
BACKUP_DIR=$INSTALL_DIR/$now
mkdir $BACKUP_DIR

# The properties file is the only thing I can think of backing up at the moment
cp $MIF_PROPERTIES $BACKUP_DIR

##########################################################################################
# Write properties
##########################################################################################

setproperty "mif.shared.location" "$MIF_SHARE_DIRECTORY" "$MIF_PROPERTIES"
setproperty "sgeconnector.sgeSharedLocation" "$GRID_SHARE_DIRECTORY" "$MIF_PROPERTIES"
setproperty "mif.working.dir" "$MIF_WORKING_DIRECTORY" "$MIF_PROPERTIES"
setproperty "mif.templatesDirectory" "$MIF_RUNTIME_DIRECTORY/templates" "$MIF_PROPERTIES"
setproperty "mif.commonScriptsDirectory" "$MIF_RUNTIME_DIRECTORY/scripts/common" "$MIF_PROPERTIES"
setproperty "mif.genericScriptsDirectory" "$MIF_RUNTIME_DIRECTORY/scripts/generic" "$MIF_PROPERTIES"
setproperty "mif.sgeInitialisationScriptFullPath" "$SGE_SCRIPT_PATH" "$MIF_PROPERTIES"

##########################################################################################
# Get more values, write as properties
##########################################################################################

ACTIVE_MQ_URL=$(get "" "Active MQ URL" "tcp://localhost:61616" "ACTIVE_MQ_URL_LAST")
setproperty "mif.transportconnector.url" "$ACTIVE_MQ_URL" "$MIF_PROPERTIES"

dbChoice=$(getLast DB_CHOICE)
if [[ -z "$dbChoice" ]]; then
    echo "I can help set up the properties for three types of database, please choose one or select \"other\""
    select dbChoice in H2 MySQL Oracle Other; do
      if [[ -n "$dbChoice" ]]; then
         break
      fi
    done
    putLast DB_CHOICE "$dbChoice"
fi

case $dbChoice in
  H2)
    DEFAULT_DIALECT="org.hibernate.dialect.H2Dialect"
    DEFAULT_DRIVER="org.h2.Driver"
    DEFAULT_JDBC_URL="jdbc:h2:file:${mif.working.dir}/db/h2db"
    DEFAULT_USER=sa
    DEFAULT_PASSWORD= ;;
  MySQL)
    DEFAULT_DIALECT="org.hibernate.dialect.MySQL5InnoDBDialect"
    DEFAULT_DRIVER="com.mysql.jdbc.Driver"
    DEFAULT_JDBC_URL="jdbc:mysql://localhost/mif"
    DEFAULT_USER="mif"
    DEFAULT_PASSWORD="mif" ;;
  Oracle)
    DEFAULT_DIALECT="org.hibernate.dialect.Oracle10gDialect"
    DEFAULT_DRIVER="oracle.jdbc.driver.OracleDriver"
    DEFAULT_JDBC_URL="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=el5-64-o11g)(PORT=1524)))(CONNECT_DATA=(SID=DEV10g)(SERVER=DEDICATED)))"
    DEFAULT_USER="mif"
    DEFAULT_PASSWORD="mif" ;;
  *)
    ;;
esac

JDBC_DIALECT=$(get "" "JDBC Dialect" "$DEFAULT_DIALECT" "JDBC_DIALECT_LAST")
JDBC_DRIVER=$(get "" "JDBC Driver" "$DEFAULT_DRIVER" "JDBC_DRIVER_LAST")
JDBC_URL=$(get "" "JDBC URL" "$DEFAULT_JDBC_URL" "JDBC_URL_LAST")
JDBC_USER=$(get "" "JDBC User" "$DEFAULT_USER" "JDBC_USER_LAST")
JDBC_PASSWORD=$(get "" "JDBC Password" "$DEFAULT_PASSWORD" "JDBC_PASSWORD_LAST")

##########################################################################################
# Establish where tomcat is, ensure the tomcat user is a member of the workbench group
##########################################################################################
DEFAULT_TOMCAT_DIR=/usr/share/tomcat6
if [[ ! -d "$DEFAULT_TOMCAT_DIR" ]]; then
    DEFAULT_TOMCAT_DIR=
fi

TOMCAT_DIR=$(get "d" "Tomcat directory" "$DEFAULT_TOMCAT_DIR" "TOMCAT_DIR_LAST")
WORKBENCH_GROUP=$(get "g" "Workbench group" "" "WORKBENCH_GROUP_LAST")

DEFAULT_TOMCAT_USER=
CONFIG="/etc/tomcat6/tomcat6.conf"
if [[ -f "$CONFIG" ]]; then
    if grep TOMCAT_USER $CONFIG > /dev/null; then
        DEFAULT_TOMCAT_USER=$(grep TOMCAT_USER $CONFIG | sed -r -e "s/TOMCAT_USER=[\"']?([^\"']+)[\"']?/\1/")
    fi
fi

while true; do
    TOMCAT_USER=$(get "u" "Tomcat user" "$DEFAULT_TOMCAT_USER" "DEFAULT_TOMCAT_USER_LAST")
    TOMCAT_USER_GROUPS=$(id -G -n $TOMCAT_USER)
    if echo "$TOMCAT_USER_GROUPS" | grep "$WORKBENCH_GROUP" > /dev/null; then
        echo "The user $TOMCAT_USER is a member of the workbench group $WORKBENCH_GROUP"
        break
    else
        echo "The user $TOMCAT_USER is not a member of the workbench group $WORKBENCH_GROUP"
    fi
done

TOMCAT_DEPLOY_DIR=$TOMCAT_DIR/conf/Catalina/localhost
if [[ ! -d $TOMCAT_DEPLOY_DIR ]]; then
    echo "Cannot find directory: $TOMCAT_DEPLOY_DIR, will not be able to create context"
else
    echo "Creating MIFServer.xml in $TOMCAT_DEPLOY_DIR"
    (cd $TOMCAT_DEPLOY_DIR; ln -s $INSTALL_DIR/$MIF_CONTEXT_XML MIFServer.xml)
fi

