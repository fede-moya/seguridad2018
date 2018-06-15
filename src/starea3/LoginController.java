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

    public LoginForm view;
    
    public LoginController(LoginForm view) {
        this.view = view;
    }
       
    public boolean logIn(String email, String password) throws SQLException {
        DBConnector.initiateConnection();
        ResultSet resultQuery = DBConnector.executeQuery("SELECT * FROM users WHERE email='"+email+"'");
        if (resultQuery.next()) {
            if ( !resultQuery.getString(3).equals(Encrypt.digestSHA1(password)) ){
                this.view.getjLabel3().setVisible(false);
                this.view.getjLabel4().setVisible(true);
                this.view.getjLabel4().setText("Invalid password");
                return false;
            }
            return true;
        }else{
            this.view.getjLabel3().setVisible(true);
            this.view.getjLabel4().setVisible(false);
            this.view.getjLabel3().setText("There is no user with that email");
            return false;
        }
    }
    
    public void displayMainMenu(){
        view.dispose();
        MainMenu mm = new MainMenu();
        //mm.setSize(344, 50);
        mm.setLocationRelativeTo(view);
        mm.show();
    }
    
    public void displaySignUpForm(){
        view.setVisible(false);
        SignUpForm signUp = new SignUpForm();
        signUp.setLocationRelativeTo(view);
        signUp.toFront();
        signUp.requestFocus();
        signUp.setVisible(true);
        signUp.show();
    }
}
