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

public class Rocket {

	public final static long timeBetweenNewRockets = Framework.secInNanosec / 4;
	public static long timeOfLastCreatedRocket = 0;

	public static int damagePower = 100;

	public int xCoordinate;
	public int yCoordinate;

	private double movingXspeed;
	public long currentSmokeLifeTime;
	public static BufferedImage rocketImg;

	public void Initialize(int xCoordinate, int yCoordinate) {
		setCoordinates(xCoordinate, yCoordinate);
		this.movingXspeed = 23;
		this.currentSmokeLifeTime = Framework.secInNanosec / 2;
	}

	private void setCoordinates(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public boolean isLeftScreen() {
		if (xCoordinate > 0 && xCoordinate < Framework.frameWidth)
			return false;
		else
			return true;
	}

	public void Update() {
		xCoordinate += movingXspeed;
	}

	public void Draw(Graphics2D g2d) {
		g2d.drawImage(rocketImg, xCoordinate, yCoordinate, null);
	}
}


