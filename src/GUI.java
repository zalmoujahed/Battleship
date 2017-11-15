import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GUI extends JFrame {

	private Container container;
	private Menu menuBar;
	private Board gamePanel;
	private Container userPanel;
	private Container opPanel;
	
	private UserBoard userB;
	private OpponentBoard opponentB;
	JPanel sidePanel;
	
	

	public GUI(){
		super("Battleship");
		this.setLocation(200, 30);
		
		InitializeGUI();
		setGridLayout();
		
	}
	//_______________________________________________________//
	
	//_______________________________________________________//
	private void InitializeGUI(){
		menuBar = new Menu();
		gamePanel = new Board(this);
		setJMenuBar(menuBar);
		menuBar.add(Menu.CreateFileMenu());
		menuBar.add(Menu.CreateHelpMenu());

		setSize( 500, 900 );

		setVisible( true );
	}
	//_______________________________________________________//	
	
	public void setGridLayout(){
				
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
		Container boards = new Container();
		boards.setLayout(new GridLayout(2, 1));
		
		userB = new UserBoard(this);
		opponentB = new OpponentBoard(this);
		
		Container gameBoard1 = new Container();
		gameBoard1.setLayout(new BorderLayout());
		
		opPanel = new Container();
		opPanel.setLayout(new GridLayout(11, 11, 0, 0));		
		for(ArrayList<Label> row : opponentB.getBoard()){
			for(Label l : row){
				opPanel.add(l);
			}
		}
		
		JLabel opLabel = new JLabel("Opponent's Board");
		opLabel.setBackground(Color.DARK_GRAY);
		gameBoard1.add(opLabel, BorderLayout.NORTH);
		gameBoard1.add(opPanel, BorderLayout.CENTER);
		
		gameBoard1.setSize(380, 400);
		
		
		Container gameBoard2 = new Container();
		gameBoard2.setLayout(new BorderLayout());
		
		userPanel = new Container();
		userPanel.setLayout(new GridLayout(11, 11, 0, 0));
		for(ArrayList<Label> row : userB.getBoard()){
			for(Label l : row){
				userPanel.add(l);
			}
		}
		
		JLabel userLabel = new JLabel("Your Board");
		userLabel.setBackground(Color.darkGray);
		gameBoard2.add(userLabel, BorderLayout.NORTH);
		gameBoard2.add(userPanel, BorderLayout.CENTER);
		gameBoard2.setSize(380, 400);
		boards.add(gameBoard1);
		boards.add(gameBoard2);
		
		sidePanel = new JPanel();
		sidePanel.setVisible(true);
		JLabel l = new JLabel(" ");
		l.setSize(25, 25);
		sidePanel.add(l);
		l.setVisible(true);
		container.add(boards);
		container.add(sidePanel, BorderLayout.SOUTH);
		
		
		setVisible(true);
		
		
	}
	
	public void printToPanel(String s){
		
		JLabel l = new JLabel(s);
		l.setSize(25, 25);
		sidePanel.removeAll();
		sidePanel.add(l);
		l.setVisible(true);
		
	}
	
	
	
}
