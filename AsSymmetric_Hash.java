package assymmetric_hash;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.InputMismatchException;
import java.util.Scanner;


public class AsSymmetric_Hash {

  
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Scanner s = new Scanner(System.in);
        int imp_type=1;
        System.out.println("===================================================================================");
    
        while(imp_type != 3){
        try {
        System.out.println("MAIN MENU");
        System.out.println("----------------------------");
        System.out.println("1.Encryption");
        System.out.println("2.Hashing");
        System.out.println("3.Exit");
        System.out.print("enter your choice:");
        imp_type = s.nextInt();       
        switch(imp_type){//As Symmatric , Hashing
            
            case 1: // Encryption (RSA)
        
            System.out.println("----------------------------");
        System.out.println("1.Encrypt");
        System.out.println("2.Decrypt");
        System.out.println("3.Exit");
        System.out.println("----------------------------");
        System.out.print("enter your choice:");
         int t1 = s.nextInt();
         switch(t1){
             case 1://encrypt
                         RSA R = new RSA();
               System.out.println("(1) file (2) folder"); 
               System.out.print("enter your choice:");
               int t4 = s.nextInt();
               
               switch(t4){
                   case 1://encrypt file
                System.out.print("Name:");
                String filename = s.next();
                 System.out.print("Enter the public key filename:");      
                 String pub_key = s.next();
                 PublicKey pub = R.get_public(pub_key);
                 R.encrypt(filename , pub);
                            break;
                   case 2://encrypt folder
                       System.out.print("Folder Name:");
                      String foldername = s.next();
                      File dir = new File(foldername);
                      File[] files = dir.listFiles();
                      System.out.print("Enter the public key filename:");      
                        pub_key = s.next();
                        pub = R.get_public(pub_key);
                     for (int k = 0; k< files.length; k++) {
                    R.encrypt(files[k].toString(),pub);
                        }                       
                     break;
         }
               break;
             case 2://decrypt
                  System.out.println("(1) file (2) folder"); 
                  System.out.print("enter your choice:");
                  int t5 = s.nextInt();
                  
                  switch(t5){
                   case 1://decrypt file
                   System.out.print("Name:");
                  String filename = s.next();   
                   System.out.print("Enter the Private key filename:");
                   String pub_key = s.next(); 
                   PrivateKey privet = RSA.get_privet(pub_key);
                   RSA.decrypt(filename , privet);
                 break;
                   case 2://decrypt folder
                       System.out.print("Folder Name:");
                      String foldername = s.next();
                      File dir = new File(foldername);
                      File[] files = dir.listFiles();
                     System.out.print("Enter the Private key filename:");
                        pub_key = s.next(); 
                        privet = RSA.get_privet(pub_key);
                    for (int k = 0; k< files.length; k++) {
                    RSA.decrypt(files[k].toString(),privet);
                        }        
                    break;
                 }
            
                
       break;
            case 3: // Exit
            System.exit(0);
               break;       
        }
        break;
            case 2:// Hashing
        System.out.print("Name:");
        String filename = s.next();
        System.out.print("Chhose the Algorithm (SHA-256, SHA-512): ");
        String alg = s.next();
        Hash.hash_file(filename , alg);
            break;      
        } }
        
        
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
         catch (IOException e) 
         {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }   
        
        
        
        
     catch (InputMismatchException exx) 
     {  
         
       System.out.println("----------------------------");
       System.out.println("try again ");
       System.out.println("and choose between 1,2 , 3 ");
       System.out.println("----------------------------");
       
       s.next();
       continue;
        
        }
        catch(NullPointerException e)
        {
          System.out.println("Please check the folder name..."+"\n"+"or the folder is empty.");
        }
        catch (Exception ex)
        {         
       System.out.println("An error occurred: "+ex);

        }
       continue;
        }
    }
    
}
            
 