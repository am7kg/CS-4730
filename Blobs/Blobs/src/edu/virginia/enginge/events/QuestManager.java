package edu.virginia.enginge.events;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.PhysicsSprite;
import edu.virginia.engine.display.Sprite;


public class QuestManager implements IEventListener {

	public QuestManager(){
		
	}
	
	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if ( event.getEventType() == CollideEvent.COLLISION ){
			((PhysicsSprite) event.getSource()).setYforce(0);
			((PhysicsSprite) event.getSource()).setYvelocity(0);
		}
		if ( event.getEventType() == HeldDoorEvent.HELD_THE_DOOR){
			((Sprite) event.getSource()).setVisible(false);
		}

	}

}
