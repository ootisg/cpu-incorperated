package main;

import java.awt.Toolkit;

import resources.ResourceLoader;

public class MainLoop {
	//Main class
	private static GameWindow gameWindow;
	private static ObjectMatrix objectMatrix;
	private static Console console;
	private static GameCode gameCode;
	private static double framerate = 30;
	private static boolean running = true; //Currently unused
	private static long startTime;
	private static long delay; //Used for loop timing
	private static boolean paused; //If the game is paused
	public static boolean freezeFrame = false; //Whether to freeze on the current frame
	public static boolean advanceFrame = false; //Whether to advance the current frame
	public static void main (String[] args) {
		//Main method
		ResourceLoader.loadResources (); //Load all of the queued resources and disable initialization mode
		ResourceLoader.setInitMode (false);
		paused = false; //Game starts unpaused
		delay = 0; //Used for loop timing
		gameWindow = new GameWindow (); //Create the window
		objectMatrix = new ObjectMatrix (); //Create the object matrix
		ObjectMatrix.initPackages (); //Initiate search packages in the object matrix
		console = new Console (); //Create the dev console
		gameCode = new GameCode (); //Initialize game code
		gameCode.initialize (); //Note: runs before gameCode.gameLoop ()
		doLoop ();
	}
	public static void doFrame () {
		try {
			//This try block catches any errors while the game is running
			gameWindow.updateClick (); //Updates mouse input information
			if (!console.isEnabled ()) {
				//Only run if the console is disabled
				gameWindow.inputCacheMode (true);
				gameCode.gameLoop ();
				objectMatrix.callAll (); //For each child of GameObject for which the method declare has been called, this calls that object's draw method, then its frameEvent method
			}
			gameWindow.clearKeyArrays (); //Resets keystroke events that only fire for 1 frame
			gameWindow.inputCacheMode (false);
			gameWindow.doPaint (); //Refreshes the game window
		}
		catch (Throwable e) {
			//Displays the console if an error occurs, and print out information usable for debugging
			e.printStackTrace ();
			console.enable ("A runtime error has occured: " + e.getClass ());
		}
	}
	public static void doLoop () {
		while (running) {
			advanceFrame = false;
			//Everything in here is run once per frame
			startTime = System.currentTimeMillis(); //Used for loop timing
			doFrame ();
			delay = System.currentTimeMillis () - startTime; //Used for timing of the loop
			//System.out.println (System.currentTimeMillis() - startTime);
			//The following is used for delaying the loop to a rate equal to the framerate
			if (delay < (1000 / framerate)) {
				try {
					if (1000 / (long) framerate - delay - 1 > 0) {
						Thread.sleep (1000 / (long) framerate - delay - 1);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while ((freezeFrame && !advanceFrame)) {
				try {
					Thread.sleep (1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while (System.currentTimeMillis() - startTime < 1000 / (long) framerate) {
			}
		}
	}
	public static GameWindow getWindow () {
		return gameWindow;
	}
	public static ObjectMatrix getObjectMatrix () {
		return objectMatrix;
	}
	public static Console getConsole () {
		return console;
	}
	public static long getDelay () {
		return delay;
	}
	public static void pause () {
		paused = true;
		objectMatrix.pauseAll ();
	}
	public static void resume () {
		paused = false;
		objectMatrix.resumeAll ();
	}
	public static boolean isPaused () {
		return paused;
	}
	public static void startFrameAdvance () {
		freezeFrame = true;
	}
	public static void advanceFrame () {
		advanceFrame = true;
	}
}