package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

public class ObjectMatrix {
	public ArrayList<ArrayList<GameObject>> objectMatrix;
	public ArrayList<String> classNameList;
	public Class testClass;
	public static ArrayList<String> checkPackages;
	public long objectCount;
	public ObjectMatrix () {
		objectMatrix = new ArrayList<ArrayList<GameObject>> ();
		classNameList = new ArrayList<String> ();
		objectCount = 0;
	}
	public int[] add (GameObject gameObject) {
		//Adds an object to the object matrix and returns its position as [x, y]
		boolean typeDeclared = false;
		int[] coords = new int[2];
		for (int i = 0; i < classNameList.size (); i ++) {
			if (gameObject.getClass ().getName ().equals (classNameList.get (i))) {
				coords [0] = i;
				if (objectMatrix.get (i).indexOf (null) != -1) {
					coords [1] = objectMatrix.get (i).indexOf (null);
					objectMatrix.get (i).set (coords [1], gameObject);
				} else {
					coords [1] = objectMatrix.get (i).size ();
					objectMatrix.get (i).add (gameObject);
				}
				typeDeclared = true;
			}
		}
		if (!typeDeclared) {
			coords [0] = classNameList.size ();
			coords [1] = 0;
			classNameList.add (gameObject.getClass ().getName ());
			objectMatrix.add (new ArrayList<GameObject> ());
			objectMatrix.get (objectMatrix.size () - 1).add (gameObject);
		}
		return coords;
	}
	public long getNextOrderNumber () {
		objectCount ++;
		return objectCount - 1;
	}
	public void remove (int[] coords) {
		//Removes an object from the object matrix with the coordinates [x, y]
		objectMatrix.get (coords [0]).set (coords [1], null);
	}
	public ArrayList<GameObject> getAll () {
		ArrayList<GameObject> objects = new ArrayList<GameObject> ();
		int objectArrayLength1 = objectMatrix.size ();
		int objectArrayLength2;
		for (int i = 0; i < objectArrayLength1; i ++) {
			ArrayList<GameObject> objectArray = objectMatrix.get (i);
			objectArrayLength2 = objectArray.size ();
			for (int c = 0; c < objectArrayLength2; c ++) {
				if (objectArray.get (c) != null) {
					objects.add (objectArray.get (c));
				}
			}
		}
		return objects;
	}
	public ArrayList<GameObject> getObjects (String objectType) {
		int type = getTypeId (objectType);
		if (type != -1) {
			return objectMatrix.get (type);
		}
		return null;
	}
	public void callAll () {
		ArrayList<GameObject> objects = getAll ();
		Collections.sort (objects);
		for (int i = 0; i < objects.size (); i ++) {
			if (objects.get (i).isDeclared ()) {
				if (MainLoop.isPaused ()) {
					if (objects.get (i).ignoresPause ()) {
						objects.get (i).frameEvent ();
					} else {
						objects.get (i).pauseEvent ();
					}
				} else {
					objects.get (i).frameEvent ();
				}
			}
		}
		for (int i = 0; i < objects.size (); i ++) {
			if (objects.get (i).isDeclared ()) {
				if (!objects.get (i).isHidden ()) {
					objects.get (i).draw ();
				}
			}
		}
	}
	public void resumeAll () {
		//Calls the onResume method on each GameObject
		ArrayList<GameObject> objects = getAll ();
		Collections.sort (objects);
		for (int i = 0; i < objects.size (); i ++) {
			if (objects.get (i).isDeclared ()) {
				objects.get (i).onResume ();
			}
		}
	}
	public void pauseAll () {
		//Calls the onPause method of each object
		ArrayList<GameObject> objects = getAll ();
		Collections.sort (objects);
		for (int i = 0; i < objects.size (); i ++) {
			if (objects.get (i).isDeclared ()) {
				objects.get (i).onPause ();
			}
		}
	}
	public GameObject get (int x, int y) {
		//Gets the GameObject with an index of x, y
		return objectMatrix.get (x).get (y);
	}
	public GameObject get (String objectName, int pos) {
		int xpos = this.getTypeId (objectName);
		if (xpos != -1) {
			return objectMatrix.get (xpos).get (pos);
		}
		return null;
	}
	public ArrayList<GameObject> getAll (Class parentType) {
		ArrayList<GameObject> objects = new ArrayList<GameObject> ();
		for (int i = 0; i < objectMatrix.size (); i ++) {
			GameObject first = getFirst (objectMatrix.get (i));
			if (first != null) {
				if (parentType.isAssignableFrom (first.getClass ())) {
					for (int j = 0; j < objectMatrix.get (i).size (); j ++) {
						if (objectMatrix.get (i).get (j) != null) {
							objects.add (objectMatrix.get (i).get (j));
						}
					}
				}
			}
		}
		return objects;
	}
	public GameObject getFirst (ArrayList<GameObject> objs) {
		GameObject current = null;
		for (int i = 0; i < objs.size (); i ++) {
			if (objs.get (i) != null) {
				current = objs.get (i);
				break;
			}
		}
		return current;
	}
	public int getTypeId (String objectName) {
		//Gets the x-coordinate on the object matrix of the name objectName
		for (int i = 0; i < classNameList.size (); i ++) {
			if (classNameList.get (i).equals (objectName)) {
				return i;
			}
		}
		return -1;
	}
	public String getStringId (int objectId) {
		//Gets the string id associated with the x-coordinate objectId on the object matrix
		return classNameList.get (objectId);
	}
	public static void initPackages () {
		checkPackages = new ArrayList<String> ();
		checkPackages.add ("gameObjects");
		checkPackages.add ("puzzle");
		checkPackages.add ("items");
		checkPackages.add ("farming");
	}
	public static GameObject makeInstance (String objectType) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> objClass = null;
		for (int i = 0; i < checkPackages.size (); i++) {
			try {
				objClass = Class.forName (checkPackages.get (i) + "." + objectType);
				break;
			} catch (ClassNotFoundException e) {
				//Do nothing
			}
		}
		if (objClass == null) {
			throw new ClassNotFoundException ();
		} else {
			Constructor<?> objConstructor = objClass.getConstructors ()[0];
			GameObject obj = (GameObject)objConstructor.newInstance ();
			return obj;
		}
	}
}