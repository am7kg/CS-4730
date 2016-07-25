package edu.virginia.engine.tweening;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.util.GameClock;

public class TweenParam {
		
	TweenableParam param;
		
	double startVal;
	double endVal;
	double endTime;
	double TweenTime;
	
	boolean isComplete = false;
	
	public TweenParam(TweenableParam paramToTween, double startVal, double endVal,
			double time){
		this.param = paramToTween;
		this.startVal = startVal;
		this.endVal = endVal;
		this.endTime = time;
		this.TweenTime = 0;
	}
	
	/*
	public void update(){
		
		System.out.println("begin:" + currTime );
		
		// Calculate percent done : currTime over endTime 
		percentDone = currTime / endTime;
		
		// Apply transition to startVal : percent done is in decimal
		applyTransition(percentDone);
		
		currTime += time.getElapsedTime();
		System.out.println("end:" + currTime);
		
	}
	*/
	
	
	/**
	 * @return the tweenTime
	 */
	public double getTweenTime() {
		return TweenTime;
	}

	/**
	 * @param tweenTime the tweenTime to set
	 */
	public void setTweenTime(double tweenTime) {
		TweenTime = tweenTime;
	}

	/**
	 * @return the param
	 */
	public TweenableParam getParam() {
		return param;
	}

	/**
	 * @param param the param to set
	 */
	public void setParam(TweenableParam param) {
		this.param = param;
	}

	/**
	 * @return the startVal
	 */
	public double getStartVal() {
		return startVal;
	}

	/**
	 * @param startVal the startVal to set
	 */
	public void setStartVal(double startVal) {
		this.startVal = startVal;
	}

	/**
	 * @return the endVal
	 */
	public double getEndVal() {
		return endVal;
	}

	/**
	 * @param endVal the endVal to set
	 */
	public void setEndVal(double endVal) {
		this.endVal = endVal;
	}

	/**
	 * @return the endTime
	 */
	public double getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}


	

}
