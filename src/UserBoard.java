import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UserBoard extends Board {

	private ArrayList<Ship> Ships;
	private int shipChosen;
	private Label curNeighbor; 
	
	
	
	public UserBoard(GUI g, ArrayList<Ship> ships){
		super(g);
		Ships = ships;
		shipChosen = -1;
	
	}
	//___________________________________________________________________________//
	@Override
	public void mouseClicked(MouseEvent e) {
		
		Label l = (Label)e.getSource();
		
		if((l.getRow() == currentLabel.getNorth() && l.getCol() == currentLabel.getCol()
		  || l.getRow() == currentLabel.getSouth() && l.getCol() == currentLabel.getCol() 
		  || l.getRow() == currentLabel.getRow() && l.getCol() == currentLabel.getEast() 
		  || l.getRow() == currentLabel.getRow() && l.getCol() == currentLabel.getWest()) && currentLabel.isChosen() ){
			 
			  curNeighbor = l;
			  fillInShip();
		  }
		
		else if(shipChosen != -1 && !currentLabel.isChosen()){
			      
	        l.changeColor(Color.RED);
	        l.setChosen(true);
	        
	        currentLabel = l;
		}
		
	}
	//___________________________________________________________________________//
	@Override
	public void mouseEntered(MouseEvent e) {
		shipChosen = chosenShipIndex();
		Label l = (Label)e.getSource();
		if(!l.hasShip()){
		
	        if(shipChosen != -1 && l.getText() == ""  && !currentLabel.isChosen() ){
	        	
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
	    		checkNeighbors(l);
	    		
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
	        	if((l.getRow() == currentLabel.getNorth() && l.getCol() == currentLabel.getCol()
				  || l.getRow() == currentLabel.getSouth() && l.getCol() == currentLabel.getCol() 
				  || l.getRow() == currentLabel.getRow() && l.getCol() == currentLabel.getEast() 
				  || l.getRow() == currentLabel.getRow() && l.getCol() == currentLabel.getWest()) && currentLabel.isChosen() ){
	        		
	        		if(curNeighbor != null){
	        			curNeighbor.changeColor(Color.red);
	        		}
					  l.changeColor(Color.white);
					  curNeighbor = l;
				  }
	        }
		}
	}
	//___________________________________________________________________________//
	public int chosenShipIndex(){
		int i = -1;
		for(Ship s: Ships){
			if(s.isChosen()){
				i = s.getIndex();
			}
		}
		return i;
	}	
	//___________________________________________________________________________//
	public void fillInShip(){
		if(currentLabel.getRow() != curNeighbor.getRow()){			//North or south

			int col = currentLabel.getCol()+1;
			
			if(currentLabel.getRow() < curNeighbor.getRow()){ //South
				int row = currentLabel.getRow()+1;
				
				for(String s : Ships.get(shipChosen).getShipVertical(row, row+Ships.get(shipChosen).getLength()-1, col)){
					board.get(row).get(col).changeImage(s);
					board.get(row).get(col).setHasShip(true, shipChosen);
					row++;
				}
				
			}
			else{
				int row = curNeighbor.getRow() +1 ;
				for(String s : Ships.get(shipChosen).getShipVertical(row +Ships.get(shipChosen).getLength()-1, row, col)){
					
					board.get(row).get(col).changeImage(s);
					board.get(row).get(col).setHasShip(true, shipChosen);
					row++;
				}
			}
			
		}
		else if(currentLabel.getCol() != curNeighbor.getCol()){ 	//East or west
			
			int row = currentLabel.getRow()+1;
			
			if(currentLabel.getCol() < curNeighbor.getCol()){ //east
				int col = currentLabel.getCol()+1;
				for(String s : Ships.get(shipChosen).getShipHorizontal(row, col, col+Ships.get(shipChosen).getLength()-1)){
					board.get(row).get(col).changeImage(s);
					board.get(row).get(col).setHasShip(true, shipChosen);
					col++;
				}
			}
			else{
				int col = curNeighbor.getCol()+1;
				for(String s : Ships.get(shipChosen).getShipHorizontal(row, col+Ships.get(shipChosen).getLength()-1, col)){
					board.get(row).get(col).changeImage(s);
					board.get(row).get(col).setHasShip(true, shipChosen);
					col++;
				}
			}
			
		}
		Ships.get(shipChosen).setInWater(true);
		
		//reset borders that were highlighted
		resetBoard();
		
	}
	//___________________________________________________________________________//
	public void checkNeighbors(Label l){
		
		if(l.getNorth() != -1){
			int i = l.getRow();
			while(i >= l.getNorth() + 1){
				if(board.get(i).get(l.getCol()+1).hasShip()){
					l.setNorth(-1);
					break;
				}
				i--;
			}
		}
		if(l.getSouth() != -1){
			int i = l.getRow()+1;
			while(i <= l.getSouth() +1){
				if(board.get(i).get(l.getCol()+1).hasShip()){
					l.setSouth(-1);
					break;
				}
				i++;
			}
		}
		if(l.getEast() != -1){
			int i = l.getCol()+1;
			while(i <= l.getEast() + 1){
				if(board.get(l.getRow()+1).get(i).hasShip()){
					l.setEast(-1);
					break;
				}
				i++;
			}
		}
		if(l.getWest() != -1){
			int i = l.getCol();
			while(i >= l.getWest() + 1){
				if(board.get(l.getRow()+1).get(i).hasShip()){
					l.setWest(-1);
					break;
				}
				i--;
			}
		}
		
	}
	//___________________________________________________________________________//
	// Reset the borders and variables after a ship is added
	public void resetBoard(){
		   	
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
    	currentLabel.setChosen(false);
    	gui.resetChosenShip();
    	Ships.get(shipChosen).setChosen(false);
    	shipChosen  = -1;
    	curNeighbor = null;
	}
	//___________________________________________________________________________//
	public void updateBoard(int row, int col){
		if(checkHit(row, col)){
			int index = board.get(row).get(col).getShipIndex();
			Ships.get(index).incrementHit();
			if(Ships.get(index).getHits() == Ships.get(index).getLength())
				Ships.get(index).setShipSunk(true);
			changeImageHit(row, col, index);
			gui.send(""+ row + " " + col + " 1");
		}
		else{
			changeImageMiss(row, col);
			gui.send(""+ row + " " + col + " 0");
		}
		
	}
	//___________________________________________________________________________//
	public boolean checkHit(int row, int col){
		if(board.get(row).get(col).hasShip()){
			return true;
		}
		else 
			return false;
	}
	//___________________________________________________________________________//
	//change one label to a red ship piece
	public void changeImageHit(int row, int col, int shipIndex){
		if(Ships.get(shipIndex).getRowEnd() == -1){  		//Horizontal ship
			if(Ships.get(shipIndex).getColBeginning() < Ships.get(shipIndex).getColEnd() ){		//east
				//leftmost image
				if(col == Ships.get(shipIndex).getColBeginning()){
					String path = Ships.get(shipIndex).getDestroyedH().get(0);
					board.get(row).get(col).changeImage(path);
				}
				//rightmost image
				else if(col == Ships.get(shipIndex).getColEnd()){
					String path = Ships.get(shipIndex).getDestroyedH().get(2);
					board.get(row).get(col).changeImage(path);
				}
				//middle
				else{
					String path = Ships.get(shipIndex).getDestroyedH().get(1);
					board.get(row).get(col).changeImage(path);
				}
				
			}
			else{
				//leftmost image
				if(col == Ships.get(shipIndex).getColEnd()){
					String path = Ships.get(shipIndex).getDestroyedH().get(0);
					board.get(row).get(col).changeImage(path);
				}
				//rightmost image
				else if(col == Ships.get(shipIndex).getColBeginning()){
					String path = Ships.get(shipIndex).getDestroyedH().get(2);
					board.get(row).get(col).changeImage(path);
				}
				//middle
				else{
					String path = Ships.get(shipIndex).getDestroyedH().get(1);
					board.get(row).get(col).changeImage(path);
				}
				
			}
		}
		else if (Ships.get(shipIndex).getColEnd() == -1){	//Vertical ship
			if(Ships.get(shipIndex).getRowBeginning() < Ships.get(shipIndex).getRowEnd() ){		//south
				//Top image
				if(row == Ships.get(shipIndex).getRowBeginning()){
					String path = Ships.get(shipIndex).getDestroyedV().get(0);
					board.get(row).get(col).changeImage(path);
				}
				//Bottom image
				if(row == Ships.get(shipIndex).getRowEnd()){
					String path = Ships.get(shipIndex).getDestroyedV().get(2);
					board.get(row).get(col).changeImage(path);
				}
				//Middle image
				else{
					String path = Ships.get(shipIndex).getDestroyedV().get(1);
					board.get(row).get(col).changeImage(path);
				}
				
			}
			else{
				//Top image
				if(row == Ships.get(shipIndex).getRowEnd()){
					String path = Ships.get(shipIndex).getDestroyedV().get(0);
					board.get(row).get(col).changeImage(path);
				}
				//Bottom image
				if(row == Ships.get(shipIndex).getRowBeginning()){
					String path = Ships.get(shipIndex).getDestroyedV().get(2);
					board.get(row).get(col).changeImage(path);
				}
				//Middle image
				else{
					String path = Ships.get(shipIndex).getDestroyedV().get(1);
					board.get(row).get(col).changeImage(path);
				}
			}
			
		}
	}
	//___________________________________________________________________________//
	//Change to dark blue
	public void changeImageMiss(int row, int col){
		board.get(row).get(col).changeImage("battleship/batt101.gif");
	}
	//___________________________________________________________________________//
	
}
