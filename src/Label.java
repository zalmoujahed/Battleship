import java.awt.Color;
import java.awt.Image;

import javax.swing.*;

public class Label extends JLabel{
	
	private int row, col;
	
	public Label(String name, int x, int y) {
		
		super(name);
		row = x; 
		col = y;
		
		if(name == ""){
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			setSize(20, 20);
			
			ImageIcon imageIcon = new ImageIcon(getClass().getResource("battleship/batt100.gif"), "water"); 
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(30, 30,  Image.SCALE_SMOOTH); 
			imageIcon = new ImageIcon(newimg);  
			
			this.setIcon(imageIcon);
					

		}
		this.setHorizontalTextPosition(CENTER);
		
	}
	
	public void changeColor(Color c){
		this.setBorder(BorderFactory.createLineBorder(c, 1));
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	

}
