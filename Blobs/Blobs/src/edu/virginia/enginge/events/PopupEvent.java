package edu.virginia.enginge.events;

public class PopupEvent extends Event {
	
	public static final String PHONE_PICKUP = "phone pickup";

	public PopupEvent(String eventType, IEventDispatcher source) {
		super(eventType, source);
		
	}
	
	

}
