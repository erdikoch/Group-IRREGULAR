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

import java.awt.Point;

public class Bullet {

     public double xCoordinate;
	public double yCoordinate;
	private static int bulletSpeed = 20;
	private double movingXspeed;
	private double movingYspeed;

public Bullet(int xCoordinate, int yCoordinate, Point mousePosition) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		setDirectionAndSpeed(mousePosition);

	}

	private void setDirectionAndSpeed(Point mousePosition) {
		// Unit direction vector of the bullet.
		double directionVx = mousePosition.x - this.xCoordinate;
		double directionVy = mousePosition.y - this.yCoordinate;
		double lengthOfVector = Math.sqrt(directionVx * directionVx
				+ directionVy * directionVy);
		
		directionVx = directionVx / lengthOfVector;
		directionVy = directionVy / lengthOfVector;

		setSpeeds(directionVx, directionVy);
	}

	private void setSpeeds(double directionVx, double directionVy) {
		this.movingXspeed = bulletSpeed * directionVx;
		this.movingYspeed = bulletSpeed * directionVy;
	}





}
