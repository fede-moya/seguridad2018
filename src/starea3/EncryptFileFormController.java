package starea3;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class EncryptFileFormController {
    EncryptFileForm view;

    public EncryptFileFormController(EncryptFileForm view) {
        this.view = view;
    }
    
    public void clearErrorLabels(){
        view.getGeneralErrorLabel().setText("");
        view.getKeyFieldErrorLabel().setText("");
    }
    
    public boolean validate(){
        clearErrorLabels();
        return true;
    }
    
    public void encryptFile(){
        if(validate()){
            try {
                Encrypt.encryptFile(view.getKeyField().getText(), view.getAttachFileField().getText());
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | IOException | IllegalBlockSizeException | BadPaddingException ex) {
                view.getGeneralErrorLabel().setText(ex.getMessage());
            } catch (java.security.InvalidKeyException ex) {
                view.getKeyFieldErrorLabel().setText(ex.getMessage());
            }
        }
    }
}
