package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject {

	public DisplayObjectContainer(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	public DisplayObjectContainer(String id, String fileName ) {
		super(id, fileName);
		// TODO Auto-generated constructor stub
	}
	
	public DisplayObjectContainer(String id, String fileName, DisplayObject parent){
		super(id, fileName, parent);
	}
	
	//children
	ArrayList<DisplayObject> children = new ArrayList<DisplayObject>();
	
	// invisibility
	boolean visibility = true;
	
	//add and remove methods
	public void addChild(DisplayObject child){
		if ( child.getParent() == this )
			if ( !this.contains(child) )
				this.children.add(child);
	}
	
	public void addChildAtIndex(int a, DisplayObject child){
		this.children.add(a, child);
	}
	
	public void removeChild(DisplayObject child){
		this.children.remove(child);
	}
	
	public void removeByIndex(int a){
		this.children.remove(a);
	}
	
	public void removeAll(){
		for (int i = 0; i < this.children.size(); i++ ){
			this.children.remove(i);
		}
	}
	
	public boolean contains(DisplayObject child){
		for ( int i = 0; i < this.size(); i++ ) {
			if ( this.children.get(i) == child )
				return true;
		}
		return false;
	}
	
	public DisplayObject getObjectByIndex(int a){
		return this.children.get(a);
	}
	
	public DisplayObject getObjectByID(String id){
		for (int i = 0; i < this.children.size(); i++){
			if ( id == this.children.get(i).getId() )
				return this.children.get(i);
		}
		return null;
	}
	
	public int size(){
		return this.children.size();
	}
	
	@Override
	public void update(ArrayList<String> pressedKeys){
		super.update(pressedKeys);

		//auto-rotation
		this.setRotation(this.getRotation()+.01);
		
		if ( this.getParent() != null )
			this.setPivotPoint(this.getParent().getPivotPoint());

	}
	
	@Override
	public void draw(Graphics g){		
		
		if ( visibility ){
			Graphics2D g2d = (Graphics2D) g;
			super.draw(g);

			applyTransformations(g2d);
			
			g2d.drawImage(displayImage, 0, 0,
					(int) (getWidth()),
					(int) (getHeight()), null);
			
			if ( this.children.size() > 0) {
				for (int i = 0; i < this.children.size(); i++){
					this.children.get(i).draw(g);
				}
			}
			reverseTransformations(g2d);
		}
		
	}
	
	public ArrayList<DisplayObject> getChildren(DisplayObjectContainer e){
		return e.children;
	}

	/**
	 * @return the visibility
	 */
	public boolean isVisibility() {
		return visibility;
	}

	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}


}
