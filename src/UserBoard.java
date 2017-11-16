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
		if(shipChosen != -1){
			Label l = (Label)e.getSource();
        
	        if(this.currentLabel != null){
	        	currentLabel.changeColor(Color.BLACK);
	        }
	        l.changeColor(Color.RED);
	        l.setChosen(true);
	        
	        currentLabel = l;
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		shipChosen = chosenShipIndex();
		Label l = (Label)e.getSource();
		
        if(shipChosen != -1 && l.getText() == ""  && !currentLabel.isChosen()){
        	
        	//Erase previous markings
        	if(this.currentLabel != null && !currentLabel.isChosen()&& currentLabel.getText() == ""){
	        	currentLabel.changeColor(Color.BLACK);
	        	if(currentLabel.getNorth() != -1){
	    			board.get(currentLabel.getNorth()+1).get(currentLabel.getCol()+1).changeColor(Color.BLACK);
	    		}
	    		if(currentLabel.getSouth() != -1){
	    			board.get(currentLabel.getSouth()+1).get(currentLabel.getCol()+1).changeColor(Color.BLACK);
	    		}
	    		if(currentLabel.getEast() != -1){
	    			board.get(currentLabel.getRow()+1).get(currentLabel.getEast()+1).changeColor(Color.BLACK);
	    		}
	    		if(currentLabel.getWest() != -1){
	    			board.get(currentLabel.getRow()+1).get(currentLabel.getWest()+1).changeColor(Color.BLACK);
	    		}
	        }
        	
    		l.setNeighbors(Ships.get(shipChosen).getLength());
    		if(l.getNorth() != -1){
    			board.get(l.getNorth()+1).get(l.getCol()+1).changeColor(Color.RED);
    		}
    		if(l.getSouth() != -1){
    			board.get(l.getSouth()+1).get(l.getCol()+1).changeColor(Color.RED);
    		}
    		if(l.getEast() != -1){
    			board.get(l.getRow()+1).get(l.getEast()+1).changeColor(Color.RED);
    		}
    		if(l.getWest() != -1){
    			board.get(l.getRow()+1).get(l.getWest()+1).changeColor(Color.RED);
    		}
	        
	        l.changeColor(Color.RED);
	        currentLabel = l;
        }
        else{
        	if(l.getRow() == currentLabel.getNorth() || l.getRow() == currentLabel.getSouth() ||
        		l.getCol() == currentLabel.getEast() || l.getCol() == currentLabel.getWest()	){
	        	l.changeColor(Color.BLUE);
	        }
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
