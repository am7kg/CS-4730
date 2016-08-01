package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.IEventListener;

public class Bar extends ItemSprite implements IEventListener{

	ItemSprite bar = new ItemSprite("bar","bar_placeholder.png");
	
	ArrayList<ItemSprite> barItems = new ArrayList<ItemSprite>();
	
	public Bar(String id) {
		super(id);
		this.setImage("bar_floor_placeholder.png");
		
		barItems.add(bar);
		
		bar.setPosition(100,0);
		
		bar.setParent(this);
		
		this.addChild(bar);
		
	}
	
	public ArrayList<ItemSprite> getBarItems(){
		return barItems;
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

}
