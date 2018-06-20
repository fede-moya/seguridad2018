package starea3;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class DecryptFileFormController {
    private DecryptFileForm view;

    public DecryptFileFormController(DecryptFileForm view) {
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
    
    public void decryptFile(){
        if(validate()){
            
            try {
                Encrypt.decryptFile(view.getKeyField().getText(), view.getAttachFileField().getText());
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | IOException | IllegalBlockSizeException | BadPaddingException ex) {
                view.getGeneralErrorLabel().setText(ex.getMessage());
            } catch (java.security.InvalidKeyException ex) {
                view.getKeyFieldErrorLabel().setText(ex.getMessage());
            }
        }
    }
    
}
