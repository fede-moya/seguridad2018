/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starea3;

/**
 *
 * @author ComputadoraCasa
 */
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
            evaluation = "Esa contraseña no existía crack!";
        } else {
            if ((response >= 1) && (response <= 50)) {
                evaluation = "Pero sos el puto amo de las contraseñas";
            } else {
                if ((response > 50) && (response <= 300)) {
                    evaluation = "Ya hay unos cuantos pajeros que usan esa contraseña";
                } else {
                    if ((response > 300) && (response <= 500)) {
                        evaluation = "Tu contraseña es mas o menos lo mismo que poner 'fefito10'";
                    } else {
                        evaluation = "Sos una verga para elegir contraseñas";
                    } 
                } 
            }
        }
        return evaluation;             
        
    }
    
    
    
 
}
