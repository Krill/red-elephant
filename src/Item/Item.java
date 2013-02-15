package Item;

import Utility.Entity;

/**
 * Abstract class for all Items in the game
 * 
 * @author kristoffer/kevin
 * @version 0.1
 */
public abstract class Item extends Entity{

	// fields:
	private String name;
	private boolean isVisible;
	private float itemValue;
	
	/**
	 * Constructor
	 * 
	 * @param id
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param name
	 * @param itemValue
	 * @param isVisible
	 */
	public Item(int id, int x, int y, int width, int height, String name, boolean isVisible, float itemValue){
		super(id, x, y, width, height);
		
		this.name = name;
		this.isVisible = isVisible;
		this.itemValue = itemValue;
	}
	
	/**
	 * Returns the items name
	 * @return name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns true if item is visible
	 * @return isVisible
	 */
	
	public boolean isVisible(){
		return isVisible;
	}
	
	/**
	 * Sets the visibility of this item
	 * @param isVisible
	 */
	public void setVisible(boolean isVisible){
		this.isVisible = isVisible;
	}
	
	/**
	 * Sets the value of the item;
	 * @param itemValue;
	 */
	public void setItemValue(float itemValue){
		this.itemValue = itemValue;
	}
		
	/**
	 * returns the value of the item;
	 * @param itemValue;
	 */
	public float getItemValue(){
		return itemValue;
	}
	
	
}

