package edu.virginia.engine.tweening;

import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.IEventDispatcher;

public class TweenEvent extends Event {
	
	final static String TWEEN_COMPLETE_EVENT = "tween complete";
	final static String TWEEN_START_EVENT = "tween start";

	
	// The source is a Tween

	public TweenEvent(String eventType, IEventDispatcher source) {
		super(eventType, source);
		// TODO Auto-generated constructor stub
	}

	public Tween getTween() {
		return (Tween) super.getSource();
	}

}
