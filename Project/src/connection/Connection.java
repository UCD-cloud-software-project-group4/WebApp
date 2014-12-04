
package connection;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/** This class enables the creation of a Connection object which is the basis for making 
 * a HTTP request to the Papillon system. Also, you can request to retrieve the data obtained
 * from such a connection and send data via the connection. 
 */
public class Connection {
    private String url;
    private String httpMethod = "GET";
    private String requestProperty="application/json";
    private HttpURLConnection connection;
    
    
    Connection() {
        
    }
    
    /** Simple connection object constructor which takes a URl as parameter. This URL represents the
     * kind of data a user wants to get from the Papillon system
     */
    Connection (String newUrl) {
        this.url = newUrl;
        
        
    }
    
    /** opens a HTTP connection and sets the type of data to be returned. 
     */ 
    void connect() throws MalformedURLException, IOException {
        try {
            String connectUrl = this.url;
            HttpURLConnection conn = (HttpURLConnection) (new URL(connectUrl)).openConnection();
            conn.setDoOutput(true);// set to true because we also want to use URL connection for output sometimes
            conn.setRequestMethod(this.httpMethod);
            conn.setRequestProperty("Accept", this.requestProperty);
            conn.setRequestProperty("Content-Type", this.requestProperty);
            
            String responseMsg = conn.getResponseMessage();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("Wrong Response Code. There appears to be a problem");
                System.out.println(responseMsg);
            }
            
            else {
                System.out.println("Connection successful");
            }
            
            this.connection = conn;
            
	    }
        
        catch (IOException  MalformedURLException ) {
            System.out.println("Something went terribly wrong");
        }
    }
    /** retrieves the JSON data from the Papillon system and returns it as a String. This overloaded 
     * method also allows a user to send the data to a local file for testing purposes
     */ 
    String getDataContent( String newFile) throws MalformedURLException, IOException{
        String result = "";
        try {
            HttpURLConnection newConnection= this.connection;
            result = DataRedirection.httpToJSON(newConnection);
            System.out.println(result);
            
            DataRedirection.writeTextToFile(result, newFile);
            
	    }
        
        catch (IOException MalformedURLException) {
            System.out.println("problem getting data");
	    }
        
        return result;
    }
    
    /** retrieves the JSON data from the Papillon system and returns is as a String. This overloaded 
     * method also allows a user to send the data to a local file for testing purposes
     */ 
    String getDataContent() {
        String result = "";
        try {
            HttpURLConnection newConnection= this.connection;
            result = DataRedirection.httpToJSON(newConnection);
            System.out.println(result);
	    }
        
        catch (IOException MalformedURLException) {
            System.out.println("problem getting data");
	    }
        
        return result;
        
    }
    
    /** This method allows a user to make a request for data from the Papillon system
     */ 
    
    String makeRequest(Connection connection, String location){
        String result = "";
        try {
			connection.connect();
			result =connection.getDataContent(location);
			connection.closeConnect();
		} catch (IOException e) {
			System.out.println("Problem with input");
			e.printStackTrace();
		}
		return result;
	}
    
    /** This method allows a user to make a request for data from the Papillon system.
     */ 
    String makeRequest(Connection connection){
		String result = "";
        try {
            connection.connect();
            result =connection.getDataContent();
            connection.closeConnect();
        } catch (IOException e) {
            System.out.println("Problem with input");
            e.printStackTrace();
        }
        return result;
    }
    
    
    /** Closes the HTTP connection to the Papillon system
     */ 
    void closeConnect(){
        this.connection.disconnect();
    }
    
    public static void main (String args[]) throws MalformedURLException, IOException {
        /* here is an example for how the classes and objects work. Create a connection object and then use the 
         * various methods to retrieve data */
        
        String url = "http://localhost:8080/papillonserver/rest/datacenters/1/allfloors"; // this can be any of the URLs from the Papillon API
        Connection connection = new Connection(url);
        connection.connect(); // create a connection
        connection.getDataContent("/home/papillon/Desktop/testconnection.odt"); // put a file location on your local machine
        connection.closeConnect(); // close the connection;
        
        
    }
}
