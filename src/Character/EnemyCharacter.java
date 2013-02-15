package Character;

import java.awt.geom.Ellipse2D;
import Character.PlayerCharacter;
import java.util.Random;
import Handler.TimeHandler;

public class EnemyCharacter extends AttributeCharacter implements Moveable, Interactable
{
    private float dropRate;
    private boolean isHostile;
    private int senseRadius;
    private static final Random random = new Random();    

    public EnemyCharacter(int id, int x, int y, int width, int height, String name,
                            boolean isAttackable, int health, int speed,float d,
                                boolean isHostile, int senseRadius)
    {
        super(id, x, y, width, height, name, isAttackable, health, speed, senseRadius);       
        this.dropRate = d;
        this.isHostile = isHostile;     
        setTimeStamp(0);
    }
    
    public float getDropRate()
    {
        return dropRate;
    }
    
    public boolean isHostile()
    {
        return isHostile;
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
    
    public void update(){
    	
    	moveRandom();
    }
    
    public void moveToPlayer(PlayerCharacter player){

		resetDirection();

		int dx = player.getX()-getX();
		int dy = player.getY()-getY();

		// if( Math.abs(dx) < WeaponItem.range() && Math.abs(dy) < WeaponItem.range()  ){
		if( Math.abs(dx) < 20 && Math.abs(dy) < 20 ){
			//attack();
		}


		if(dy > 0){
			if( Math.abs(dy) > Math.abs(dx) ){
				setDown(true);
				move();
			}else{
				if(dx < 0){
					setLeft(true);
					move();
				}else{
					setRight(true);
					move();
				}
			}
		}else{
			if( Math.abs(dy) > Math.abs(dx) ){
				setUp(true);
				move();
			}else{
				if(dx < 0){
					setLeft(true);
					move();
				}else{
					setRight(true);
					move();
				}

			}
		}
	}

	/**
	 * Generate random movement for enemy.
	 */
    public void moveRandom(){		
		
		if(getTimeStamp() == 0)		// Decide new direction
		{			
			switch(random.nextInt(4))
			{
			case 0:
				setUp(true);
				break;
			case 1:
				setDown(true);
				break;
			case 2:
				setRight(true);
				break;
			case 3:
				setLeft(true);
				break;
			}			
			setTimeStamp(System.currentTimeMillis());			
		}
				
		if( !TimeHandler.timePassed(getTimeStamp(), 2000) )
		{
			move();			
		}
		else
		{
			resetDirection();
			setTimeStamp(0);
		}
	}
	
	/**
	 * Moves this 1 unit in the specified direction.
	 */	
	public void move()
	{
    	// reference to set direction
    	int dx = getX();
    	int dy = getY();
    	
    	// move character
        if(isUp()){
        	setY(getY()-1);
        }
        if(isLeft()){
        	setX(getX()-1);
        }
        if(isRight()){
        	setX(getX()+1);
        }
        if(isDown()){
        	setY(getY()+1);
        }  
        
        // set the current direction
        if(dx-getX() < 0){
        	setDirection("right");
        } else if(dx-getX() > 0) {
        	setDirection("left");
        }
        
        if(dy-getY() < 0){
        	setDirection("down");
        } else if(dy-getY() > 0){
        	setDirection("up");
        }
	}

	/**
	 * Resets all directions for the enemy.
	 */
	public void resetDirection(){
		setUp(false);
		setRight(false);
		setDown(false);
		setLeft(false);
	}

}