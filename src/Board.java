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
		boardLabels = new ArrayList<ArrayList<Label>>(10);
		for(int i = 0; i < 10; i++){
			row = new ArrayList<Label>(10);
			for(int j = 0; j < 10; j++){
				row.add(new Label());
				//row.get(j).addMouseListener(new LabelListener());
			}
			boardLabels.add(row);
		}
	}

	private void CreateGamePanel(){
		int i = 0;
		int j = 0;
	    	innerPanel = new JPanel(new GridLayout(5, 5, 2, 2 ), false);
	    	//innerPanel.setOpaque(true);
	    	innerPanel.setBackground(Color.YELLOW);
	   	for(i = 0; i < 10; i++){
	   		for(j = 0; j < 10; j++){
	    			innerPanel.add( boardLabels.get(i).get(j) );
	    			}
	    		}
	    		this.add(innerPanel);
	   	}
}
