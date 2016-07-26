package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
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
	
	//BlobSprite Blob = new BlobSprite("Blob", "blob.png");
	//ItemSprite Phone = new ItemSprite("Phone","phone.png");
	
	
	SoundManager mSM = new SoundManager();
	
	GameClock t = new GameClock();
	
	
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
		
		// if blob is not null
		
		if ( KeyEvent.getKeyText(KeyEvent.VK_UP) != null) {};
		if ( KeyEvent.getKeyText(KeyEvent.VK_DOWN) != null) {};
		if ( KeyEvent.getKeyText(KeyEvent.VK_RIGHT) != null) {};
		if ( KeyEvent.getKeyText(KeyEvent.VK_LEFT) != null) {};
		
		
		//Game Over

	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		// if not null draw blob and the sprites
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
		clickedItem(e.getX(), e.getY());
		
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
