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
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        DBConnector.initiateConnection();
//        ResultSet a = DBConnector.executeQuery("SELECT * FROM users");
//        try {
//            if (a.next()) {
//                System.out.println(a.getString(1));
//            }   } catch (SQLException ex) {
//            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//        }

        new Login(null,true).show();

    
    

    }
    
}