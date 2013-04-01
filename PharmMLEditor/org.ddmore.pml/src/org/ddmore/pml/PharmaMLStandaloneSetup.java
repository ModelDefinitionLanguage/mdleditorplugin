
package org.ddmore.pml;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class PharmaMLStandaloneSetup extends PharmaMLStandaloneSetupGenerated{

	public static void doSetup() {
		new PharmaMLStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

