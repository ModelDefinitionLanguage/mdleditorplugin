@echo off

SET home=%~dp0

reg query "HKLM\Hardware\Description\System\CentralProcessor\0" | find /i "x86" > nul
if %errorlevel%==0 (
    set TRAINING_JAVA_HOME=%home%..\MDL_IDE\x86\jre
) else (
    set TRAINING_JAVA_HOME=%home%..\MDL_IDE\x86_64\jre
)

IF EXIST {%TRAINING_JAVA_HOME%\bin\java} (
    SET JAVA_CMD=%TRAINING_JAVA_HOME%\bin\java
) ELSE IF EXIST {%JAVA_HOME%\bin\java} (
    SET JAVA_CMD=%JAVA_HOME%\bin\java
) ELSE (
    SET JAVA_CMD=java
)

"%JAVA_CMD%" -cp "%home%/lib/*" eu.ddmore.convertertoolbox.cli.Main -in %1 -out %2 -sn %3 -sv %4 -tn %5 -tv %6
