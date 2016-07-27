package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import edu.virginia.engine.display.BlobSprite;
import edu.virginia.engine.display.DisplayBox;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.ItemSprite;
import edu.virginia.engine.sound.SoundManager;
import edu.virginia.engine.util.GameClock;
import edu.virginia.enginge.events.PopupEvent;

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
	
	DisplayBox Phone_Pickup = new DisplayBox("Phone_Pickup", "phone_pickup_event.png");
	DisplayBox Brush_Pickup = new DisplayBox("Brush_Pickup", "brush_first_event.png");
	
	private static ArrayList<String> brushes = new ArrayList<String>();

	int gameMode = 0;
	
	int MouseX;
	int MouseY;

	ArrayList<ItemSprite> Remover = new ArrayList<ItemSprite>();
	
	
	int disCount = 0;
	int n = 0;
	String distraction = PopupEvent.BRUSH_FIRST;
	
	
	ArrayList<ItemSprite> ItemList = new ArrayList<ItemSprite>();
	
	boolean popupMode = false;
	
	SoundManager mSM = new SoundManager();
	
	GameClock t = new GameClock();
	
	
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
		
		if ( Blob != null && Phone != null && Brush != null && ItemList != null ) { 
		
		// if blob is not null
		
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
		
		if ( Phone_Pickup.isVisible() && !ItemList.contains(Phone_Pickup))
			ItemList.add(Phone_Pickup);
		
		if ( Brush_Pickup.isVisible() && !ItemList.contains(Brush_Pickup))
			ItemList.add(Brush_Pickup);
		
		
		Phone_Pickup.setPosition(250,250);
		Phone_Pickup.setPivotPoint(Phone_Pickup.getUnscaledWidth()/2, Phone_Pickup.getUnscaledHeight()/2);
		
		
		Phone.addEventListener(Phone_Pickup, PopupEvent.PHONE_PICKUP);
		
		Phone_Pickup.addEventListener(Phone_Pickup, PopupEvent.PHONE_CLOSE);
		
		Brush_Pickup.setPosition(250,250);
		Brush_Pickup.setPivotPoint(Brush_Pickup.getUnscaledWidth()/2, Brush_Pickup.getUnscaledHeight()/2);
		
		Brush.addEventListener(Brush_Pickup, PopupEvent.BRUSH_FIRST);
		Brush.addEventListener(Brush_Pickup, PopupEvent.BRUSH_SECOND);
		Brush.addEventListener(Brush_Pickup, PopupEvent.BRUSH_THIRD);
		Brush.addEventListener(Brush_Pickup, PopupEvent.BRUSH_FOURTH);
		Brush.addEventListener(Brush_Pickup, PopupEvent.BRUSH_FIFTH);
		
		Brush_Pickup.addEventListener(Brush_Pickup, PopupEvent.BRUSH_CLOSE);
		
		if ( Blob.collidesWith(Brush) ){
			for ( ItemSprite i : ItemList )
				i.setVisible(false);
			Blob.setPosition(300,0);
			gameMode = 1;
		}
		
		if ( Blob.collidesWith(Phone) ){
			for ( ItemSprite i : ItemList )
				i.setVisible(false);
			Blob.setPosition(200,400);
			gameMode = 2;
		}
		
		
		//Game Over
		if ( pressedKeys.contains("J"))
			this.exitGame();
		}

	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		// if not null draw blob and the sprites
		if ( Blob != null && Phone != null && Brush != null ){
			Blob.draw(g);
			Phone.draw(g);
			Brush.draw(g);
			Phone_Pickup.draw(g);
			Brush_Pickup.draw(g);
		}
		if ( gameMode == 1 ) {
			g.drawRect(a.x, a.y, a.width, a.height);
			if ( a.contains(MouseX, MouseY) )  
				g.fillRect(a.x, a.y, a.width, a.height);
			g.drawString("Blob's House", 350, 350);
			}
		if ( gameMode == 2 ) {
			g.drawRect(150, 0, 300, 150);
			g.drawString("Bar", 300, 50 );
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
		if ( gameMode == 1 ) {
			MouseX = e.getX();
			MouseY = e.getY();
		}
		else{
		for ( ItemSprite i : ItemList ) {
			 if ( i.getGlobalHitBox().contains(e.getX(), e.getY() - 25) ) {
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