package starea3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;


public class VerifySignatureFormController {
    private final VerifySignatureForm view;

    public VerifySignatureFormController(VerifySignatureForm view) {
        this.view = view;
    }

    public VerifySignatureForm getView() {
        return view;
    }
    
    public void clearErrorLabel(){
        getView().getErrorLabel().setText((""));
    }
    public void clearInfoLabel(){
        getView().getInfoLabel().setText((""));
    }
    
    public String getDataFilePath(){
        return getView().getDataFileTextField().getText();
    }
    
    public String getPublicKeyFilePath(){
        return getView().getPublicKeyTextField().getText();
    }
    
    public String getSignatureFilePath(){
        return getView().getSignatureTextField().getText();
    }
    
    public boolean validateForm(){
        
        if ("".equals(getDataFilePath())){
            getView().getErrorLabel().setText("None of the fields can be blank");
            return false;
        }
        
        if ("".equals(getPublicKeyFilePath())){
            getView().getErrorLabel().setText("None of the fields can be blank");
            return false;
        }
        
        if ("".equals(getSignatureFilePath())){
            getView().getErrorLabel().setText("None of the fields can be blank");
            return false;
        }
        
        return true;
    }
    
    public void setErrorLabel(String message){
        getView().getErrorLabel().setText(message);
    }
    
    public void setInfoLabel(String message){
        getView().getInfoLabel().setText(message);
    }
    
    public void informVerificationResult(boolean result){
        if(result){
            setInfoLabel("The signature is valid");
        }else{
            setInfoLabel("The signature is not valid");
        }
    }
    
    public void verify(String dataFilePath, String publicKeyFilePath, String signatureFilePath) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, InvalidKeyException, SignatureException{
         /* import encoded public key */

        FileInputStream keyfis = new FileInputStream(publicKeyFilePath);
        byte[] encKey = new byte[keyfis.available()];  
        keyfis.read(encKey);

        keyfis.close();

        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);

        KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

        /* input the signature bytes */
        FileInputStream sigfis = new FileInputStream(signatureFilePath);
        byte[] sigToVerify = new byte[sigfis.available()]; 
        sigfis.read(sigToVerify );

        sigfis.close();

        /* create a Signature object and initialize it with the public key */
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(pubKey);

        /* Update and verify the data */

        FileInputStream datafis = new FileInputStream(dataFilePath);
        BufferedInputStream bufin = new BufferedInputStream(datafis);

        byte[] buffer = new byte[1024];
        int len;
        while (bufin.available() != 0) {
            len = bufin.read(buffer);
            sig.update(buffer, 0, len);
            };

        bufin.close();
        
        boolean verifies = sig.verify(sigToVerify);
        informVerificationResult(verifies);
    }
    
    public void onVerify(){
        if(validateForm()){
            try {
                verify(getDataFilePath(), getPublicKeyFilePath(), getSignatureFilePath());
            } catch (Exception ex) {
                setErrorLabel(ex.getMessage());
            }
        }
    }
    
}
