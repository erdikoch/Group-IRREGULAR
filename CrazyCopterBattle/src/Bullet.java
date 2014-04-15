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
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bullet {

	public double xCoordinate;
	public double yCoordinate;
	private static int bulletSpeed = 20;
	private double movingXspeed;
	private double movingYspeed;
public static BufferedImage bulletImg;

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
	public boolean isLeftScreen() {
		if (xCoordinate > 0 && xCoordinate < Framework.frameWidth
				&& yCoordinate > 0 && yCoordinate < Framework.frameHeight)
			return false;
		else
			return true;
	}
public void Update() {
		xCoordinate += movingXspeed;
		yCoordinate += movingYspeed;
	}

	public void Draw(Graphics2D g2d) {
		g2d.drawImage(bulletImg, (int) xCoordinate, (int) yCoordinate, null);
	}
}
