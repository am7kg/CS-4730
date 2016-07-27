package edu.virginia.engine.display;

import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.IEventListener;
import edu.virginia.enginge.events.PopupEvent;

public class DisplayBox extends ItemSprite implements IEventListener {

	public DisplayBox(String id, String fileName) {
		super(id, fileName);
		this.visible = false;
	}

	@Override
	public void handleEvent(Event event) {
		
		if (event.getEventType() == PopupEvent.PHONE_PICKUP){
			this.setVisible(true);
		}
		
		if (event.getEventType() == PopupEvent.PHONE_CLOSE){
			this.setVisible(false);
			
		}
		
		if (event.getEventType() == PopupEvent.BRUSH_FIRST){
			this.setImage("brush_first_event.png");
			this.setVisible(true);
		}
		
		if (event.getEventType() == PopupEvent.BRUSH_SECOND){
			this.setImage("brush_second_event.png");
			this.setVisible(true);
		}
		
		if (event.getEventType() == PopupEvent.BRUSH_THIRD){
			this.setImage("brush_third_event.png");
			this.setVisible(true);
		}
		
		if (event.getEventType() == PopupEvent.BRUSH_FOURTH){
			this.setImage("brush_fourth_event.png");
			this.setVisible(true);
		}
		
		if (event.getEventType() == PopupEvent.BRUSH_CLOSE){
			this.setVisible(false);
		}
		
	}
	
	

}
