package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.BlobsOnlyJob.BlobsOnlyJob;

public class Spouse extends AnimatedSprite{
	
	String ending = null;

	DisplayBox painted = new DisplayBox("painted","house_painted_txt.png");
	DisplayBox paintedFirst = new DisplayBox("paintedFirst","painted_first_txt.png");
	DisplayBox byeBye = new DisplayBox("byeBye", "spouse_mad_txt.png");
	
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

}
