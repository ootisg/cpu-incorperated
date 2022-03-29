package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import main.MainLoop;
import resources.Sprite;

public class TextButton extends Button {
	
	private String buttonText;
	
	private Font font = null;
	private Color textColor = null;
	
	private Align align;
	
	public TextButton (String buttonText, GUI handler, String id) {
		super (handler, id);
		this.buttonText = buttonText;
	}
	
	//Getters and setters
	public Color getTextColor () {
		return textColor;
	}

	public void setTextColor (Color textColor) {
		this.textColor = textColor;
	}

	public void setFont (Font font) {
		this.font = font;
	}

	public Font getFont () {
		return font;
	}
	
	public String getButtonText () {
		return buttonText;
	}

	public void setButtonText (String buttonText) {
		this.buttonText = buttonText;
	}
	
	public Align getAlign () {
		return align == null ? getDefaultAlign () : align;
	}

	public void setAlign (Align align) {
		this.align = align;
	}
	
	//Helper methods
	private Align getDefaultAlign () {
		return new AlignCenter ();
	}
	
	//Overrides
	@Override
	public void draw () {
		
		//Get window's graphics and configure the font
		Graphics g = MainLoop.getWindow ().getBufferGraphics ();
		g.setColor (Color.RED);
		g.fillRect (getBoundingRectangle ().x, getBoundingRectangle ().y, getBoundingRectangle ().width, getBoundingRectangle ().height);
		if (font != null) {
			g.setFont (font);
		}
		if (textColor != null) {
			g.setColor (textColor);
		}
		
		//Compute the positioning of the text
		FontMetrics fm = g.getFontMetrics ();
		Rectangle bounds = getBoundingRectangle ();
		Point alignedPt = getAlign ().alignTo (bounds, getButtonText (), fm);
		int drawX = alignedPt.x;
		int drawY = alignedPt.y;
		
		//Draw the text
		g.drawString (getButtonText (), drawX, drawY);
		
	}

	public static abstract class Align {
		
		public Align () {
			
		}
		
		public abstract Point alignTo (Rectangle outside, String s, FontMetrics fm);
		
	}
	
	public static class AlignCenter extends Align {
		
		public AlignCenter () {
			super ();
		}
		
		public Point alignTo (Rectangle outside, String s, FontMetrics fm) {
			Dimension inside = new Dimension (fm.stringWidth (s), fm.getHeight ());
			int offsX = (outside.width - inside.width) / 2;
			int offsY = (outside.height - inside.height) / 2;
			return new Point (outside.x + offsX, outside.y + offsY + fm.getAscent ());
		}
		
	}
	
	public static class AlignLeft extends Align {
		
		private int padding;
		
		public AlignLeft (int padding) {
			super ();
			this.padding = padding;
		}
		
		public Point alignTo (Rectangle outside, String s, FontMetrics fm) {
			Dimension inside = new Dimension (fm.stringWidth (s), fm.getHeight ());
			int offsX = padding;
			int offsY = (outside.height - inside.height) / 2;
			return new Point (outside.x + offsX, outside.y + offsY + fm.getAscent ());
		}
		
	}

}