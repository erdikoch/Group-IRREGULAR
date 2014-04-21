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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class PlayerHelicopter {

	private final int healthInit = 100;
	public int playerHealth;
    private boolean isPaused = false;
	public int xCoordinate;
	public int yCoordinate;

	private double movingXspeed;
	public double movingYspeed;
	private double acceleratingXspeed;
	private double acceleratingYspeed;
	private double stoppingXspeed;
	private double stoppingYspeed;

	private final int numberOfRocketsInit = 80;
	public int numberOfRockets;

	private final int numberOfAmmoInit = 1400;
	public int numberOfBullets;

	public BufferedImage helicopterBodyImg;
	private BufferedImage helicopterFrontPropellerAnimImg;
	private BufferedImage helicopterRearPropellerAnimImg;

	private Animation helicopterFrontPropellerAnim;
	private Animation helicopterRearPropellerAnim;

	private int offsetXFrontPropeller;
	private int offsetYFrontPropeller;
	private int offsetXRearPropeller;
	private int offsetYRearPropeller;

	private int offsetXRocketHolder;
	private int offsetYRocketHolder;

	public int rocketHolderXcoordinate;
	public int rocketHolderYcoordinate;

	private int offsetXMachineGun;
	private int offsetYMachineGun;

	public int machineGunXcoordinate;
	public int machineGunYcoordinate;

	public PlayerHelicopter(int xCoordinate, int yCoordinate) {
		setCoordinates(xCoordinate, yCoordinate);
		LoadContent();
		Initialize();
	}

	private void setCoordinates(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	private void Initialize() {
		setHealth();

		setAmmo();

		setSpeeds();
		this.acceleratingXspeed = 0.2;
		this.acceleratingYspeed = 0.2;
		this.stoppingXspeed = 0.1;
		this.stoppingYspeed = 0.1;

		this.offsetXFrontPropeller = 70;
		this.offsetYFrontPropeller = -23;
		this.offsetXRearPropeller = -6;
		this.offsetYRearPropeller = -21;

		this.offsetXRocketHolder = 138;
		this.offsetYRocketHolder = 40;
		updateRocketHolders();

		this.offsetXMachineGun = helicopterBodyImg.getWidth() - 40;
		this.offsetYMachineGun = helicopterBodyImg.getHeight();
		setMachineGunCoordinates();
	}

	private void LoadContent() {
		try {
			helicopterBodyImg = ImageIO
					.read(new File(
							"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\1_helicopter_body.png"));
			helicopterFrontPropellerAnimImg = ImageIO
					.read(new File(
							"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\1_front_propeller_anim.png"));
			helicopterRearPropellerAnimImg = ImageIO
					.read(new File(
							"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\1_rear_propeller_anim_blur.png"));

		} catch (IOException ex) {
			Logger.getLogger(PlayerHelicopter.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		// Now that we have images of propeller animation we initialize
		// animation object.
		helicopterFrontPropellerAnim = new Animation(
				helicopterFrontPropellerAnimImg, 204, 34, 3, 20, true,
				xCoordinate + offsetXFrontPropeller, yCoordinate
						+ offsetYFrontPropeller, 0);
		helicopterRearPropellerAnim = new Animation(
				helicopterRearPropellerAnimImg, 54, 54, 4, 20, true,
				xCoordinate + offsetXRearPropeller, yCoordinate
						+ offsetYRearPropeller, 0);
	}

	public void Reset(int xCoordinate, int yCoordinate) {
		setHealth();
		setAmmo();
		setCoordinates(xCoordinate, yCoordinate);
		setMachineGunCoordinates();
		setSpeeds();
	}

	private void setHealth() {
		this.playerHealth = healthInit;
	}

	private void setAmmo() {
		this.numberOfRockets = numberOfRocketsInit;
		this.numberOfBullets = numberOfAmmoInit;
	}

	private void setSpeeds() {
		this.movingXspeed = 0;
		this.movingYspeed = 0;
	}

	private void setMachineGunCoordinates() {
		this.machineGunXcoordinate = this.xCoordinate + this.offsetXMachineGun;
		this.machineGunYcoordinate = this.yCoordinate + this.offsetYMachineGun;
	}

	public boolean isShooting(long gameTime) {
		if (UserController.mouseButtonState(MouseEvent.BUTTON1)
				&& ((gameTime - Bullet.timeOfLastCreatedBullet) >= Bullet.timeBetweenNewBullets)
				&& this.numberOfBullets > 0) {
			return true;
		} else
			return false;
	}

	public boolean isRocketFired(long gameTime) {
		if (UserController.mouseButtonState(MouseEvent.BUTTON3)
				&& ((gameTime - Rocket.timeOfLastCreatedRocket) >= Rocket.timeBetweenNewRockets)
				&& this.numberOfRockets > 0) {
			return true;
		} else
			return false;
	}

	public void isMoving() throws InterruptedException {
		if (UserController.keyboardKeyState(KeyEvent.VK_D)
				|| UserController.keyboardKeyState(KeyEvent.VK_RIGHT))
			movingXspeed += acceleratingXspeed;
		else if (UserController.keyboardKeyState(KeyEvent.VK_A)
				|| UserController.keyboardKeyState(KeyEvent.VK_LEFT))
			movingXspeed -= acceleratingXspeed;
		else // Stopping
		if (movingXspeed < 0)
			movingXspeed += stoppingXspeed;
		else if (movingXspeed > 0)
			movingXspeed -= stoppingXspeed;
		
		
	    if(UserController.keyboardKeyState(KeyEvent.VK_P)){
	    	isPaused =true;
	    	 pause();
	    }else {
	    	isPaused = false;
	   
	       
	    }

		if (UserController.keyboardKeyState(KeyEvent.VK_W)
				|| UserController.keyboardKeyState(KeyEvent.VK_UP))
			movingYspeed -= acceleratingYspeed;
		else if (UserController.keyboardKeyState(KeyEvent.VK_S)
				|| UserController.keyboardKeyState(KeyEvent.VK_DOWN))
			movingYspeed += acceleratingYspeed;
		else // Stopping
		if (movingYspeed < 0)
			movingYspeed += stoppingYspeed;
		else if (movingYspeed > 0)
			movingYspeed -= stoppingYspeed;
	}

	private void pause() {
	    try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Update() {

		updateSpeeds();
		updatePropellers();
		updateRocketHolders();
		setMachineGunCoordinates();
	
	}

	private void updateRocketHolders() {
		this.rocketHolderXcoordinate = this.xCoordinate
				+ this.offsetXRocketHolder;
		this.rocketHolderYcoordinate = this.yCoordinate
				+ this.offsetYRocketHolder;
	}

	private void updatePropellers() {
		helicopterFrontPropellerAnim.changeCoordinates(xCoordinate
				+ offsetXFrontPropeller, yCoordinate + offsetYFrontPropeller);
		helicopterRearPropellerAnim.changeCoordinates(xCoordinate
				+ offsetXRearPropeller, yCoordinate + offsetYRearPropeller);
	}

	private void updateSpeeds() {
		xCoordinate += movingXspeed;
		yCoordinate += movingYspeed;
	}

	public void Draw(Graphics2D g2d) {
		helicopterFrontPropellerAnim.Draw(g2d);
		helicopterRearPropellerAnim.Draw(g2d);
		g2d.drawImage(helicopterBodyImg, xCoordinate, yCoordinate, null);
	}

}