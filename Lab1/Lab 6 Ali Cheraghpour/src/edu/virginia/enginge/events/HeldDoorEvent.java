package edu.virginia.enginge.events;

public class HeldDoorEvent extends Event {
	
	public static final String HELD_THE_DOOR = "holding";

	public HeldDoorEvent(String eventType, IEventDispatcher source) {
		super(eventType, source);
		// TODO Auto-generated constructor stub
	}

}
