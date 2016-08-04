package edu.virginia.engine.display;

import java.awt.Graphics;
import java.util.ArrayList;

import edu.virginia.BlobsOnlyJob.BlobsOnlyJob;

public class Inventory extends Sprite {
	
	ArrayList<ItemSprite> inventory = new ArrayList<ItemSprite>();

	public Inventory(String id) {
		super(id);
		setImage("inventory1.png");
		this.setPosition(100,100);
		this.setVisible(false);
	}
	
	
	public void addItem(ItemSprite item){
		if (!this.inventory.contains(item)){
			item.setPosition(30 * (this.inventory.size() +1), 7);
			item.setParent(this);
			this.addChild(item);
			inventory.add(item);
			this.setVisible(true);
		}
	}
	
	public boolean removeItem(ItemSprite item){
		return inventory.remove(item);
	}
	
	public boolean hasItem(ItemSprite item){
		return inventory.contains(item);
	}
	
	public ArrayList<ItemSprite> getInv(){
		return this.inventory;
	}

}
