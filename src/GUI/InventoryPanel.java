package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Character.PlayerCharacter;
import Handler.ItemImageHandler;
import Item.Item;

/**
 * Show the players inventory
 * @author kristoffer
 */
@SuppressWarnings("serial")
public class InventoryPanel extends JPanel implements Observer{
	
	// fields:
	private JPanel slotPanel;
	private JPanel equippedPanel;
	private ItemImageHandler itemImages;
	
	// consants:
	private static final String EMPTY_ICON = "Empty";
	private static final String PANEL_BACKGROUND = "images/gui/inventory.png";
	
	/**
	 * Constructor
	 */
	public InventoryPanel(){
		
		itemImages = new ItemImageHandler();
		
		setPanelDetails();
		createTopPanel();
		createBottomPanel();
	}
	
	/**
	 * Sets this panels default visuals
	 */
	private void setPanelDetails(){
		setOpaque(true);
		setDoubleBuffered(true);
		setPreferredSize(new Dimension(200, 640));
		setVisible(true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	/**
	 * Paints a background image
	 */
	public void paintComponent(Graphics g) {
		Image img = new ImageIcon(PANEL_BACKGROUND).getImage();	
		g.drawImage(img, 0, 0, null);
	}
	
	/**
	 * Creates the topPanel which handles the inventory slots
	 */
	private void createTopPanel(){
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		topPanel.setPreferredSize(new Dimension(180, 420));
		
		// fill out inventory label
		add(Box.createVerticalStrut(50));
		
		slotPanel = new JPanel();
		slotPanel.setOpaque(false);
		slotPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 10));
		slotPanel.setPreferredSize(new Dimension(180, 420));
		
		topPanel.add(slotPanel, BorderLayout.SOUTH);
		add(topPanel);
	}
	
	/**
	 * Creates the bottomPanel which shows which items is active
	 */
	private void createBottomPanel(){
		// fill out equipped label
		add(Box.createVerticalStrut(50));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		bottomPanel.setPreferredSize(new Dimension(180, 80));
		
		equippedPanel = new JPanel();
		equippedPanel.setOpaque(false);
		equippedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 10));
		equippedPanel.setPreferredSize(new Dimension(180, 100));
		
		bottomPanel.add(equippedPanel);
		
		add(bottomPanel);
	}

	/**
	 * Handles the updates in the inventory
	 * @param player
	 * @param items
	 */
	private void updateInventory(PlayerCharacter player, ArrayList<Item> items){
		
		// clean slots
		slotPanel.removeAll();
		equippedPanel.removeAll();
		
		// update panels
		updateSlots(player, items);
		updateEquip(player, items);
		
		slotPanel.revalidate();
		equippedPanel.revalidate();
	}
	
	/**
	 * Updates the inventory slots
	 * @param player
	 * @param items
	 */
	private void updateSlots(PlayerCharacter player, ArrayList<Item> items){
		// paint taken slots
		for(Item item : items){
			ItemIcon itemIcon = new ItemIcon(item.getName());
			slotPanel.add(itemIcon);			
		}
		
		// paint empty slots
		for(int i=player.getMaxInventorySize(); i>items.size(); i--){
			ItemIcon itemIcon = new ItemIcon(EMPTY_ICON);
			slotPanel.add(itemIcon);			
		}
	}
	
	/**
	 * Updates the equipslots
	 * @param player
	 * @param items
	 */
	private void updateEquip(PlayerCharacter player, ArrayList<Item> items){
		
		// local variable
		String itemName;
		
		// weaponslot
		if(player.getWeapon() != null){
			itemName = player.getWeapon().getName();
		} else {
			itemName = "Empty";
		}
		ItemIcon weaponIcon = new ItemIcon(itemName);
		equippedPanel.add(weaponIcon);
		
		// weaponslot
		if(player.getWeapon() != null){
			itemName = player.getArmor().getName();
		} else {
			itemName = "Empty";
		}
		ItemIcon armorIcon = new ItemIcon(itemName);
		equippedPanel.add(armorIcon);		
	}
	
	/**
	 * Reset the inventory when game loads etc...
	 * @param player
	 */
	public void reset(PlayerCharacter player){
		updateInventory(player, player.getInventory());
	}
	
	/**
	 * When somthing has changed in the inventory, update is called
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof PlayerCharacter && arg instanceof ArrayList<?>){	
			updateInventory( (PlayerCharacter) o, (ArrayList<Item>) arg);
		}	
	}
}
