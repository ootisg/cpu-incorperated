package ui;

import resources.Sprite;

public class XPBar extends GuiComponent {
	
	public Sprite bg = new Sprite ("resources/ui/lv_progress_bg.png");
	
	public XPBar (GUI handler, String id) {
		super (handler, id);
		setSprite (bg);
	}

	@Override
	public void draw () {
		super.draw ();
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
