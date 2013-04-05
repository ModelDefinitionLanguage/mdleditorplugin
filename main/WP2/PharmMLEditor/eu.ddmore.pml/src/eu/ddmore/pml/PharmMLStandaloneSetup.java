
package eu.ddmore.pml;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class PharmMLStandaloneSetup extends PharmMLStandaloneSetupGenerated{

	public static void doSetup() {
		new PharmMLStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

