/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starea3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author ComputadoraCasa
 */
public class HTTPClient {
    
    private static String BASIC_URL = "https://api.pwnedpasswords.com/range/";
    private static String USER_AGENT = "Mozilla/5.0";
    
    public static int executeGet(String prefix, String suffix) {
        HttpURLConnection connection = null;
        int result = -1;
        String target = BASIC_URL + prefix;
        
        try {
            URL url = new URL(target);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = connection.getResponseCode();
            System.out.println("Sending 'GET' request to URL: " + url);
            System.out.println("Response Code: " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                String[] splitted = inputLine.split(":");
                    if (splitted[0].equals(suffix)) {
                        result = Integer.parseInt(splitted[1]);
                    }
            }
            in.close();          
            
            return result;
        }
        catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return result;
        
    }
    
}
