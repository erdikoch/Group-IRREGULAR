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
	public int xCoordinate;
	public int yCoordinate;
	private double movingXspeed;

	public void Initialize(int xCoordinate, int yCoordinate) {
		setCoordinates(xCoordinate, yCoordinate);
		this.movingXspeed = 23;
	}

	private void setCoordinates(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
}
