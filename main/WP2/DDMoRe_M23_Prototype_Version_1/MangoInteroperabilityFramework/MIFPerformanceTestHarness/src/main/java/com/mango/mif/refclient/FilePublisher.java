package com.mango.mif.refclient;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mango.mif.core.api.ResourceUtils;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.core.resource.UserSharedLocationManager;
import com.mango.mif.core.resource.fileutils.UUIDResourcePublisher;
import com.mango.mif.domain.ExecutionType;

public class FilePublisher {
    
    private static final Logger LOG = Logger.getLogger(FilePublisher.class);
    
    private UserSharedLocationManager slm;
    
    public FilePublisher(String sharedLocation) {
    
        slm = new UserSharedLocationManager();        
        File sharedFileLocation = new File(sharedLocation);
        
        if (!sharedFileLocation.canWrite()) {
        	System.err.println("Not able to write to " + sharedFileLocation.getAbsolutePath());
        	System.exit(-1);
        }
        
        if (!sharedFileLocation.canRead()) {
        	System.err.println("Not able to read to " + sharedFileLocation.getAbsolutePath());
        	System.exit(-1);
        }
        
		slm.setWorkspaceManager(new DefaultWorkspaceManager(sharedFileLocation));    
        
    }    
    
    public void publishDataResourceFiles(ExecutionType execType, UUID uuid) throws MalformedURLException {            	     
    	
    	switch (execType) {
    	
	    	case PsN_EXEC: {
	    		
	    		File directory = new File(this.getClass().getResource("psnexec").getPath());
	    		publish(directory, uuid);
	    		break;
	    	}
	    	
	    	case NMFE: {
	    		
	    		File directory = new File(this.getClass().getResource("nmfe").getPath());
	    		publish(directory, uuid);
	    		break;
	    	}
	    	
	    	case PsN_SCM: {
	    		File directory = new File(this.getClass().getResource("scm").getPath());
	    		publish(directory, uuid);
	    		break;
	    	}
	    	
	    	case PsN_VPC: {
	    		File directory = new File(this.getClass().getResource("vpc").getPath());
	    		publish(directory, uuid);
	    		break;
	    	}
	    	
	    	case PsN_Bootstrap: {
	    		File directory = new File(this.getClass().getResource("bootstrap").getPath());	    		
	    		publish(directory, uuid);
	    		break;
	    	}
	    	
		default:
			LOG.error("Can't publish files for exec type of " + execType.name());
			break;
    	}    	       
    }

	private void publish(File source, UUID uuid) {
		
		Preconditions.checkArgument(source.exists() && source.canRead(), "Could not read from source file " + source.getAbsolutePath());
		
		List<File> files = ResourceUtils.listFiles(source, new FilenameFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        });
        
        Preconditions.checkArgument(files.size() > 0);
        
        UUIDResourcePublisher publisher = new UUIDResourcePublisher();
        publisher.setSharedLocationManager(slm);                           
                
        File root = files.get(0).getParentFile();
        
        for (File f: files) {
            publisher.addFile(f);
            LOG.info(f.getAbsolutePath() + " ready to publish");            
        }        
        publisher.setRootDirectory(root);
        
        LOG.info("Publishing " + files.size() + " files to " + slm.getSharedLocationRoot().getAbsolutePath() + "/" + uuid);
        publisher.publish(uuid);
	}
    
    public List<File> getScriptFiles(File dataDirectory) {
        
        // Publish script files
        Iterable<File> scriptFiles = Iterables.filter(Arrays.asList(dataDirectory.listFiles()), new Predicate<File> () {

            @Override
            public boolean apply(File file) {
                return file.getName().toLowerCase().endsWith(".ctl");
            }
        });
        return Lists.newArrayList(scriptFiles);
    }
}
