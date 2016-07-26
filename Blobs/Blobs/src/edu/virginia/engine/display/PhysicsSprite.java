package edu.virginia.engine.display;

public class PhysicsSprite extends AnimatedSprite {

	final double GRAVITY = 37039.37007874; // pixels per second converted from 9.8 meters per second
	
	double xacceleration = 0;
	double yacceleration = 0;
	double xvelocity = 0;
	double yvelocity = 0;
	double xforce = 0;
	double yforce = 0;
	double mass = 0;
	int xpos;
	int ypos;
	
	public PhysicsSprite(String id) {
		super(id);
	}
	
	public PhysicsSprite(String id, String imageFileName) {
		super(id, imageFileName);
	}
	
	public PhysicsSprite(String id, String imageFileName, String animation){
		super(id, imageFileName, animation);
	}
	
	public void updatePhysics(double dt){
		this.xacceleration = this.xforce/mass;
		this.xvelocity = this.xvelocity + this.xacceleration*dt;
		xpos = (int) (this.getPosition().getX() + this.xvelocity*dt);
		
		this.yacceleration = this.yforce/mass;
		this.yvelocity = this.yvelocity + this.yacceleration*dt;
		ypos = (int) (this.getPosition().getY() + this.yvelocity*dt);
		
		this.setPosition(xpos, ypos);
	}

	/**
	 * @return the xforce
	 */
	public double getXforce() {
		return xforce;
	}

	/**
	 * @param xforce the xforce to set
	 */
	public void setXforce(double xforce) {
		this.xforce = xforce;
	}

	/**
	 * @return the yforce
	 */
	public double getYforce() {
		return yforce;
	}

	/**
	 * @param yforce the yforce to set
	 */
	public void setYforce(double yforce) {
		this.yforce = yforce;
	}
	
	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * @param mass the mass to set
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * @return the xacceleration
	 */
	public double getXacceleration() {
		return xacceleration;
	}

	/**
	 * @param xacceleration the xacceleration to set
	 */
	public void setXacceleration(double xacceleration) {
		this.xacceleration = xacceleration;
	}

	/**
	 * @return the yacceleration
	 */
	public double getYacceleration() {
		return yacceleration;
	}

	/**
	 * @param yacceleration the yacceleration to set
	 */
	public void setYacceleration(double yacceleration) {
		this.yacceleration = yacceleration;
	}

	/**
	 * @return the xvelocity
	 */
	public double getXvelocity() {
		return xvelocity;
	}

	/**
	 * @param xvelocity the xvelocity to set
	 */
	public void setXvelocity(double xvelocity) {
		this.xvelocity = xvelocity;
	}

	/**
	 * @return the yvelocity
	 */
	public double getYvelocity() {
		return yvelocity;
	}

	/**
	 * @param yvelocity the yvelocity to set
	 */
	public void setYvelocity(double yvelocity) {
		this.yvelocity = yvelocity;
	}

	public void jump() {
		// TODO Auto-generated method stub
		this.setYforce(-4);
	}


}
