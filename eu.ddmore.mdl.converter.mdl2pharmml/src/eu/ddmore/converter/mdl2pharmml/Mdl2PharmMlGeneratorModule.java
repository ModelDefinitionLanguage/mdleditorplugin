package eu.ddmore.converter.mdl2pharmml;

import org.eclipse.xtext.service.AbstractGenericModule;

import eu.ddmore.mdl.generator.MdlGenerator;

public class Mdl2PharmMlGeneratorModule extends AbstractGenericModule {
	public Class<? extends org.eclipse.xtext.generator.IGenerator> bindIGenerator() {
        return MdlGenerator.class;
    }
}
