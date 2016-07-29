package edu.virginia.engine.display;

public class HouseSprite extends AnimatedSprite {

	public HouseSprite(String id, String imageFileName) {
		super(id, imageFileName);
		// TODO Auto-generated constructor stub
	}
	
	
	// Create more if() statements for varying filenames
	public void paintHouse() {
		if (this.getImageFileName() == "unpainted_house.png" )
			this.setImage("painted_house.png");
	}
	
}
