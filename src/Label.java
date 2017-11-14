import java.awt.Color;
import java.awt.Image;

import javax.swing.*;

public class Label extends JLabel{
	
	
	public Label(String name) {
		
		super(name);
		
		if(name == ""){
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			setSize(20, 20);
			
			ImageIcon imageIcon = new ImageIcon(getClass().getResource("battleship/batt100.gif"), "water"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(60, 60,  Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			
			this.setIcon(imageIcon);

		}
		this.setHorizontalTextPosition(CENTER);
		
	}
	

}
