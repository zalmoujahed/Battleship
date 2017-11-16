import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JFrame {

	private Container container;
	private Menu menuBar;
	private Container userPanel;
	private Container opPanel;
	
	private UserBoard userB;
	private OpponentBoard opponentB;
	private JLabel statusBar;
	
	int curShipIndex = -1;
	
	private ArrayList<Ship> Ships;
	
	private static JMenu moveMenu, addShipMenu; 
	

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
		menuBar.add(Menu.CreateConnectionMenu());
		menuBar.add(createMoveMenu());

		setSize( 400, 900 );

		setVisible( true );
	}
	//_______________________________________________________//	
	public JMenu createMoveMenu(){
		moveMenu = new JMenu("Make Move");
		addShipMenu = new JMenu("Add Ship");
		
		for(Ship s: Ships){
			addShipMenu.add(s.getShipItem());
			s.getShipItem().addActionListener(new ActionListener(){
				public void actionPerformed( ActionEvent event ){
		    		JMenuItem item = (JMenuItem) event.getSource();
		    		
		    		for(Ship s : Ships)
		    			if(s.getName() == item.getText() ){
		    				s.setChosen(true);
		    				if(curShipIndex != -1 && curShipIndex != s.getIndex()){
		    					if(!Ships.get(curShipIndex).isInWater()){
		    						Ships.get(curShipIndex).setChosen(false);
		    						Ships.get(curShipIndex).getShipItem().setEnabled(true);
		    					}
		    				}
		    				s.getShipItem().setEnabled(false);
		    				curShipIndex = s.getIndex();
		    			}
		        }
			});
		}

		moveMenu.add(addShipMenu);
		return moveMenu;		
	}

	
	public void setGridLayout(){
		
		//Outside container
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
		//Container that holds the game boards
		Container boards = new Container();
		boards.setLayout(new GridLayout(2, 1));
		
		//Set up opponent board	
		opponentB = new OpponentBoard(this);
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
		gameBoard2.add(userPanel, BorderLayout.CENTER);
		gameBoard2.setSize(380, 400);
		
		boards.add(gameBoard1);
		boards.add(gameBoard2);
		
		statusBar = new JLabel("Mode:");
		statusBar.setVisible(true);
		
		container.add(boards);
		container.add(statusBar, BorderLayout.SOUTH);
		setVisible(true);
		
		
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
		
		Ships.add(new Ship( 5, "Aircraft Carrier", 0 , pathNamesH, pathNamesV, destroyedH, destroyedV));
		pathNamesH.remove(2);
		pathNamesV.remove(2);
		Ships.add(new Ship( 4, "Battleship", 1 , pathNamesH, pathNamesV, destroyedH, destroyedV));
		pathNamesH.remove(1);
		pathNamesV.remove(1);
		Ships.add(new Ship( 3, "Destroyer", 2 , pathNamesH, pathNamesV, destroyedH, destroyedV));
		Ships.add(new Ship( 3, "Submarine", 3 , pathNamesH, pathNamesV, destroyedH, destroyedV));
		pathNamesH.remove(1);
		pathNamesV.remove(1);
		destroyedH.remove(1);
		destroyedV.remove(1);
		Ships.add(new Ship( 2, "Patrol Boat", 4 , pathNamesH, pathNamesV, destroyedH, destroyedV));
				
		
	}	
	//_______________________________________________________//
	public void printToPanel(String s){
		statusBar.setText(s);
		this.repaint();
	}
	//_______________________________________________________//
	public void addShip(int shipIndex){
		 
	}
	//_______________________________________________________//
	
}
