==================================================================
MangoInteroperabilityFramework(MIF)
==================================================================
The project has been divided into maven multi-module structure


===============================================================
Building
===============================================================
1. go to MangoInteroperabilityFramework directory
2.A. If the R_HOME environment variable set on your machine:
simply run: 
mvn install 
The above command will install all the 3 artifacts into the local repository.

2.B If R_HOME is not set
you need to run the following command:
mvn install -P custom -DrHome="[R_HOME]" -DrScriptLocation="[Rscript executable location]"



================================================================
Setting up for Eclipse
================================================================
0. DO NOT CHECK THE CODE OUT INTO AN EXISTING ECLIPSE WORKSPACE.
1. go to MangoInteroperabilityFramework directory
2. Run:
mvn install
3. Run:
mvn eclipse:eclipse

It will install the eclipse project for all sub modules, You then import the projects in Eclipse. 


===============================================================
Notes
===============================================================
- MIF Parent folder( MangoInteroperabilityFramework) - 
This pom acts as a parent that will have the modules under MIF, It determines the order in which the modules are built.This is also refers to MangoParentPom as its parent.
- MIFClient: 
contains client related code for MIF (also contains the common code for the modules for now, which will have to be to be moved to separate module) 
- MIFServer: 
Contains server related code for MIF


