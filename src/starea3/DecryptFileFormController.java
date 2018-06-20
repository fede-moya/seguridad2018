package starea3;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;


public class DecryptFileFormController {
    private DecryptFileForm view;

    public DecryptFileFormController(DecryptFileForm view) {
        this.view = view;
    }
    
    public DecryptFileForm getView(){
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
        
        if("".equals(getView().getKeyField().getText())){
            getView().getKeyFieldErrorLabel().setText("Can't be blank");
            return false;
        }
        
        if("".equals(getView().getAttachFileField().getText())){
            getView().getGeneralErrorLabel().setText("No file to do the decoding ");
            return false;
        }
        
        return true;
    }
    
    public void onDecrypt(){
        if(validate())
        {
            decryptFile();
        }
    }
    
    public void decryptFile(){
        try {
            Encrypt.decryptFile(getView().getKeyField().getText(), getView().getAttachFileField().getText());
            clearInputFields();
            JOptionPane.showMessageDialog(null, "Successful decoding!",null, JOptionPane.INFORMATION_MESSAGE);
        } catch (java.security.InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IOException | IllegalBlockSizeException | BadPaddingException ex ) {
            getView().getGeneralErrorLabel().setText(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Unsuccessful decoding!",null, JOptionPane.INFORMATION_MESSAGE);
        } 
    }
    
}
