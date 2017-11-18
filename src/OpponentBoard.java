import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class OpponentBoard extends Board{
	
	
	public OpponentBoard(GUI g){
		super(g);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(gui.gameHasBegun() && gui.opgame()){
			Label l = (Label)e.getSource();
	        
	        gui.send("" + (l.getRow()+1)+ " "+ (l.getCol()+1));
	        checkWin();
	        gui.repaint();
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
		Label l = (Label)e.getSource();
		if(gui.gameHasBegun() && gui.getTurn()){
		
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
			board.get(row).get(col).changeImage("battleship/batt103.gif");			
		}
		else{			//Was a miss
			this.misses++;
			board.get(row).get(col).changeImage("battleship/batt102.gif");		
		}
		gui.turn();
	}
	public void checkWin(){
		if(gui.win()){
			JOptionPane.showMessageDialog( gui,"Sorry you lost."
	                , "You Lost", JOptionPane.PLAIN_MESSAGE);
			gui.send("wi");
		}
	}
	
	
}
