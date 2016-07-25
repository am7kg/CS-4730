/**
 * 
 */
package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import edu.virginia.engine.util.GameClock;

/**
 * @author alicheraghpour
 *
 */
public class AnimatedSprite extends Sprite {

	ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
	int currentFrame = 0;
	int startIndex = 0;
	int runEndIndex;
	int swagEndIndex;
	int animationSpeed = 100;
	boolean runPlaying;
	boolean swagPlaying;
	
	String animation;
	
	GameClock clock = new GameClock();
	
	public AnimatedSprite(String id, String animation ) {
		super(id);
		this.setAnimation(animation);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(ArrayList<String> pressedKeys){
		super.update(pressedKeys);
		
		BufferedImage megaman = readImage("spritesheet-demo.png");
		
		for ( int i = 0; i < 331; i++ ){
			frames.add(megaman.getSubimage(i,90,30,40));
			i += 27;
		}
		runEndIndex = 10;
		
		// run animation
		if ( this.animation == "run"){
			runPlaying = true;
			swagPlaying = false;
		}
		
		for ( int i = 0; i < 331; i++ ){
			frames.add(megaman.getSubimage(i,50,30,40));
			i += 27;
		}
		swagEndIndex = 24;
		
		//swag animation
		if ( this.animation == "swag") {
			swagPlaying = true;
			runPlaying = false;
		}
	}
		
	
	
	@Override
	public void draw(Graphics g) {
		if (runPlaying)
			if ( clock.getElapsedTime() >= animationSpeed ){
				if ( currentFrame < runEndIndex)
					currentFrame++;
				super.setImage(frames.get(currentFrame));
				if ( currentFrame >= runEndIndex)
					currentFrame = startIndex;
				clock.resetGameClock();
				
			}
		if (swagPlaying)
			if ( clock.getElapsedTime() >= animationSpeed ){
				if ( currentFrame < runEndIndex)
					currentFrame = 11;
				if ( currentFrame < swagEndIndex)
					currentFrame++;
				super.setImage(frames.get(currentFrame));
				if ( currentFrame >= swagEndIndex)
					currentFrame = startIndex;
				clock.resetGameClock();
				
			}
		super.draw(g);
		
	}
	
	public void setSpeed(int a){
		this.animationSpeed = a;
	}
	
	public void setAnimation(String s ) {
		this.animation = s;
	}

}
