package ui;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.GameObject;
import resources.Sprite;

public class Textbox extends GameObject {
	
	public static final int BOX_OFFSET_H = 10; //Offset from the left side of the screen
	public static final int BOX_OFFSET_V = 10; //Offset from the bottom of the screen
	
	public static final int TEXT_OFFSET_X = 308; //Offset from the left of the textbox
	public static final int TEXT_OFFSET_Y = 4; //Offset from the top of the textbox
	
	public static final int EMOTE_OFFSET = 4; //Symmetric x and y offset for the emote
	
	public static Sprite textboxBg = new Sprite ("resources/ui/textbox.png");
	
	private String emoteId;
	private Sprite emoteImg;
	
	private TextRenderer textRenderer;
	
	public Textbox () {
		
		//Initialize bg and emote display
		setSprite (textboxBg);
		declare (BOX_OFFSET_H, getWindow ().getResolution ()[1] - textboxBg.getHeight () - BOX_OFFSET_V);
		setEmote ("face_neutral");
		
		//Initialize the text renderer
		textRenderer = new TextRenderer (null);
		
	}
	
	public void setEmote (String id) {
		emoteId = id;
		emoteImg = new Sprite ("resources/ui/" + id + ".png");
	}
	
	public String getEmoteId () {
		return emoteId;
	}
	
	@Override
	public void draw () {
		
		//Cast x and y to integers for convenience
		int textX = (int)getX () + TEXT_OFFSET_X;
		int textY = (int)getY () + TEXT_OFFSET_Y;
		
		//Draw the textbox and the background
		super.draw ();
		emoteImg.draw ((int)getX () + EMOTE_OFFSET, (int)getY () + EMOTE_OFFSET);
		
		//Draw the text
		Rectangle bounds = new Rectangle (textX, textY, 200, 40);
		textRenderer.renderText (textX, textY, bounds);
		
	}

}
