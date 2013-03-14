MangoInteroperabilityFramework(MIF)
The project has been divided into maven multi-module structure

1)MIF Parent folder( MangoInteroperabilityFramework) - provide mvn eclipse:eclipse under this folder to create individual eclipse project
This pom acts as a parent that will have to be refer modules (also order in which they are built) that are artefacts which compose the MIF. This is also refers to MangoParentPom as its parent.
MIFClient: contains client related stuff for MIF (also contains the common code for the modules for now, which will have to be to be moved to separate module) 
MIFServer: Contains server related stuff for MIF

2) Please issue the following command from the (MIF parent) project folder “mvn eclipse:eclipse"
It will install the eclipse project for all three projects
You then include the projects in Eclipse. 
3) Running tests from the (MIFParent) 
1. R_HOME environment variable set on your machine:
simply run: 
mvn install 
The above command will install all the 3 artifacts into the local repository.
2. R_HOME is not set
you need to run the following command:
mvn install -P custom -DrHome="[R_HOME]" -DrScriptLocation="[Rscript executable location]"
3. On current jenkins setup execute:
mvn install -P jenkins

