package starea3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

public class SignFileFormController {
    private final SignFileForm view;

    public SignFileFormController(SignFileForm view) {
        this.view = view;
    }
    
    public void clearErrorLabel(){
        getView().getErrorLabel().setText("");
    }
    
    public boolean validateForm(){
        clearErrorLabel();
        
        if ("".equals(getView().getAttachFileText().getText())){
            getView().getErrorLabel().setText("File can't be blank");
            return false;
        }
        return true;
    }
    
    public String getAttachedFilePath(){
        return getView().getAttachFileText().getText();
    }
    
    public void goToMenu(){
        view.dispose();
        MainMenu mm = new MainMenu();
        mm.setLocationRelativeTo(view);
        mm.show();
    }
    
    public void sign(String filePath) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, IOException, SignatureException{
        /* Generate a key pair */

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");

        keyGen.initialize(1024, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();


        /* Create a Signature object and initialize it with the private key */

        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 

        dsa.initSign(priv);

        /* Update and sign the data */

        FileInputStream fis = new FileInputStream(filePath);
        BufferedInputStream bufin = new BufferedInputStream(fis);
        byte[] buffer = new byte[1024];
        int len;
        while (bufin.available() != 0) {
            len = bufin.read(buffer);
            dsa.update(buffer, 0, len);
            };

        bufin.close();

        /* Now that all the data to be signed has been read in, 
                generate a signature for it */

        byte[] realSig = dsa.sign();


        /* Save the signature in a file */
        FileOutputStream sigfos = new FileOutputStream(filePath + "_signature");
        sigfos.write(realSig);

        sigfos.close();


        /* Save the public key in a file */
        byte[] key = pub.getEncoded();
        FileOutputStream keyfos = new FileOutputStream(filePath + "_public_key");
        keyfos.write(key);

        keyfos.close();
    
    }
    
    public void signFile(){
        if (validateForm()){
            try {
                sign(getAttachedFilePath());
                goToMenu();
            } catch (Exception ex) {
                getView().getErrorLabel().setText("Sorry, something went wrong");
            } 
        }
    }
    
    public SignFileForm getView() {
        return view;
    }

    
}
