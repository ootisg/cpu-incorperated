package ui;

import java.awt.Rectangle;

import main.MainLoop;
import resources.Sprite;

public abstract class Button extends GuiComponent {

	private ButtonState state;
	
	public static enum ButtonState {
		BUTTON_UP,
		BUTTON_HOVERED,
		BUTTON_DOWN
	}
	
	public Button (GUI handler, String id) {
		super (handler, id);
		state = ButtonState.BUTTON_UP;
	}
	
	public ButtonState getButtonState () {
		return state;
	}

	public void setButtonState (ButtonState state) {
		this.state = state;
	}
	
	@Override
	public void mouseButtonEvent (int button, double x, double y) {
		
		//Left-click
		if (button == 0) {
			state = ButtonState.BUTTON_DOWN;
		}
		
		//Right-click
		
		//Middle-click
		
	}

	@Override
	public void mouseMoveEvent (double x, double y) {
		
	}

	@Override
	public void mouseEnterEvent () {
		if (state == ButtonState.BUTTON_UP) {
			state = ButtonState.BUTTON_HOVERED;
		}
	}

	@Override
	public void mouseExitEvent () {
		if (state == ButtonState.BUTTON_HOVERED) {
			state = ButtonState.BUTTON_UP;
		}
	}

}
