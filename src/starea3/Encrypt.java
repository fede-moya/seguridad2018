/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starea3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author federico
 */
public class Encrypt {
    
    
    private static String getHash(byte[] inputBytes) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(inputBytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        return hashValue; 
    }
    
    public static String digestSHA1(String message){
        return getHash(message.getBytes(StandardCharsets.UTF_8));
    }
    
    public static void fileProcessor(int cipherMode,String key,File inputFile,File outputFile){
	 try {
	       Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
	       Cipher cipher = Cipher.getInstance("AES");
	       cipher.init(cipherMode, secretKey);

	       FileInputStream inputStream = new FileInputStream(inputFile);
	       byte[] inputBytes = new byte[(int) inputFile.length()];
	       inputStream.read(inputBytes);

	       byte[] outputBytes = cipher.doFinal(inputBytes);

	       FileOutputStream outputStream = new FileOutputStream(outputFile);
	       outputStream.write(outputBytes);

	       inputStream.close();
	       outputStream.close();

	    } catch (NoSuchPaddingException | NoSuchAlgorithmException 
                     | InvalidKeyException | BadPaddingException
	             | IllegalBlockSizeException | IOException e) {
		e.printStackTrace();
            }
     }
    
      public static void foo(String[] args) {
	String key = "This is a secret";
        
	File inputFile = new File("/Users/federico/Desktop/text.txt");
	File encryptedFile = new File("/Users/federico/Desktop/text.encrypted");
	File decryptedFile = new File("/Users/federico/Desktop/decrypted-text.txt");
		
	try {
	     Encrypt.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
	     Encrypt.fileProcessor(Cipher.DECRYPT_MODE,key,encryptedFile,decryptedFile);
	     System.out.println("Sucess");
	 } catch (Exception ex) {
	     System.out.println(ex.getMessage());
             ex.printStackTrace();
	 }
     }
    
}
