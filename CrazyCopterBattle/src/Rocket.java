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
