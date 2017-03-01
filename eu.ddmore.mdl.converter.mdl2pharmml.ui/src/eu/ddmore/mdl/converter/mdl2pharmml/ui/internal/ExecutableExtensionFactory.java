package eu.ddmore.mdl.converter.mdl2pharmml.ui.internal;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import eu.ddmore.mdl.converter.mdl2pharmml.ui.Activator;

public class ExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

    @Override
    protected Bundle getBundle() {
        return Activator.getInstance().getBundle();
    }
     
    @Override
    protected Injector getInjector() {
        return Activator.getInstance().getInjector();
    }

}
