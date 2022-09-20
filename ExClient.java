import java.net.*;
import java.io.*;

/*
 * The program got 3 arguments (host port and directory)
 * connect to the server and send the directory to the server
 * 
 * Then read all the sub folders and files for the provided directory from the server
 * 
 */

public class ExClient {
    // Need get 3 arguments host port and directory
    public static void main(String[] args) {
        //check if got 3 arguments
        if (args.length < 2) return;
 
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
 
        try (Socket socket = new Socket(hostname, port)) {

            
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output);
            
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            //Send direcory to Server
            writer.println(args[2]);
            writer.flush();

            //read from Server
            String line;
            while ((line=reader.readLine())!=null){
                System.out.println(line);

            }


        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}





    

