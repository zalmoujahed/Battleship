import java.awt.Color;
import java.awt.Image;

import javax.swing.*;

public class Label extends JLabel{
	
	private int row, col;
	private boolean hasShip;
	private boolean Chosen;
	private int north, south, east, west = -1;
	
	public Label(String name, int x, int y) {
		
		super(name);
		row = x; 
		col = y;
		hasShip = false;
		
		if(name == ""){
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			setSize(20, 20);
			this.setHorizontalAlignment(CENTER);
			ImageIcon imageIcon = new ImageIcon(getClass().getResource("battleship/batt100.gif"), "water"); 
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(this.getWidth()*5, this.getHeight()*5,  Image.SCALE_SMOOTH); 
			imageIcon = new ImageIcon(newimg);  
			
			this.setIcon(imageIcon);	

		}
	}
	
	public void changeColor(Color c){
		this.setBorder(BorderFactory.createLineBorder(c, 1));
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public void changeImage(String path){
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(path)); 
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(40, 40,  Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);  
		
		this.setIcon(imageIcon);
		
	}
	public void setChosen(boolean chosen){
		Chosen = chosen;
	}
	public boolean isChosen(){
		return Chosen;
	}
	public void setHasShip(boolean ship){
		hasShip = ship;
	}
	public boolean hasShip(){
		return hasShip;
	}
	public void resetNeighbors(){
		north = south = west = east = -1;
	}
	public void setNeighbors(int length){
		resetNeighbors();
		
		if(row + length - 1  < 10){
			south = row + length - 1;
		}
		
		if(row - length + 1> -1){
			north = row - length + 1;
		}
		
		if(col + length - 1 < 10){
			east = col + length - 1;
		}
		
		if(col - length + 1 > -1){
			west = col - length + 1;
		}
	}
	public int getNorth(){return north;}
	public int getSouth(){return south;}
	public int getEast(){return east;}
	public int getWest(){return west;}
	public void setNorth(int i){
		north = i;
	}
	public void setSouth(int i){
		south = i;
	}
	public void setEast(int i){
		east = i;
	}
	public void setWest(int i){
		west = i;
	}

}
