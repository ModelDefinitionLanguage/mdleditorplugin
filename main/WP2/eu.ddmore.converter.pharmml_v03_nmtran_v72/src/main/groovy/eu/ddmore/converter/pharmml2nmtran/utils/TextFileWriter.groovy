/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.utils;

public class TextFileWriter {

    def writeToFile(File targetFile, content) {
        targetFile.write(content,"UTF-8")
    }

    def appendToFile(File targetFile, content) {
        targetFile.append(content,"UTF-8")
    }
}
