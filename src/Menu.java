import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;


public class Menu extends JMenuBar{
	//private int toggleCheckOnFillFlag;
	private static GUI gui;
	private static JMenuBar bar;
	private static JMenu fileMenu, helpMenu; ; // the menus that will be attached to the JManuBar
	private static JMenuItem exitItem; //  items for the "File" menu
	private static JMenuItem rulesItem, connectionItem, aboutItem, aboutGameItem; //  items for the "Help" menu
	
	
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
	    		System.exit(0);
	        }
	      }
	    );
	    return fileMenu;
	}

	public static JMenu CreateHelpMenu(){
		helpMenu = new JMenu( "Help" );
		helpMenu.setMnemonic( 'H' );
		rulesItem = new JMenuItem( "How to play Battleship" );
		aboutItem = new JMenuItem( "About Us" );
		aboutGameItem = new JMenuItem("About Battleship");
		connectionItem = new JMenuItem("Connection Help");
		helpMenu.add(rulesItem);
		helpMenu.addSeparator();
		helpMenu.add(aboutItem);
		helpMenu.addSeparator();
		helpMenu.add(connectionItem);
		rulesItem.addActionListener(new ActionListener() {
	    	public void actionPerformed( ActionEvent event ){
	    		JOptionPane.showMessageDialog( gui,"The object of Battleship is to try and sink all of the other player's before they sink all of your ships.\nAll of the other player's ships are somewhere on his/her board.\nYou try and hit them by calling out the coordinates of one of the squares on the board.\nThe other player also tries to hit your ships by calling out coordinates.\nNeither you nor the other player can see the other's board so you must try to guess where they are.\nEach board in the physical game has two grids:  the lower (horizontal) section for the player's ships and the upper part (vertical during play) for recording the player's guesses."
		                , "How to Play the Game", JOptionPane.PLAIN_MESSAGE);
	        }
	      }
	    );
		aboutGameItem.addActionListener( new ActionListener() {
	    	public void actionPerformed( ActionEvent event ){
	    		JOptionPane.showMessageDialog( gui,""
		                , "About Battleship", JOptionPane.PLAIN_MESSAGE);
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
	    		JOptionPane.showMessageDialog( gui,"Battleship\n"+
		                "Authors: Zaynab Almoujahed & Dana Dolat\n"
		                , "About Us", JOptionPane.PLAIN_MESSAGE);
	        }
	      }
	    );
		return helpMenu;
	}
	
}
