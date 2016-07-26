package edu.virginia.enginge.events;

import edu.virginia.engine.display.*;

public class PopupManager implements IEventListener {
	
	public PopupManager(){
		
	}
	
public void handleEvent(Event event){
	
		
		
		if (event.getEventType() == PopupEvent.PHONE_PICKUP){
			//draw a popup window that says stuff about the phone
			((ItemSprite) event.getSource()).pop();
		}
		
	}

	

}
