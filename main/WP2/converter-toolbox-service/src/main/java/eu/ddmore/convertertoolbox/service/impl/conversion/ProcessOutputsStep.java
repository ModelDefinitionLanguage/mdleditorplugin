/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl.conversion;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import eu.ddmore.convertertoolbox.domain.internal.Conversion;
import eu.ddmore.convertertoolbox.service.impl.ConversionResourcesConvention;


/**
 * {@link ConversionStep} implementation responsible for archiving Conversion outputs 
 */
@Order(Integer.MAX_VALUE)
@Component
public class ProcessOutputsStep implements ConversionStep {
    private static final Logger LOG = Logger.getLogger(ProcessOutputsStep.class);
    @Override
    public void execute(ConversionContext conversionContext) {
        Preconditions.checkNotNull(conversionContext, "Conversion context was null");
        Preconditions.checkNotNull(conversionContext.getConversion(), "Conversion in Conversion Context was null");
        Conversion conversion = conversionContext.getConversion();
        File outputArchive = new File(conversion.getWorkingDirectory(),ConversionResourcesConvention.OUTPUTS_ARCHIVE_NAME);
        File inputDir = new File(conversion.getWorkingDirectory(),ConversionResourcesConvention.OUTPUTS_DIRECTORY_NAME);
        ZipArchiveOutputStream zipOutput = null;
        LOG.debug(String.format("Conversion [%s]: archiving %s to %s.",conversion.getId(),inputDir.getAbsolutePath(),outputArchive.getAbsolutePath()));
        try {
            outputArchive.createNewFile();
            zipOutput = new ZipArchiveOutputStream(outputArchive);
            addFilesToArchive(inputDir,"",zipOutput);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Could not create archive file %s.",outputArchive.getAbsolutePath()),e);
        } finally {
            if(zipOutput!=null) {
                try {
                    zipOutput.close();
                } catch (IOException e) {
                    throw new IllegalStateException(String.format("Could not create archive file %s.",outputArchive.getAbsolutePath()),e);
                    
                }
            }
        }
        conversion.setOutputArchive(outputArchive);
        conversion.setOutputFileSize(outputArchive.length());
    }
    
    private void addFilesToArchive(File root, String subDir, ZipArchiveOutputStream zipOutput) throws IOException {
        File dir = new File(root, subDir).getAbsoluteFile();
        if(dir.listFiles()==null) {
            return;
        }
        for(File f : dir.listFiles()) {
            if(f.getName().equals(".") || f.getName().equals("..")) {
                continue;
            }
            ZipArchiveEntry entry = new ZipArchiveEntry(f, new File(subDir, f.getName()).getPath());
            LOG.debug(String.format("Archiving %s to %s.",f.getAbsoluteFile(),entry.getName()));
            
            zipOutput.putArchiveEntry(entry);
            if(!f.isDirectory()) {
                BufferedInputStream bis = null;
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(f);
                    bis = new BufferedInputStream(fis);
                    byte[] buffer = new byte[1024];
                    
                    while(bis.read(buffer)>0) {
                            zipOutput.write(buffer);
                    }
                } catch (IOException e) {
                    throw new IllegalStateException(String.format("Could not add %s to archive",f),e);
                } finally {
                    if(bis!=null) {
                        IOUtils.closeQuietly(bis);
                    } else if(fis!=null) {
                            IOUtils.closeQuietly(fis);
                    }
                }
            }
            zipOutput.closeArchiveEntry();
            if(f.isDirectory()) {
                addFilesToArchive(root, new File(subDir, f.getName()).getPath(),zipOutput);
            }
        }
    }

}
