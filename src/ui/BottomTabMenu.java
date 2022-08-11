package ui;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import resources.Sprite;

public class BottomTabMenu extends Menu {
	
	Button[] buttons = new Button[4];
	
	public static Sprite defaultSpr = new Sprite ("resources/ui/bottom_tabs/build/default.png");
	public static Sprite selectedSpr = new Sprite ("resources/ui/bottom_tabs/build/select.png");
	
	public BottomTabMenu (GUI handler, String id) {
		
		//Menu constructor
		super (handler, id);
		
		//Add menu components
		buttons[0] = new SelectableMenuTab (null, 
				new Sprite ("resources/ui/bottom_tabs/build/default.png"), 
				new Sprite ("resources/ui/bottom_tabs/build/select.png"), 
				new Sprite ("resources/ui/bottom_tabs/build/down.png"), this, id + "0");
		buttons[1] = new SelectableMenuTab (null, 
				new Sprite ("resources/ui/bottom_tabs/tools/default.png"), 
				new Sprite ("resources/ui/bottom_tabs/tools/select.png"), 
				new Sprite ("resources/ui/bottom_tabs/tools/down.png"), this, id + "1");
		buttons[2] = new SelectableMenuTab (null, 
				new Sprite ("resources/ui/bottom_tabs/solver/default.png"), 
				new Sprite ("resources/ui/bottom_tabs/solver/select.png"), 
				new Sprite ("resources/ui/bottom_tabs/solver/down.png"), this, id + "2");
		buttons[3] = new SelectableMenuTab (null, 
				new Sprite ("resources/ui/bottom_tabs/unlock/default.png"), 
				new Sprite ("resources/ui/bottom_tabs/unlock/select.png"), 
				new Sprite ("resources/ui/bottom_tabs/unlock/down.png"), this, id + "3");
		int wy = 720 - selectedSpr.getHeight ();
		for (int i = 0; i < buttons.length; i++) {
			int wx = i * selectedSpr.getWidth ();
			Rectangle bounds = new Rectangle (wx, wy, selectedSpr.getWidth (), selectedSpr.getHeight ());
			buttons[i].setBoundingRectangle (bounds);
			this.addComponent (buttons[i]);
		}
		
	}

	@Override
	public void notifySelect (GuiComponent comp) {
		ArrayList<GuiComponent> allComps = this.getComponents ();
		Iterator<GuiComponent> iter = allComps.iterator ();
		while (iter.hasNext ()) {
			GuiComponent curr = iter.next ();
			if (curr != comp) {
				((SelectableMenuTab)curr).deselect ();
			}
		}
	}

	@Override
	public void notifyDeselect (GuiComponent comp) {
		//Do nothing; only BottomTabMenu can de-select a SelectableMenuTab
	}
	
	public class SelectableMenuTab extends ImageButton implements Selectable {
		
		private BottomTabMenu parentMenu;
		private Menu selectMenu;
		
		private boolean isSelected;

		public SelectableMenuTab (Menu selectMenu, Sprite upSprite, Sprite hoverSprite, Sprite downSprite, BottomTabMenu parent, String id) {
			super (upSprite, hoverSprite, downSprite, parent.getHandler (), id);
			parentMenu = parent;
			this.selectMenu = selectMenu;
		}

		@Override
		public void select () {
			
			parentMenu.notifySelect (this);
			setButtonState (ButtonState.BUTTON_DOWN);
			isSelected = true;
			
			if (selectMenu != null) {
				selectMenu.show ();
			}
			
		}

		@Override
		public void deselect () {
			
			setButtonState (ButtonState.BUTTON_UP);
			isSelected = false;
			
			if (selectMenu != null) {
				selectMenu.hide ();
			}
			
		}

		@Override
		public boolean isSelected () {
			return isSelected;
		}
		
		@Override
		public void mouseButtonEvent (int button, double x, double y) {
			select ();
		}
		
	}

	@Override
	public void mouseButtonEvent (int button, double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoveEvent (double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEnterEvent () {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExitEvent () {
		// TODO Auto-generated method stub
		
	}

	
	
}
