package eu.ddmore.converter.mdl2json.interfaces;

/**
 * Interface to be implemented by the top-level blocks in an MDL file: Data, Parameter, Model, Task, Mog.
 */
public interface TopLevelBlock {

    /**
     * @return an integer value indicating whereabouts in the MDL file this block is to printed;
     * 		   the lower the number the nearer to the top of the file it will appear
     */
    public int getPrintedOrder();

}
