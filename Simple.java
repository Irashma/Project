import java.io.File;
import java.util.Scanner;
class Simple {  
    public static void main(String args[]){ 
        String Dir;
        if (args.length == 0)
        {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Please enter a directory");
            Dir = myObj.nextLine();
            File rootDir = new File(Dir);
            while (!rootDir.exists()){
            System.out.println("The directory not exist"); 
            System.out.println("Please enter a correct directory or press exit"); 
                Dir = myObj.nextLine();
                if (Dir.equals("exit")){
                    System.exit(0);
                }
                else
                    rootDir = new File(Dir);   
            }
        }  
        else{
            Dir = args[0];
        } 
        printAllSub(Dir);         
    }
    
    public static void printAllSub(String Dir) {
		//File rootDir = new File("C:\\users\\ira_S\\rootfolder");
        File rootDir = new File(Dir);
        File[] allFiles = rootDir.listFiles();
            //System.out.println("Parent:" + rootDir.getParentFile());
            if (allFiles != null){
                for (File file : allFiles) {
                   if (file.isDirectory()){
                    System.out.println("Dir:" + file.getAbsolutePath());
                       printAllSub(file.getAbsolutePath());   
                   }  
                   else 
                        System.out.println("File:" + file.getAbsolutePath());

                } 
            }       
	}  
}
     
  
   