package Engine;

import java.io.Serializable;
import java.util.ArrayList;

import Character.CivilianCharacter;
import Character.Character;
import Quest.Quest;
import World.Map;

/**
 * This class keeps track of the course of the game. When a quest or
 * something mandatory in the game is completed and event will be triggered.
 * @author kristoffer
 */
public class EventEngine implements Serializable{
	
	// fields:
	private GameEngine engine;
	private ArrayList<Quest> quests;
	
	// constants:
	private static final long serialVersionUID = 126L;
	
	/**
	 * Constructor
	 * @param engine
	 */
	public EventEngine(GameEngine engine){	
		this.engine = engine;
		this.quests = new ArrayList<Quest>();
		
		addQuests();
	}
	
	/**
	 * Iterates through every civilian in the world and add their quests to the list
	 */
	private void addQuests(){
		for(Map map : engine.getWorld().getMaps()){
			for(Character c : map.getCharacters()){
				if(c instanceof CivilianCharacter){
					
					// add each civilian charaters quest to the list
					for(Quest q : ((CivilianCharacter) c).getQuests()){
						quests.add(q);
					}		
				}
			}
		}
	}
	
	/**
	 * Updates the events
	 */
	public void update(){
		checkQuests();
	}
	
	/**
	 * Check if any quest is done, and should lead to an event
	 */
	private void checkQuests(){
		for(int i=0; i < quests.size(); i++){
			Quest q = quests.get(i);
			
			firstQuest(q, i);
		}
	}
	
	/**
	 * Handles events triggered by the first event
	 * @param q
	 * @param id
	 */
	private void firstQuest(Quest q, int id){
		// Check if first quest completed, if so, do something

	}
}
