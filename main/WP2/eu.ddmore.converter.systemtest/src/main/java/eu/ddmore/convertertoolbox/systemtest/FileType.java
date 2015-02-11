package eu.ddmore.convertertoolbox.systemtest;


public enum FileType {
    MDL("mdl","6.0.7"),
    PHARMML("xml","0.3.1"),
    NMTRAN("ctl","7.2.0"),
    JSON("json","6.0.7");
    
    private String version;
    private String extension;
    
    FileType(String extension, String version){
        this.extension = extension;
        this.version = version;
    }

    public String getExtension(){
        return this.extension;
    }
    
    public String getVersion(){
        return this.version;
    }
}
