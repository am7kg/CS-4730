package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import edu.virginia.engine.display.BlobSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.ItemSprite;
import edu.virginia.engine.sound.SoundManager;
import edu.virginia.engine.util.GameClock;

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
	ItemSprite Phone = new ItemSprite("Phone","phone.png");
	ItemSprite Brush = new ItemSprite("Brush", "brush.jpeg");
	
	
	ArrayList<ItemSprite> ItemList = new ArrayList<ItemSprite>();
	
	
	SoundManager mSM = new SoundManager();
	
	GameClock t = new GameClock();
	
	
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
		
		if ( Blob != null && Phone != null && Brush != null ) { 
		
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
		for ( ItemSprite i : ItemList ) {
			 if ( i.getGlobalHitBox().contains(e.getX(), e.getY() - 25) ) {
				 if ( i.getId() == "Phone")
					 System.out.println("You clicked the Phone");
				 if ( i.getId() == "Brush")
					 System.out.println("You clicked the Brush");
				 }	
		}
		if ( Blob.getGlobalHitBox().contains(e.getX(), e.getY() - 25 ) )
			System.out.println("chill bro");
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
