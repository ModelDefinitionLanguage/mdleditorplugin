@echo off

REM  This Windows Powershell command (built in to Windows 7) is merely a 'nice-to-have' that
REM  sets a large console buffer size in order that the entire output can be scrolled around.
powershell -command "&{$H=get-host;$W=$H.ui.rawui;$B=$W.buffersize;$B.height=2000;$W.buffersize=$B;}" <NUL 1>NUL 2>NUL

REM  Locations without trailing '\'
SET SERVICE_HOME=%~dp0
IF %SERVICE_HOME:~-1%==\ SET SERVICE_HOME=%SERVICE_HOME:~0,-1%

CD "%SERVICE_HOME%"

SET SERVICE_BINARY=${project.build.finalName}.${project.packaging}

REM Converter Toolbox Service requires absolute path of the additional classpath entries
REM if they reside outside its home directory
pushd..
SET CONVERTER_LIBS=%cd%
popd

SET CTS_LIBS_PATH="./lib,%CONVERTER_LIBS%/lib"

java.exe %JAVA_OPTS% -DAPP_HOME="%SERVICE_HOME%" -Dcts.workingDirectory="%SERVICE_HOME%\tmp" -Dloader.path=%CTS_LIBS_PATH% -jar %SERVICE_BINARY%

EXIT
