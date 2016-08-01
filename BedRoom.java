package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.IEventListener;

public class BedRoom extends ItemSprite implements IEventListener{
	
	public final static String CLEAN_MESS = "clean mess";
	
	//Dirty version of bedroom Sprites
	ItemSprite dirtyBed = new ItemSprite("dirty bed","dirty_bed_placeholder.png");
	ItemSprite dirtyStand = new ItemSprite("dirty stand","dirty_stand_placeholder.png");
	ItemSprite dirtyDesk = new ItemSprite("dirty desk","dirty_desk_placeholder.png");
	ItemSprite dirtyFloor = new ItemSprite("dirty floor","dirty_floor_placeholder.png");
	
	//Clean version of bedroom Sprites
	Sprite cleanBed = new Sprite("clean bed","clean_dirty_bed_placeholder.png");
	Sprite cleanStand = new Sprite("clean stand","clean_dirty_stand_placeholder.png");
	Sprite cleanDesk = new Sprite("clean desk","clean_dirty_desk_placeholder.png");
	
	//TV Sprite ya feel me
	ItemSprite tv = new ItemSprite("tv","tv_placeholder.png");
	
	ArrayList<ItemSprite> furniture = new ArrayList<ItemSprite>();
	

	public BedRoom(String id) {
		super(id);
		this.setImage("bedroom_floor_placeholder.png");
		
		furniture.add(dirtyBed);
		furniture.add(dirtyDesk);
		furniture.add(dirtyStand);
		furniture.add(dirtyFloor);
		furniture.add(tv);
		
		dirtyBed.setPosition(450,200);
		dirtyStand.setPosition(450, 250);
		dirtyDesk.setPosition(100, 430);
		dirtyFloor.setPosition(250, 250);
		tv.setPosition(250, 0);
		
		dirtyBed.setParent(this);
		dirtyStand.setParent(this);
		dirtyDesk.setParent(this);
		dirtyFloor.setParent(this);
		tv.setParent(this);
		this.addChild(dirtyBed);
		this.addChild(dirtyStand);
		this.addChild(dirtyDesk);
		this.addChild(dirtyFloor);
		this.addChild(tv);
		
	}
	
	public ArrayList<ItemSprite> getFurniture(){
		return this.furniture;
	}



	@Override
	public void handleEvent(Event event) {
		for (ItemSprite i : furniture){
			if (event.getSource() == i && event.getSource() != tv){
				i.setImage("clean_"+i.getImageFileName());
			}
		}
		
	}

}
