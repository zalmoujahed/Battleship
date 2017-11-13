import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GUI extends JFrame {

	Menu bar;
<<<<<<< HEAD
	Container container;
	
=======
	//private JPanel puzzlePanel;
	private Menu menuBar;

>>>>>>> fc335676e10c284b88179e1b13d3ab18e962940a
	public GUI(){
		super("Battleship");
		this.setLocation(500, 200);
		
		setMenu();
		setGridLayout();
		
		InitializeGUI();
		
	}
	//_______________________________________________________//
	
	public void setMenu(){
		bar = new Menu();
		setJMenuBar(bar);
		
	}
	//_______________________________________________________//	
	
	public void setGridLayout(){
		
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
	}
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
	
	
	
	
	
	
}
