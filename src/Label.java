import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Label extends JLabel{
	
	
	public Label(String name) {
		
		super(name);
		
		if(name == ""){
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}
		setSize(20, 20);
		
	}
	

}
