package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ExtendedMouseListener implements MouseListener {
	private int xOffset;
	private int yOffset;
	private int[] mouseCoords;
	private boolean isClicked;
	private MouseEvent mouseEvent;
	public ExtendedMouseListener (int xOffset, int yOffset) {
		//Pretty self-explanitory constructor
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.isClicked = false;
		mouseCoords = new int[] {0, 0};
	}
	@Override
	public void mouseClicked (MouseEvent event) {
		//Fires when the mouse is clicked
		this.isClicked = true;
		mouseEvent = event;
	}
	@Override
	public void mouseEntered (MouseEvent event) {
	}
	@Override
	public void mouseExited (MouseEvent event) {
	}
	@Override
	public void mousePressed (MouseEvent event) {
	}
	@Override
	public void mouseReleased (MouseEvent event) {
	}
	public int[] getClick () {
		//Gets the most recent mouse click and causes all subsequent calls of this method for the next click to return null
		if (isClicked) {
			isClicked = false;
			int clickX = (mouseEvent.getX () - xOffset);
			int clickY = (mouseEvent.getY () - yOffset);
			return new int[] {clickX, clickY};
		} else {
			return null;
		}
	}
	public int[] getMouseCoords () {
		return mouseCoords;
	}
}