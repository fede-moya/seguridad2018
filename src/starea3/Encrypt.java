package starea3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

public class Encrypt {
    
    
    private static String getHashSHA1(byte[] inputBytes) {
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
    
    private static String getHashSHA256(byte[] inputBytes) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(inputBytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        return hashValue; 
    }
    
    public static String digestSHA1(String message){
        return getHashSHA1(message.getBytes(StandardCharsets.UTF_8));
    }
    
    public static String digestSHA256(String message){
        return getHashSHA256(message.getBytes(StandardCharsets.UTF_8));
    }
    
    public static void fileProcessor(int cipherMode,String key,File inputFile,File outputFile) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException{
        Key secretKey = new SecretKeySpec(digestSHA256(key).getBytes(), 0, 16, "AES");
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

     }
    
    public static void encryptFile(String key, String path) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, FileNotFoundException, IllegalBlockSizeException, BadPaddingException{
        File inputFile = new File(path);
        File encryptedFile = new File(inputFile.getParent() + "/encrypted_" + inputFile.getName());
        Encrypt.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
    }
    
    public static void decryptFile(String key, String path) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, FileNotFoundException, IllegalBlockSizeException, BadPaddingException{
        File inputFile = new File(path);
        File decryptedFile = new File(inputFile.getParent() + "/decrypted_" + inputFile.getName());
        Encrypt.fileProcessor(Cipher.DECRYPT_MODE,key,inputFile,decryptedFile);
    }
    
}
