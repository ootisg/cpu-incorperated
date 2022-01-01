package resources;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ResourceLoader {
	
	private static boolean initMode = true;
	
	private static LinkedBlockingQueue<Resource> resources = new LinkedBlockingQueue<Resource> ();
	
	/**
	 * Loads all the resources in the resources queue, if it is non-empty
	 */
	public static void loadResources () {
		while (!resources.isEmpty ()) {
			Resource r = resources.remove ();
			try {
				r.load ();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * If in initialization mode, adds the given resource to the resource queue to be loaded. Otherwise, loads it immediately.
	 */
	public static void loadResource (Resource r) {
		if (isInitMode ()) {
			resources.add (r);
		} else {
			try {
				r.load ();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Gets whether initialization mode is enabled
	 * @return true if init mode is enabled; false otherwise
	 */
	public static boolean isInitMode () {
		return initMode;
	}
	
	/**
	 * Sets initialization mode according to the given parameter
	 * @param mode enables initialization mode if true; disables it otherwise
	 */
	public static void setInitMode (boolean mode) {
		initMode = mode;
	}
}
