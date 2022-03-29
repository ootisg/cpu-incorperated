package ui;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Menu extends GuiComponent implements SelectionHandler {

	private ArrayList<GuiComponent> comps;
	
	public Menu (GUI handler, String id) {
		super (handler, id);
		comps = new ArrayList<GuiComponent> ();
	}
	
	@Override
	public void show () {
		
		//Show the menu
		super.show ();
		
		//Show all the subcomponents in the menu
		Iterator<GuiComponent> iter = comps.iterator ();
		while (iter.hasNext ()) {
			iter.next ().show ();
		}
		
	}
	
	@Override
	public void hide () {
		
		//Hide the menu
		super.hide ();
		
		//Hide all the subcomponents in the menu
		Iterator<GuiComponent> iter = comps.iterator ();
		while (iter.hasNext ()) {
			iter.next ().hide ();
		}
		
	}
	
	protected void addComponent (GuiComponent comp) {
		comps.add (comp);
	}
	
	protected ArrayList<GuiComponent> getComponents () {
		return comps;
	}

}
