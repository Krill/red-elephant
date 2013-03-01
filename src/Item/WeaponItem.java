package Item;

/**
 * WeaponItem extends Item and has two instance variables for a attack damage and a attack speed.
 * WeaponItem contains a weapons attack speed and how much damage a weapon inflicts on its opponents, weapons are added to the inventory and are equipped when in use.
 * @author norling/kevin
 * @version 0.1
 */

public class WeaponItem extends Item{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 123878;
	private int attackDamage;
	private int attackSpeed;
	private int attackRange;
	
	/**
	 * Constructor
	 * 
	 * @param id
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param name
	 * @param isVisible
	 * @param itemValue
	 * @param attackDamage
	 * @param attackSpeed
	 * @param attackRange
	 */
	public WeaponItem(int id, int x, int y, int width, int height, String name, boolean isVisible, int itemValue, int attackDamage, int attackSpeed,  int attackRange){	
		
		super(id,x,y,width,height,name,isVisible, itemValue);
		this.attackDamage = attackDamage;
		this.attackSpeed = attackSpeed;
		this.attackRange = attackRange;
	}
	
	
	/**
	 * Sets attack damage for the item
	 * @param attackDamage
	 */
	public void setAttackDamage(int attackDamage){
		this.attackDamage = attackDamage;
	}
	
	/**
	 * Sets attack speed for the item
	 * @param attackSpeed
	 */
	public void setAttackSpeed(int attackSpeed){
		this.attackSpeed = attackSpeed;
	}
	
	/**
	 * Returns the attack damage
	 * @return attackDamage
	 */
	public int getAttackDamage(){
		return attackDamage;
	}
	
	/**
	 * Returns the attack speed
	 * @return attackSpeed
	 */
	public int getAttackSpeed(){
		return(attackSpeed);
	}
	
	/**
	 * Sets Wepons attackRange
	 * @param attackRange
	 */
	public void setAttackRange(int attackRange){
		this.attackRange = attackRange;
	}
	/**
	 * Returns the attack range
	 * @return attackRange
	 */
	public int getAttackRange(){
		return attackRange;
	}
}