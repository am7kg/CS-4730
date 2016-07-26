package edu.virginia.enginge.events;

import edu.virginia.engine.display.Sprite;

public class PopupManager implements IEventListener {
	
public void handleEvent(Event event){
		
		if (event.getEventType() == PopupEvent.PHONE_PICKUP){
			//draw a popup window that says stuff about the phone
			((Sprite) event.getSource()).Pop();
		}
		
	}

	

}
