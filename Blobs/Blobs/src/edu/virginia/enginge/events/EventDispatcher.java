package edu.virginia.enginge.events;

import java.util.ArrayList;
import java.util.HashMap;

public class EventDispatcher implements IEventDispatcher {

	//private ArrayList<IEventListener> listeners = new ArrayList<IEventListener>();
	private HashMap<String, ArrayList<IEventListener>> map = new HashMap<String, ArrayList<IEventListener>>();
	
	@Override
	public void addEventListener(IEventListener listener, String eventType) {
		if (!map.containsKey(eventType)){
			map.put(eventType, new ArrayList<IEventListener>());
			addEventListener(listener,eventType);
		}
		else
			map.get(eventType).add(listener);
	}

	@Override
	public void removeEventListener(IEventListener listener, String eventType) {
		map.get(eventType).remove(listener);
		if ( map.get(eventType).isEmpty() ) {
			map.remove(eventType);
		}
	}

	@Override
	public void dispatchEvent(Event event) {
		//notify()
		if ( map.containsKey(event.eventType) ){
			for ( IEventListener i : map.get(event.eventType)){
				i.handleEvent(event);
			}
		}
	}

	@Override
	public boolean hasEventListener(IEventListener listener, String eventType) {
		if (map.containsKey(eventType))
			if (map.get(eventType).contains(listener))
				return true;
		return false;
	}

}
