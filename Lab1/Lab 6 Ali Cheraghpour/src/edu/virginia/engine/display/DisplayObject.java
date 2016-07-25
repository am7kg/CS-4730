package edu.virginia.engine.display;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;







import javax.imageio.ImageIO;

import edu.virginia.enginge.events.CollideEvent;
import edu.virginia.enginge.events.Event;
import edu.virginia.enginge.events.EventDispatcher;
import edu.virginia.enginge.events.QuestManager;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject extends EventDispatcher  {

	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	protected BufferedImage displayImage;
	//private Queue<DisplayObject> frames = new LinkedList<DisplayObject>();
	protected int totalFrames;
	
	//additional fields
	boolean visible = true;
	float alpha = 1.0f;
	Point position = new Point(0,0);
	Point pivotPoint = new Point(this.getUnscaledHeight()/2,this.getUnscaledWidth()/2);
	double scaleX = 1.0;
	double scaleY = 1.0;
	double rotation = 0;	
	
	// parent
	private DisplayObject parent;
	
	//Quest Manager
	QuestManager myQuestManager = new QuestManager();
	
	// physics
	//boolean hasPhysics;
	
	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {
		this.setId(id);
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
	}
	
	public DisplayObject(String id, String fileName, DisplayObject parent){
		this.setId(id);
		this.setImage(fileName);
		this.setParent(parent);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}


	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}
	
	public int getWidth() {
		if (displayImage == null) return 0;
		return (int) (displayImage.getWidth() * this.getScaleX());
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}
	
	public int getHeight() {
		if(displayImage == null) return 0;
		return (int) (displayImage.getHeight() * this.getScaleY());
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	public void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}


	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}


	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<String> pressedKeys) {
	
		

	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {

		if (displayImage != null && visible == true) {
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			//applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */

			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			
			//reverseTransformations(g2d);
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		g2d.translate(position.x, position.y);
		g2d.scale(scaleX, scaleY);
		g2d.rotate(rotation);
		g2d.translate(-pivotPoint.x, -pivotPoint.y);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		this.setAlpha(1.0f);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.translate(pivotPoint.x, pivotPoint.y);
		g2d.rotate(-rotation);
		g2d.scale(1/scaleX, 1/scaleY);
		g2d.translate(-position.x, -position.y);
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/*
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**s
	 * @return the alpha
	 */
	public float getAlpha() {
		return alpha;
	}

	/**
	 * @param a the alpha to set
	 */
	public void setAlpha(float a) {
		this.alpha = a;
	}


	/**
	 * @return the position
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public void setPosition(int x, int y){
		this.position.setLocation(x, y);
	}

	/**
	 * @return the pivotPoint
	 */
	public Point getPivotPoint() {
		return pivotPoint;
	}

	/**
	 * @param pivotPoint the pivotPoint to set
	 */
	public void setPivotPoint(double x, double y) {
		pivotPoint.setLocation(x, y);
	}
	
	public void setPivotPoint(Point pivotPoint) {
		pivotPoint.setLocation(pivotPoint);
	}


	/**
	 * @return the scaleX
	 */
	public double getScaleX() {
		return scaleX;
	}

	/**
	 * @param scaleX the scaleX to set
	 */
	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	/**
	 * @return the scaleY
	 */
	public double getScaleY() {
		return scaleY;
	}

	/**
	 * @param scaleY the scaleY to set
	 */
	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}

	/**
	 * @return the rotation
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * @param d the rotation to set
	 */
	public void setRotation(double d) {
		this.rotation = d;
	}

	public DisplayObject getParent() {
		return parent;
	}

	public void setParent(DisplayObject parent) {
		this.parent = parent;
	}
	
	//Don't use this method
	public boolean noParent(){
		if ( this.parent == null ) 
			return false;
		else
			return true;
	}
	
	public Point localToGlobal(Point p) {
		if ( this.parent == null ){
			return p;
		}
		else{
			Point t =  new Point((int)(p.getX() + this.getPosition().x), (int)(p.getY() + this.getPosition().getY()));
			System.out.println("OSDIF");
			return this.parent.localToGlobal(t);
		}
	}
	
	public Point globalToLocal(Point p){
		Point m = localToGlobal(p);
		Point k = new Point(p.x - m.x, p.y - m.y);
		return k;
	}
	
	public Rectangle getGlobalHitBox() {
		Point p = new Point(this.getPosition().x, this.getPosition().y);
		p = localToGlobal(p);
		return new Rectangle(p.x,p.y,this.getUnscaledWidth(),this.getUnscaledHeight());
		//return new Rectangle(this.getPosition().x,this.getPosition().y,this.getUnscaledWidth(),this.getUnscaledHeight());

	}
	


	public boolean collidesWith(DisplayObject other) {
		if ( this.visible && other.visible ) {
			if ( this.getGlobalHitBox().intersects(other.getGlobalHitBox()) ){
				if (!this.hasEventListener(myQuestManager, CollideEvent.COLLISION))
					this.addEventListener(myQuestManager, CollideEvent.COLLISION);
				this.dispatchEvent(new Event(CollideEvent.COLLISION, this));
				return true;
			}
		}
		return false;
	}
	
	public boolean overlap(DisplayObject other) {
		if ( this.getGlobalHitBox().intersects(other.getGlobalHitBox()) && 
				(this.getGlobalHitBox().intersection(other.getGlobalHitBox()).height>10 ||
						this.getGlobalHitBox().intersection(other.getGlobalHitBox()).width>10 )
			)
			return true;
		return false;
	}
}