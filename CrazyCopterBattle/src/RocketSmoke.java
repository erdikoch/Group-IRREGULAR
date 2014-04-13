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

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class RocketSmoke {

	private int xCoordinate;
	private int yCoordinate;

	public long smokeLifeTime;

	public long timeOfCreation;

	public static BufferedImage smokeImg;

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
	}

}
