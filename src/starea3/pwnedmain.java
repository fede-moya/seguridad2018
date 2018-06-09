/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starea3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;


/**
 *
 * @author ComputadoraCasa
 */
public class pwnedmain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String pwned = "https://api.pwnedpasswords.com/range/";
        String[] passwords = {"contraseña123", "27set1995", "Ax4rRt96.,aqsd123", "password", "abc"}; 
        String algorithm = "SHA-1";
        
        //for (int i = 0; i < passwords.length; i++) {
            byte[] bytes = passwords[0].getBytes(); 
            String hash = getHash(bytes, algorithm);
            String prefix = ""; //Need first five chars of the hashed text.
            String suffix = ""; //Need the others chars of the hashed text in order to compare.
            for (int j = 0; j < hash.length(); j++) {
                if (j <= 4) { //From 0 to 4 (First five chars).
                    prefix = prefix + hash.charAt(j);
                }
                else { //After the char number 4.
                    suffix = suffix + hash.charAt(j);
                } 
                    
            }
            String url = pwned + prefix; 
            System.out.println("PASSWORD -> " + passwords[0]);
            System.out.println("HASH: " + hash); //Password hashed.
            System.out.println("PREFIX: " + prefix); //First five characters of the hashed string. 
            System.out.println("SUFFIX: " + suffix); //After the five first characters of the hashed string.
            System.out.println(url); //URL used in the HTTP Request.
            System.out.println(executeGet(url, suffix));
            System.out.println("------");
        //}
       
    }
    
    public static String getHash(byte[] inputBytes, String algorithm) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(inputBytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error en el hasheado.");
        }
        return hashValue; 
    }
    
    public static String executeGet(String targetURL, String suffix) {
        HttpURLConnection connection = null;
        String USER_AGENT = "Mozilla/5.0";
        String result = null;
        
        try {
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = connection.getResponseCode();
            System.out.println("Sending 'GET' request to URL: " + url);
            System.out.println("Response Code: " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                String[] splitted = inputLine.split(":");
                    if (splitted[0].equals(suffix)) {
                        response.append(inputLine);
                    }
            }
            in.close();

            return result = response.toString();
            //System.out.println(response.toString());
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return result;
    }
    
}
