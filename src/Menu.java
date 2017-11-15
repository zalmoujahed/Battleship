import java.awt.event.*;
import javax.swing.*;


public class Menu extends JMenuBar{
	//private int toggleCheckOnFillFlag;
	private GUI gui;
	private static JMenuBar bar;
	private static JMenu fileMenu, helpMenu, connectionMenu, moveMenu, addShipMenu; // the menus that will be attached to the JManuBar
	private static JMenuItem exitItem; //  items for the "File" menu
	private static JMenuItem rulesItem, connectionItem, aboutItem; //  items for the "Help" menu
	
	
	public Menu(GUI g){
		bar = new JMenuBar();
		gui = g;
	}

	public static JMenu CreateFileMenu(){
		fileMenu = new JMenu( "File" );
		fileMenu.setMnemonic( 'F' );
		exitItem = new JMenuItem( "Exit");
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
	    exitItem.addActionListener( new ActionListener() {
	    	public void actionPerformed( ActionEvent event ){

	        }
	      }
	    );
	    return fileMenu;
	}

	public static JMenu CreateHelpMenu(){
		helpMenu = new JMenu( "Help" );
		helpMenu.setMnemonic( 'H' );
		rulesItem = new JMenuItem( "How to play Battleship" );
		aboutItem = new JMenuItem( "About" );
		connectionItem = new JMenuItem("Connection Help");
		helpMenu.add(rulesItem);
		helpMenu.addSeparator();
		helpMenu.add(aboutItem);
		helpMenu.addSeparator();
		helpMenu.add(connectionItem);
		rulesItem.addActionListener( new ActionListener() {
	    	public void actionPerformed( ActionEvent event ){
	    		
	        }
	      }
	    );
		
		connectionItem.addActionListener( new ActionListener() {
	    	public void actionPerformed( ActionEvent event ){

	        }
	      }
	    );
		
	    aboutItem.addActionListener( new ActionListener() {
	    	public void actionPerformed( ActionEvent event ){

	        }
	      }
	    );
		return helpMenu;
	}
	
	public static JMenu CreateConnectionMenu() {
		
		connectionMenu = new JMenu( "Connect" );
		connectionMenu.setMnemonic( 'C' );
		
		connectionMenu.addActionListener( new ActionListener() {
	    	public void actionPerformed( ActionEvent event ){

	        }
	      }
	    );
		
		return connectionMenu;
	}
	
	
}
