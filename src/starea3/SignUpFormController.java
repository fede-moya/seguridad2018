/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starea3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author federico
 */
public class SignUpFormController {
    private SignUpForm view;

    public SignUpFormController(SignUpForm view) {
        this.view = view;
    }
    
    public void signUpSQL(String name, String email, String password) throws SQLException{
        String insertQuery = "INSERT INTO users VALUES ('" + email +"','"+name +"','"+password+"')";
        DBConnector.initiateConnection();
        DBConnector.executeUpdate(insertQuery);
    }
    
    public void signUp(String name, String email, String password) throws SQLException{
        String encryptedPassword = Encrypt.digestSHA1(password);
        signUpSQL("foo",email,encryptedPassword);
    }
    
    public void clearErrorLables(){
        this.view.getDbErrorLabel().setText("");
        this.view.getEmailErrorLabel().setText("");
        this.view.getPasswordErrorLabel().setText("");
        this.view.getPasswordConfirmationErrorLabel().setText("");
    }
    
    public void evaluatePasswordQuality(){
        this.view.getPasswordField().getText();
        this.view.getPasswordErrorLabel().setText("Can't be blank");
    }
    
    public boolean validate(){
        boolean valid = true;
        clearErrorLables();
        evaluatePasswordQuality();
        
        
        if ("".equals(this.view.getEmailField().getText())){
            this.view.getEmailErrorLabel().setText("Can't be blank");
            valid = false;
        }
        
        if ("".equals(this.view.getPasswordField().getText())){
            this.view.getPasswordErrorLabel().setText("Can't be blank");
            valid = false;
        }
        
        if ("".equals(this.view.getPasswordConfirmationField().getText())){
            this.view.getPasswordConfirmationErrorLabel().setText("Can't be blank");
            valid = false;
        }
        return valid;
    }
    
    public void displayMainMenu(){
        view.dispose();
        MainMenu mm = new MainMenu();
        mm.setBounds(400,100,500,500);
        mm.setLocationRelativeTo(view);
        mm.show();
    }
    
    public void onConfirm(){
        if (this.validate()){
            try {
                signUp("foo",this.view.getEmailField().getText(),this.view.getPasswordField().getText());
                displayMainMenu();
            } catch (SQLException ex) {
                this.view.getDbErrorLabel().setText(ex.getMessage());
            }
        }
    }
    
    
    
}
