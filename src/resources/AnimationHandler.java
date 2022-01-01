package resources;

import main.MainLoop;

public class AnimationHandler {
	private Sprite sprite;
	private int frame = 0;
	private int animationTime = 0;
	private double animationSpeed = 0.25;
	private boolean enabled = true;
	private boolean ignorePause = false;
	private boolean repeat = true;
	private boolean done = false;
	public AnimationHandler (Sprite sprite) {
		this.sprite = sprite;
	}
	public void animate (int x, int y, boolean flipHorizontal, boolean flipVertical) {
		sprite.setFrame (frame);
		sprite.draw (x, y, flipHorizontal, flipVertical);
		if (!MainLoop.isPaused () || ignorePause) {
			if (animationTime >= Math.round (1/animationSpeed)) {
				frame ++;
				animationTime = 0;
			}
			if (frame > sprite.getFrameCount () - 1) {
				if (repeat) {
					frame = 0;
				} else {
					frame = sprite.getFrameCount () - 1;
					done = true;
				}
			}
			animationTime ++;
		}
	}
	public void setSprite (Sprite sprite) {
		this.sprite = sprite;
	}
	public Sprite getSprite () {
		return this.sprite;
	}
	public double getAnimationSpeed () {
		return animationSpeed;
	}
	public void setAnimationSpeed (double animationSpeed) {
		this.animationSpeed = animationSpeed;
	}
	public void setFrame (int frame) {
		this.frame = frame;
	}
	public int getFrame () {
		return this.frame;
	}
	public boolean repeats () {
		return repeat;
	}
	public void setRepeat (boolean doRepeat) {
		this.repeat = doRepeat;
	}
	public void setIgnorePause (boolean ignorePause) {
		this.ignorePause = ignorePause;
	}
	public boolean ignoresPause () {
		return ignorePause;
	}
	public boolean isDone () {
		return done;
	}
}