package edu.virginia.enginge.events;

import edu.virginia.engine.display.Sprite;

public class CollideEvent extends Event {

	public static final String COLLISION = "collide";
	
	public CollideEvent(String eventType, IEventDispatcher source) {
		super(eventType, source);
		// TODO Auto-generated constructor stub
	}
}
