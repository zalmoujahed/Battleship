import java.io.*;
import java.net.*;

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

			echoSocket = new Socket("10.8.228.85", 34343);

			//echoSocket = new Socket("10.8.232.36", 34343);

			
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
		gui.setIsHost(false);
		recieveData();
		
	}
	
	public void sendData(String userInput) {
		send.println(userInput);
		try {
			recieveData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recieveData() throws IOException{
<<<<<<< HEAD
		
		String inputLine = recieve.readLine(); 
=======
		String inputLine; 
>>>>>>> parent of b6d3ad4... IT WORKS JESUS CHRIST GOOD LORD PRAISE THE LORD

		//while ((inputLine = recieve.readLine()) != null) 
		//{
			gui.processData(inputLine);
<<<<<<< HEAD
			//break; 
		//}
=======
			break; 
		} 
>>>>>>> parent of b6d3ad4... IT WORKS JESUS CHRIST GOOD LORD PRAISE THE LORD
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
