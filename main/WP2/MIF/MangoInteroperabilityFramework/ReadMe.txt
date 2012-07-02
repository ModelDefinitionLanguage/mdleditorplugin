==================================================================
MangoInteroperabilityFramework(MIF)
==================================================================
The project has been divided into maven multi-module structure


==============================================================
Prerequisites
==============================================================
Mango Parent Pom needs to be installed
1.	Checkout the parent pom project from repository:
svn co http://svn.mango.local/svn/MangoRepository/MangoParentPom/

2.	Then install it into your local repository using this command 
“mvn install:install-file -DgroupId=com.mango.build -DartifactId=mango-parent-pom -Dpackaging=pom -Dversion=1.0 -Dfile=pom.xml”


===============================================================
Building
===============================================================
On windows:

Run:
mvn install 

from the MangoInteroperabilityFramework directory


===============================================================
Building on Linux
===============================================================
mvn install -P jenkins

===============================================================
Building on Linux with integration tests and deployment to local tomcat
===============================================================
1. Update:
MIFServer/local-dev-integration-tests.properties

2. Automatic deployment uses the tomcat manager, to which access is disabled by default, to enable it
update tomcat-users.xml in tomcat configuration directory and add similar lines:

<user username="jenkins-gui" password="jenkins" roles="manager-gui"/>
<user username="jenkins" password="jenkins" roles="manager-script"/>

3. you will need to update the local.dev.server in MIFServer/pom.xml 
with the password that you set in tomcat.

		<nightly.build.server>http://localhost:8080/manager</nightly.build.server>
		<nightly.build.server.username>jenkins</nightly.build.server.username>
		<nightly.build.server.password>jenkins</nightly.build.server.password>
		<local.dev.server>http://localhost:8080/manager</local.dev.server>
		<local.dev.server.username>jenkins</local.dev.server.username>
		<local.dev.server.password>jenkins</local.dev.server.password>

4. Run
mvn install -P local-dev-integration-tests



================================================================
Setting up for Eclipse
================================================================
1. go to MangoInteroperabilityFramework directory
2. Run:
mvn install
3. Run:
mvn eclipse:eclipse

It will install the eclipse project for all sub modules, You then include the projects in Eclipse. 


===============================================================
Notes
===============================================================
- MIF Parent folder( MangoInteroperabilityFramework) - 
This pom acts as a parent that will have the modules under MIF, It determines the order in which the modules are built.This is also refers to MangoParentPom as its parent.
- MIFClient: 
contains client related code for MIF (also contains the common code for the modules for now, which will have to be to be moved to separate module) 
- MIFServer: 
Contains server related code for MIF


