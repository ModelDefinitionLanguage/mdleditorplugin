package com.mango.mif.core.resource;

import java.io.File;
import java.util.List;

import com.mango.mif.core.exec.CommandBuilder;
import com.mango.mif.core.exec.Invoker;

/**
 * The Class ResourceCopierValueObject.
 * This acts as a Object that is passed for the creation of the Resource Copier.
 */
public class ResourceCopierParameters {
	/**
	 * Destination directory where the result files should be copied into
	 */
	protected File destDirectory;

	/**
	 * Destination directory where the result files should be copied into
	 */
	protected File sourceDirectory;
	
	
	/** The Regular Expression pattern to ignore file list. */
	private List<String> fileRegexPatternIgnoreList;
	

	/** The Directory ignore list. */
	private List<String> directoryIgnoreList;
	
	/** The command builder. */
	private CommandBuilder commandBuilder;
	
	/** The invoker to execute the command. */
	Invoker invoker;
    private File filesListing;
	
	public File getDestDirectory() {
		return destDirectory;
	}

	public void setDestDirectory(File destDirectory) {
		this.destDirectory = destDirectory;
	}

	public File getSourceDirectory() {
		return sourceDirectory;
	}

	public void setSourceDirectory(File sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	}

	public CommandBuilder getCommandBuilder() {
		return commandBuilder;
	}

	public void setCommandBuilder(CommandBuilder commandBuilder) {
		this.commandBuilder = commandBuilder;
	}

	public Invoker getInvoker() {
		return invoker;
	}

	public void setInvoker(Invoker invoker) {
		this.invoker = invoker;
	}


	public List<String> getFileRegexPatternIgnoreList() {
		return fileRegexPatternIgnoreList;
	}

	public void setFileRegexPatternIgnoreList(
			List<String> fileRegexPatternIgnoreList) {
		this.fileRegexPatternIgnoreList = fileRegexPatternIgnoreList;
	}

	public List<String> getDirectoryIgnoreList() {
		return directoryIgnoreList;
	}

	public void setDirectoryIgnoreList(List<String> directoryIgnoreList) {
		this.directoryIgnoreList = directoryIgnoreList;
	}

    public void setFilesListing(File filesListing) {
        this.filesListing = filesListing;
    }
    
    public File getFilesListing() {
        return filesListing;
    }
}
