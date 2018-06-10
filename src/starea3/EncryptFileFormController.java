/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starea3;

/**
 *
 * @author federico
 */
public class EncryptFileFormController {
    EncryptFileForm view;

    public EncryptFileFormController(EncryptFileForm view) {
        this.view = view;
    }
    
    
    public boolean validate(){
        return true;
    }
    
    public void encryptFile(){
        if(validate()){
            Encrypt.encryptFile(view.getKeyField().getText(), view.getAttachFileField().getText());
        }
    }
}
