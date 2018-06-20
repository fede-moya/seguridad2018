package starea3;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;


public class EncryptFileFormController {
    EncryptFileForm view;

    public EncryptFileFormController(EncryptFileForm view) {
        this.view = view;
    }
    
    public EncryptFileForm getView(){
        return view;
    }
    
    public void clearInputFields(){
        getView().getAttachFileField().setText("");
        getView().getKeyField().setText("");
    }
    
    public void clearErrorLabels(){
        getView().getGeneralErrorLabel().setText("");
        getView().getKeyFieldErrorLabel().setText("");
    }
    
    public boolean validate(){
        clearErrorLabels();
        
        if("".equals(getView().getAttachFileField().getText())){
            getView().getGeneralErrorLabel().setText("No file to do the encoding");
            return false;
        }
        
        if("".equals(getView().getKeyField().getText())){
            getView().getKeyFieldErrorLabel().setText("Can't be blank");
            return false;
        }
        
        return true;
    }
    
    public void encryptFile(){
        if(validate()){
            try {
                Encrypt.encryptFile(getView().getKeyField().getText(), getView().getAttachFileField().getText());
                JOptionPane.showMessageDialog(null, "Successful encoding!",null, JOptionPane.INFORMATION_MESSAGE);
                clearInputFields();
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | IOException | IllegalBlockSizeException | BadPaddingException ex) {
                getView().getGeneralErrorLabel().setText(ex.getMessage());
            } catch (java.security.InvalidKeyException ex) {
                getView().getKeyFieldErrorLabel().setText(ex.getMessage());
            }
        }
    }
}
