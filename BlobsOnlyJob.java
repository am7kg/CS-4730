package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.BlobSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.ItemSprite;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.sound.SoundManager;
import edu.virginia.engine.util.GameClock;
import edu.virginia.enginge.events.PopupEvent;
import edu.virginia.enginge.events.PopupManager;

public class BlobsOnlyJob extends Game implements MouseListener {

	public BlobsOnlyJob() {
		super("Blob's Only Job", 500, 500);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		BlobsOnlyJob game = new BlobsOnlyJob();
		game.start();
	}
	
	// Sprites
	
	BlobSprite Blob = new BlobSprite("Blob", "blob1.png");
	ItemSprite Phone = new ItemSprite("Phone","phone.png", "phone_pickup_event.png");
	ItemSprite Brush = new ItemSprite("Brush", "brush.jpeg","phone_pickup_event.png");
	
	PopupManager Pops = new PopupManager();
	
	Rectangle a = new Rectangle(200, 200, 300 , 300 );

	int b = 0;
	int n = 0;
	int frame = 1;
	
	ArrayList<ItemSprite> ItemList = new ArrayList<ItemSprite>();
	
	//boolean paintMode = false;
	//boolean barMode = false;
	
	int gameMode = 0;
	
	int MouseX;
	int MouseY;
	
	SoundManager mSM = new SoundManager();
	
	GameClock t = new GameClock();
	
	
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
		if ( frame == 1 ) {
		}
		
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
		
		Brush.setPosition(300, 300);
		
		// Event Listener
		Phone.addEventListener(Pops, PopupEvent.PHONE_PICKUP);
		
		
		//After you accept event, part of screen lights up
		//If Blob collides with light, teleport to new area
		
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

		
		frame++;
	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		// if not null draw blob and the sprites
		if ( Blob != null && Phone != null && Brush != null && a != null ){
			Blob.draw(g);
			Phone.draw(g);
			Brush.draw(g);
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
				 if ( i.getId() == "Phone") {
					 System.out.println("You clicked the Phone");
					 i.dispatchEvent(new PopupEvent(PopupEvent.PHONE_PICKUP,i));
				 }
				 if ( i.getId() == "Brush")
					 System.out.println("You clicked the Brush");
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
