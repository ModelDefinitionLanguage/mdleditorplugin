package eu.ddmore.mdl.converter.mdl2pharmml.ui;

import org.apache.log4j.Logger;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.util.Modules2;
import org.osgi.framework.BundleContext;

import com.google.inject.Guice;
import com.google.inject.Injector;

import eu.ddmore.converter.mdl2pharmml.Mdl2PharmMlGeneratorModule;
import eu.ddmore.mdl.MdlRuntimeModule;
import eu.ddmore.mdl.ui.MdlUiModule;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.ddmore.mdl.comverter.mdl2pharmml.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator INSTANCE;
	private Injector injector;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}
	
	public Injector getInjector(){
		return this.injector;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
        try {
            injector = Guice.createInjector(Modules2.mixin(new MdlRuntimeModule(), new SharedStateModule(), new MdlUiModule(this), new MdlRuntimeModule(), new Mdl2PharmMlGeneratorModule(), new Mdl2PharmMlGeneratorUiModule(this)));
        } catch (Exception e) {
            Logger.getLogger(getClass()).error(e.getMessage(), e);
            throw e;
        }
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		INSTANCE = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getInstance() {
		return INSTANCE;
	}

}
