import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

public class Board extends JPanel{
	
	private ArrayList<ArrayList<Label>> board;
	
	

	public Board(){
		
		board = new ArrayList<ArrayList<Label>>();
		CreateBoard();
	}

	private void CreateBoard(){
		
		// Create the top label row
		ArrayList<Label> topRow = new ArrayList<Label>();
		topRow.add(new Label(""));
		
		char c = 'A';
		for(; c >= 'J'; c++){		//Initialize the labels with chars
			Label l = new Label(""+ c);
			l.setBackground(Color.YELLOW);
			topRow.add(l);
		}
		
		board.add(0,topRow);
		
		for(int i = 0; i < 10; i++){
			
			ArrayList<Label> row = new ArrayList<Label>();
			row.add(new Label("" + (i+1)));
			for(int j = 0; j < 10; j++){
				row.add(new Label(""));
			}
			board.add(row);
		}
		
		
			
	}
	public ArrayList<ArrayList<Label>> getBoard(){
		return board;
	}

//	private void CreateGamePanel(){
//		int i = 0;
//		int j = 0;
//	    	innerPanel = new JPanel(new GridLayout(5, 5, 2, 2 ), false);
//	    	//innerPanel.setOpaque(true);
//	    	innerPanel.setBackground(Color.YELLOW);
//	   	for(i = 0; i < 10; i++){
//	   		for(j = 0; j < 10; j++){
//	    			innerPanel.add( boardLabels.get(i).get(j) );
//	    			}
//	    		}
//	    		this.add(innerPanel);
//	   	}
}
