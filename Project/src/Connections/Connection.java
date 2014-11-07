/* This class enables the creation of a Connection object which is the basis for making a HTTP request to the Papillon system. Also,
 * you can request to retrieve the data obtained from such a connection and send data via the connection.   */
package Connections;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class Connection {
    private String url;
    private String httpMethod = "GET";
    private String requestProperty="application/json";
    private HttpURLConnection connection;
    
    
    Connection() {
        
    }
    
    Connection (String newUrl) {
        this.url = newUrl;
        
        
    }
    
    // opens a connection and sets the type of data to be returned
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
    // retrieves the JSON data and sends it to the specified file
    void getDataContent( String newFile) throws MalformedURLException, IOException{
        try {
            HttpURLConnection newConnection= this.connection;
            String result = DataRedirection.httpToJSON(newConnection);
            System.out.println(result);
            
            DataRedirection.writeTextToFile(result, newFile);
	    }
        
        catch (IOException MalformedURLException) {
            System.out.println("problem getting data");
	    }
    }
    
    public void makeRequest(Connection connection, String location){
		try {
			connection.connect();
			connection.getDataContent(location);
			connection.closeConnect();
		} catch (IOException e) {
			System.out.println("Problem with input");
			e.printStackTrace();
		}
		
	}
    
    
    // closes the connection
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