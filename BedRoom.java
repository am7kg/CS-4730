package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.BlobsOnlyJob.BlobsOnlyJob;
import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.IEventListener;
import edu.virginia.enginge.events.PopupEvent;

public class BedRoom extends ItemSprite implements IEventListener{
	
	public final static String CLEAN_MESS = "clean mess";
	public final static String INSTRUCT = "instruct";
	public final static String GOT_GAME = "got game";
	
	//Dirty version of bedroom Sprites
	ItemSprite dirtyBed = new ItemSprite("dirty bed","dirty_bed_placeholder.png");
	ItemSprite dirtyStand = new ItemSprite("dirty stand","dirty_stand_placeholder.png");
	ItemSprite dirtyDesk = new ItemSprite("dirty desk","dirty_desk_placeholder.png");
	ItemSprite dirtyFloor = new ItemSprite("dirty floor","dirty_floor_placeholder.png");
	
	//TV Sprite ya feel me
	ItemSprite tv = new ItemSprite("tv","tv_placeholder.png");
	
	//Promps for the level
	DisplayBox prompts = new DisplayBox("intro","bedroom_intro.png");
	DisplayBox exit = new DisplayBox("next","exit.png");
	
	ArrayList<ItemSprite> furniture = new ArrayList<ItemSprite>();
	ArrayList<DisplayBox> displayBoxes = new ArrayList<DisplayBox>();
	

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
		dirtyBed.addEventListener(this, this.INSTRUCT);
		tv.addEventListener(this, this.INSTRUCT);
		
		
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
		
		exit.addEventListener(this, this.INSTRUCT);

	}
	
	public ArrayList<ItemSprite> getFurniture(){
		return this.furniture;
	}
	
	public ArrayList<DisplayBox> getBoxes(){
		return this.displayBoxes;
	}



	@Override
	public void handleEvent(Event event) {
		for (ItemSprite i : furniture){
			if (event.getSource() == i && event.getSource() != tv){
				i.setImage("clean_"+i.getImageFileName());
			}
		}
		
		if (event.getEventType() == this.INSTRUCT){
			if (exit.getId() == "next"){
				exit.getParent().setImage("bedroom_instructions.png");
				exit.setId("exit_intro");
			} else
			
			if (exit.getId() == "exit_intro"){	
				exit.getParent().setVisible(false);
				exit.setId("game_start");
			} else
		
			if (exit.getId() == "game_start"){
				System.out.println("event is thrown");
				exit.setId("game");
				prompts.setVisible(true);
				prompts.setImage("bedroom_game_text.png");
			} else
			
			if (exit.getId() == "game"){
				exit.setId("exit_game");
				prompts.setImage("game_aquired.png");
			} else
			
			if (exit.getId() == "exit_game"){
				exit.setId("achievement_start");
				prompts.setVisible(false);
				prompts.setImage("game_awesome.png");
			} else
			
			if (exit.getId() == "achievement_start"){
				prompts.setVisible(true);
				exit.setId("achievement_exit");
				prompts.setImage("game_awesome.png");
			} else
				
			if (exit.getId() == "achievement_exit"){
				exit.setId("game_achievement");
				prompts.setImage("got_achievement.png");
				
			} else
			
			if (exit.getId() == "game_achievement"){
				exit.setId("go_outside");
				prompts.setImage("go_walk.png");
			} else
			
			if (exit.getId() == "go_outside"){
				prompts.setVisible(false);
			}
				
		}
		
	}

}
