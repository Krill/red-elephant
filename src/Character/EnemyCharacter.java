package Character;

import java.awt.geom.Ellipse2D;
import Character.PlayerCharacter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import Handler.TimeHandler;
import Item.Item;

@SuppressWarnings("serial")
public class EnemyCharacter extends AttributeCharacter implements Cloneable
{
	private PlayerCharacter player;
	
	private ArrayList<Item> inventory; 
	
    private float dropRate;
    private int senseRadius;
    private int deathCounter;
    
    private static final Random random = new Random();    
    
    private boolean isMoving;
    private boolean isHostile;
    private boolean detectedPlayer;    
    
    public EnemyCharacter(int id, int x, int y, int width, int height, String name, int health,
                            boolean isAttackable, int speed,float d,
                                boolean isHostile, int senseRadius, Item[] items)
    {
        super(id, x, y, width, height, name, health, isAttackable, speed, senseRadius);       
        this.dropRate = d;
        this.isHostile = isHostile;  
        inventory = new ArrayList<>();
        
        for(Item item : items){
            inventory.add(item);
        }
        
        isMoving = true;
        detectedPlayer = false;
        deathCounter = 8;       
    }
    
    @Override
    public EnemyCharacter clone()
    {
    	try{
    		EnemyCharacter copy = (EnemyCharacter)super.clone();
    		return copy;
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error Cloning");
    	}
    	
		return null;
    }
    
    public float getDropRate()
    {
        return dropRate;
    }
    
    public boolean isHostile()
    {
        return isHostile;
    }
    
    public boolean hasDetectedPlayer()
    {
        return detectedPlayer;
    }
    
    public void setDetectedPlayer(boolean detectedPlayer, PlayerCharacter player)
    {
        this.detectedPlayer = detectedPlayer;
        this.player = player;
    }
    
    public int getSenseRadius()
    {
        return senseRadius;
    }
    
    public void setSenseRadius(int senseRadius)
    {
        this.senseRadius = senseRadius;
    }
    
    public Ellipse2D.Double getSenseArea(){
    	return new Ellipse2D.Double(getX() - (senseRadius/2) + (getWidth()/2), 
    			 getY() - (senseRadius/2) + (getHeight()/2), senseRadius, senseRadius);
    }
    
    @Override
    public void interact(PlayerCharacter player)
    {
    	//Interact..
    }
    
    public boolean isMoving()
    {
 	   return isMoving;
    }
    
    public void setMoving()
    {
 	   isMoving = !isMoving;
    }
    
    public void update(){
    	if(getHealth()>0){
    		if( detectedPlayer){
    			moveToPlayer();
    		
    		}else{
    			moveRandom();
    		}
    	}else{
    		die();
    		
    		setChanged();
    		notifyObservers("Monster");
    	}
    }
    
    public void moveToPlayer()
    {
		resetDirection();

		int dx = player.getX()-getX();
		int dy = player.getY()-getY();

		// if( Math.abs(dx) < WeaponItem.range() && Math.abs(dy) < WeaponItem.range()  ){
		if( Math.abs(dx) < 30 && Math.abs(dy) < 30 ){
			// Attack with delay 1500 ms inbetween slashes
			if(getTimeStamp() == 0)
			{
				setTimeStamp(TimeHandler.getTime());
				setActionTime(1500);
				resetDirection();				
				setAttacking(true);
			}
			
			if( !TimeHandler.timePassed(getTimeStamp(), getActionTime()))
			{
				// Do nothing..
			}
			else
			{
				setTimeStamp(0);
			}
		}else{
			if(dy > 0){
				if( Math.abs(dy) > Math.abs(dx) ){
					moveY(1);
					move();
				}else{
					if(dx < 0){
						moveX(-1);
						move();
					}else{
						moveX(1);
						move();
					}
				}
			}else{
				if( Math.abs(dy) > Math.abs(dx) ){
					moveY(-1);
					move();
				}else{
					if(dx < 0){
						moveX(-1);
						move();
					}else{
						moveX(1);
						move();
					}

				}
			}
		}
	}

	/**
	 * Generate random movement for enemy.
	 */
    public void moveRandom()
    {		
		if(getTimeStamp() == 0)		// If clock is reset..
		{		
			setTimeStamp(TimeHandler.getTime());		// Set clock
			setActionTime(random.nextInt(1500) + 500);	// Set a random duration (500-2000 ms) to move/stay										
			switch(random.nextInt(4))  					// Randomize a direction			
				{
				case 0:
					moveY(-1);
					break;
				case 1:
					moveY(1);
					break;
				case 2:
					moveX(1);
					break;
				case 3:
					moveX(-1);
					break;
				}		
		}
				
		if( !TimeHandler.timePassed(getTimeStamp(), getActionTime()) )	// If time hasn't expired, move or stand still..
		{
			if(isMoving())
			{
				move();			
			}
			else resetDirection();
		}
		else											// ..else reset clock and invert isMoving state
		{						
			setMoving();
			setTimeStamp(0);
		}
	}
	
	
	/**
	 * Kill enemy after playing a short animation
	 */
	public void die()
	{		
		if( getTimeStamp() == 0)
		{
			setTimeStamp(TimeHandler.getTime());
			setActionTime(100);
			rotate();
		}	
		
		if( !TimeHandler.timePassed(getTimeStamp(), getActionTime()) )	// If time hasn't expired..
		{
			// ..do nothing
		}
		else											
		{		
			if(deathCounter != 0)
			{
				setTimeStamp(0);
				deathCounter--;
			}
			else if(!isDead())
			{
				giveInventory();
				setDead(true);
			}
			else
			{
				setDead(true);
			}
		}		
	}

	public void rotate()
	{
		switch(getDirection())
		{
			case "up":
				setDirection("right");
				break;
			case "right":
				setDirection("down");
				break;
			case "down":
				setDirection("left");
				break;
			case "left":
				setDirection("up");
				break;
			default:
				System.out.println("rotate() is bugged");
		}
	}
	
	//give (maybe drop) inventory to player.
	public void giveInventory()
	{
		
		if(random.nextInt(10) <= (int)(dropRate*10))
		{
			Iterator<Item> it = inventory.iterator();
			while(it.hasNext())
			{
		  		Item i = it.next();
				player.addToInventory(i);
	  		}
		}
	}
	
	public List<Item> getInventory()
	{
	    return inventory;
	} 
}


