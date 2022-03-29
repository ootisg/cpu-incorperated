package ui;

import java.awt.Rectangle;

import main.GameObject;

public abstract class GuiComponent extends GameObject {
	
	private GUI handler;
	private String id;
	
	private Rectangle boundRect;
	
	private boolean isHidden;
	
	public GuiComponent (GUI handler, String id) {
		
		this.handler = handler;
		this.id = id;
		this.boundRect = new Rectangle (0, 0, 0, 0);
		handler.registerUiComponent (this);
		
	}
	
	public String getID () {
		return id;
	}
	
	public Rectangle getBoundingRectangle () {
		return boundRect;
	}
	
	public void setBoundingRectangle (Rectangle boundRect) {
		this.boundRect = boundRect;
	}
	
	protected GUI getHandler () {
		return handler;
	}
	
	public void hide () {
		isHidden = true;
	}
	
	public void show () {
		isHidden = false;
	}
	
	public boolean hidden () {
		return isHidden;
	}
	
	@Override
	public void draw () {
		Rectangle bounds = getBoundingRectangle ();
		setX (bounds.x);
		setY (bounds.y);
		drawSprite ();
	}
	
	public abstract void mouseButtonEvent (int button, double x, double y);
	public abstract void mouseMoveEvent (double x, double y);
	public abstract void mouseEnterEvent ();
	public abstract void mouseExitEvent ();

}
