package starea3;

import java.sql.SQLException;

public class SignUpFormController {
    private final SignUpForm view;

    public SignUpFormController(SignUpForm view) {
        this.view = view;
    }
    
    public SignUpForm getView(){
        return view;
    }
    
    public void signUpSQL(String name, String email, String password) throws SQLException{
        String insertQuery = "INSERT INTO users VALUES ('" + email +"','"+name +"','"+password+"')";
        DBConnector.initiateConnection();
        DBConnector.executeUpdate(insertQuery);
    }
    
    public void signUp(String name, String email, String password) throws SQLException{
        String encryptedPassword = Encrypt.digestSHA1(password);
        signUpSQL(name, email, encryptedPassword);
    }
    
    public void clearErrorLables(){
        getView().getDbErrorLabel().setText("");
        getView().getEmailErrorLabel().setText("");
        getView().getPasswordErrorLabel().setText("");
        getView().getPasswordConfirmationErrorLabel().setText("");
        getView().getNameErrorLabel().setText("");
    }
    
    public String evaluatePasswordQuality(){
        String digestedPassword = Encrypt.digestSHA1(getView().getPasswordField().getText());
        String passwordQualityMessage = PwnedValidator.evaluatePassword(digestedPassword);
        return passwordQualityMessage;
    }
    
    public boolean validate(){
        boolean valid = true;
        clearErrorLables();
        
        if ("".equals(getView().getNameField().getText())){
            getView().getNameErrorLabel().setText("Can't be blank");
            valid = false;
        }
        
        if ("".equals(getView().getEmailField().getText())){
            getView().getEmailErrorLabel().setText("Can't be blank");
            valid = false;
        }
        
        if ("".equals(getView().getPasswordField().getText())){
            getView().getPasswordErrorLabel().setText("Can't be blank");
            valid = false;
        }
        
        if ("".equals(getView().getPasswordConfirmationField().getText())){
            getView().getPasswordConfirmationErrorLabel().setText("Can't be blank");
            valid = false;
        }
        
        if (!(getView().getPasswordField().getText().equals(getView().getPasswordConfirmationField().getText()))){
            getView().getPasswordErrorLabel().setText("Passwords don't match");
            valid = false;
        }
        
        return valid;
    }
    
    public void onEvaluatePassword(){
        if (validate()){
            getView().getDbErrorLabel().setText(evaluatePasswordQuality());
        }
    }
    
    public void onConfirm(){
        if (validate()){
            try {
                signUp(getView().getNameField().getText(), getView().getEmailField().getText(), getView().getPasswordField().getText());
                MainMenu.displayMainMenu(getView());
            } catch (SQLException ex) {
                getView().getDbErrorLabel().setText(ex.getMessage());
            }
        }
    }
    
    
    
}
