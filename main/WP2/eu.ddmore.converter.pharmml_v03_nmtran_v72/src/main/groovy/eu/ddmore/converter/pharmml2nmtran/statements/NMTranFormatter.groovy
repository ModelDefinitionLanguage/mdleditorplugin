package eu.ddmore.converter.pharmml2nmtran.statements


public class NMTranFormatter {

    private static final String PREFIX = "NM_";
    
    public String pk(String pk) {
        return "\$PK\n " + pk;
    }
    public String error(String error) {
        return "\$ERROR\n " + error;
    }

    public String des(String des) {
        return "\$DES\n " + des;
    }

    public String model(String model) {
        return "\$MODEL\n " + model;
    }

    public String pred(String pred) {
        return "\$PRED\n " + pred;
    }

    public String indent(String text) {
        return "\t" + text;
    }

    public String endline(String text) {
        return text + "\n";
    }
    
    public String rename(String name) {
        if (!name.contains(PREFIX)) {
            return PREFIX + name;
        } else {
            return name;
        }
    }
}
