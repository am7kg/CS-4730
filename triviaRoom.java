package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.IEventListener;

public class triviaRoom extends ItemSprite implements IEventListener{
	
	public static final String DRINK = "drink";

	ItemSprite trivia = new ItemSprite("bar","trivia.png");
	
	DisplayBox prompts = new DisplayBox("intro", "trivia_come.png");
	DisplayBox exit = new DisplayBox("exit","exit.png");
	DisplayBox yes = new DisplayBox("yes","trivia_yes.png");
	DisplayBox no = new DisplayBox("no","trivia_no.png");
	DisplayBox ok = new DisplayBox("ok","trivia_ask_away.png");
	
	Sprite blub = new Sprite("blub", "blub.png");
	
	ArrayList<ItemSprite> trivItems = new ArrayList<ItemSprite>();
	ArrayList<DisplayBox> displayBoxes = new ArrayList<DisplayBox>();
	
	public triviaRoom(String id) {
		super(id);
		this.setImage("bar_floor_placeholder.png");
		
		trivItems.add(trivia);
		trivia.setPosition(100,0);
		trivia.setParent(this);
		this.addChild(trivia);
		
		blub.setPosition(250, 250);
		blub.setParent(this);
		this.addChild(blub);
		
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
		
		yes.setVisible(true);
		no.setVisible(true);
		yes.setParent(prompts);
		no.setParent(prompts);
		this.trivItems.add(yes);
		this.trivItems.add(no);

		prompts.addChild(no);
		prompts.addChild(yes);
		yes.setPosition(prompts.getUnscaledWidth()/4,prompts.getUnscaledHeight()-10);
		no.setPosition(prompts.getUnscaledWidth()/2,prompts.getUnscaledHeight()-10);
		
		exit.addEventListener(this, this.DRINK);
		yes.addEventListener(this, this.DRINK);
		no.addEventListener(this, this.DRINK);
	}
	
	public ArrayList<ItemSprite> getTivItems(){
		return trivItems;
	}
	
	public ArrayList<DisplayBox> getBoxes(){
		return this.displayBoxes;
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getEventType() == this.DRINK){
			if (((DisplayObject) event.getSource()).getId() == "exit"){
				exit.getParent().setVisible(false);
			}
			if (((DisplayObject) event.getSource()).getId() == "yes"){
				prompts.setImage("trivia_lets.png");
				yes.setImage("trivia_ask_away.png");
				yes.setPosition(prompts.getUnscaledWidth()/3, prompts.getUnscaledHeight()-10);
				no.setVisible(false);
				System.out.println("yes");
				yes.setId("ask_away");
			} else
			if (((DisplayObject) event.getSource()).getId() == "no"){
				prompts.setImage("trivia_oh_we_playing.png");
				System.out.println("no");
				yes.setVisible(false);
				no.setImage("trivia_ask_away.png");
				no.setPosition(prompts.getUnscaledWidth()/3, prompts.getUnscaledHeight()-10);
				no.setId("ask_away");
			} else
			if (((DisplayObject) event.getSource()).getId() == "ask_away"){
				prompts.setImage("trivia_love.png");
				yes.setVisible(true);
				no.setVisible(true);
				yes.setImage("trivia_yes.png");
				no.setImage("trivia_no.png");
				yes.setPosition(prompts.getUnscaledWidth()/4,prompts.getUnscaledHeight()-10);
				no.setPosition(prompts.getUnscaledWidth()/2,prompts.getUnscaledHeight()-10);
				yes.setId("ask_again");
				no.setId("ask_again");
			} else 
			if (((DisplayObject) event.getSource()).getId() == "ask_again"){
				prompts.setImage("trivia_sky.png");
				yes.setId("trivia_done");
				no.setId("trivia_done");
			} else 
			if (((DisplayObject) event.getSource()).getId() == "trivia_done"){
				prompts.setImage("trivia_shirt.png");
				yes.setId("non_sense");
				no.setId("non_sense");
				no.setVisible(false);
				yes.setImage("trivia_sense.png");
			} else 
			if (((DisplayObject) event.getSource()).getId() == "non_sense"){
				prompts.setImage("trivia_won.png");
				yes.setVisible(true);
				no.setVisible(true);
				yes.setImage("trivia_yes.png");
				no.setImage("trivia_no.png");
				yes.setId("throw_up");
				no.setId("exit");
			} else 
			if (((DisplayObject) event.getSource()).getId() == "throw_up"){
				prompts.setImage("trivia_puke.png");
				yes.setVisible(false);
				no.setVisible(false);	
			} 
		}
	}

}