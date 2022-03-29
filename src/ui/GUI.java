package ui;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import main.GameObject;

public class GUI extends GameObject {
	
	private double lastMouseX = 0;
	private double lastMouseY = 0;
	
	private HashMap<String, UiComponentEntry> componentIds;
	private ArrayList<UiComponentEntry> allComponents; //TODO proper container class?
	
	public GUI () {
		componentIds = new HashMap<String, UiComponentEntry> ();
		allComponents = new ArrayList<UiComponentEntry> ();
		declare (0, 0);
	}
	
	public void registerUiComponent (GuiComponent comp) {
		UiComponentEntry entry = new UiComponentEntry (comp);
		componentIds.put (comp.getID (), entry);
		allComponents.add (entry);
	}
	
	public void setHint (String id, String hint, String value) {
		UiComponentEntry working = componentIds.get (id);
		if (working != null) {
			working.setHint (hint, value);
		}
	}
	
	@Override
	public void frameEvent () {
		
		//Check if mouse moved
		boolean mouseMoved = false;
		if (lastMouseX != getMouseX () || lastMouseY != getMouseY ()) {
			mouseMoved = true;
		}
		lastMouseX = getMouseX ();
		lastMouseY = getMouseY ();
		
		//Dispatch mouse events
		Collections.sort (allComponents);
		Iterator<UiComponentEntry> iter = allComponents.iterator ();
		while (iter.hasNext ()) {
			UiComponentEntry curr = iter.next ();
			
			//Mouse entry/exit
			if (curr.getValue ().getBoundingRectangle ().contains (getMouseX (), getMouseY ())) {
				if (!curr.mouseInside ()) {
					curr.mouseEnter ();
				}
			} else {
				if (curr.mouseInside ()) {
					curr.mouseExit ();
				}
			}
			
			//Mouse moved/clicked
			if (curr.mouseInside ()) {
				Rectangle bounds = curr.getValue ().getBoundingRectangle ();
				double relX = (getMouseX () - bounds.x);
				double relY = (getMouseY () - bounds.y);
				if (mouseMoved) {
					curr.getValue ().mouseMoveEvent (relX, relY);
				}
				if (mouseClicked ()) {
					curr.getValue ().mouseButtonEvent (0, relX, relY);
				}
			}
			
		}
		
	}
	
	@Override
	public void draw () {
		Collections.sort (allComponents);
		Iterator<UiComponentEntry> iter = allComponents.iterator ();
		while (iter.hasNext ()) {
			UiComponentEntry curr = iter.next ();
			curr.getValue ().draw ();
		}
	}
	
	private class UiComponentEntry implements Comparable<UiComponentEntry> {
		
		private GuiComponent comp;
		private HashMap<String, String> hints;
		
		private boolean mouseInside;

		public UiComponentEntry (GuiComponent comp) {
			this.comp = comp;
			this.hints = new HashMap<String, String> ();
			this.mouseInside = false;
			setHint ("layer", "0");
		}
		
		public GuiComponent getValue () {
			return comp;
		}
		
		public String getHint (String hintName) {
			return hints.get (hintName) == null ? "false" : hints.get (hintName);
		}
		
		public void setHint (String hintName, String value) {
			hints.put (hintName, value);
		}
		
		public boolean mouseInside () {
			return mouseInside;
		}
		
		public void mouseEnter () {
			mouseInside = true;
			comp.mouseEnterEvent ();
		}
		
		public void mouseExit () {
			mouseInside = false;
			comp.mouseExitEvent ();
		}

		@Override
		public int compareTo (UiComponentEntry o) {
			int myLayer = Integer.parseInt (getHint ("layer"));
			int otherLayer = Integer.parseInt (getHint ("layer"));
			if (myLayer > otherLayer) {
				return 1;
			} else if (myLayer < otherLayer) {
				return -1;
			} else {
				return 0;
			}
		}
		
	}

}
