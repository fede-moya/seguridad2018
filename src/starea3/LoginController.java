package starea3;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author federico
 */
public class LoginController {

    public Login view;
    
    public LoginController(Login view) {
        this.view = view;
    }
       
    public boolean logIn(String email, String password) throws SQLException {
        DBConnector.initiateConnection();
        ResultSet a = DBConnector.executeQuery("SELECT * FROM users");
        if (a.next()) {
            if ( !a.getString(1).equals(email) ){
                this.view.getjLabel3().setVisible(true);
                this.view.getjLabel4().setVisible(false);
                this.view.getjLabel3().setText("There is no user with that email");
                return false;
            }
            if ( !a.getString(3).equals(password) ){
                this.view.getjLabel3().setVisible(false);
                this.view.getjLabel4().setVisible(true);
                this.view.getjLabel4().setText("Invalid password");
                return false;
            }
            return true;
        }
        return false;
    }
    
    public void displayMainMenu(){
        view.dispose();
                MainMenu mm = new MainMenu();
                mm.setBounds(400,100,500,500);
                mm.setLocationRelativeTo(view);
                mm.show();
    }
}
