package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.IEventListener;

public class triviaRoom extends ItemSprite implements IEventListener{
	
	public static final String DRINK = "drink";

	ItemSprite bar = new ItemSprite("bar","bar_placeholder.png");
	
	DisplayBox prompts = new DisplayBox("intro", "bar_intro.png");
	DisplayBox exit = new DisplayBox("exit","exit.png");
	
	ArrayList<ItemSprite> barItems = new ArrayList<ItemSprite>();
	ArrayList<DisplayBox> displayBoxes = new ArrayList<DisplayBox>();
	
	public triviaRoom(String id) {
		super(id);
		this.setImage("bar_floor_placeholder.png");
		
		barItems.add(bar);
		
		bar.setPosition(100,0);
		
		bar.setParent(this);
		
		this.addChild(bar);
		
		//displayBoxes and their exit childs for the level
		//intro text
		prompts.setPosition(25, 150);
		prompts.setParent(this);
		prompts.setVisible(true);
		this.addChild(prompts);
		//exit button for intro text
		displayBoxes.add(exit);
		exit.setParent(prompts);
		exit.setVisible(true);
		prompts.addChild(exit);
		
		exit.addEventListener(this, this.DRINK);	
	}
	
	public ArrayList<ItemSprite> getBarItems(){
		return barItems;
	}
	
	public ArrayList<DisplayBox> getBoxes(){
		return this.displayBoxes;
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getEventType() == this.DRINK){
			if (exit.getId() == "exit"){
				exit.getParent().setVisible(false);
			}
		}
	}

}