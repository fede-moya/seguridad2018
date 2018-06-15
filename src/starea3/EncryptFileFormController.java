/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starea3;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author federico
 */
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
    
    public void goToMenu() {
        MainMenu mm = new MainMenu();
        mm.setLocationRelativeTo(view);
        mm.setVisible(true);
        this.view.dispose();
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
