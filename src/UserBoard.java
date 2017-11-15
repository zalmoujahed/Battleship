import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UserBoard extends Board {

	private ArrayList<Ship> Ships;
	private int shipChosen;
	
	public UserBoard(GUI g, ArrayList<Ship> ships){
		super(g);
		Ships = ships;
		shipChosen = -1;
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
	@Override
	public void mouseEntered(MouseEvent e) {
		shipChosen = chosenShipIndex();
		Label l = (Label)e.getSource();
        if(shipChosen != -1){
	        if(this.currentLabel != null){
	        	currentLabel.changeColor(Color.BLACK);
	        }
	        l.changeColor(Color.RED);
	        currentLabel = l;
        }
    
	}
	public int chosenShipIndex(){
		int i = -1;
		for(Ship s: Ships){
			if(s.isChosen()){
				i = s.getIndex();
			}
		}
		return i;
	}
}
