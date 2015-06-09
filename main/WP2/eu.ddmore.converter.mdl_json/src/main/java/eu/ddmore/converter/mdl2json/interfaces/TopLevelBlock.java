/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.interfaces;

/**
 * Interface to be implemented by the top-level blocks in an MDL file: Data, Parameter, Model, Task, Mog.
 */
public interface TopLevelBlock {

    /**
     * @return {@link TopLevelBlock.Identifier} enum value that identifies this top-level block;
     *         it is the string that tags the top-level block in the MDL file
     */
    Identifier getIdentifier();

    /**
     * Enumerates the top-level blocks in an MDL file. The values are the strings that tag the
     * top-level blocks in the MDL file.
     * <p>
     * The order in which the identifier names appear here is important in that this defines
     * the order in which the blocks will appear in a written out MDL file; the top-level blocks
     * are sorted into this order (using the {@link Enum#ordinal()) method on each enum value)
     * before being written out.
     */
    enum Identifier {
        dataobj,
        parobj,
        mdlobj,
        taskobj,
        mogobj // Must appear last in this list - see the JavaDoc comment
    }

}
