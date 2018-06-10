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
public class DecryptFileFormController {
    private DecryptFileForm view;

    public DecryptFileFormController(DecryptFileForm view) {
        this.view = view;
    }
    
     public boolean validate(){
        return true;
    }
    
    public void decryptFile(){
        if(validate()){
            Encrypt.decryptFile(view.getKeyField().getText(), view.getAttachFileField().getText());
        }
    }
    
}
