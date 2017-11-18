import java.net.*; 
import javax.swing.*;
import java.io.*;

public class Server implements Runnable{

	ServerSocket connectionSocket = null; 

	PrintWriter send = null;
	BufferedReader recieve = null;
	
	Socket communicationSocket = null; 
	GUI gui;

	public Server(GUI g) throws IOException{
		
		gui = g;
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					host();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
	}

	public void host() throws IOException {

		try { 
						
			connectionSocket = new ServerSocket(34343); 
			
			System.out.println ("Socket open on Port: " +
					connectionSocket.getLocalPort());
			InetAddress addr = InetAddress.getLocalHost();
			System.out.println("Java InetAddress localHost info: " + addr);
			System.out.println("Local Host Name: " + addr.getHostName());
			System.out.println("Local Host Address: " + addr.getHostAddress());
			
			
		} 
		catch (IOException e) 
		{ 
			System.err.println("Could not listen on port: 34343."); 
			System.exit(1); 
		} 

		gui.printToConnectionStat("You are now hosting");

		try { 
			communicationSocket = connectionSocket.accept();
			send = new PrintWriter(communicationSocket.getOutputStream(), true);
			recieve = new BufferedReader(new InputStreamReader(
					communicationSocket.getInputStream()));
		} 
		catch (IOException e) 
		{ 
			System.err.println("Accept failed."); 
			System.exit(1); 
		} 
		
		gui.startGame();
		gui.setIsHost(true);
		recieveData();
		
	}
	
	public void sendData(String userInput) {

		send.println(userInput);

	}
	
	public void recieveData() throws IOException{

		String inputLine;

		while ((inputLine = recieve.readLine()) != null) 
		{
			gui.processData(inputLine);
			
			//break; 
		} 
	}
	
	public void closeServer() throws IOException{
		send.close(); 
		recieve.close(); 
		communicationSocket.close(); 
		connectionSocket.close(); 
	}

	@Override
	public void run() {
		
		
	}

}
