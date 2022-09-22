import java.io.*;
import java.net.*;

/*
 * The program gets one argument (port) 
 * Create a socket server
 * when client connected it read directory from the client
 * and write to the client all the sub folders and files for the provided directory
 * 
 */

public class ExServer {
    // need to get one argument for port
    public static void main(String[] args) {
        if (args.length < 1) return;
 
        int port = Integer.parseInt(args[0]);
 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);

            while (true) {
                //client conected
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output);

                //read the root directory from the client
                String rootDir = reader.readLine();
                System.out.println(rootDir);
                //run recursive printAllSub method 
                printAllSub(rootDir,writer);
                
                writer.flush();
                serverSocket.close();
            }    

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void printAllSub(String Dir, PrintWriter writer) {
        //create file object 
        File rootDir = new File(Dir);
        
        //create list of all the files and directories in the root directory
        File[] allFiles = rootDir.listFiles();
              //check if root directory exist and not empty          
              if (allFiles != null){  
                //go throw all the files in the root direcrory 
                for (File file : allFiles) {
                    if (file.isDirectory()){
                        //if the file is directory write it to client and execute printAllSub method for this directory 
                        writer.println("Dir:" + file.getAbsolutePath());
                        printAllSub(file.getAbsolutePath(),writer);   
                   }  
                   else 
                        //if the file is a file and not directory so write it to client
                        writer.println("File:" + file.getAbsolutePath());
                } 
              }
	}
}
