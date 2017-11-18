import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JFrame {

	private Container container;
	private Menu menuBar;
	private JMenu connectionMenu;
	private JMenuItem start;
	private Container userPanel;
	private Container opPanel;
	
	private UserBoard userB;
	private OpponentBoard opponentB;
	private JLabel statusBar;
	private JLabel comments, connectionStatus, turn;
	private JPanel topPanel;
	private static boolean yourTurn = false;
	private static boolean gameBegun = false;
	private boolean isHost;
	
	int curShipIndex = -1;
	
	private ArrayList<Ship> Ships;
	
	private static JMenu  addShipMenu; 
	private static JMenuItem hostItem, connectItem;
	
	Server server;
	Client client;

	public GUI(){
		super("Battleship");
		this.setLocation(200, 30);
		
		createShips();
		InitializeGUI();
		setGridLayout();
		
		
	}	
	//_______________________________________________________//
	private void InitializeGUI(){
		menuBar = new Menu(this);
		setJMenuBar(menuBar);
		menuBar.add(Menu.CreateFileMenu());
		menuBar.add(Menu.CreateHelpMenu());
		menuBar.add(CreateConnectionMenu());
		menuBar.add(createMoveMenu());
		menuBar.add(createStartButton());
		setSize( 480, 900 );

		setVisible( true );
	}
	//_______________________________________________________//	
	public JMenu createMoveMenu(){
		addShipMenu = new JMenu("Add Ship");
		addShipMenu.setEnabled(false);
		for(Ship s: Ships){
			addShipMenu.add(s.getShipItem());
			s.getShipItem().addActionListener(new ActionListener(){
				
				public void actionPerformed( ActionEvent event ){
		    		JMenuItem item = (JMenuItem) event.getSource();
		    		if(curShipIndex != -1){
		    			s.setChosen(true);
		    		}
		    		for(Ship s2 : Ships)
		    			if(s2.getName() == item.getText() ){
		    				s2.setChosen(true);
		    				if(curShipIndex != -1 && curShipIndex != s2.getIndex()){
		    					if(!Ships.get(curShipIndex).isInWater()){
		    						Ships.get(curShipIndex).setChosen(false);
		    						Ships.get(curShipIndex).getShipItem().setEnabled(true);
		    					}
		    				}
		    				s2.getShipItem().setEnabled(false);
		    				curShipIndex = s2.getIndex();
		    			}
		        }
			});
		}

		return addShipMenu;		
	}
	//_______________________________________________________//	
	
public JMenu CreateConnectionMenu() {
		
		connectionMenu = new JMenu( "Connect" );
		connectionMenu.setMnemonic( 'C' );
		hostItem = new JMenuItem( "Host Game" );
		connectItem = new JMenuItem( "Connect Game" );

		connectionMenu.add(hostItem);
		connectionMenu.addSeparator();
		connectionMenu.add(connectItem);
		connectionMenu.addSeparator();
		
		hostItem.addActionListener( new ActionListener() {
	    	public void actionPerformed( ActionEvent event ){
	    		try {
	    				server = new Server(getGui());
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	      }
	    );
		
		connectItem.addActionListener( new ActionListener() {
	    	public void actionPerformed( ActionEvent event ){
	    		try {
					client = new Client(getGui());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
	        }
	      }
	    );
		
		return connectionMenu;
	}

    //_______________________________________________________//
	public JMenuItem createStartButton(){
		start = new JMenuItem("Start Game");
		start.setEnabled(false);
		start.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(allShipsAdded()){
					start.setEnabled(false);
					gameBegun = !gameBegun;
					statusBar.setText("Hits: "+ opponentB.getHits() + "    Misses: "+ opponentB.getMisses());
					printToComments("You have started a new game.");
					printToTurn("It's your turn");
				}
				else{
					printToComments("You must add all ships before starting a new game");
				}
			}
			
		});
		return start;
	}
	//_______________________________________________________//	
	public void startGame(){
		start.setEnabled(true);
		addShipMenu.setEnabled(true);
		}
	//_______________________________________________________//	
	public void setGridLayout(){
		
		//Outside container
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
		//Container that holds the game boards
		Container boards = new Container();
		boards.setLayout(new GridLayout(2, 1));
		
		//Set up opponent board	
		opponentB = new OpponentBoard(this, gameBegun, yourTurn);
		opPanel = new Container();
		opPanel.setLayout(new GridLayout(11, 11, 0, 0));		
		for(ArrayList<Label> row : opponentB.getBoard()){
			for(Label l : row){
				opPanel.add(l);
			}
		}
		Container gameBoard1 = new Container();
		gameBoard1.setLayout(new BorderLayout());
		gameBoard1.add(new JLabel("Opponent's Board"), BorderLayout.NORTH);
		gameBoard1.add(opPanel, BorderLayout.CENTER);
		gameBoard1.setSize(380, 400);
		
		//Set up user board
		userB = new UserBoard(this, Ships);
		userPanel = new Container();
		userPanel.setLayout(new GridLayout(11, 11, 0, 0));
		for(ArrayList<Label> row : userB.getBoard()){
			for(Label l : row){
				userPanel.add(l);
			}
		}
		Container gameBoard2 = new Container();
		gameBoard2.setLayout(new BorderLayout());
		gameBoard2.add(new JLabel("Your Board"), BorderLayout.NORTH);
		gameBoard2.add(userPanel);
		
		boards.add(gameBoard1);
		boards.add(gameBoard2);
		
		statusBar = new JLabel();
		statusBar.setVisible(true);
		
		// Set up top panel
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(200, 60));
		topPanel.setLayout(new GridLayout(3,1));
		
		connectionStatus = new JLabel();
		connectionStatus.setText("Establish a connection to begin.");
		
		comments = new JLabel();
		comments.setText(" ");
		
		turn = new JLabel();
		turn.setText(" ");
		
		topPanel.add(connectionStatus);
		topPanel.add(comments);
		topPanel.add(turn);
		topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		container.add(boards);
		container.add(statusBar, BorderLayout.SOUTH);
		container.add(topPanel, BorderLayout.NORTH);
		setVisible(true);
		
		
	}
	
	public void setIsHost(boolean host) {
		isHost = host;
	}
	
	public boolean isHost() {
		return isHost;
	}
	
	public void send(String coord) {
		if(isHost){
			server.sendData(coord);
			System.out.println("sending"+coord);
		}
		
		else{
			client.sendData(coord);
			System.out.println("sending"+coord);
		}
	}
	
	public void processData(String coord) {
		String[] splitStr = coord.split("\\s+");
		int x = Integer.parseInt(splitStr[0]);
		int y = Integer.parseInt(splitStr[1]);	
		System.out.println("received" +coord);
		if(coord.length() != 3){	
			int hm = Integer.parseInt(splitStr[2]);
			
			opponentB.updateBoard(x,y,hm);
			
		}
		
		userB.updateBoard(x, y);
	}
	
	//_______________________________________________________//
	public void createShips(){
		Ships = new ArrayList<Ship>();
		
		
		ArrayList<String> pathNamesH = new ArrayList<String>();
		pathNamesH.add("battleship/batt1.gif");
		pathNamesH.add("battleship/batt2.gif");
		pathNamesH.add("battleship/batt3.gif");
		pathNamesH.add("battleship/batt4.gif");
		pathNamesH.add("battleship/batt5.gif");
		ArrayList<String> destroyedH = new ArrayList<String>();
		destroyedH.add("battleship/batt201.gif");
		destroyedH.add("battleship/batt202.gif");
		destroyedH.add("battleship/batt203.gif");
		
		ArrayList<String> pathNamesV = new ArrayList<String>();
		pathNamesV.add("battleship/batt6.gif");
		pathNamesV.add("battleship/batt7.gif");
		pathNamesV.add("battleship/batt8.gif");
		pathNamesV.add("battleship/batt9.gif");
		pathNamesV.add("battleship/batt10.gif");
		ArrayList<String> destroyedV = new ArrayList<String>();
		destroyedV.add("battleship/batt204.gif");
		destroyedV.add("battleship/batt205.gif");
		destroyedV.add("battleship/batt206.gif");
		
		Ships.add(0, new Ship( 5, "Aircraft Carrier", 0 , pathNamesH, pathNamesV, destroyedH, destroyedV));
		ArrayList<String> pNH4 = (ArrayList<String>) pathNamesH.clone();
		ArrayList<String> pNV4 = (ArrayList<String>) pathNamesV.clone();
		pNH4.remove(2);
		pNV4.remove(2);
		Ships.add(1, new Ship( 4, "Battleship", 1 , pNH4, pNV4, destroyedH, destroyedV));
		ArrayList<String> pNH3 = (ArrayList<String>) pNH4.clone();
		ArrayList<String> pNV3 = (ArrayList<String>) pNV4.clone();
		pNH3.remove(1);
		pNV3.remove(1);
		Ships.add(2, new Ship( 3, "Destroyer", 2 , pNH3, pNV3, destroyedH, destroyedV));
		Ships.add(3, new Ship( 3, "Submarine", 3 , pNH3, pNV3, destroyedH, destroyedV));
		ArrayList<String> pNH2 = (ArrayList<String>) pNH3.clone();
		ArrayList<String> pNV2 = (ArrayList<String>) pNV3.clone();
		ArrayList<String> destH = (ArrayList<String>) destroyedH.clone();
		ArrayList<String> destV = (ArrayList<String>) destroyedV.clone();
		pNH2.remove(1);
		pNV2.remove(1);
		destH.remove(1);
		destV.remove(1);
		Ships.add(4, new Ship( 2, "Patrol Boat", 4 , pNH2, pNV2, destH, destV));
				
		
	}	
	//_______________________________________________________//
	public boolean allShipsAdded(){
		
		for(Ship s : Ships){
			if (!s.isInWater()){
				return false;
			}
		}
		
		return true;
	}
	//_______________________________________________________//
	
	//_______________________________________________________//
	//_______________________________________________________//
	//_______________________________________________________//
	public void printToStatusBar(String s){
		statusBar.setText(s);
		this.repaint();
	}
	//_______________________________________________________//
	public void printToConnectionStat(String s){
		connectionStatus.setText(s);
		this.repaint();
	}
	//_______________________________________________________//
	public void printToComments(String s){
		comments.setText(s);
		this.repaint();
	}
	//_______________________________________________________//
	public void printToTurn(String s){
		turn.setText(s);
		this.repaint();
	}
	//_______________________________________________________//
	public void resetChosenShip(){
		 curShipIndex = -1;
	}
	//_______________________________________________________//
	public boolean gameHasBegun(){
		return gameBegun;
	}
	//_______________________________________________________//
	public GUI getGui(){
		return this;
	}
}
