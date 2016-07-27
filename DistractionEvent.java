package edu.virginia.enginge.events;

import java.awt.Point;

import javax.swing.text.Position;

public class DistractionEvent extends Event{
	
	private Point where = new Point();
	
	public static final String PHONE_ARROW = "phone arrow";
	
	public DistractionEvent(String eventType, IEventDispatcher source, double x,double y) {
		super(eventType, source);
		this.where.setLocation(x, y);
	}
	
	public Point getPosition(){
		return this.where;
	}

}
