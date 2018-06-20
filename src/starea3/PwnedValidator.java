package starea3;

public class PwnedValidator {
    
    
    public static String evaluatePassword(String hashedPassword) {
        String prefix = "";
        String suffix = "";
        String evaluation = "";
        
        for (int j = 0; j < hashedPassword.length(); j++) {
            if (j <= 4) { //From 0 to 4 (First five chars).
                prefix = prefix + hashedPassword.charAt(j);
            }
            else { //After the char number 4.
                suffix = suffix + hashedPassword.charAt(j);
            }  
        }
        
        int response = HTTPClient.executeGet(prefix, suffix);
        
        if (response == -1) {
            evaluation = "Excelente";
        } else {
            if ((response >= 1) && (response <= 100)) {
                evaluation = "Buena";
            } else {
                if ((response > 100) && (response <= 1000)) {
                    evaluation = "Regular";
                } else {
                    if ((response > 1000) && (response <= 10000)) {
                        evaluation = "Mala";
                    } else {
                        evaluation = "Muy mala";
                    } 
                } 
            }
        }
        return evaluation;             
        
    }
    
    
    
 
}
