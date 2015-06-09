@echo off

SET HOME=%~dp0
SET CURRENT_DIR=%cd%

REM Extending the classpath with location of directory holding converters and their dependencies
cd "%HOME%"
pushd..
SET PARENT_DIR=%cd%
popd
SET CONVERTER_LIBS="%PARENT_DIR%/lib/*"
cd "%CURRENT_DIR%"

java.exe -cp "%HOME%/lib/*;%CONVERTER_LIBS%" eu.ddmore.convertertoolbox.cli.Main -in %1 -out %2 -sn %3 -sv %4 -tn %5 -tv %6
