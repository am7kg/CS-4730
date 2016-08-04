package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.IEventListener;

public class Museum extends ItemSprite implements IEventListener{
	
	public static final String ARTY = "arty";

	ItemSprite art = new ItemSprite("art","art_placeholder.png");
	
	DisplayBox prompts = new DisplayBox("intro", "art_intro.png");
	DisplayBox exit = new DisplayBox("exit","exit.png");
	
	ArrayList<ItemSprite> gallery = new ArrayList<ItemSprite>();
	ArrayList<DisplayBox> displayBoxes = new ArrayList<DisplayBox>();
	
	public Museum(String id) {
		super(id);
		this.setImage("museum_floor_placeholder.png");
		
		gallery.add(art);
		
		art.setPosition(250,250);
		
		art.setParent(this);
		
		this.addChild(art);
		
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
				
		exit.addEventListener(this, this.ARTY);
		
	}
	
	public ArrayList<ItemSprite> getGallery(){
		return gallery;
	}
	
	public ArrayList<DisplayBox> getBoxes(){
		return this.displayBoxes;
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getEventType() == this.ARTY){
			if (exit.getId() == "exit"){
				exit.getParent().setVisible(false);
			}
		}
		
	}

	

}
