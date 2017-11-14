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
		gamePanel = new Board();
		setJMenuBar(menuBar);
		menuBar.add(Menu.CreateFileMenu());
		menuBar.add(Menu.CreateHelpMenu());
//		getContentPane().setLayout(new BorderLayout());
//		getContentPane().add(gamePanel, BorderLayout.CENTER);
		
		setSize( 380, 800 );

		setVisible( true );
	}
	//_______________________________________________________//	
	
	public void setGridLayout(){
		
		userB = new UserBoard();
		opponentB = new OpponentBoard();
		
		
		container = getContentPane();
		container.setLayout(new GridLayout(2, 1));
		
		userPanel = new Container();
		userPanel.setLayout(new GridLayout(11, 11, 0, 0));
		
		Label boardLabel = new Label("Your Board");
		userPanel.add(boardLabel);
		
		opPanel = new Container();
		opPanel.setLayout(new GridLayout(11, 11, 0, 0));
		
		
		for(ArrayList<Label> row : userB.getBoard()){
			for(Label l : row){
				userPanel.add(l);
			}
		}
		for(ArrayList<Label> row : opponentB.getBoard()){
			for(Label l : row){
				opPanel.add(l);
			}
		}
		
		container.add(userPanel);
		container.add(opPanel);
		
		setVisible(true);
		
		
	}
	
	
	
	
	
	
}
