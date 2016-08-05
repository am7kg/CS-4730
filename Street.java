package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.BlobsOnlyJob.BlobsOnlyJob;
import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.IEventListener;
import edu.virginia.enginge.events.PopupEvent;

public class Street extends ItemSprite implements IEventListener{
	
	public static final String OBTAIN = "obtain";
	
	//Promps for the level
	DisplayBox prompts = new DisplayBox("intro","bedroom_intro.png");
	DisplayBox exit = new DisplayBox("next","exit.png");
	
	ItemSprite cat = new ItemSprite("cat","cat.png");
	
	ArrayList<ItemSprite> kittens = new ArrayList<ItemSprite>();
	ArrayList<DisplayBox> displayBoxes = new ArrayList<DisplayBox>();
	

	public Street(String id) {
		super(id);
		this.setImage("street.png");
		
		
		kittens.add(cat);
		
		cat.setPosition(250, 250);
		
		cat.setParent(this);
		
		this.addChild(cat);
		
		cat.addEventListener(this, this.OBTAIN);
		

	}
	
	public ArrayList<ItemSprite> getKittens(){
		return this.kittens;
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getEventType() == this.OBTAIN){
			System.out.print("you got a kitty");
				
		}
		
	}

}