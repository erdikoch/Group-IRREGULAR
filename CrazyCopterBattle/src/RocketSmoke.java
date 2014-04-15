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

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class RocketSmoke {

	private int xCoordinate;
	private int yCoordinate;

	public long smokeLifeTime;
	public long timeOfCreation;

	public static BufferedImage smokeImg;

	public float imageTransparency;

	public void Initialize(int xCoordinate, int yCoordinate, long gameTime,
			long smokeLifeTime) {
		setGameTime(gameTime);
		setCoordinates(xCoordinate, yCoordinate);
		setSmokeLifeTime(smokeLifeTime);
		setImageTransparency();
	}

	private void setGameTime(long gameTime) {
		this.timeOfCreation = gameTime;
	}

	private void setImageTransparency() {
		this.imageTransparency = 1.0f;
	}

	private void setSmokeLifeTime(long smokeLifeTime) {
		this.smokeLifeTime = smokeLifeTime;
	}

	private void setCoordinates(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public void updateTransparency(long gameTime) {

		long currentLifeTime = gameTime - timeOfCreation;

		int currentLTInPercentages = (int) (currentLifeTime * 100 / smokeLifeTime);
		currentLTInPercentages = 100 - currentLTInPercentages;
		float rSmokeTransparency = 1.0f * (currentLTInPercentages * 0.01f);

		if (rSmokeTransparency > 0)
			imageTransparency = rSmokeTransparency;
	}

	public boolean isSmokeDisapper(long gameTime) {
		long currentLifeTime = gameTime - timeOfCreation;
		if (currentLifeTime >= smokeLifeTime)
			return true;
		else
			return false;
	}

	public void Draw(Graphics2D g2d) {
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				imageTransparency));

		// While smoke is disappearing (imageTransparency), it also expanding.
		// We will multiply smoke image with imageMultiplier so that will slowly
		// become bigger.
		float imageMultiplier = 2 - imageTransparency;
		int newImageWidth = (int) (smokeImg.getWidth() * imageMultiplier);
		int newImageHeight = (int) (smokeImg.getHeight() * imageMultiplier);
		// We will set new y coordinate of smoke so that its stays in center
		// behind the rocket.
		int newImageYCoordinate = (int) (smokeImg.getHeight() / 2 * (1 - imageTransparency));
		g2d.drawImage(smokeImg, xCoordinate, yCoordinate - newImageYCoordinate,
				newImageWidth, newImageHeight, null);

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
	}
}