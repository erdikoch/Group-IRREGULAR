import java.awt.image.BufferedImage;

/*
 * Cs 320 Group Project / Group IRREGULAR
 * 
 * Crazy Copter Java Application Game Project (CCB)
 * 
 * Group Members : 
 * 1 - Erdi Koç
 * 2 - Gamze Küçükçolak
 * 3 - Mehmet Kaðan Kayaalp
 * 4 - Nazlý Karalar
 * 5 - Ýsmetcan Hergünþen
 * 
 */

public class Animation {

	private BufferedImage imageOfAnimation;
	private int frameWidth;
	private int frameHeight;
	private int numberOfFrames;
	private long frameTime; // Amount of time between frames in milliseconds.
	private long startingFrameTime;
	private long timeForNextFrame;
	private int currentFrameNumber;
	private boolean hasLoop;
	public int xLocation;
	public int yLocation;
	private int startingXLocationOfImageFrame;
	private int endingXLocationOfImageFrame;
	public boolean isActivated;
	private long showDelay;
	private long timeOfAnimationCreation;

	public Animation(BufferedImage animImage, int frameWidth, int frameHeight,
			int numberOfFrames, long frameTime, boolean loop, int x, int y,
			long showDelay) {

		this.imageOfAnimation = animImage;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.numberOfFrames = numberOfFrames;
		this.frameTime = frameTime;
		this.hasLoop = loop;
		this.xLocation = x;
		this.yLocation = y;
		this.showDelay = showDelay;
		timeOfAnimationCreation = System.currentTimeMillis();
		startingXLocationOfImageFrame = 0;
		endingXLocationOfImageFrame = frameWidth;
		startingFrameTime = System.currentTimeMillis() + showDelay;
		timeForNextFrame = startingFrameTime + this.frameTime;
		currentFrameNumber = 0;
		isActivated = true;
	}

	public void changeCoordinates(int x, int y) {
		this.xLocation = x;
		this.yLocation = y;
	}

	private void Update() {
		if (timeForNextFrame <= System.currentTimeMillis()) {
			currentFrameNumber++;

			// If the animation is reached the end, we restart it by setting
			// current frame to zero. If the animation isn't loop then we set
			// that animation isn't active.
			if (currentFrameNumber >= numberOfFrames) {
				currentFrameNumber = 0;

				if (!hasLoop)
					isActivated = false;
			}

			startingXLocationOfImageFrame = currentFrameNumber * frameWidth;
			endingXLocationOfImageFrame = startingXLocationOfImageFrame
					+ frameWidth;
			
			startingFrameTime = System.currentTimeMillis();
			timeForNextFrame = startingFrameTime + frameTime;
		}
	}

	public void Draw() {
		this.Update();
		//..
	}
}
