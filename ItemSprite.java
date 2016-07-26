package edu.virginia.engine.display;

public class ItemSprite extends AnimatedSprite {
	
	String pop;
	String normal;

	public ItemSprite(String id, String fileName, String popFileName) {
		super(id, fileName);
		// TODO Auto-generated constructor stub
		this.pop = popFileName;
		this.normal = fileName;
	}
	
	public void pop(){
		this.setImage(pop);
	}
	
	public void normal(){
		this.setImage(this.normal);
	}

}
