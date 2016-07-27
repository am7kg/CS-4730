package edu.virginia.enginge.events;

import java.util.ArrayList;

public class PopupEvent extends Event {
	
	public static final String PHONE_PICKUP = "phone pickup";
	
	public static final String PHONE_CLOSE = "phone close";
	
	public static final String BRUSH_FIRST = "brush first";

	public static final String BRUSH_SECOND = "brush second";

	public static final String BRUSH_THIRD = "brush third";

	public static final String BRUSH_FOURTH = "brush fourth";

	public static final String BRUSH_FIFTH = "brush fifth";
	
	public static final String BRUSH_CLOSE = "brush close";
	
	

	public PopupEvent(String eventType, IEventDispatcher source) {
		super(eventType, source);
	}
	

}
