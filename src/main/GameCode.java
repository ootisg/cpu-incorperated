package main;

import java.awt.Color;
import java.awt.Rectangle;

import resources.Sprite;
import ui.BottomTabMenu;
import ui.Button;
import ui.GUI;
import ui.ImageButton;
import ui.TextButton;
import ui.Textbox;

public class GameCode extends GameAPI {
	
	public static void initialize () {
		
		//new Textbox ();
		
		GUI gui = new GUI ();
		
		//Make button
		Sprite up = new Sprite ("resources/ui/sample_button_up.png");
		Sprite hover = new Sprite ("resources/ui/sample_button_hover.png");
		Sprite down = new Sprite ("resources/ui/sample_button_down.png");
		TextButton b = new TextButton ("button", gui, "button");
		b.setTextColor (Color.BLACK);
		b.setBoundingRectangle (new Rectangle (200, 300, 128, 24));
		
		//Make menu
		BottomTabMenu btm = new BottomTabMenu (gui, "tabMenu");
		
	}
	
	public static void gameLoop () {
		
	}
	
}