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

	public BufferedImage smokeImg;

	public float imageTransparency;


	public void Initialize(int xCoordinate, int yCoordinate, long gameTime, long smokeLifeTime)
	{
		this.timeOfCreation = gameTime;

		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;

		this.smokeLifeTime = smokeLifeTime;

		this.imageTransparency = 1.0f;
	}



	public boolean didSmokeDisapper(long gameTime){
		long currentLifeTime = gameTime - timeOfCreation;

		if(currentLifeTime >= smokeLifeTime)
			return true;
		else
			return false;
	}


	public void Draw(Graphics2D g2d)
	{
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imageTransparency));

		float imageMultiplier = 2 - imageTransparency; 
		int newImageWidth = (int)(smokeImg.getWidth() * imageMultiplier);
		int newImageHeight = (int)(smokeImg.getHeight() * imageMultiplier);
		int newImageYCoordinate = (int)(smokeImg.getHeight()/2 * (1-imageTransparency)); 
		g2d.drawImage(smokeImg, xCoordinate, yCoordinate - newImageYCoordinate, newImageWidth, newImageHeight, null);

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
	}
}


