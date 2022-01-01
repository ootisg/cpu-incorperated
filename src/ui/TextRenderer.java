package ui;

import json.JSONArray;
import json.JSONObject;

public class TextRenderer {
	
	private JSONArray contents;
	
	public TextRenderer (JSONObject obj) {
		
		//Set text contents to the given JSON
		contents = obj.getJSONArray ("textContents");
		
	}
	
	public void renderSubText (int x, int y, int boundWidth, int boundHeight, int startIndex, int endIndex) {
		
	}
	
	public void renderText (int x, int y, int boundWidth, int boundHeight) {
		
	}
	
	private void renderString (JSONObject context, String str) {
		
	}

}