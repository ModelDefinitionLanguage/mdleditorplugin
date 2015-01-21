@echo off

SET HOME=%~dp0
SET CURRENT_DIR=%cd%

set TRAINING_JAVA_HOME=%SERVICE_HOME%\..\MDL_IDE\jre

IF EXIST "%TRAINING_JAVA_HOME%\bin\java.exe" (
    echo Using Java from SEE
    SET JAVA_CMD="%TRAINING_JAVA_HOME%\bin\java"
) ELSE IF EXIST "%JAVA_HOME%\bin\java.exe" (
    echo Using Java from JAVA_HOME environment variable
    SET JAVA_CMD="%JAVA_HOME%\bin\java"
) ELSE (
    echo Falling beck to using Java from path; it will fail if Java is not installed
    SET JAVA_CMD=java
)

REM Extending the classpath with location of directory holding converters and their dependencies
cd %HOME%
pushd..
SET PARENT_DIR=%cd%
popd
SET CONVERTER_LIBS="%PARENT_DIR%/lib/*"
cd %CURRENT_DIR%


%JAVA_CMD% -cp "%HOME%/lib/*;%CONVERTER_LIBS%" eu.ddmore.convertertoolbox.cli.Main -in %1 -out %2 -sn %3 -sv %4 -tn %5 -tv %6
