package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.IEventListener;

public class Museum extends ItemSprite implements IEventListener{

	ItemSprite art = new ItemSprite("art","art_placeholder.png");
	
	ArrayList<ItemSprite> gallery = new ArrayList<ItemSprite>();
	
	public Museum(String id) {
		super(id);
		this.setImage("museum_floor_placeholder.png");
		
		gallery.add(art);
		
		art.setPosition(250,250);
		
		art.setParent(this);
		
		this.addChild(art);
		
	}
	
	public ArrayList<ItemSprite> getGallery(){
		return gallery;
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	

}
