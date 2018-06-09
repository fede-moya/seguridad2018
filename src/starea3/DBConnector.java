/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starea3;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author federico
 */
public class DBConnector {
    
    public static Connection connection = null;
    public static Statement sentence = null;
    public static ResultSet result = null;
    
    public static void closeConnection() {
        connection = null;
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex) {
                System.out.println("Error closing the connection");
                System.out.println(ex);
            }
        }
        if (sentence != null) {
            try {
                sentence.close();
            } catch (SQLException ex) {
                System.out.println("Error closing the connection");
                System.out.println(ex);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing the connection");
                System.out.println(ex);
            }
        }
        

    }

    public static void initiateConnection() {
        try {
            // Register driver
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/seguridad", "postgres","postgres");
            sentence = connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            
            System.out.println("Error trying to initiate connection with DB");
            System.out.println(ex);
        }
    }
    
    public static  ResultSet executeQuery(String query){
        try {
            return sentence.executeQuery(query);
        } catch (SQLException ex) {
            return null;
        }
    }

}
