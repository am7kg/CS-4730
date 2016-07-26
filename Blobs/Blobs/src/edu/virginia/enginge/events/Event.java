package edu.virginia.enginge.events;

public class Event {
	
	String eventType;
	IEventDispatcher source;

	public Event(String eventType, IEventDispatcher source){
		this.eventType = eventType;
		this.source = source;
	}
	
	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the source
	 */
	public IEventDispatcher getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(IEventDispatcher source) {
		this.source = source;
	}






}
