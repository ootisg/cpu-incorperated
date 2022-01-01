package main;

import java.awt.event.MouseMotionListener;

import java.awt.event.MouseEvent;

public class ExtendedMouseMotionListener implements MouseMotionListener {
	private int xOffset;
	private int yOffset;
	private int[] mouseCoords;
	private boolean isClicked;
	private boolean clickDetected;
	public ExtendedMouseMotionListener (int xOffset, int yOffset) {
		//Pretty self-explanitory constructor
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.isClicked = false;
		this.clickDetected = false;
		mouseCoords = new int[] {0, 0};
	}
	@Override
	public void mouseMoved (MouseEvent event) {
		//Fires when the mouse is moved
		mouseCoords [0] = event.getX () - xOffset;
		mouseCoords [1] = event.getY () - yOffset;
		this.isClicked = false;
	}
	@Override
	public void mouseDragged (MouseEvent event) {
		//Fires when the mouse is dragged; acts similarly to mouseMoved and triggers a click event
		mouseCoords [0] = event.getX () - xOffset;
		mouseCoords [1] = event.getY () - yOffset;
		if (!this.isClicked) {
			clickDetected = true;
		}
		this.isClicked = true;
	}
	public int[] getMouseCoords () {
		//Returns the current mouse coordinates in the format [x, y]
		return mouseCoords;
	}
	public boolean getClicked () {
		//Returns true if a click was detected this frame; causes all subsequent calls of this method until the next click to return false
		boolean temp = clickDetected;
		clickDetected = false;
		return temp;
	}
}