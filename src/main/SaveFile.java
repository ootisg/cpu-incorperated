package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class SaveFile {
	
	private String fileName;
	
	private LinkedList<SaveNode> saveData;
	
	public SaveFile () {
		this.fileName = null;
		saveData = new LinkedList<SaveNode> ();
	}
	
	public SaveFile (String fileName) {
		this.fileName = fileName;
		setFile (fileName);
	}
	
	private class SaveNode {
		
		private String objId;
		private String mapName;
		private String data;
		
		public SaveNode (String data) {
			String[] parsed = data.split (":");
			if (parsed.length == 3) {
				this.mapName = parsed [0];
				this.objId = parsed [1];
				this.data = parsed [2];
			}
		}
		
		public SaveNode (String mapName, String objId, String data) {
			this.mapName = mapName;
			this.objId = objId;
			this.data = data;
		}
		
		public void setData (String data) {
			this.data = data;
		}
		
		public String getObjId () {
			return objId;
		}
		
		public String getMapName () {
			return mapName;
		}
		
		public String getData () {
			return data;
		}
		
		@Override
		public String toString () {
			return mapName + ":" + objId + ":" + data;
		}
	}
	
	public void save (String mapName, String objId, String data) {
		Iterator<SaveNode> iter = saveData.iterator ();
		while (iter.hasNext ()) {
			SaveNode working = iter.next ();
			if (working.getMapName ().equals (mapName) && working.getObjId ().equals (objId)) {
				working.setData (data);
				return;
			}
		}
		saveData.add (new SaveNode (mapName, objId, data));
	}
	
	public String getSaveData (String mapName, String objId) {
		Iterator<SaveNode> iter = saveData.iterator ();
		while (iter.hasNext ()) {
			SaveNode working = iter.next ();
			if (working.getMapName ().equals (mapName) && working.getObjId ().equals (objId)) {
				return working.getData ();
			}
		}
		return null;
	}
	
	public void writeToFile () {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter (new File (fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File saveFile = new File (fileName);
		Iterator<SaveNode> iter = saveData.iterator ();
		while (iter.hasNext ()) {
			SaveNode working = iter.next ();
			if (iter.hasNext ()) {
				writer.println (working);
			} else {
				writer.print (working);
			}
		}
		writer.close ();
	}
	
	public String getFileName () {
		return fileName;
	}
	
	public void setFile (String fileName) {
		this.fileName = fileName;
		saveData = new LinkedList<SaveNode> ();
		File workingFile = new File (fileName);
		try {
			Scanner fileScanner = new Scanner (workingFile);
			while (fileScanner.hasNextLine ()) {
				saveData.add (new SaveNode (fileScanner.nextLine ()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
