package edu.virginia.engine.tweening;

import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.util.GameClock;
import edu.virginia.enginge.events.EventDispatcher;
import edu.virginia.enginge.events.IEventDispatcher;

public class Tween extends EventDispatcher {

	DisplayObject object;
	ArrayList<TweenParam> list = new ArrayList<TweenParam>();
	
	GameClock t = new GameClock();
	
	double percentDone;
	
	public Tween(DisplayObject object){
		this.object = object;
	}
	
	public Tween(DisplayObject object, TweenTransition transition  ){
		this.object = object;
		
	}
		
	public void animate(TweenableParam param, double startVal, double endVal, double time){
		TweenParam tp = new TweenParam(param, startVal, endVal, time );
		list.add(tp);
	}
	
	public void update(){
		for ( TweenParam tp : list ){
			if ( !tp.isComplete ){
				
				// Calculate Percent Done
				percentDone = tp.getTweenTime() / tp.getEndTime();
			
				// Throw an event that the Tween started : Perhaps somebody somewhere is listening to this
				if ( percentDone == 0 ){
					this.object.dispatchEvent(new TweenEvent(TweenEvent.TWEEN_START_EVENT, this ));
				}
		
				// Apply transition to startVal : percent done is in decimal
				// We can change what transition we are doing according to some variable
				applyTransition(percentDone, tp);

				// Update the current time for our Tween
				tp.setTweenTime(tp.getTweenTime() + t.getElapsedTime());;
				t.resetGameClock();	

				// Throw an event that the Tween ended : Same reason
				if ( percentDone >= 1 ){
					this.object.dispatchEvent(new TweenEvent(TweenEvent.TWEEN_COMPLETE_EVENT, this ));
					tp.isComplete = true;
				}
			}
		}
	}
	
	public boolean isComplete(){
		int i = 0;
		for ( TweenParam tp : list )
			if ( tp.isComplete )
				i++;
		if ( i == list.size() )
			return true;
		return false;
	}
	
	
	public void applyTransition(double percentDone, TweenParam tp){
		// Obtain value of percent done in transition
		double a = ((tp.getEndVal() - tp.getStartVal()) * percentDone ) + tp.getStartVal();
		
		// Update the Param
		if ( tp.getParam() == TweenableParam.ALPHA ){
			// Alpha check
			if ( a < 0 )
				a = 0;
			if ( a > 1 )
				a = 1;
			this.object.setAlpha((float)a);
		}
		if ( tp.getParam() == TweenableParam.ROTATION ){
			this.object.setRotation(a);
		}
		if ( tp.getParam() == TweenableParam.X_POS ){
			this.object.setPosition((int)a, this.object.getPosition().y);
		}
		if ( tp.getParam() == TweenableParam.X_SCALE ){
			this.object.setScaleX(a);
		}
		if ( tp.getParam() == TweenableParam.Y_POS ){
			this.object.setPosition(this.object.getPosition().x, (int)a);
		}
		if ( tp.getParam() == TweenableParam.Y_SCALE ){
			this.object.setScaleY(a);
		}
	}

}
