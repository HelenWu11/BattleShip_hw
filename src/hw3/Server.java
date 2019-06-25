package hw3;

// File Name GreetingServer.java
import java.net.*;
import java.io.*;

public class Server extends Thread {
   private ServerSocket serverSocket;
   private int portNumber;
   private Socket socket;
   private Start_Application app;
   
   
   public Socket getSocket() {
	   return socket;
   }
   
   public Server(Start_Application app) throws IOException {
      serverSocket = new ServerSocket(0);
      portNumber = serverSocket.getLocalPort();
      socket = null;
      this.app = app;
      //serverSocket.setSoTimeout(10000);
   }
   
   public String getHostAddress() {
	   return serverSocket.getInetAddress().getHostName();
   }
   
   public int getPortName() {
	   return portNumber;
   }
   
   public void close_server() throws IOException {
	   if(this.socket!=null && !this.socket.isClosed()) {
		   this.socket.close();
		   System.out.println("Server close!\n");
	   }
	   
   }
   
   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
               socket = serverSocket.accept();
            
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());
            //DataInputStream in = new DataInputStream(socket.getInputStream());
            app.connection();
            app.getWait_for_connection().setVisible(false);
            
            //System.out.println(in.readUTF());
            //DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            break;
            //server.close();
            
         } catch (SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         } catch (IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }
   
//   public static void main(String [] args) {
//      int port = Integer.parseInt(args[0]);
//      try {
//         Thread t = new Server(port);
//         t.start();
//      } catch (IOException e) {
//         e.printStackTrace();
//      }
//   }
}