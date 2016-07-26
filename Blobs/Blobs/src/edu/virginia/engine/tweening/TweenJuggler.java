package edu.virginia.engine.tweening;

import java.util.ArrayList;

public class TweenJuggler {
	
	static ArrayList<Tween> tweens = new ArrayList<Tween>();
	
	private static TweenJuggler instance;
	
	public TweenJuggler() {
		if (instance != null) System.out.println("ERROR can not reinitialize singleton class");
		instance = this;
	}
	
	public static TweenJuggler getInstance(){
		return instance;
	}
	
	public static void add(Tween tween){
		tweens.add(tween);
	}
	
	public void nextFrame(){
		// in Game class call this in update method 60 fps
		for ( Tween t : tweens )
			t.update();
		//dispose of complete tweens
		for ( int i = tweens.size() - 1; i >= 0; i-- ){
			if ( tweens.get(i).isComplete() )
				tweens.remove(i);
		}
	}

}
