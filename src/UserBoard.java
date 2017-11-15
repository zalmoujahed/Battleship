import java.awt.Color;
import java.awt.event.MouseEvent;

public class UserBoard extends Board {

	public UserBoard(GUI g){
		super(g);
		//
		//set up action listeners
		//
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Label l = (Label)e.getSource();
        
        if(this.currentLabel != null){
        	currentLabel.changeColor(Color.BLACK);
        }
        l.changeColor(Color.RED);
        gui.printToPanel("user");
        System.out.println(l.getRow() + " " + l.getCol());
        currentLabel = l;
        gui.repaint();
		
	}
}
