package hw3;

// File Name GreetingClient.java
import java.net.*;
import java.io.*;

public class Client {
	private Socket client;
	private Start_Application app;
	
	public Socket getClient() {
		   return client;
	 }
	   
	 public void close_client() throws IOException {
		   if(this.client!=null && !this.client.isClosed()) {
			   this.client.close();
			   System.out.println("client close!\n");
		   }
		   
	   }
	
	public Client(String serverName, int port, Start_Application app) throws IOException {
	      client = new Socket(serverName, port);
	      this.app = app;
	      //System.out.println("Just connected to " + client.getRemoteSocketAddress());
	      app.connection();
	      app.getConnect_to_fram().setVisible(false);
	      //serverSocket.setSoTimeout(10000);
	 }
	   
		
//   public static void main(String [] args) {
//      String serverName = args[0];
//      int port = Integer.parseInt(args[1]);
//      try {
//    	  
//         System.out.println("Connecting to " + serverName + " on port " + port);
//         Socket client = new Socket(serverName, port);
//         System.out.println(client.getLocalPort());
//         
//         System.out.println("Just connected to " + client.getRemoteSocketAddress());
//         OutputStream outToServer = client.getOutputStream();
//         DataOutputStream out = new DataOutputStream(outToServer);
//         
//         out.writeUTF("Hello from " + client.getLocalSocketAddress());
//         InputStream inFromServer = client.getInputStream();
//         DataInputStream in = new DataInputStream(inFromServer);
//         
//         System.out.println("Server says " + in.readUTF());
//         client.close();
//      } catch (IOException e) {
//         e.printStackTrace();
//      }
//   }
}