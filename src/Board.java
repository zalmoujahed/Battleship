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
	private JPanel innerPanel;
	private GridLayout grid;
	private ArrayList<ArrayList<Label>> boardLabels;
	private ArrayList<Label> row;

	public Board(){
		super();
		this.setOpaque(true);
        this.setBackground(Color.BLUE);
		this.setSize(800, 800);
		this.setLayout(new GridLayout( 5, 5, 5, 5 ));
		Create2DArray();
		CreateGamePanel();
	}

	private void Create2DArray(){
		boardLabels = new ArrayList<ArrayList<Label>>(9);
		for(int i = 0; i < 9; i++){
			row = new ArrayList<Label>(9);
			for(int j = 0; j < 9; j++){
				row.add(new Label());
				row.get(j).addMouseListener( new LabelListener());
			}
			boardLabels.add(row);
		}
	}

	private void CreateGamePanel(){
		int i, j, k, l;
	    for(i = 0; i < 9; i = i + 3){
	   		for(j = 0; j < 9; j = j + 3) {
	   			grid = new GridLayout(3, 3, 2, 2 );
	    		innerPanel = new JPanel( grid );
	    		innerPanel.setOpaque(true);
	    		innerPanel.setBackground(Color.YELLOW);
	   			for(k = 0; k < 3; k++){
	   				for(l = 0; l < 3; l++){
	    				innerPanel.add( boardLabels.get(k + i).get(l + j) );
	    			}
	    		}
	    		this.add(innerPanel);
	   		}
	    }
	}

}
