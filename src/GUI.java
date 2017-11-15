import java.awt.*;
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
	private DefaultListModel<String> l1;
	
	

	public GUI(){
		super("Battleship");
		this.setLocation(200, 30);
		
		InitializeGUI();
		setGridLayout();
		
	}	
	//_______________________________________________________//
	private void InitializeGUI(){
		menuBar = new Menu();
		setJMenuBar(menuBar);
		menuBar.add(Menu.CreateFileMenu());
		menuBar.add(Menu.CreateHelpMenu());

		setSize( 400, 900 );

		setVisible( true );
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
		userB = new UserBoard(this);
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
	
	//_______________________________________________________//
	public void printToPanel(String s){
		statusBar.setText(s);
		this.repaint();
	}
	//_______________________________________________________//
	
	//_______________________________________________________//
	
}
