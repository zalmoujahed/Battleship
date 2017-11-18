import java.util.ArrayList;

import javax.swing.JMenuItem;

public class Ship {
	
	private int index;
	private int length, hits, rowB, rowE, colB, colE;
	private String name;
	private ArrayList<String> iconNamesHorizontal;
	private ArrayList<String> iconNamesVertical;
	private ArrayList<String> destroyedH;
	private ArrayList<String> destroyedV;
	private JMenuItem shipItem;
	private boolean inWater;
	private boolean Chosen;
	private boolean shipHasSunk;
	
	public Ship(int len,String n,int i, ArrayList<String> namesH, ArrayList<String> namesV, ArrayList<String> desH, ArrayList<String> desV){
		
		length = len;
		iconNamesHorizontal = namesH;
		iconNamesVertical = namesV;
		destroyedH = desH;
		destroyedV = desV;
		name = n;
		index = i;
		inWater = false;
		Chosen = false;
		shipHasSunk = false;
		shipItem = new JMenuItem(name);
		hits = 0;
		
		rowB = -1;
		rowE = -1;
		colB = -1;
		colE = -1;		
				
	}
	
	public void setChosen(boolean chosen){
		Chosen = chosen;
	}
	public void setInWater(boolean in){
		inWater = in;
	}
	
	public boolean isChosen(){
		return Chosen;
	}
	public boolean isInWater(){
		return inWater;
	}

	public JMenuItem getShipItem(){
		return shipItem;
	}

	public String getName(){
		return name;
	}
	public int getIndex(){
		return index;
	}
	public int getLength(){
		return length;
	}
	public ArrayList<String> getShipHorizontal(int r, int c1, int c2){
		rowB = r;
		colB = c1;
		colE = c2;
		return iconNamesHorizontal;
	}
	public ArrayList<String> getShipVertical(int r1, int r2, int c){
		rowB = r1;
		rowE = r2;
		colB = c;
		return iconNamesVertical;
	}
	public ArrayList<String> getDestroyedH(){
		return destroyedH;
	}
	public ArrayList<String> getDestroyedV(){
		return destroyedV;
	}
	public void incrementHit(){
		hits++;
	}
	public int getHits(){
		return hits;
	}
	public void setShipSunk(boolean sink){
		shipHasSunk = sink;
	}
	public boolean shipIsSunk(){
		return shipHasSunk;
	}
	
	
	public int getRowBeginning(){
		return rowB;
	}
	public int getRowEnd(){
		return rowE;
	}
	public int getColBeginning(){
		return colB;
	}
	public int getColEnd(){
		return colE;
	}
	//fix this
	public String getDestroyedLabel(){
		
		return "";
	}
	

	
	
	
}
