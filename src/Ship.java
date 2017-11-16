import java.util.ArrayList;

import javax.swing.JMenuItem;

public class Ship {
	
	private int index;
	private int length;
	private String name;
	private ArrayList<String> iconNamesHorizontal;
	private ArrayList<String> iconNamesVertical;
	private ArrayList<String> destroyedH;
	private ArrayList<String> destroyedV;
	private JMenuItem shipItem;
	private boolean inWater;
	private boolean Chosen;
	
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
		shipItem = new JMenuItem(name);
		
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
	public ArrayList<String> getShipHorizontal(){
		return iconNamesHorizontal;
	}
	public ArrayList<String> getShipVertical(){
		return iconNamesVertical;
	}
	
	
}
