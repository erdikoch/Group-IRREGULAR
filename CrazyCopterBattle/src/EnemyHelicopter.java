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

public class EnemyHelicopter {


	private static final long timeBetweenNewEnemiesInit = Framework.secInNanosec * 3;
	public static long timeBetweenNewEnemies = timeBetweenNewEnemiesInit;
	public static long timeOfLastCreatedEnemy = 0;

	public int health;

	public int xCoordinate;
	public int yCoordinate;

	private static final double movingXspeedInit = -4;
	private static double movingXspeed = movingXspeedInit;

	public BufferedImage helicopterBodyImg;
	public BufferedImage helicopterFrontPropellerAnimImg;
	public BufferedImage helicopterRearPropellerAnimImg;

	private Animation helicopterFrontPropellerAnim;
	private Animation helicopterRearPropellerAnim;

	private static int offsetXFrontPropeller = 4;
	private static int offsetYFrontPropeller = -7;
	private static int offsetXRearPropeller = 205;
	private static int offsetYRearPropeller = 6;


	public void Initialize(int xCoordinate, int yCoordinate)
	{
		health = 100;

		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;

		helicopterFrontPropellerAnim = new Animation(helicopterFrontPropellerAnimImg, 158, 16, 3, 20, true, xCoordinate + offsetXFrontPropeller, yCoordinate + offsetYFrontPropeller, 0);
		helicopterRearPropellerAnim = new Animation(helicopterRearPropellerAnimImg, 47, 47, 10, 10, true, xCoordinate + offsetXRearPropeller, yCoordinate + offsetYRearPropeller, 0);

		EnemyHelicopter.movingXspeed = -4;
	}


	public static void restartEnemy(){
		EnemyHelicopter.timeBetweenNewEnemies = timeBetweenNewEnemiesInit;
		EnemyHelicopter.timeOfLastCreatedEnemy = 0;
		EnemyHelicopter.movingXspeed = movingXspeedInit;
	}

	public void speedUp(){
		
	}


	public boolean isLeftScreen()
	{
		if(xCoordinate < 0 - helicopterBodyImg.getWidth()) // When the entire helicopter is out of the screen.
		return true;
		else
			return false;
	}

	public void Update()
	{

		xCoordinate += movingXspeed;

		helicopterFrontPropellerAnim.changeCoordinates(xCoordinate + offsetXFrontPropeller, yCoordinate + offsetYFrontPropeller);
		helicopterRearPropellerAnim.changeCoordinates(xCoordinate + offsetXRearPropeller, yCoordinate + offsetYRearPropeller);
	}

	public void Draw(Graphics2D g2d)
	{ 
		
	}

}

