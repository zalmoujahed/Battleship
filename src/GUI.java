import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GUI extends JFrame {

	private Container container;
	private Menu menuBar;

	

	public GUI(){
		super("Battleship");
		this.setLocation(500, 200);
		
		InitializeGUI();
		setGridLayout();
		
	}
	//_______________________________________________________//
	//_______________________________________________________//
	private void InitializeGUI(){
		menuBar = new Menu();
		//gamePanel = new SudokuGamePanel();
		setJMenuBar(menuBar);
		menuBar.add(Menu.CreateFileMenu());
		menuBar.add(Menu.CreateHelpMenu());
		getContentPane().setLayout(new BorderLayout());
		//getContentPane().add(gamePanel, BorderLayout.CENTER);
		setSize( 855, 800 );
		setVisible( true );
	}
	//_______________________________________________________//	
	
	public void setGridLayout(){
		
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
		
		
	}
	
	
	
	
	
	
}
