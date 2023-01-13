package assymmetric_hash;


import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Hash 
{
    

    public static void hash_file(String filename,String alg){
          try{
        MessageDigest SHADigest = MessageDigest.getInstance(alg);
        FileOutputStream   new_file = new FileOutputStream(filename.replace(".txt", "")+".msgdigest");
        byte[] input_file = Files.readAllBytes(new File(filename).toPath());
        byte[] digest = SHADigest.digest(input_file);
        new_file.write(digest);
        new_file.close();
        System.out.println("----------------------------");
        System.out.println("Done! The message digest of the file"+filename+"is generated using "+alg);
        System.out.println("Output file is "+filename.replace(".txt", "")+".msgdigest ");  
        System.out.println("----------------------------");

       }
    catch (NoSuchFileException e) {
                      System.out.println();
                      System.out.println("file not found!");
                      System.out.println("Please make sure to write the full file name with the extension");
                      
                   }catch(NoSuchAlgorithmException e){
                                System.out.print("No such Algorithm is implemnted");
                                  }catch(Exception e){
                                System.out.print(e);
                                }   
    }
}















































