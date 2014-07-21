@echo off

SET home=%~dp0

set TRAINING_JAVA_HOME=%SERVICE_HOME%\..\MDL_IDE\jre

IF EXIST "%TRAINING_JAVA_HOME%\bin\java.exe" (
    echo Using Java from SEE
    SET JAVA_CMD=%TRAINING_JAVA_HOME%\bin\java
) ELSE IF EXIST "%JAVA_HOME%\bin\java.exe" (
    echo Using Java from JAVA_HOME environment variable
    SET JAVA_CMD=%JAVA_HOME%\bin\java
) ELSE (
    echo Falling beck to using Java from path; it will fail if Java is not installed
    SET JAVA_CMD=java
)

"%JAVA_CMD%" -cp "%home%/lib/*" eu.ddmore.convertertoolbox.cli.Main -in %1 -out %2 -sn %3 -sv %4 -tn %5 -tv %6
