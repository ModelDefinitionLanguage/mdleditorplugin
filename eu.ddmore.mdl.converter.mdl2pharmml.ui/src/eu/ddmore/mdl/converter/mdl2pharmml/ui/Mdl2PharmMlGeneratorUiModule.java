package eu.ddmore.mdl.converter.mdl2pharmml.ui;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.service.AbstractGenericModule;

import com.google.inject.Binder;

public class Mdl2PharmMlGeneratorUiModule extends AbstractGenericModule {

    private final AbstractUIPlugin plugin;
    
    public Mdl2PharmMlGeneratorUiModule (AbstractUIPlugin plugin) {
        this.plugin = plugin;
    }
 
    @Override
    public void configure(Binder binder) {
        super.configure(binder);
        binder.bind(AbstractUIPlugin.class).toInstance(plugin);
        binder.bind(IDialogSettings.class).toInstance(plugin.getDialogSettings());
    }
    /**
     * Bind the JavaProjectBasedBuilderParticipant in order to invoke the generator during the build.
     */
    public Class<? extends org.eclipse.xtext.builder.IXtextBuilderParticipant> bindIXtextBuilderParticipant() {
        return org.eclipse.xtext.builder.JavaProjectBasedBuilderParticipant.class;
    }
 
}
