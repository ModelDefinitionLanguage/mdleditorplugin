/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Task

class JSONTaskObjectToMDLTest extends ConverterTestsParent  {

    private static Logger logger = Logger.getLogger(JSONTaskObjectToMDLTest.class)

    // Using slashy strings /.../ here so we don't have to escape anything other than forward slashes
    private static final String estimateBlockJson_UseCase16 =
        / {"ESTIMATE":"target=NMTRAN_CODE\ncov=true\nalgo=[\"FOCE\"]"} /
    private static final String estimateBlockJson_UseCase2 =
        / {"ESTIMATE":"target=MLXTRAN_CODE\nversion=\"4.3.2\"\nalgo=[\"SAEM\"]"} /

    @Test
    public void testEstimateBlock_ContainingUnquotedAttributeValue() {

        def json = getJson(estimateBlockJson_UseCase16)

        def taskObj = new Task(json)

        String expected = """taskobj {

    ESTIMATE {
        target=NMTRAN_CODE
        cov=true
        algo=["FOCE"]
    }

}
"""
        assertEquals(expected, taskObj.toMDL())
    }

    @Test
    public void testEstimateBlock_ContainingQuotedAttributeValue() {

        def json = getJson(estimateBlockJson_UseCase2)

        def taskObj = new Task(json)

        String expected = """taskobj {

    ESTIMATE {
        target=MLXTRAN_CODE
        version="4.3.2"
        algo=["SAEM"]
    }

}
"""
        assertEquals(expected, taskObj.toMDL())
    }

}