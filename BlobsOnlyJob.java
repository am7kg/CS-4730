package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import edu.virginia.engine.display.BlobSprite;
import edu.virginia.engine.display.DisplayBox;
import edu.virginia.engine.display.DistractionSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.HouseSprite;
import edu.virginia.engine.display.ItemSprite;
import edu.virginia.engine.sound.SoundManager;
import edu.virginia.engine.util.GameClock;
import edu.virginia.enginge.events.DistractionEvent;
import edu.virginia.enginge.events.PopupEvent;


/**
 * 
 * @author alicheraghpour
 * 4/28/16
 * Version 1.01 (Alpha)
 * 
 * Not finished with all of the tasks that we assigned in class today.
 * Changes in: BlobsOnlyJob.java, HouseSprite.java, DisplayObject.java
 * Resources: painted_house.png, unpainted_house.png
 * 
 * Blob now travels by touching the end of the screen. This will change the gameMode
 * thus drawing new things on the screen. Going left takes him to the bar (for now)
 * and going down takes him outside his house to the mini game. Right now going left
 * or down on any screen will take you to those screens. I will fix that in the next
 * patch.
 * 
 * Started the implementation of the HouseSprite mechanic, you should be able to
 * go outside the house and click on Mr. Bean to change his image. Made a new class 
 * for this called HouseSprite.java.
 * 
 * Lastly I added the bed function. For now, the bed is spawned in the beginning and
 * when Blob collides with it the game ends and we see a game over message for beating
 * the game. This changes the gameMode int to 7.
 * 
 * That's it for tonight, I'm exhausted.
 *
 */

public class BlobsOnlyJob extends Game implements MouseListener {

	
	
	public BlobsOnlyJob() {
		super("Blob's Only Job", 500, 500);
		// TODO Auto-generated constructor stub
		brushes.add(0,PopupEvent.BRUSH_FIRST);
		brushes.add(1, PopupEvent.BRUSH_SECOND);
		brushes.add(2, PopupEvent.BRUSH_THIRD);
		brushes.add(3,PopupEvent.BRUSH_FOURTH);
		brushes.add(4,PopupEvent.BRUSH_FIFTH);
	
	}
	
	public static void main(String[] args) {
		BlobsOnlyJob game = new BlobsOnlyJob();
		game.start();
	}
	
	Rectangle a = new Rectangle(200, 200, 300 , 300 );

	int b = 0;
	int frame = 1;
	
	// Sprites
	
	BlobSprite Blob = new BlobSprite("Blob", "blob1.png");

	ItemSprite Phone = new ItemSprite("Phone","phone.png");
	ItemSprite Brush = new ItemSprite("Brush", "brush.jpeg");
	ItemSprite Bed = new ItemSprite("Bed", "bed.png");
	
	DisplayBox Phone_Pickup = new DisplayBox("Phone_Pickup", "phone_pickup_event.png");
	DisplayBox Brush_Pickup = new DisplayBox("Brush_Pickup", "brush_first_event.png");
	
	DistractionSprite heyListen = new DistractionSprite("heyListen","right_arrow.png");
	
	private static ArrayList<String> brushes = new ArrayList<String>();

	int gameMode = 0;
	
	int MouseX;
	int MouseY;

	ArrayList<ItemSprite> Remover = new ArrayList<ItemSprite>();
	
	int frames = 1;
	
	int disCount = 0;
	int n = 0;
	String distraction = PopupEvent.BRUSH_FIRST;
	
	//House Sprites
	HouseSprite house1 = new HouseSprite("house1","unpainted_house.png"); 
	
	//House Sprites List
	ArrayList<HouseSprite> HouseList = new ArrayList<HouseSprite>();
	
	
	ArrayList<ItemSprite> ItemList = new ArrayList<ItemSprite>();
	
	boolean popupMode = false;
	
	SoundManager mSM = new SoundManager();
	
	GameClock t = new GameClock();
	
	
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
		
		//Added House
		if ( Blob != null && Phone != null && Brush != null && ItemList != null
				&& house1 != null && HouseList != null ) { 
		
		// if blob is not null
			if ( frames == 1 ) {
				Blob.setPosition(200,400);
			}
		
		if ( pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_UP))) 
			Blob.getPosition().translate(0, -5);
		if ( pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_DOWN)))
			Blob.getPosition().translate(0, 5);
		if ( pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_RIGHT)))
			Blob.getPosition().translate(5, 0);
		if ( pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_LEFT)))
			Blob.getPosition().translate(-5, 0);

		// Add visible items to the ItemList
		if ( Phone.isVisible() && !ItemList.contains(Phone))
			ItemList.add(Phone);
		
		Phone.setPosition(100, 100);
		
		if ( Brush.isVisible() && !ItemList.contains(Brush))
			ItemList.add(Brush);
		
		Brush.setPosition(400, 400);
		
		//House Sprite(s)
		if ( gameMode == 1 )
			house1.setVisible(true);
		else
			house1.setVisibility(false);
		
		house1.setPosition(100,100);
		
		if ( house1.isVisible() && !HouseList.contains(house1))
			HouseList.add(house1);
		//
		
		
		
		//Bed Item
		Bed.setPosition(300,300);
		
		if ( Bed.isVisible() && !ItemList.contains(Bed))
			ItemList.add(Bed);
		
		if ( Blob.collidesWith(Bed) ){
			//Normally throw an event
			for ( ItemSprite i : ItemList )
				i.setVisible(false);
			gameMode = 7;
		}
		
		if ( gameMode == 7 )
			Blob.setVisible(false);
		
		//
		
		if ( Phone_Pickup.isVisible() && !ItemList.contains(Phone_Pickup))
			ItemList.add(Phone_Pickup);
		
		if ( Brush_Pickup.isVisible() && !ItemList.contains(Brush_Pickup))
			ItemList.add(Brush_Pickup);
		
		if ( heyListen.isVisible() && !ItemList.contains(heyListen))
			ItemList.add(heyListen);
		
		
		Phone_Pickup.setPosition(100,150);
		
		
		Phone.addEventListener(Phone_Pickup, PopupEvent.PHONE_PICKUP);
		
		Phone_Pickup.addEventListener(Phone_Pickup, PopupEvent.PHONE_CLOSE);
		
		Brush_Pickup.setPosition(100,150);
		
		Brush.addEventListener(Brush_Pickup, PopupEvent.BRUSH_FIRST);
		Brush.addEventListener(Brush_Pickup, PopupEvent.BRUSH_SECOND);
		Brush.addEventListener(Brush_Pickup, PopupEvent.BRUSH_THIRD);
		Brush.addEventListener(Brush_Pickup, PopupEvent.BRUSH_FOURTH);
		Brush.addEventListener(Brush_Pickup, PopupEvent.BRUSH_FIFTH);
		
		Brush_Pickup.addEventListener(Brush_Pickup, PopupEvent.BRUSH_CLOSE);
		Brush_Pickup.addEventListener(heyListen, DistractionEvent.PHONE_ARROW);
		
		if ( Blob.getPosition().getY() >= 500 ){
			for ( ItemSprite i : ItemList )
				i.setVisible(false);
			Blob.setPosition((int)Blob.getPosition().getX(),0);
			gameMode = 1;
		}
		
		if ( Blob.getPosition().getX() <= -1){
			for ( ItemSprite i : ItemList )
				i.setVisible(false);
			Blob.setPosition(500-Blob.getUnscaledWidth(),(int)Blob.getPosition().getY());
			gameMode = 2;
		}
		
		
		//Game Over
		if ( pressedKeys.contains("J"))
			this.exitGame();
		}
		
		frames++;

	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		// if not null draw blob and the sprites
		// Added House
		if ( Blob != null && Phone != null && Brush != null  && Phone_Pickup != null && Brush_Pickup != null && heyListen != null
				&& house1 != null && HouseList != null ){
			Blob.draw(g);
			Phone.draw(g);
			Brush.draw(g);
			Phone_Pickup.draw(g);
			Brush_Pickup.draw(g);
			heyListen.draw(g);
			Bed.draw(g);
		}
		if ( gameMode == 1 ) {
			/*
			g.drawRect(a.x, a.y, a.width, a.height);
			if ( a.contains(MouseX, MouseY) )  
				g.fillRect(a.x, a.y, a.width, a.height);
			g.drawString("Blob's House", 350, 350);
			*/
			// Rewritten for new House mechanic
			for ( HouseSprite i : HouseList )
				i.draw(g);
			}
		if ( gameMode == 2 ) {
			g.drawRect(150, 0, 300, 150);
			g.drawString("Bar", 300, 50 );
		}
		if ( gameMode == 7 )
			g.drawString("Yay! You did it! You beat the thing!", 150, 220);
		}
		
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// Will delete this after new functionality is tested to work
		
			// House check
		if ( gameMode == 1 ){
			for ( HouseSprite i : HouseList ) {
				if ( i.getGlobalHitBox().contains(e.getX(), e.getY() - 25 )) {
					i.paintHouse();
					
				}
			}
		}
			else{
					
		for ( ItemSprite i : ItemList ) {
			 if ( i.getGlobalHitBox().contains(e.getX(), e.getY() - 25) ) {
				 heyListen.setVisible(false);
				 if ( i.getId() == "Phone" && !popupMode){
					 System.out.println("You clicked the Phone");
					 i.dispatchEvent( new PopupEvent(PopupEvent.PHONE_PICKUP,i));
					 popupMode = true;
				 }
				 if ( i.getId() == "Brush" && disCount < 5  && !popupMode){
					 i.dispatchEvent( new PopupEvent(brushes.get(disCount),i));					 
					 System.out.println("You clicked the Brush");
					 if (disCount<3){disCount++;}
					 popupMode = true;
				 }
				 if ( i.getId() == "Phone_Pickup"){
					 i.dispatchEvent( new PopupEvent(PopupEvent.PHONE_CLOSE,i));
					 System.out.println("Phone_Popup was clicked");
					 Remover.add(i);
					 popupMode = false;
				 }
				 if ( i.getId() == "Brush_Pickup"){
					 i.dispatchEvent( new PopupEvent(PopupEvent.BRUSH_CLOSE,i));
					 if(disCount == 1){
						 System.out.println("dispatch zone");
						 i.dispatchEvent( 
						new DistractionEvent(
						DistractionSprite.PHONE_ARROW,i,Phone.getPosition().getX()-30,Phone.getPosition().getY()));
					 }
					 System.out.println("Brush_Popup was clicked");
					 Remover.add(i);
					 popupMode = false;
				 }
					 
			 }	
			}
		for ( ItemSprite i : Remover ) {
			ItemList.remove(i);
		}
		}
		if ( Blob.getGlobalHitBox().contains(e.getX(), e.getY() - 25 ) ){
			if ( n < 20) System.out.println("chill bro");
			if ( n > 20 && n < 100 ) System.out.println("please stop");
			if ( n > 100 && n < 1000 ) System.out.println("clicking on me doesn't do anything!!");
			if ( n == 1001 ) System.out.println("JESUS CHRIST");
			n++;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}