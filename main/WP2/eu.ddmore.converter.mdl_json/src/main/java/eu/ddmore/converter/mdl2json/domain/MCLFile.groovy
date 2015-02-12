package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.eclipse.emf.common.util.URI;

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.interfaces.TopLevelBlock;
import groovy.util.Expando;

public class MCLFile extends Expando {

    public MCLFile(Mcl mclFile) {

        for (MclObject mclObj : mclFile.getObjects()) {
            String name = mclObj.getObjectName().getName()
            // log println " found ${name}"
            if(mclObj.getDataObject()) {
                setProperty(name, new Data(mclObj.getDataObject()))
            } else if (mclObj.getModelObject()) {
                setProperty(name, new Model(mclObj.getModelObject()))
            } else if (mclObj.getParameterObject()) {
                setProperty(name, new Parameter(mclObj.getParameterObject()))
            } else if (mclObj.getTaskObject()) {
                setProperty(name, new Task(mclObj.getTaskObject()))
            } else if (mclObj.getMogObject()) {
                setProperty(name, new Mog(mclObj.getMogObject()))
            }
        }

    }

    public MCLFile(Object json) {
        // JSON is a list of Maps
        json.each {
            // "it" is a Map
            it.each { key, value ->
                // Each key/value pair is the object (e.g. ex_model7_prolactin_Jan2014_dat -> properties )
                String type = value.identifier
                switch (type) {
                    case Task.IDENTIFIER:
                        setProperty(key, new Task(value));
                        break;
                    case Model.IDENTIFIER:
                        setProperty(key, new Model(value));
                        break;
                    case Parameter.IDENTIFIER:
                        setProperty(key, new Parameter(value));
                        break;
                    case Data.IDENTIFIER:
                        setProperty(key, new Data(value));
                        break;
                    case Mog.IDENTIFIER:
                        setProperty(key, new Mog(value));
                        break;
                }
            }
        }
    }

    /**
     * Write out the contents of the MDL file, with the nice-to-have feature that the top-level
     * MDL blocks are written out in a sensible order.
     * <p>
     * @return The String content to be written out to the MDL file
     */
    public String toMDL() {
        StringBuffer sb = new StringBuffer()
        getProperties().sort{ Map.Entry<String, TopLevelBlock> me ->
            me.value.getPrintedOrder()
        }.each{ String key, MDLPrintable value ->
            if (key) {
                sb.append("\n${key} = ${value.toMDL()}")
            }
        }
        sb.toString()
    }

}
