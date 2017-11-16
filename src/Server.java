import java.net.*; 
import java.io.*;

public class Server {
	
	// Network Items
	boolean connected;
	Socket echoSocket;
	PrintWriter out;
	BufferedReader in;
	
	public Server() {
	      connected = false;
	}

}
