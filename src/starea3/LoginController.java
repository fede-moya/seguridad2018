package starea3;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {

    private LoginForm view;
    
    public LoginController(LoginForm view) {
        this.view = view;
    }
    
    public LoginForm getView(){
        return view;
    }
       
    public boolean logIn(String email, String password) throws SQLException {
        DBConnector.initiateConnection();
        ResultSet resultQuery = DBConnector.executeQuery("SELECT * FROM users WHERE email='"+email+"'");
        if (resultQuery.next()) {
            if ( !resultQuery.getString(3).equals(Encrypt.digestSHA1(password)) ){
                getView().getjLabel3().setVisible(false);
                getView().getjLabel4().setVisible(true);
                getView().getjLabel4().setText("Invalid password");
                return false;
            }
            return true;
        }else{
            getView().getjLabel3().setVisible(true);
            getView().getjLabel4().setVisible(false);
            getView().getjLabel3().setText("There is no user with that email");
            return false;
        }
    }
    
    
    public void displaySignUpForm(){
        getView().setVisible(false);
        SignUpForm signUp = new SignUpForm();
        signUp.setLocationRelativeTo(view);
        signUp.toFront();
        signUp.requestFocus();
        signUp.setVisible(true);
        signUp.show();
    }
}
