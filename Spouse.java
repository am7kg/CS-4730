package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.lab1test.BlobsOnlyJob;

public class Spouse extends AnimatedSprite{
	
	String ending = null;

	DisplayBox painted = new DisplayBox("painted","house_painted_txt.png");
	DisplayBox paintedFirst = new DisplayBox("paintedFirst","painted_first_txt.png");
	DisplayBox byeBye = new DisplayBox("byeBye", "spouse_mad_txt.png");
	
	//More endings!
	DisplayBox achievement = new DisplayBox("achievement","spouse_achievement.png");
	DisplayBox art = new DisplayBox("art","spouse_art.png");
	DisplayBox cat = new DisplayBox("achievement","spouse_achievement.png");
	DisplayBox pint = new DisplayBox("pint","spouse_pint.png");
	DisplayBox playbill = new DisplayBox("playbill","spouse_playbill.png");
	DisplayBox puke = new DisplayBox("puke","spouse_puke.png");
	DisplayBox shirt = new DisplayBox("shirt","spouse_shirt.png");
	DisplayBox skull = new DisplayBox("skull","spouse_skull.png");
	DisplayBox videogame = new DisplayBox("videogame","spouse_videogame.png");
	
	DisplayBox bed = new DisplayBox("bed","spouse_bed.png");
	
	
	public Spouse(String id) {
		super(id);
		this.setVisible(false);
		this.setImage("spouse.png");
		this.setPosition(100,100);
	}
	
	public void ending(ArrayList<ItemSprite> inv){
		if(inv.size()>0 && inv.get(0).getId() == "brush"){
			this.setVisible(true);
			ending = "paintedFirst";
			paintedFirst.setPosition(150,0);
			paintedFirst.setVisible(true);
			paintedFirst.setParent(this);
			this.addChild(paintedFirst);
			
		}
		if (inv.size()>1){
			for(ItemSprite i: inv){
				if (i.getId() == "brush"){
					ending = "painted";
					this.setVisible(true);
					painted.setPosition(150,0);
					painted.setVisible(true);
					painted.setParent(this);
					this.addChild(painted);
					break;
				} else {
					ending = "byeBye";
					this.setVisible(true);
					byeBye.setPosition(150,0);
					byeBye.setVisible(true);
					byeBye.setParent(this);
					this.addChild(byeBye);
				}
			}
		}
	}
	
	public String getEnding(){
		return this.ending;
	}
	
	public void cycle(ArrayList<ItemSprite> inv, int k){
		byeBye.setVisible(false);
		painted.setVisible(false);
		paintedFirst.setVisible(false);
		if ( k < inv.size() ){
			if ( inv.get(k).getImageFileName() == "item_playbill.png" ){
				playbill.setVisible(true);
				playbill.setPosition(150, 0);
				playbill.setParent(this);
				this.addChild(playbill);
			}
			if ( inv.get(k).getImageFileName() == "item_pint.png"){
				pint.setVisible(true);
				pint.setPosition(150,0);
				pint.setParent(this);
				this.addChild(pint);
			}
			if ( inv.get(k).getImageFileName() == "item_art1.png"){
				art.setVisible(true);
				art.setPosition(150,0);
				art.setParent(this);
				this.addChild(art);
			}
			if ( inv.get(k).getImageFileName() == "item_videogame.png"){
				videogame.setVisible(true);
				videogame.setPosition(150,0);
				videogame.setParent(this);
				this.addChild(videogame);
			}
			if ( inv.get(k).getImageFileName() == "item_achievement.png"){
				achievement.setVisible(true);
				achievement.setPosition(150,0);
				achievement.setParent(this);
				this.addChild(achievement);
			}
			if ( inv.get(k).getImageFileName() == "item_skull.png"){
				skull.setVisible(true);
				skull.setPosition(150,0);
				skull.setParent(this);
				this.addChild(skull);
			}
			if ( inv.get(k).getImageFileName() == "item_cat.png"){
				cat.setVisible(true);
				cat.setPosition(150,0);
				cat.setParent(this);
				this.addChild(cat);
			}
			if ( inv.get(k).getImageFileName() == "item_puke.png"){
				puke.setVisible(true);
				puke.setPosition(150,0);
				puke.setParent(this);
				this.addChild(puke);
			}
			if ( inv.get(k).getImageFileName() == "item_shirt.png"){
				shirt.setVisible(true);
				shirt.setPosition(150,0);
				shirt.setParent(this);
				this.addChild(shirt);
			}
		}
		else{
		bed.setVisible(true);
		bed.setPosition(150,0);
		bed.setParent(this);
		this.addChild(bed);
		}
	}
}