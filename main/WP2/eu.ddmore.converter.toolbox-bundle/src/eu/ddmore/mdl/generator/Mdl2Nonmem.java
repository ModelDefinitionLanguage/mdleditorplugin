package eu.ddmore.mdl.generator;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

public class Mdl2Nonmem extends ConverterWrapper {
	
	public Mdl2Nonmem() {
	    //Version-language attributes are hand coded until I learn how the MDLEditor will interact with the user.
        LanguageVersion lang = new LanguageVersionImpl();
        lang.setLanguage("NMTRAN");
        Version version = new VersionImpl(7, 2, 0, null);
        lang.setVersion(version);
		setTargetLanguage(lang);
	}

}
