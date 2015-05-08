/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json

import groovy.json.JsonSlurper

import java.util.regex.Matcher

import org.apache.commons.io.FileUtils
import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger

import eu.ddmore.converter.mdl2json.domain.Data
import eu.ddmore.converter.mdl2json.domain.Model
import eu.ddmore.converter.mdl2json.domain.Mog
import eu.ddmore.converter.mdl2json.domain.Parameter
import eu.ddmore.converter.mdl2json.domain.Source
import eu.ddmore.converter.mdl2json.domain.Task
import static eu.ddmore.converter.mdl2json.interfaces.MDLPrintable.IDT

import org.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdlparse.MdlParser

import static org.junit.Assert.*

/**
 * The fields and methods below are used in testing a MDL->JSON->MDL pipeline.
 * Some of these are imported and used by the converter toolbox "systemtest" project.
 *
 */
class MdlTestUtils {

    private static Logger logger = Logger.getLogger(MdlTestUtils.class)

    public static List<String> allBlockNames = [
        Data.DECLARED_VARIABLES,
        Data.DATA_INPUT_VARIABLES,
        Data.DATA_DERIVED_VARIABLES,
        Data.SOURCE,
        Parameter.STRUCTURAL,
        Parameter.VARIABILITY,
        Model.COVARIATES,
        Model.VARIABILITY_LEVELS,
        Model.STRUCTURAL_PARAMETERS,
        Model.VARIABILITY_PARAMETERS,
        Model.RANDOM_VARIABLE_DEFINITION + /\(.+\)/, // note the regex matching for the parameters of the block name
        Model.INDIVIDUAL_VARIABLES,
        Model.MODEL_PREDICTION,
        Model.OBSERVATION,
        Model.GROUP_VARIABLES,
        Task.ESTIMATE,
        Task.SIMULATE,
        Task.EVALUATE,
        Task.OPTIMISE,
        Task.DATA,
        Task.MODEL,
        Mog.OBJECTS,
        Mog.MAPPING
    ]
	
    public static extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(final File origMdlFile, final String blockName, final File newMdlFile) {
        extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(origMdlFile, blockName, FileUtils.readFileToString(newMdlFile))
    }

    public static extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(final File origMdlFile, final String blockName, final String newMdlFileContent) {
        def String origMdlFileContent = readInAndStripComments(origMdlFile)

        def String origMdlFileBlockContent = putParameterListsIntoKnownOrder(extractSpecificBlock(origMdlFileContent, blockName))
        def String newMdlFileBlockContent = putParameterListsIntoKnownOrder(extractSpecificBlock(newMdlFileContent, blockName))

        if (!StringUtils.isEmpty(origMdlFileBlockContent) || !StringUtils.isEmpty(newMdlFileBlockContent)) { // Check that we actually have something to compare
            logger.info("Verifying block " + blockName + "...")

            // Special additional preprocessing for the "SOURCE" block:
            // The items within this block can be in any order so put the lines of the original and new blocks into a known order
            if (blockName == Data.SOURCE) {
                origMdlFileBlockContent = putSOURCEBlockContentInKnownOrder(origMdlFileBlockContent)
                newMdlFileBlockContent = putSOURCEBlockContentInKnownOrder(newMdlFileBlockContent)
            }
            // Special additional preprocessing for the "mog" block:
            // The items within this block can be in any order so put the lines of the original and new blocks into a known order
            if (blockName.endsWith(Mog.IDENTIFIER)) {
                origMdlFileBlockContent = putMogBlockContentInKnownOrderAndRenameMogToKnownName(blockName, origMdlFileBlockContent)
                newMdlFileBlockContent = putMogBlockContentInKnownOrderAndRenameMogToKnownName(blockName, newMdlFileBlockContent)
            }

            // Trim off whitespace from both the expected and the actual
            assertEquals("Checking the content of the block " + blockName,
                origMdlFileBlockContent.replaceAll(~/\s*/, "") /* REDUNDANT: .replaceAll(~/if\((.+?)\)\{(.*?)\}/, /if($1)$2/) */ ,
                newMdlFileBlockContent.replaceAll(~/\s*/, "") /* REDUNDANT: .replaceAll(~/if\((.+?)\)\{(.*?)\}/, /if($1)$2/) */
            )

            // If the original MDL file had an empty block for this blockName then this is treated the same as if the block is absent
            if (origMdlFileBlockContent.equals(blockName + "{}")) {
                origMdlFileBlockContent = "";
            }

            assertEquals("Checking the content of the block " + blockName,
                // TODO: Remove the "OBSOLETE" commented out code below once we are confident that "if"
                // exprs/stmts won't reappear in the MDL, maybe appropriate towards end of Product 4
                origMdlFileBlockContent /* OBSOLETE?: .replaceAll(~/if\((.+?)\)\{(.*?)\}/, /if($1)$2/) */ ,
                newMdlFileBlockContent /* OBSOLETE?: .replaceAll(~/if\((.+?)\)\{(.*?)\}/, /if($1)$2/) */
            )
        }
    }

    private static String readInAndStripComments(final File origMdlFile) {
        final BufferedReader rdr = new BufferedReader(new FileReader(origMdlFile));
        final StringBuffer strBuf = new StringBuffer();
        rdr.eachLine() { String str ->
            removeCommentFromLineOfMDL(str, 0, strBuf)
        }
        strBuf.toString()
    }

    /**
     * Strip off hash-character-commencing comment, if present, from the input string;
     * but ignore hash characters appearing within quoted strings.
     * <p>
     * @param fullStr - the full line of text read in from the MDL file
     * @param fromIndex - the zero-based index in the string at which to start processing
     * @param strBuf - the string buffer to which to append the processed line of text
     */
    private static void removeCommentFromLineOfMDL(final String fullStr, final int fromIndex, final StringBuffer strBuf) {
        def hashCharPos = fullStr.indexOf("#", fromIndex)
        if (hashCharPos < 0) {
            strBuf.append(fullStr.substring(fromIndex))
            strBuf.append("\n")
        } else {
            // There is a # character somewhere in the line
            String subStr = fullStr.substring(fromIndex, hashCharPos)
            if (subStr.count("\"") % 2 == 1) {
                // Hash char is within a quoted string (may be multiple quoted strings on a single line)
                strBuf.append(subStr)
                def closingQuotePos = fullStr.indexOf("\"", hashCharPos)
                strBuf.append(fullStr.substring(hashCharPos, closingQuotePos + 1)) // Ensure we include the closing quote
                removeCommentFromLineOfMDL(fullStr, closingQuotePos + 1, strBuf) // Repeat until the end of the string is reached
            } else {
                // Hash char is not within a quoted string so is most likely starting a comment
                if (!subStr.matches(~/\s*$/)) { // Not just whitespace
                    strBuf.append(subStr)
                    strBuf.append("\n")
                }
            }
        }
    }

    private static String extractSpecificBlock(final String mdlFileContent, final String blockName) {
        final StringBuffer strBuf = new StringBuffer()

        def found = false;
        def interestedIn = false
        def nestingLevel = -1
        mdlFileContent.eachLine { str ->
            if (str.matches("^\\s*" + blockName + "\\s*\\{.*")) {
                found = true
                interestedIn = true
                // There might be open curly brackets before the name of the block name; the '-' sign on the RHS is deliberate
                nestingLevel = - StringUtils.countMatches(str.split(blockName)[0], "{")
            }
            if (interestedIn) {
                // Append this line to the string to be returned
                strBuf.append(str)
                strBuf.append("\n")
                // Update how deep in the { } nesting we are so we know when to escape eventually
                nestingLevel = nestingLevel + StringUtils.countMatches(str, "{")
                nestingLevel = nestingLevel - StringUtils.countMatches(str, "}")
            }
            if (nestingLevel <= 0) { // End of block we're interested in has been reached
                interestedIn = false
                nestingLevel = -1;
            }
        }

        if (!found) {
            logger.info("Block \"" + blockName + "\" was not found in the MDL")
        }

        strBuf.toString()
    }

    /**
     * Find all occurrences of lists of parameters (i.e. <code>use=covariate, type=continuous</code>
     * in the line of MDL <code>RATE=list(use=covariate,type=continuous)</code>), within the given
     * MDL fragment, and rewrite these lists to be alphabetically ordered by key in order to facilitate
     * comparison of the MDL fragment.
     * <p>
     * @param str - original fragment of MDL
     * @return fragment of MDL that is identical save for the ordering of aforementioned lists of parameters
     */
    private static String putParameterListsIntoKnownOrder(final String str) {

        // Some explanation of this regex needed!
        // The (?s) is the "dot-all" instruction to the matcher to match newline characters,
        // so parameter definitions spread over multiple lines will match.
        // The \{\s*(.+?)\s*\} is to match the list of attributes of the parameter, in the { }
        // brackets; the "?" after the ".+" instructs the matcher to lazily match rather than
        // greedily match, otherwise the matcher would keep going until it found the last closing
        // bracket rather than the one that matched the opening bracket.
        def outStr1 = sortParameterList(str, ( str =~ /(?s)[A-Za-z0-9]+\s*\:\s*\{\s*(.+?)\s*\}/ ))
        // This regex is almost the same as the previous one but is to match the list of 'complex
        // attributes' i.e. for a distribution parameter VAR ~ (...).
        def outStr2 = sortParameterList(outStr1, ( outStr1 =~ /(?s)[A-Za-z0-9]+\s*\~\s*\{\s*(.+?)\s*\}/ ))

        outStr2
    }

    private static sortParameterList(final String str, Matcher matcher) {
        def outStr = str

        while (matcher.find()) {
            final String paramsStr = matcher.group(1)
            String[] params = paramsStr.split(/\s*,\s*/)
            outStr = outStr.replace(paramsStr, params.sort().join(","))
        }

        outStr
    }

    /**
     * Special additional preprocessing for the "SOURCE" block.
     * The <code>name = value</code> pairs within this block can be in any order so put the lines
     * of the original and new blocks into a known order.
     * <p>
     * @param blockText - the string comprising the block name and its unordered content
     * @return the string comprising the block name and its reordered content
     */
    private static String putSOURCEBlockContentInKnownOrder(final String blockText) {
        putSOURCEBlockOrMogBlockContentInKnownOrder(Data.SOURCE, blockText, /\s*\S+\s*=\s*\S+\s*/)
    }

    /**
     * Special additional preprocessing for the "mog" top-level block:
     * <ol>
     * <li>The object identifiers within this block can be in any order so put the lines of the
     *     original and new blocks into a known order.
     * <li>The name of the MOG in the written out MDL will in general be different to the name
     *     of the MOG in the input MDL file, so replace the MOG name with a standard name
     *     "theMog" for the purposes of comparison of the block text.
     * </ol>
     * <p>
     * @param blockName - the name of the block, actually a regular expression
     * @param blockText - the string comprising the block name and its unordered content
     * @return the string comprising the block name and its reordered content
     */
    private static String putMogBlockContentInKnownOrderAndRenameMogToKnownName(final String blockName, final String blockText) {
        putSOURCEBlockOrMogBlockContentInKnownOrder(blockName, blockText, /\s*\S+\s*/).toString().replaceFirst(/\S+\s*=\s*mog\s*\{/, "theMog = mog {")
    }

    /**
     * Special additional preprocessing for the "SOURCE" block and the "mog" top-level block.
     * The items within these blocks can be in any order so put the lines of the original and new blocks into a known order.
     * <p>
     * @param blockName - the name of the block, actually a regular expression
     * @param blockText - the string comprising the block name and its unordered content
     * @param itemsMatcher - the regular expression to extract the individual attributes / items within the content of the block
     * @return the string comprising the block name and its reordered content
     */
    private static String putSOURCEBlockOrMogBlockContentInKnownOrder(final String blockName, final String blockText, final String itemsRegex) {

        // Similar regex behaviour to those in putParameterListsIntoKnownOrder()
        final Matcher outerMatcher = ( blockText =~ /(?s)/ + blockName + /\s*\{(\s*.+?\s*)\}/ )

        def outStr = blockText

        while (outerMatcher.find()) { // Should only be one match
            final String blockContent = outerMatcher.group(1)

            final Matcher itemsMatcher = ( blockContent =~ itemsRegex )

            outStr = outStr.replace(blockContent, "\n${IDT*2}" + itemsMatcher.collect{ attrStr -> attrStr.trim() }.sort().join("\n${IDT*2}") + "\n${IDT}" )
        }

        outStr
    }
}