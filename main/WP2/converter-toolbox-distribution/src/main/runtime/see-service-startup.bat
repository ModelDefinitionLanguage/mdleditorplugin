@echo off

REM  This Windows Powershell command (built in to Windows 7) is merely a 'nice-to-have' that
REM  sets a large console buffer size in order that the entire output can be scrolled around.
powershell -command "&{$H=get-host;$W=$H.ui.rawui;$B=$W.buffersize;$B.height=2000;$W.buffersize=$B;}" <NUL 1>NUL 2>NUL

REM  Locations without trailing '\'
SET SERVICE_HOME=%~dp0
IF %SERVICE_HOME:~-1%==\ SET SERVICE_HOME=%SERVICE_HOME:~0,-1%

REM Skipping startup for now, SEE uses Converter Toolbox CLI
REM call %SERVICE_HOME%\converter-toolbox-service\startup.bat

EXIT
