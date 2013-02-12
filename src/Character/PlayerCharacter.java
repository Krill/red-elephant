package Character;

import java.util.ArrayList;

public class PlayerCharacter extends AttributeCharacter implements Moveable
{
    private boolean gender;
    private boolean sexPref;
    private int money;
    private int inventorySize;
//    private List<Quest> quests;
//    private List<Item> inventory;
//    private Item primaryAttack;    

    public PlayerCharacter(int x, int y, int width, int height, String name,
                            boolean isAttackable, int health, int speed, boolean gender,
                                boolean sexPref, int money, int inventorySize)
    {
        super(x, y, width, height, name, isAttackable, health, speed);
        this.gender = gender;
        this.sexPref = sexPref;
        this.money = money;
        this.inventorySize = inventorySize;       
        
//        quests = new ArrayList<Quest>();       
//        inventory = new ArrayList<Item>();      
    }
    
    public boolean getGender()
    {
        return gender;
    }
    
    public boolean getSexPref()
    {
        return sexPref;
    }
    
    public int getMoney()
    {
        return money;
    }
    
    public int getInventorySize()
    {
        return inventorySize;
    }
    
    public void update()
    {
        move();
    }
    
    @Override
    public void move()
    {
        //Move character..
    }
}