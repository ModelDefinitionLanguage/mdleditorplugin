package eu.ddmore.mdl.generator;

import org.eclipse.xtext.generator.IGenerator;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

public class Mdl2PharmML extends ConverterWrapper implements IGenerator {

	public Mdl2PharmML() {
        //Version-language attributes are hand coded until I learn how the MDLEditor will interact with the user.
        LanguageVersion lang = new LanguageVersionImpl();
        lang.setLanguage("PharmML");
        Version version = new VersionImpl();
        version.setMajor(0);
        version.setMinor(2);
        version.setPatch(1);
        lang.setVersion(version);

        setTargetLanguage(lang);
	}

}