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


public class PlayerHelicopter {

	private final int healthInit = 100;
	public int playerHealth;

	public int xCoordinate;
	public int yCoordinate;
	
	private double movingXspeed;
	public double movingYspeed;
	
	private final int numberOfRocketsInit = 80;
	public int numberOfRockets;

	private final int numberOfAmmoInit = 1400;
	public int numberOfBullets;
	
	private int offsetXMachineGun;
	private int offsetYMachineGun;

	public int machineGunXcoordinate;
	public int machineGunYcoordinate;
	
	public PlayerHelicopter(int xCoordinate, int yCoordinate) {
		setCoordinates(xCoordinate, yCoordinate);

	}
	private void Initialize() {
		
	}
	
	public void Reset(int xCoordinate, int yCoordinate) {
		setHealth();
		setAmmo();
		setCoordinates(xCoordinate, yCoordinate);
		setMachineGunCoordinates();
		setSpeeds();
	}
	
	private void setCoordinates(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
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

	
}
