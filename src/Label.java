import java.awt.Color;

import javax.swing.*;

public class Label extends JLabel{
	
	
	public Label(String name) {
		
		super(name);
		
		if(name == ""){
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			ImageIcon icon = createImageIcon("battleship/batt100.gif", "water");
			this.setIcon(icon);
		}
		setSize(20, 20);
		
	}
	protected ImageIcon createImageIcon(String path, String description) {
	
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} 
		else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
