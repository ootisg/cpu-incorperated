package main;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import resources.Sprite;
import resources.Spritesheet;

public class TextInterface extends GameAPI {
	private int textColor;
	private BufferedImage font;
	private Spritesheet fontRef;
	private Sprite renderSprite;
	private BufferedImage renderImage;
	private WritableRaster charRaster;
	private int[] charData = new int[64];
	public TextInterface (Spritesheet font) {
		this.fontRef = font;
		this.font = new BufferedImage (font.getWidth (), font.getHeight (), BufferedImage.TYPE_4BYTE_ABGR);
		this.font.getGraphics ().drawImage (font.getImage (), 0, 0, null);
		this.renderImage = new BufferedImage (8, 8, BufferedImage.TYPE_INT_ARGB);
		this.renderSprite = new Sprite (renderImage);
		this.charRaster = renderImage.getRaster ();
		this.textColor = 0xFF000000;
	}
	public void drawChar (char character, int x, int y) {
		drawCharById ((int)character, x, y);
	}
	public void drawCharById (int charId, int x, int y) {
		Raster fontRaster = font.getAlphaRaster ();
		if (fontRaster != null) {
		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j ++) {
				if (fontRaster.getSample (charId * 8 + j, i, 0) != 0) {
					charData [i * 8 + j] = textColor;
				} else {
					charData [i * 8 + j] = 0x00000000;
				}
			}
		}
		charRaster.setDataElements (0, 0, 8, 8, charData);
		renderImage.setData (charRaster);
		renderSprite.draw (x, y);
		}
	}
	public void setTextColorARGB (int color) {
		this.textColor = color;
	}
	public void setFont (Spritesheet font) {
		this.fontRef = font;
		this.font = new BufferedImage (font.getWidth (), font.getHeight (), BufferedImage.TYPE_4BYTE_ABGR);
		this.font.getGraphics ().drawImage (font.getImage (), 0, 0, null);
	}
	public int getTextColorARGB () {
		return this.textColor;
	}
	public Spritesheet getFont () {
		return this.fontRef;
	}
}