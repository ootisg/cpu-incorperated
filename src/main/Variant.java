package main;

import java.util.Collection;

import resources.Sprite;

public class Variant {
	private String[] attributeArray;
	private VariantSpriteList spriteList;
	public Variant (String variantData) {
		this (variantData, null);
	}
	public Variant (String variantData, VariantSpriteList spriteList) {
		this.attributeArray = variantData.split ("&");
		this.setSpriteList (spriteList);
	}
	public String getVariantData () {
		return String.join ("&", attributeArray);
	}
	public String getAttribute (String attributeName) {
		for (int i = 0; i < attributeArray.length; i ++) {
			String[] arr = attributeArray [i].split (":");
			if (arr.length == 2) {
				if (arr [0].equals (attributeName)) {
					return arr [1];
				}
			}
		}
		return null;
	}
	public boolean hasAttribute (String attributeName) {
		return getAttribute (attributeName) == null;
	}
	public void setVariantData (String variantData) {
		this.attributeArray = variantData.split ("&");
	}
	public void setAttribute (String attributeName, String attributeValue) {
		for (int i = 0; i < attributeArray.length; i ++) {
			String[] arr = attributeArray [i].split (":");
			if (arr.length == 2) {
				if (arr [0].equals (attributeName)) {
					attributeArray [i] = attributeName + ":" + attributeValue;
					return;
				}
			}
		}
		String[] temp = attributeArray;
		attributeArray = new String[temp.length + 1];
		System.arraycopy (temp, 0, attributeArray, 0, temp.length);
		attributeArray [temp.length] = attributeName + ":" + attributeValue;
	}
	public void setSpriteList (VariantSpriteList spriteList) {
		this.spriteList = spriteList;
	}
	public VariantSpriteList getSpriteList () {
		return this.spriteList;
	}
	public Sprite getSprite () {
		if (spriteList.getAttributeName () != null) {
			for (int i = 0; i < attributeArray.length; i ++) {
				String[] attributeSplit = attributeArray [i].split (":");
				if (attributeSplit.length == 2) {
					if (attributeSplit [0].equals (spriteList.getAttributeName ())) {
						return spriteList.getSprite (attributeSplit [1]);
					}
				}
			}
		}
		return null;
	}
}