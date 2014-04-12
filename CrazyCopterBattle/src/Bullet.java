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
