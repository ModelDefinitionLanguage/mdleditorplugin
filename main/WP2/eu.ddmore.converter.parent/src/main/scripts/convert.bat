@echo off

SET home=%~dp0

if "%PROCESSOR_ARCHITECTURE%" == "x86" ( 
    if not defined PROCESSOR_ARCHITEW6432 (
		set TRAINING_JAVA_HOME=%SERVICE_HOME%\..\MDL_IDE\x86\jre
	) else (
		set TRAINING_JAVA_HOME=%SERVICE_HOME%\..\MDL_IDE\x86_64\jre
	)
) else (
	set TRAINING_JAVA_HOME=%SERVICE_HOME%\..\MDL_IDE\x86_64\jre
)

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