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
		contents = obj.getJSONArray ("textContents");
		
	}
	
	public void renderSubText (int x, int y, Rectangle bounds, int startIndex, int endIndex) {
		
	}
	
	public StringRenderInfo renderText (int x, int y, Rectangle bounds) {
		
		int wx = x, wy = y;
		StringRenderInfo ri = null;
		for (int i = 0; i < contents.getContents ().size (); i++) {
			ri = renderString (wx, wy, bounds, (JSONObject)contents.get (i), ((JSONObject)contents.get (i)).getString ("text"));
			wx = ri.endX;
			wy = ri.endY;
		}
		return ri;
		
	}
	
	private StringRenderInfo renderString (int x, int y, Rectangle bounds, JSONObject context, String str) {
		
		//# of padding pixels
		int PADDING = 2;
		
		//Get the graphics object
		Graphics g = getWindow ().getBufferGraphics ();
		
		//Set the color appropriately
		String usedColor = context.getString ("color");
		if (usedColor != null) {
			g.setColor (new Color (Integer.parseInt (usedColor, 16)));
		} else {
			g.setColor (Color.WHITE); //Default color
		}
		
		//Draw the text
		FontMetrics fm = g.getFontMetrics ();
		int maxLen = bounds.x + bounds.width - x; //Line length
		char[] buffer = str.toCharArray (); //Use a char[] for efficiency
		int len = 0; //Line length
		int offs = 0; //Line offset
		int wy = y + fm.getAscent (); //Y to draw line
		int wx = x; //X to draw line (wraps to start of bounds, so first line is indented to x)
		int strEndX = wx, strEndY = wy;
		while (wy < bounds.y + bounds.height && offs + len < buffer.length) {
			while (fm.charsWidth (buffer, offs, len) < maxLen && offs + len < buffer.length) {
				len++; //Add chars until out of data or the line length is exceeded
			}
			if (len == 0) {
				return null; //Prevent infinite loop edge case
			}
			strEndX = wx + fm.charsWidth (buffer, offs, len);
			strEndY = wy - fm.getAscent (); //Save ending coords for string
			g.drawChars (buffer, offs, len, wx, wy); //Draws the line
			wy += fm.getAscent () + PADDING; //Move wy down for the next line
			wx = bounds.x; //Reset wx to the left of bounds
			offs += len; //Advance the line offset in the buffer
			len = 0; //Reset the string length
			maxLen = bounds.x + bounds.width - wx; //Update maxLen
		}
		return new StringRenderInfo (strEndX, strEndY, offs); //Return the index of the last character drawn
		
	}
	
	public class StringRenderInfo {
		
		public int endX;
		public int endY;
		public int endCharIndex;
		
		public StringRenderInfo (int endX, int endY, int endCharIndex) {
			this.endX = endX;
			this.endY = endY;
			this.endCharIndex = endCharIndex;
		}
		
	}

}