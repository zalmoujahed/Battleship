import java.io.*;
import java.net.*;

public class Client implements Runnable{

	Socket echoSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	GUI gui;

	public Client(GUI g) throws IOException{
		
		gui = g;
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					client();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
	}

	public void client() throws IOException {
		try {
			// echoSocket = new Socket("taranis", 7);
			
			echoSocket = new Socket("10.5.220.77", 34343);

			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: taranis.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for "
					+ "the connection to: taranis.");
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(
				new InputStreamReader(System.in));
		String userInput;

		while ((userInput = stdIn.readLine()) != null) {
			out.println(userInput);
			System.out.println("Client: " + in.readLine());
		}

		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
