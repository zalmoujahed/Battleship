import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GUI extends JFrame {

	Menu bar;
	
	public GUI(){
		super("Battleship");
		this.setLocation(500, 200);
		
		setMenu();
		setGridLayout();
		
		setSize(700, 600);
		setVisible(true);
		
	}
	//_______________________________________________________//
	
	public void setMenu(){
		bar = new Menu();
		setJMenuBar(bar);
		
		
		
		
	}
	//_______________________________________________________//	
	
	public void setGridLayout(){
		
		
		
	}
	//_______________________________________________________//
	
	
	
	
	
	
	
	
	
	
	
	
}
