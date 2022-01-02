package ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import json.JSONArray;
import json.JSONObject;
import main.GameAPI;
import main.GameObject;

public class TextRenderer extends GameAPI {
	
	private JSONArray contents;
	
	public TextRenderer (JSONObject obj) {
		
		//Set text contents to the given JSON
		//contents = obj.getJSONArray ("textContents");
		
	}
	
	public void renderSubText (int x, int y, Rectangle bounds, int startIndex, int endIndex) {
		
	}
	
	public int renderText (int x, int y, Rectangle bounds) {
		
		return renderString (x, y, bounds, null, "123456789012345678901234567890123456789 123456789012345678901234567890123456789 A");
		
	}
	
	private int renderString (int x, int y, Rectangle bounds, JSONObject context, String str) {
		
		//# of padding pixels
		int PADDING = 2;
		
		//Get the graphics object
		Graphics g = getWindow ().getBufferGraphics ();
		
		//Set the color appropriately
		g.setColor (Color.WHITE);
		
		//Draw the text
		FontMetrics fm = g.getFontMetrics ();
		int maxLen = bounds.x + bounds.width - x; //Line length
		char[] buffer = str.toCharArray (); //Use a char[] for efficiency
		int len = 0; //Line length
		int offs = 0; //Line offset
		int wy = y + fm.getAscent (); //Y to draw line
		int wx = x; //X to draw line (wraps to start of bounds, so first line is indented to x)
		while (wy < bounds.y + bounds.height && offs + len < buffer.length) {
			while (fm.charsWidth (buffer, offs, len) < maxLen && offs + len < buffer.length) {
				len++; //Add chars until out of data or the line length is exceeded
			}
			if (len == 0) {
				return -1; //Prevent infinite loop edge case
			}
			g.drawChars (buffer, offs, len, x, wy); //Draws the line
			wy += fm.getAscent () + PADDING; //Move wy down for the next line
			wx = bounds.x; //Reset wx to the left of bounds
			offs += len; //Advance the line offset in the buffer
			len = 0; //Reset the string length
		}
		return offs; //Return the index of the last character drawn
		
	}

}