/*This class contains utility methods which can be used to manipulate data by sending it to
 *  certain locations in various different ways */
package connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class DataRedirection {
	
	// clears a given directory with the address of that directory provided as a parameter
    static void clearDirectory (String directory) {
        String files;
        File file = new File(directory);
        File[] listOfFiles = file.listFiles(); // array to store all the files in a given directory
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                files = listOfFiles[i].getName();
                
                {
                    boolean issuccess=new File(listOfFiles[i].toString()).delete(); // delete each file
                    
                }
            }
        }
    }
    
    // clears a specific file
    static  void clearSpecificFile (String file) throws FileNotFoundException {
        PrintStream console = System.out;
        
        File file1 = new File (file);
        FileOutputStream fos = new FileOutputStream(file1);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
        System.out.println(""); // inserts a blank String into the directory to replace existing content
        
        System.setOut(console);
        
    }
    
    
    
    // appends text (as a String) to a file. Each String appended goes to a new line in the file
    static void appendTextToFile (String input, String location) throws FileNotFoundException {
        try{
        	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(location , true)));
            out.println(input);
        }catch (IOException e) {
            
        }
    }
	// this sends text (as a String) to a file but each instance of it erases any previous content in the file
    static void writeTextToFile (String input, String location) {
        File file = new File(location);
        String content = input;
        
        try {
        	FileOutputStream fop = new FileOutputStream(file);
            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            
            // get the content in bytes
            byte[] contentInBytes = content.getBytes();
            
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // parses the HTTP stream data into a human readable JSON format
    static String httpToJSON (HttpURLConnection conn) throws IOException {
        String result="";
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line= br.readLine()) != null) {
            sb.append(line+"\n");
            result = sb.toString();
            
            
		}
        return result;
        
    }
}
