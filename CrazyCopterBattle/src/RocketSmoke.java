/*
 * Cs 320 Group Project / Group IRREGULAR
 * 
 * Crazy Copter Java Application Game Project (CCB)
 * 
 * Group Members : 
 * 1 - Erdi Ko�
 * 2 - Gamze K���k�olak
 * 3 - Mehmet Ka�an Kayaalp
 * 4 - Nazl� Karalar
 * 5 - �smetcan Herg�n�en
 * 
 */

/*
 * Cs 320 Group Project / Group IRREGULAR
 * 
 * Crazy Copter Java Application Game Project (CCB)
 * 
 * Group Members : 
 * 1 - Erdi Ko�
 * 2 - Gamze K���k�olak
 * 3 - Mehmet Ka�an Kayaalp
 * 4 - Nazl� Karalar
 * 5 - �smetcan Herg�n�en
 * 
 */

/*
 * Cs 320 Group Project / Group IRREGULAR
 * 
 * Crazy Copter Java Application Game Project (CCB)
 * 
 * Group Members : 
 * 1 - Erdi Ko�
 * 2 - Gamze K���k�olak
 * 3 - Mehmet Ka�an Kayaalp
 * 4 - Nazl� Karalar
 * 5 - �smetcan Herg�n�en
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
