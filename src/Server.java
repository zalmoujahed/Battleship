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
					serve();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
	}

	public void serve() throws IOException {

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
		} 
		catch (IOException e) 
		{ 
			System.err.println("Accept failed."); 
			System.exit(1); 
		} 
		
		//TODO: send hits that are input into opponent board
		//send two ints specifying coord of hit
		//parse inside of while loop
		//update the board inside of while loop method that takes in those params
		//check if opponent ship has been hit
		//update opponent board with images where user hits regardless
		
		//TODO: receive hits to the user board 
		//receive two ints specifying coord of hit 
		//check if user ships have been hit
		
		//need 2 different sockets
	
		gui.startGame();
		send = new PrintWriter(communicationSocket.getOutputStream(), true);
		recieve = new BufferedReader(new InputStreamReader(
				communicationSocket.getInputStream()));
		

		String inputLine; 

		while ((inputLine = recieve.readLine()) != null) 
		{
			System.out.println ("Client(in server): " + inputLine); 
			send.println(inputLine); 
			System.out.println("Client: (in server)" + recieve.readLine());

			if (inputLine.equals("Bye.")) 
				break; 
		} 
		
		send.close(); 
		recieve.close(); 
		communicationSocket.close(); 
		connectionSocket.close(); 
	}

	@Override
	public void run() {
		
		
	}

}
