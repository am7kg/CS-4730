package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.IEventListener;

public class Bar extends ItemSprite implements IEventListener{
	
	public static final String DRINK = "drink";
	public static final String BLUB = "hey blub";
	
	double time = 0;

	ItemSprite bar = new ItemSprite("bar","bar_placeholder.png");
	
	DisplayBox prompts = new DisplayBox("intro", "bar_intro.png");
	DisplayBox exit = new DisplayBox("exit","exit.png");
	
	ArrayList<ItemSprite> barItems = new ArrayList<ItemSprite>();
	ArrayList<DisplayBox> displayBoxes = new ArrayList<DisplayBox>();
	
	public Bar(String id) {
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
		this.addEventListener(this, BLUB);
	}
	
	public ArrayList<ItemSprite> getBarItems(){
		return barItems;
	}
	
	public ArrayList<DisplayBox> getBoxes(){
		return this.displayBoxes;
	}
	
	public double getTime(){
		return time;
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getEventType() == this.DRINK){
			if (exit.getId() == "exit"){
				exit.getParent().setVisible(false);
				time = (int) System.currentTimeMillis();
			} else if (exit.getId() == "triv_start"){
				prompts.setImage("trivia_dots.png");
				exit.setId("dots");
			} else if (exit.getId() == "dots"){
				prompts.setImage("trivia_jager.png");
				exit.setId("jager");
			} else if (exit.getId() == "jager"){
				prompts.setVisible(false);
			}
		}
		if (event.getEventType() == this.BLUB){
			if(exit.getId() == "exit"){
				exit.getParent().setVisible(true);
				prompts.setImage("trivia_start.png");
				exit.setId("triv_start");
			} 
		}
		
	}

}
