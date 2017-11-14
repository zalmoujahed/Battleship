import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

public class Board{
	
	private ArrayList<ArrayList<Label>> board;
<<<<<<< HEAD
	private Label currentLabel = new Label(" ", -1, -1);
	
=======
>>>>>>> fe370c9ddefefeac66fc1567e62a961f28429fb6

	public Board(){
		
		board = new ArrayList<ArrayList<Label>>();
		CreateBoard();
	}

	private void CreateBoard(){
		
		// Create the top label row
		ArrayList<Label> topRow = new ArrayList<Label>();
		
		String c[] = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
		for(String s: c){		//Initialize the labels with chars
			topRow.add(new Label(s, -1, -1));
		}
		
		board.add(topRow);
		for(int i = 0; i < 10; i++){
			
			ArrayList<Label> row = new ArrayList<Label>();
			row.add(new Label("" + (i+1), -1, -1));
			
			for(int j = 0; j < 10; j++){
				Label l = new Label("", i, j);
				l.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                	
	                    System.out.println("no don't do it");
	                    Label l = (Label)e.getSource();
	                  
	                    
	                    if(currentLabel != null){
	                    	currentLabel.changeColor(Color.BLACK);
	                    }
	                    l.changeColor(Color.RED);
	                    System.out.println(l.getRow() + " " + l.getCol());
	                    currentLabel = l;
	                }

	            });
				row.add(l);
			}
			board.add(row);
		}
		
			
	}
	public ArrayList<ArrayList<Label>> getBoard(){
		return board;
	}


}
