import java.awt.Color;
import java.awt.event.MouseEvent;

public class OpponentBoard extends Board{

	
	public OpponentBoard(GUI g){
		super(g);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Label l = (Label)e.getSource();
        
        if(this.currentLabel != null){
        	currentLabel.changeColor(Color.BLACK);
        }
        l.changeColor(Color.RED);
        gui.printToPanel("opponent");
        System.out.println(l.getRow() + " " + l.getCol());
        currentLabel = l;
        gui.repaint();
		
	}
	
}
