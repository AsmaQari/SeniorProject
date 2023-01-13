
package assymmetric_hash;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec; //FOR KEY FORMATS 
import java.security.spec.X509EncodedKeySpec;  //FOR KEY FORMATS 
import javax.crypto.Cipher;
import java.util.*;


public class RSA {
    private PrivateKey privatekey;
    private PublicKey publicKey;
    private KeyPair pair;
    public RSA(){

        try {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        pair = generator.generateKeyPair();
        privatekey = pair.getPrivate();
        publicKey = pair.getPublic();
        int j =1;
       File pri = new File("private"+j+ ".key") ;
       while(pri.exists()){ 
           j++;
        pri = new File("private"+j+".key") ;
       }
       
       FileOutputStream   out = new FileOutputStream("private"+j+ ".key");
        out.write(privatekey.getEncoded());
        out.close(); 
        File pub = new File("public"+j+ ".key") ;
       while(pub.exists())
           {
            j++;  
        pub = new File("public"+j+".pub") ;
           }
        FileOutputStream  out1 = new FileOutputStream("public"+j+".pub");
        out1.write(publicKey.getEncoded());
        out1.close();
 
        
    } catch (Exception e) {
                System.out.println("Error: " + e.toString());
}
}
     public static void encrypt(String filename,  PublicKey p) throws Exception {
         try{
        Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, p);
        FileOutputStream   newf = new FileOutputStream(filename.replace(".txt", "")+".encrypted");
        byte[] input_file = Files.readAllBytes(new File(filename).toPath());
        byte[] cipherText = encryptCipher.doFinal(input_file);
        byte [] encodedBytes = Base64.getEncoder().encode(cipherText);
        newf.write(encodedBytes);
        newf.close(); 
        System.out.println("----------------------------");
        System.out.println("Done! File "+filename+" is encrypted using RSA ");
        System.out.println("Output file is "+filename.replace(".txt", "")+".encrypted ");
        System.out.println("---------------------------------------------------------------");
         } 
         catch (NoSuchFileException e) {
                      System.out.println();
                      System.out.println("file not found!");
                      System.out.println("Please make sure to write the full file name with the extension");
                      
                   }catch(NullPointerException e){
                    System.out.println("Maybe you misspilled the folder name..."+"\n"+"or the folder is empty.");
                            }catch(Exception e){
                                System.out.print(e);
                                }
    }
  public static void decrypt(String filename, PrivateKey p) throws Exception {
      try{
          if(filename.contains(".encrypted")){
            FileOutputStream   newf = new FileOutputStream(filename.replace(".encrypted", "")+".decrypted");
            byte[] input_file = Files.readAllBytes(new File(filename).toPath());
            byte[] bytes = Base64.getDecoder().decode(input_file);
            Cipher decriptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            decriptCipher.init(Cipher.DECRYPT_MODE, p);
            byte [] encodedBytes = decriptCipher.doFinal(bytes);
            newf.write(encodedBytes);
            newf.close();
            System.out.println("----------------------------");
            System.out.println("Done! File "+filename+" is decrypted using RSA ");
            System.out.println("Output file is "+filename.replace(".encrypted", "")+".decrypted ");
            System.out.println("---------------------------------------------------------------");
      }}
        catch (NoSuchFileException e) {
                      System.out.println();
                      System.out.println("file not found!");
                      System.out.println("Please make sure to write the full file name with the extension");
                      
                   }catch(NullPointerException e){
                    System.out.println("Maybe you misspilled the folder name..."+"\n"+"or the folder is empty.");
                            }catch(Exception e){
                                System.out.print(e);
                                }
  }
  
  
    public static PublicKey get_public(String pub) throws NoSuchFileException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException{
                byte[] keyBytes = Files.readAllBytes(new File(pub).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
        
    }
    public static PrivateKey get_privet(String privet) throws NoSuchFileException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException{
                byte[] keyBytes = Files.readAllBytes(new File(privet).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
                
    }
    
}
