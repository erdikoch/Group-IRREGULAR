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

import java.awt.image.BufferedImage;

public class MovingBackground {

	private BufferedImage backgroundImage;
	private double speedOfBackground;

	private double[] xPositions;
	private int yPosition;

	public void initialize(BufferedImage image, double speed, int yPosition) {
		this.backgroundImage = image;
		this.speedOfBackground = speed;

		this.yPosition = yPosition;

		int numberOfPositions = (Framework.frameWidth / this.backgroundImage
				.getWidth()) + 2; // We need to add 2 so that we don't get blank
		// spaces between images.
		xPositions = new double[numberOfPositions];

		for (int i = 0; i < xPositions.length; i++) {
			xPositions[i] = i * image.getWidth();
		}
	}

	private void Update() {
		for (int i = 0; i < xPositions.length; i++) {
			xPositions[i] += speedOfBackground;
			updateSpeedOfBackground(i);
		}
	}

	private void updateSpeedOfBackground(int i) {
		if (speedOfBackground < 0) {
			// If image is out of the screen, it moves it to the end of line
			// of images.
			if (xPositions[i] <= -backgroundImage.getWidth()) {
				xPositions[i] = backgroundImage.getWidth()
						* (xPositions.length - 1);
			}
		} else {
			if (xPositions[i] >= backgroundImage.getWidth()
					* (xPositions.length - 1)) {
				xPositions[i] = -backgroundImage.getWidth();
			}
		}
	}

	public void Draw() {
		this.Update();

		for (int i = 0; i < xPositions.length; i++) {
			g2d.drawImage(backgroundImage, (int) xPositions[i], yPosition, null);
		}

	}
}
