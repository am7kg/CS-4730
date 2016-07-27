package edu.virginia.engine.display;

import edu.virginia.enginge.events.DistractionEvent;
import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.IEventListener;
import edu.virginia.enginge.events.PopupEvent;

public class DistractionSprite extends ItemSprite implements IEventListener{

	public static final String PHONE_ARROW = "phone arrow";
	
	public DistractionSprite(String id) {
		super(id);
		this.setVisible(false);
		
	}


	@Override
	public void handleEvent(Event event) {
		if (event.getEventType() == PHONE_ARROW){
			System.out.println("event is being handled");
			this.setImage("right_arrow.png");
			this.setPosition(((DistractionEvent)event).getPosition());
			this.setVisible(true);
		}
		
	}

}
