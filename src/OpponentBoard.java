import java.awt.Color;
import java.awt.event.MouseEvent;

public class OpponentBoard extends Board{
	
	private boolean turn;
	
	public OpponentBoard(GUI g, boolean gameStarted, boolean t){
		super(g);
		turn = t;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Label l = (Label)e.getSource();
        
        if(this.currentLabel != null){
        	currentLabel.changeColor(Color.BLACK);
        }
        l.changeColor(Color.RED);
        
        gui.send("" + l.getRow()+ " "+ l.getCol());
        
        System.out.println(l.getRow() + " " + l.getCol());
        currentLabel = l;
        gui.repaint();
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
		Label l = (Label)e.getSource();
		if(gui.gameHasBegun()){
		
	        if( l.getText() == ""  && !currentLabel.isChosen() ){
	        	
	        	//Erase previous markings
	        	if(this.currentLabel != null && !currentLabel.isChosen()&& currentLabel.getText() == ""){
		        	currentLabel.changeColor(Color.BLACK);
		        	
		        }
	        			        
		        l.changeColor(Color.RED);
		        currentLabel = l;
	        }
	        
		}
	}
	
	public void updateBoard(int row, int col, int hit) {
		if(hit == 1){ 	//Was a hit
			this.hits++;
			board.get(row).get(col).changeImage("battleship/batt103");			
		}
		else{			//Was a miss
			this.misses++;
			board.get(row).get(col).changeImage("battleship/batt102");		
		}
	}
	
}
