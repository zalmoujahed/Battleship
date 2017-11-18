import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class Client implements Runnable{

	Socket echoSocket = null;

	PrintWriter send = null;
	BufferedReader recieve = null;

	GUI gui;

	public Client(GUI g) throws IOException{
		
		gui = g;
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					connect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
	}

	public void connect() throws IOException {
		try {

			echoSocket = new Socket("10.16.212.32", 34343);
			//echoSocket = new Socket("10.107.208.83", 34343);
			
			send = new PrintWriter(echoSocket.getOutputStream(), true);
			recieve = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: taranis.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for "
					+ "the connection to: taranis.");
			System.exit(1);
		}
		
		gui.printToConnectionStat("You are now connected to the host");
		
		gui.startGame();
		
	}
	
	public void sendData(int x, int y) {

		String userInput = x + " " + y;
		
		send.println(userInput);
		System.out.println("Client(in client): " + userInput);

	}
	
	public void recieveData() throws IOException{
		String inputLine; 

		while ((inputLine = recieve.readLine()) != null) 
		{
			System.out.println ("Client(in server): " + inputLine); 
			send.println(inputLine); 
			System.out.println("Client: (in server)" + recieve.readLine());

			if (inputLine.equals("Bye.")) 
				break; 
		} 
	}
	
	public void closeServer() throws IOException{
		send.close();
		recieve.close();
		echoSocket.close();
	}

	@Override
	public void run() {
		
	}
}