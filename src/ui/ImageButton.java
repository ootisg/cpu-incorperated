package ui;

import java.util.HashMap;

import resources.Sprite;

public class ImageButton extends Button {
	
	private HashMap<ButtonState, Sprite> spriteMap;
	
	public ImageButton (HashMap<ButtonState, Sprite> spriteMap, GUI handler, String id) {
		super (handler, id);
		this.spriteMap = spriteMap;
	}
	
	public ImageButton (Sprite upSprite, Sprite hoverSprite, Sprite downSprite, GUI handler, String id) {
		super (handler, id);
		spriteMap = new HashMap<ButtonState, Sprite> ();
		spriteMap.put (ButtonState.BUTTON_UP, upSprite);
		spriteMap.put (ButtonState.BUTTON_HOVERED, hoverSprite);
		spriteMap.put (ButtonState.BUTTON_DOWN, downSprite);
	}
	
	public Sprite getSpriteFromState (ButtonState s) { 
		return spriteMap.get (s);
	}
	
	@Override
	public void draw () {
		
		//Set the sprite accordingly
		setSprite (getSpriteFromState (getButtonState ()));
		
		//Use GuiComponent's draw
		super.draw ();
		
	}

}
