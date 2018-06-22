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
        String password = getView().getPasswordField().getText();
        clearErrorLables();
        
        if ("".equals(getView().getNameField().getText())){
            getView().getNameErrorLabel().setText("Can't be blank");
            valid = false;
        }
        
        if ("".equals(getView().getEmailField().getText())){
            getView().getEmailErrorLabel().setText("Can't be blank");
            valid = false;
        }
        
        if ("".equals(password)){
            getView().getPasswordErrorLabel().setText("Can't be blank");
            valid = false;
        }
        
        if ("".equals(getView().getPasswordConfirmationField().getText())){
            getView().getPasswordConfirmationErrorLabel().setText("Can't be blank");
            valid = false;
        }
        
        
        
        if (password.length()<8){
            valid =  false; 
            getView().getPasswordErrorLabel().setText("At least eight characters");
        } else {      
            char c;  
            int count = 1;   
            for (int i = 0; i < password.length() - 1; i++) {  
                c = password.charAt(i);  
                if (!Character.isLetterOrDigit(c)) {          
                    valid =  false; 
                    getView().getPasswordErrorLabel().setText("Must consist of only letters and digits");
                } else if (Character.isDigit(c)) {  
                    count++;  
                }  
            }
            if (count < 2) {     
                valid =  false; 
                getView().getPasswordErrorLabel().setText("Must conaint at least two digits");
            }     
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
