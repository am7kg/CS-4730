package edu.virginia.engine.display;

import java.awt.Graphics;
import java.util.ArrayList;

public class Inventory extends Sprite {
	
	ArrayList<ItemSprite> inventory = new ArrayList<ItemSprite>();

	public Inventory(String id) {
		super(id);
		setImage("inventory1.png");
		this.setPosition(100,100);
		if ( id == "quest_module") {
			setImage("quest_mod.png");
			this.setPosition(0,0);
		}
	}
	
	
	public void addItem(ItemSprite item){
		if ( this.getId() == "inventory" && !this.inventory.contains(item)){
			item.setPosition(30 * (this.inventory.size() +1), 7);
			item.setParent(this);
			this.addChild(item);
			inventory.add(item);
			this.setVisible(true);
		}
		if ( this.getId() == "quest_module" && !this.inventory.contains(item) ){
			if ( item.getId() == "side_header" )
				item.setPosition(0,176);
			else
				item.setPosition(0,inventory.size()*32);
			item.setParent(this);
			this.addChild(item);
			inventory.add(item);
			this.setVisible(true);
		}
		
	}
	
	public boolean removeItem(ItemSprite item){
		this.removeChild(item);
		return inventory.remove(item);
	}
	
	public boolean hasItem(ItemSprite item){
		return inventory.contains(item);
	}
	
	public ArrayList<ItemSprite> getInv(){
		return this.inventory;
	}

}