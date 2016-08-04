package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import edu.virginia.engine.display.Bar;
import edu.virginia.engine.display.BedRoom;
import edu.virginia.engine.display.BlobSprite;
import edu.virginia.engine.display.DisplayBox;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.DistractionSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.HouseSprite;
import edu.virginia.engine.display.Inventory;
import edu.virginia.engine.display.ItemSprite;
import edu.virginia.engine.display.Museum;
import edu.virginia.engine.display.Spouse;
import edu.virginia.engine.sound.SoundManager;
import edu.virginia.engine.tweening.Tween;
import edu.virginia.engine.tweening.TweenableParam;
import edu.virginia.engine.util.GameClock;
import edu.virginia.enginge.events.DistractionEvent;
import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.PopupEvent;


/**
 * 
 * @author alicheraghpour
 * 4/30/16
 * Version 1.02 (Alpha)
 * 
 * Almost done, just waiting for other functionality to start designing the levels,
 * finishing the bed function and the house minigame.
 * 
 * Changes in: BlobsOnlyJob.java
 * 
 * Instead of creating new classes for the different screens, I just use a gameMode int
 * and the draw method will draw different things to the screen depending on
 * what the gameMode is. 0 is inside, 1 is outside, 2 is bar etc. etc.
 * 
 * Blob now transitions back and forth between the maps.
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
		

		bedroom.addEventListener(bedroom, bedroom.CLEAN_MESS);
	
		Bed.setVisible(false);
		
		Blob.setPosition(200, 200);
		
		//Music and Sound
		mSM.LoadMusic("cute", "resources/cute.wav");
		mSM.PlayMusic("cute");
		
		mSM.LoadMusic("dubstep", "resources/dubstep.wav");
		mSM.LoadSoundEffect("test", "resources/sfxtest.wav");
				
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
	ItemSprite Brush = new ItemSprite("Brush", "brush.png");
	ItemSprite Bed = new ItemSprite("Bed", "bed.png");
	
	DisplayBox Phone_Pickup = new DisplayBox("Phone_Pickup", "phone_pickup_event.png");
	DisplayBox Brush_Pickup = new DisplayBox("Brush_Pickup", "brush_first_event.png");
	
	DistractionSprite heyListen = new DistractionSprite("heyListen");
	
	private static ArrayList<String> brushes = new ArrayList<String>();

	int gameMode = 0;
	
	ArrayList<ItemSprite> Remover = new ArrayList<ItemSprite>();
	
	int frames = 1;
	
	int disCount = 0;
	int n = 0;
	String distraction = PopupEvent.BRUSH_FIRST;
	
	//House Sprites
	HouseSprite house = new HouseSprite("house","unpainted_house.png"); 
	HouseSprite roof = new HouseSprite("roof","unpainted_roof.png");
	HouseSprite door = new HouseSprite("door","unpainted_door.png");
	
	//House Sprites List
	ArrayList<HouseSprite> HouseList = new ArrayList<HouseSprite>();
	
	
	ArrayList<ItemSprite> ItemList = new ArrayList<ItemSprite>();
	
	boolean popupMode = false;
	
	SoundManager mSM = new SoundManager();
	
	GameClock t = new GameClock();
	
	//inventory
	Inventory inventory = new Inventory("inventory");
	//ItemSprite inventory = new ItemSprite("inventory", "inventory1.png");
		
	//Collectable Items
	ItemSprite art = new ItemSprite("art","item_art1.png");
	ItemSprite pint = new ItemSprite("pint","item_pint.png");
	ItemSprite videoGame = new ItemSprite("videoGame","item_videogame.png");
	ItemSprite invBrush = new ItemSprite("brush","item_brush.png");	
	
	//Each level object creation
	BedRoom bedroom = new BedRoom("bedroom");
	Bar bar = new Bar("bar");
	Museum museum = new Museum("museum");
		
	Spouse spouse = new Spouse("spouse");
	
	// spouses angry messages
	int k = 0;
	
	// More distractions
	DistractionSprite right = new DistractionSprite("right","right_arrow.png");
	DistractionSprite left = new DistractionSprite("left","left_arrow.png");
	DistractionSprite down = new DistractionSprite("down","down_arrow.png");
	DistractionSprite up = new DistractionSprite("up","up_arrow.png");
	
	//Tweening
	Tween test = new Tween(heyListen);
	
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
		
		//Added House
		if ( Blob != null && Phone != null && Brush != null && ItemList != null
				&& house != null && HouseList != null && spouse != null 
				&& inventory != null ) { 
						
		if ( pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_UP))) 
			Blob.getPosition().translate(0, -5);
		if ( pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_DOWN)))
			Blob.getPosition().translate(0, 5);
		if ( pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_RIGHT)))
			Blob.getPosition().translate(5, 0);
		if ( pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_LEFT)))
			Blob.getPosition().translate(-5, 0);

		
		// Sound test
		if ( pressedKeys.contains("L"))
			mSM.PlaySoundEffect("test");
		if ( pressedKeys.contains("K")){
			mSM.StopMusic("cute");
			mSM.PlayMusic("dubstep");
		}
		
		// Directions
		down.setPosition(196,440);
		up.setPosition(196,0);
		right.setPosition(440,196);
		left.setPosition(0, 196);

		if (!down.isVisible())
			down.setVisible(true);
		if (!up.isVisible())
			up.setVisible(true);
		if (!right.isVisible())
			right.setVisible(true);
		if (!left.isVisible())
			left.setVisible(true);
		
		//House Sprite(s)
		if ( gameMode == 1 ) {
			house.setVisible(true);
			roof.setVisible(true);
			door.setVisible(true);
		}
		else {
			house.setVisible(false);
			roof.setVisible(false);
			door.setVisible(false);
		}
		
		if ( gameMode == 0 ){
			Brush.setVisible(true);
			Phone.setVisible(true);	
		}
		else{
			Brush.setVisible(false);
			Phone.setVisible(false);
			}

		if ( down.isVisible() )
			System.out.println("askdjfh");
		
		house.setPosition(200,50);
		roof.setPosition(200,50);
		door.setPosition(200,50);
		
		if ( house.isVisible() && !HouseList.contains(house))
			HouseList.add(house);
		if ( roof.isVisible() && !HouseList.contains(roof))
			HouseList.add(roof);
		if ( door.isVisible() && !HouseList.contains(door))
			HouseList.add(door);
		//
		
		if ( spouse.isVisible() )
			Bed.setVisible(true);
		
		//Bed game over screen
		if ( gameMode == 7 )
			Blob.setVisible(false);

		//Game Mode 0
		if ( gameMode == 0 ){
			// Added a isVisible check so spouse doesn't change ending
			if (!spouse.isVisible())
				spouse.ending(inventory.getInv());
		
		// Add visible items to the ItemList
		if ( Phone.isVisible() && !ItemList.contains(Phone))
			ItemList.add(Phone);
		
		Phone.setPosition(100, 100);
		
		if ( Brush.isVisible() && !ItemList.contains(Brush))
			ItemList.add(Brush);
		
		Brush.setPosition(400, 400);
		
		
		//Bed Item
		if ( Bed.isVisible() )
			Bed.setPosition(300,300);
		
		if ( Bed.isVisible() && !ItemList.contains(Bed))
			ItemList.add(Bed);
		
		// Blob.collidesWith(Bed) && Blob.hasSomeItem()
		if ( Blob.collidesWith(Bed) &&  spouse.getEnding() == "painted" )
			gameMode = 7;
		if ( Blob.collidesWith(Bed) && spouse.getEnding() == "paintedFirst" )
			gameMode = 9;
		if ( Blob.collidesWith(Bed) && spouse.getEnding() == "byeBye" ){
			gameMode = 8;
		}
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
		}
		
		// map!
		// Before we had a for each loop to make all the items invisible
		// but we don't need that anymore because of the if else statements on
		// lines 140-155 and the draw method lines 298-315
		// We might need to put the item loop back in the future, we'll see
		
		//Inside to outside
		if ( Blob.getPosition().getY() >= 500 && gameMode == 0){
			Blob.setPosition(200,114);
			gameMode = 1;
		}
		
		//Inside to room
		if ( Blob.getPosition().getX() >= 500 && gameMode == 0 ){
			Blob.setPosition(10,(int)Blob.getPosition().getY());
			gameMode = 3;
		}
		
		//Outside to inside
		if ( Blob.collidesWith(door) && gameMode == 1 ){
			Blob.setPosition((int)Blob.getPosition().getX(), 500);
			gameMode = 0;
		}
		
		if ( Blob.getPosition().getX()>=500 && gameMode == 1 ) {
			Blob.setPosition(10,(int)(Blob.getPosition().getY()*.3));
			gameMode = 5;
		}
		
		if ( Blob.getPosition().getX() <= -1 && gameMode == 1){
			Blob.setPosition(500-Blob.getUnscaledWidth(),(int)Blob.getPosition().getY());
			gameMode = 2;
		}
		
		if ( Blob.getPosition().getY() >= 500 && gameMode == 1 ){
			Blob.setPosition((int)Blob.getPosition().getX(), 10);
			gameMode = 4;
		}
		
		if ( Blob.getPosition().getX() >= 500 && gameMode == 2 ){
			Blob.setPosition(0, (int)Blob.getPosition().getY());
			gameMode = 1;
		}
		
		if ( Blob.getPosition().getX() <= 0 && gameMode == 3 ){
			Blob.setPosition(500, (int) Blob.getPosition().getY());
			gameMode = 0;
		}
		
		if ( Blob.getPosition().getY() <= 0 && gameMode == 4 ){
			Blob.setPosition((int)Blob.getPosition().getX(), 500);
			gameMode = 1;
		}
		if ( Blob.getPosition().getX()<=0 && gameMode == 5 ){
			Blob.setPosition(490,(int)Blob.getPosition().getY());
			gameMode = 1;
		}
		
		
		
		//Game Over
		if ( pressedKeys.contains("J"))
			this.exitGame();
		}
		
		//Open up the inventory
		if (pressedKeys.contains("I")){
			inventory.update(pressedKeys);
			inventory.setVisible(true);
		} else if ( inventory != null ) {inventory.setVisible(false);}
				
		
		frames++;

	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		// if not null draw blob and the sprites
		// Added House
		
		
		if ( gameMode == 1 ) {
			// Rewritten for new House mechanic
			for ( HouseSprite i : HouseList )
				i.draw(g);
			down.draw(g);
			right.draw(g);
			left.draw(g);
		}
		if ( gameMode == 2 ) {
			bar.draw(g);
			right.draw(g);
		}
		if ( gameMode == 3 ){
			bedroom.draw(g);
			left.draw(g);
		}
		if ( gameMode == 4 ){
			museum.draw(g);
			up.draw(g);
		}
		if ( gameMode == 5 ){
			//street.draw(g);
			left.draw(g);
		}
		if ( gameMode == 7 ){
			g.drawString("Yay! You did it! You beat the thing!", 150, 220);
			g.drawString("You got "+ inventory.getInv().size() + " items!", 150, 250);
		}
		
		if (gameMode == 8){
			g.drawString("Oh no!!", 150, 220);
			g.drawString("You got "+ inventory.getInv().size() + "items!",150,250);
			g.drawString("But your spouse is leaving you.... for a Square!!",150,300);
		}
		if ( gameMode == 9 ){
			g.drawString("Yay! You did it! You beat the thing! And your spouse loves you!", 100, 220);
			g.drawString("You got "+ inventory.getInv().size() + " items!", 150, 250);
		}
		if ( Blob != null && Phone != null && Brush != null  && Phone_Pickup != null && Brush_Pickup != null && heyListen != null
				&& house != null && HouseList != null && inventory != null
				&& spouse != null){
			Blob.draw(g);
			if ( gameMode == 0){
			Phone.draw(g);
			Brush.draw(g);
			Phone_Pickup.draw(g);
			Brush_Pickup.draw(g);
			heyListen.draw(g);
			spouse.draw(g);
			right.draw(g);
			down.draw(g);
			// Bed is only drawn if spouse is there
			if ( spouse.isVisible())
				Bed.draw(g);
			}
			inventory.draw(g);
		}
		
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
		
		// I put this in the main body
		/*
		if (this.gameMode == 0){
			if (spouse.getEnding() == "painted")
				//if (spouse.getObjectByID("painted").getGlobalHitBox().contains(e.getX(), e.getY()-25)){
					gameMode = 7;
				}
			}
		
			if (spouse.getEnding() == "byeBye"){
				if (spouse.getObjectByID("byeBye").getGlobalHitBox().contains(e.getX(), e.getY()-25)){
					gameMode = 8;
				}
			}
		}
		*/
		
		if (this.gameMode == 2){
			for(ItemSprite i : bar.getBarItems()){
				if(i.getGlobalHitBox().contains(e.getX(), e.getY()-25)) {
					if(i.getId() == "bar"){
						inventory.addItem(this.pint);
					}
				}	
			}
		}
		
		if (this.gameMode == 3){
			for (ItemSprite i : bedroom.getFurniture()){
				if(i.getGlobalHitBox().contains(e.getX(), e.getY()-25)) {
					bedroom.dispatchEvent(new Event(bedroom.CLEAN_MESS , i));
					if(i.getId() == "dirty bed"){
						inventory.addItem(this.videoGame);
					}
				}
			}
		}
		
		if (this.gameMode == 4){
			for(ItemSprite i : museum.getGallery()){
				if(i.getGlobalHitBox().contains(e.getX(), e.getY()-25)) {
					if(i.getId() == "art"){
						inventory.addItem(this.art);
					}
				}	
			}
		}
		
		if (this.gameMode == 3){
			for (ItemSprite i : bedroom.getFurniture()){
				if(i.getGlobalHitBox().contains(e.getX(), e.getY()-25)) {
					bedroom.dispatchEvent(new Event(bedroom.CLEAN_MESS , i));
					if(i.getId() == "dirty bed"){
						inventory.addItem(this.videoGame);
					}
				}
			}
		}
		
			// House check
		if ( gameMode == 1 ){
			for ( HouseSprite i : HouseList ) {
				if ( i.getGlobalHitBox().contains(e.getX(), e.getY() - 25 )) {
					i.paintHouse();
					if (!inventory.contains(invBrush)){
						inventory.addItem(invBrush);
					}
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
						 test.animate(TweenableParam.ALPHA, 0, 1, 500);
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
			if ( n == 1001 ) System.out.println("GAME OVER");
			if ( n == 1002 ) this.exitGame();
			n++;
		}
		// endings
		if ( spouse.isVisible() )
			if ( spouse.getGlobalHitBox().contains(e.getX(), e.getY())){
				spouse.cycle(inventory.getInv(),k);
				k++;
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