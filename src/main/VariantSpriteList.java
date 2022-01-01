package main;

import java.util.ArrayList;
import java.util.Collection;

import resources.Sprite;

public class VariantSpriteList {
	private String attributeName;
	private ArrayList<String> attributeValueList;
	private ArrayList<Sprite> spriteList;
	public VariantSpriteList () {
		this (new Sprite[0], new String[0], null);
	}
	public VariantSpriteList (Sprite[] spriteList, String[] attributeValueList) {
		this (spriteList, attributeValueList, null);
	}
	public VariantSpriteList (Collection<Sprite> spriteList, Collection<String> attributeValueList) {
		this (spriteList, attributeValueList, null);
	}
	public VariantSpriteList (Sprite[] spriteList, String[] attributeValueList, String attributeName) {
		this.attributeValueList = new ArrayList<String> ();
		this.spriteList = new ArrayList<Sprite> ();
		addSprites (spriteList, attributeValueList);
		this.attributeName = attributeName;
	}
	public VariantSpriteList (Collection<Sprite> spriteList, Collection<String> attributeValueList, String attributeName) {
		this.attributeValueList = new ArrayList<String> ();
		this.spriteList = new ArrayList<Sprite> ();
		addSprites (spriteList, attributeValueList);
		this.attributeName = attributeName;
	}
	public Sprite getSprite (String attributeValue) {
		for (int i = 0; i < attributeValueList.size (); i ++) {
			if (attributeValueList.get (i).equals (attributeValue)) {
				return spriteList.get (i);
			}
		}
		return null;
	}
	public void addSprite (Sprite sprite, String attributeValue) {
		spriteList.add (sprite);
		attributeValueList.add (attributeValue);
	}
	public void addSprites (Sprite[] spriteList, String[] attributeValueList) {
		if (attributeValueList != null && spriteList != null) {
			if (spriteList.length == attributeValueList.length) {
				for (int i = 0; i < attributeValueList.length; i ++) {
					this.attributeValueList.add (attributeValueList [i]);
				}
				for (int i = 0; i < spriteList.length; i ++) {
					this.spriteList.add (spriteList [i]);
				}
			}
		}
	}
	public void addSprites (Collection<Sprite> spriteList, Collection<String> attributeValueList) {
		if (attributeValueList != null && spriteList != null) {
			if (spriteList.size () != attributeValueList.size ()) {
				this.spriteList.addAll (spriteList);
				this.attributeValueList.addAll (attributeValueList);
			}
		}
	}
	public void setAttributeName (String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeName () {
		return attributeName;
	}
}
