@echo off

SET FINDCMD=C:\Windows\System32\find.exe


:: ==================================
:: Build the complete MDL IDE package
:: ==================================

:: Set up environment
title Full MDL-IDE Build


:: Uncomment and set the variables below 
::
:: Make sure that BUILD_FOLDER points to the location where the DDMoRe repositories are checked out
:: Mind that all checked out repositories should be direct children of the BUILD_FOLDER directory.
::
:: set BUILD_FOLDER="%userprofile%\.git"
::
:: Location of Maven local repository
:: set MVN_REPOSITORY_ROOT="%userprofile%\.m2"

:: name of the directory holding the use.cases repository
SET USE_CASES_REPO_DIR_NAME="use.cases"

:: name of the directory holding the ddmore-code repository
SET DDMORE_CODE_REPO_DIR_NAME="ddmore-code"

:: name of the directory holding the nmtranconverter repository
SET NMTRANCONVERTER_REPO_DIR_NAME="nmtranconverter"

if not defined BUILD_FOLDER (
	ECHO BUILD_FOLDER IS NOT DEFINED YOU MUST EDIT THIS SCRIPT BEFORE RUNNING IT
	goto :failed
	)
if not defined MVN_REPOSITORY_ROOT (
	ECHO MVN_REPOSITORY_ROOT IS NOT DEFINED YOU MUST EDIT THIS SCRIPT BEFORE RUNNING IT
	goto :failed
	)

:: Clean out certain bits of the Maven Repo to avoid any unexpected build issues
echo Cleaning up MVN Repository ...
echo ... com\mango
rd /s /q %MVN_REPOSITORY_ROOT%\repository\com\mango
echo ... eu\ddmore
rd /s /q %MVN_REPOSITORY_ROOT%\repository\eu\ddmore
echo ... org\ddmore
rd /s /q %MVN_REPOSITORY_ROOT%\repository\org\ddmore


:: Clean out any existing log files
del /P *-log.txt


:: Test Data Models
echo =============================
echo Building Test Data Models ...
echo =============================
call mvn -f %BUILD_FOLDER%/%USE_CASES_REPO_DIR_NAME%/pom.xml clean install -X > build-testdata-models-log.txt

:: MDL IDE core
echo ===========================
echo Building MDL IDE (core) ...
echo ===========================
call mvn -U -f %BUILD_FOLDER%/%DDMORE_CODE_REPO_DIR_NAME%/main/WP2/MDLEditor/releng/mdl-core/pom.xml clean install -X > build-mdl-core-log.txt

:: Check everything has worked!
%FINDCMD% "BUILD SUCCESS" build-mdl-core-log.txt
if not %ERRORLEVEL%==0 goto :failed

:: Converter Toolbox
echo ==============================
echo Building Converter Toolbox ...
echo ==============================

call mvn -U -f %BUILD_FOLDER%/%DDMORE_CODE_REPO_DIR_NAME%/main/WP2/converter-toolbox-aggregator/pom.xml clean install -X > build-converter-toolbox-aggregator-log.txt
:: Check everything has worked!
%FINDCMD% "BUILD SUCCESS" build-converter-toolbox-aggregator-log.txt
if not %ERRORLEVEL%==0 goto :failed

call mvn -U -f %BUILD_FOLDER%/%NMTRANCONVERTER_REPO_DIR_NAME%/pom.xml clean install -X > build-converter-nonmem-log.txt
%FINDCMD% "BUILD SUCCESS" build-converter-nonmem-log.txt
:: Check everything has worked!
if not %ERRORLEVEL%==0 goto :failed

call mvn -U -f %BUILD_FOLDER%/%DDMORE_CODE_REPO_DIR_NAME%/main/WP2/converter-toolbox-service-aggregator/pom.xml clean install -X > build-converter-toolbox-service-aggregator-log.txt
:: Check everything has worked!
%FINDCMD% "BUILD SUCCESS" build-converter-toolbox-service-aggregator-log.txt
if not %ERRORLEVEL%==0 goto :failed

:: MDL IDE complete
echo ===============================
echo Building MDL IDE (complete) ...
echo ===============================
call mvn -U -f %BUILD_FOLDER%/%DDMORE_CODE_REPO_DIR_NAME%/main/WP2/MDLEditor/releng/mdl-full/pom.xml clean verify -X > build-mdl-complete-log.txt
:: Check everything has worked!
%FINDCMD% "BUILD SUCCESS" build-mdl-complete-log.txt
if not %ERRORLEVEL%==0 goto :failed


:success
echo.
echo.
echo Success! Full build pipeline completed successfully.
goto :end

:failed
echo.
echo.
echo Full build pipeline FAILED! See the build log files.
goto :end

:end
pause
