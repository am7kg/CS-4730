package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.PhysicsSprite;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.sound.SoundManager;
import edu.virginia.engine.tweening.Tween;
import edu.virginia.engine.tweening.TweenJuggler;
import edu.virginia.engine.tweening.TweenableParam;
import edu.virginia.engine.util.GameClock;
import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.HeldDoorEvent;
import edu.virginia.enginge.events.QuestManager;

public class LabSixGame extends Game {

	PhysicsSprite Hodor = new PhysicsSprite("Hodor", "hodor.png");
	Sprite Door = new Sprite("Door", "door.png");
	SoundManager mSM = new SoundManager();
	
	Sprite Rekt = new Sprite("Rekt", "rekt.png");
	Sprite Rekt2 = new Sprite("Rekt2", "rekt.png");
	
	GameClock t = new GameClock();
	
	double startTime = 0;
	double endTime = 0;
	double dt = 0;
	
	double dm = .1;
	
	int frame = 1;
	
	boolean a;
	boolean b;
	boolean c;
	boolean d;

	public LabSixGame() {
		super("Hold the door?", 750, 750);
	}

	@SuppressWarnings("static-access")
	@Override
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
		
		if (Hodor != null && Door != null && t != null && Rekt != null && Rekt2 != null
			) {
			
			// Our Tweens
			Tween hodorTween = new Tween(Hodor);

			
			// Tween Hodor into existence 
			if (frame == 1) { 
				hodorTween.animate(TweenableParam.Y_POS, 0.0, 100, 500);
				hodorTween.animate(TweenableParam.X_SCALE, 0.1, 1.0, 500);
				hodorTween.animate(TweenableParam.Y_SCALE, 0.1, 1.0, 500);
			}
			
			endTime = t.getElapsedTime();
			dt = endTime - startTime;
			
			a = Hodor.collidesWith(Rekt);
			b = Hodor.collidesWith(Rekt2);
						
			if (!a || !b ){
				double i = Hodor.getYforce()+.5;
				Hodor.setYforce(i);
			}
			
			if ( pressedKeys.contains("L") && (a||b) ){
				Hodor.jump();
			}
			
			// Hodor is heavy
			Hodor.setMass(1);
						
			Hodor.updatePhysics(dm*10);
			
			Hodor.update(pressedKeys);
			Door.setPosition(600, 100);
			Rekt.setPosition(-100,450);
			Rekt2.setPosition(400,350);
			
			if ( !mSM.getMySoundManager().containsKey("got"))
				mSM.LoadMusic("got", "resources/got.wav");
			if ( !mSM.getMySoundManager().get("got").isRunning() )
				mSM.PlayMusic("got");
			if ( !mSM.getMySoundManager().containsKey("aura"))
				mSM.LoadSoundEffect("aura", "resources/1aura.wav");
			
			if ( Hodor.collidesWith(Door) ){
				Hodor.setImage("wylis.jpg");
				Door.setVisible(false);
				mSM.PlaySoundEffect("aura");
				hodorTween.animate(TweenableParam.X_POS, Hodor.getPosition().getX(), 350, 1000);
				hodorTween.animate(TweenableParam.Y_POS, Hodor.getPosition().getY(), 350, 1000);
				hodorTween.animate(TweenableParam.ALPHA, 1.0, 0, 1500);
			}
				
			if (pressedKeys.contains("←"))
				Hodor.getPosition().translate((int)(dm*-100), 0);
			if (pressedKeys.contains("→") && !(a&&b) )
				Hodor.getPosition().translate((int)(dm*100), 0);
			
			//overlap testing
			c = Hodor.overlap(Rekt);
			d = Hodor.overlap(Rekt2);
			
			if ( c || d ){
				dm /= 2;
				c = Hodor.overlap(Rekt);
				d = Hodor.overlap(Rekt2);
				if (c || d )
					dm /= 2;
				Hodor.updatePhysics(dm);
			}
			


			//Tweening			
			TweenJuggler.getInstance().add(hodorTween);
			
			startTime = endTime;
			
			dm = .1;
			frame++;
		}
}
	

	public void draw(Graphics g) {
		super.draw(g);

		if (Door != null && Hodor != null && Rekt != null && Rekt2 != null) {
			Door.draw(g);
			Hodor.draw(g);
			Rekt.draw(g);
			Rekt2.draw(g);
			
			g.drawString("Press L to jump", 0, 50);

		}

	}

	public static void main(String[] args) {
		LabSixGame game = new LabSixGame();
		game.start();
	}

}
