package starea3;

import java.sql.*;

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
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/seguridad", "postgres","postgres");
            sentence = connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            
            System.out.println("Error trying to initiate connection with DB");
            System.out.println(ex);
        }
    }
    
    public static  ResultSet executeQuery(String query) throws SQLException{
        return sentence.executeQuery(query);
    }
    
    public static  int executeUpdate(String query) throws SQLException{
        return sentence.executeUpdate(query);
    }

}
