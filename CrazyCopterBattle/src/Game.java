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

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Game {

	private Random random;

	// We will use this for setting mouse position.
	private Robot robot;
	private PlayerHelicopter player;
	private ArrayList<EnemyHelicopter> enemyHelicopterList = new ArrayList<EnemyHelicopter>();

	private ArrayList<Animation> explosionsList;
	private BufferedImage explosionAnimImg;

	private ArrayList<Bullet> bulletsList;
	private ArrayList<Rocket> rocketsList;
	private ArrayList<RocketSmoke> rocketSmokeList;

	private BufferedImage skyColorImg;

	private BufferedImage cloudLayer1Img;
	private BufferedImage cloudLayer2Img;
	private BufferedImage mountainsImg;
	private BufferedImage groundImg;

	private MovingBackground cloudLayer1Moving;
	private MovingBackground cloudLayer2Moving;
	private MovingBackground mountainsMoving;
	private MovingBackground groundMoving;

	private BufferedImage mouseCursorImg;

	private Font font;

	private int runAwayEnemies;
	private int destroyedEnemies;

	public Game() {
		loadGameContent();
		Thread threadForInitGame = new Thread() {
			@Override
			public void run() {
				initialize();
				LoadContent();
				playGame();
			}

			private void playGame() {
				Framework.gameState = Framework.GameState.PLAYING;
			}
		};
		threadForInitGame.start();
	}

	private void loadGameContent() {
		Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
	}

	private void initialize() {
		random = new Random();
		try {
			robot = new Robot();
		} catch (AWTException ex) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}

		createPlayerHelicopter();
		createEnemyHelicopter();
		createExplosionLists();
		createBulletLists();
		createRocketLists();
		createRocketSmokeLists();
		setBackground();
		setFont();
		setData();
	}

	private void setData() {
		runAwayEnemies = 0;
		destroyedEnemies = 0;
	}

	private void setFont() {
		font = new Font("monospaced", Font.BOLD, 18);
	}

	private void setBackground() {
		cloudLayer1Moving = new MovingBackground();
		cloudLayer2Moving = new MovingBackground();
		mountainsMoving = new MovingBackground();
		groundMoving = new MovingBackground();
	}

	private void createRocketSmokeLists() {
		rocketSmokeList = new ArrayList<RocketSmoke>();
	}

	private void createRocketLists() {
		rocketsList = new ArrayList<Rocket>();
	}

	private void createBulletLists() {
		bulletsList = new ArrayList<Bullet>();
	}

	private void createExplosionLists() {
		explosionsList = new ArrayList<Animation>();
	}

	private void createEnemyHelicopter() {
		enemyHelicopterList = new ArrayList<EnemyHelicopter>();
	}

	private void createPlayerHelicopter() {
		player = new PlayerHelicopter(Framework.frameWidth / 4,
				Framework.frameHeight / 4);
	}

	private void LoadContent() {
		try {
			loadBackgroundImages();
			loadEnemyImages();
			loadRocketImages();
			loadRocketSmokeImages();
			loadMouseCursorImage();
			loadBulletImage();
			loadExplosionImages();
		} catch (IOException ex) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
		initializeBackground();
	}

	private void initializeBackground() {
		cloudLayer1Moving.initialize(cloudLayer1Img, -6, 0);
		cloudLayer2Moving.initialize(cloudLayer2Img, -2, 0);
		mountainsMoving.initialize(mountainsImg, -1, Framework.frameHeight
				- groundImg.getHeight() - mountainsImg.getHeight() + 40);
		groundMoving.initialize(groundImg, -1.2, Framework.frameHeight
				- groundImg.getHeight());
	}

	private void loadExplosionImages() throws IOException {
		explosionAnimImg = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\explosion_anim.png"));
	}

	private void loadBulletImage() throws IOException {
		Bullet.bulletImg = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\bullet.png"));
	}

	private void loadMouseCursorImage() throws IOException {
		mouseCursorImg = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\mouse_cursor.png"));
	}

	private void loadRocketSmokeImages() throws IOException {
		RocketSmoke.smokeImg = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\rocket_smoke.png"));
	}

	private void loadRocketImages() throws IOException {
		Rocket.rocketImg = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\rocket.png"));
	}

	private void loadEnemyImages() throws IOException {
		EnemyHelicopter.helicopterBodyImg = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\2_helicopter_body.png"));
		EnemyHelicopter.helicopterFrontPropellerAnimImg = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\2_front_propeller_anim.png"));
		EnemyHelicopter.helicopterRearPropellerAnimImg = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\2_rear_propeller_anim.png"));
	}

	private void loadBackgroundImages() throws IOException {
		skyColorImg = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\sky_color.jpg"));
		cloudLayer1Img = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\cloud_layer_1.png"));
		cloudLayer2Img = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\cloud_layer_2.png"));
		mountainsImg = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\mountains.png"));
		groundImg = ImageIO
				.read(new File(
						"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\ground.png"));
	}

	public void RestartGame() {
		resetPlayerData();
		resetEnemyData();
		resetBulletData();
		resetRocketData();
		clearTheLists();
		setData();
	}

	private void clearTheLists() {
		enemyHelicopterList.clear();
		bulletsList.clear();
		rocketsList.clear();
		rocketSmokeList.clear();
		explosionsList.clear();
	}

	private void resetRocketData() {
		Rocket.timeOfLastCreatedRocket = 0;
	}

	private void resetBulletData() {
		Bullet.timeOfLastCreatedBullet = 0;
	}

	private void resetEnemyData() {
		EnemyHelicopter.restartEnemy();
	}

	private void resetPlayerData() {
		player.Reset(Framework.frameWidth / 4, Framework.frameHeight / 4);
	}

	public void UpdateGame(long gameTime, Point mousePosition) {
		if (isGameOver()) {
			gameOver();
			return;
		}
		if (isListEmpty()) {
			gameOver();
			return;
		}
		if (isPlayerAlive()) {
			isPlayerShooting(gameTime, mousePosition);
			isPlayerFiredRocket(gameTime);
			player.isMoving();
			player.Update();
		}

		limitMousePosition(mousePosition);
		updateBullets();
		updateRockets(gameTime);
		updateRocketSmoke(gameTime);
		createEnemyHelicopter(gameTime);
		updateEnemies();
		updateExplosions();
	}

	private boolean isListEmpty() {
		return player.numberOfBullets <= 0 && player.numberOfRockets <= 0
				&& bulletsList.isEmpty() && rocketsList.isEmpty()
				&& explosionsList.isEmpty();
	}

	private void gameOver() {
		Framework.gameState = Framework.GameState.GAMEOVER;
	}

	private boolean isGameOver() {
		return !isPlayerAlive() && explosionsList.isEmpty();
	}

	public void draw(Graphics2D g2d, Point mousePosition, long gameTime) {
		g2d.drawImage(skyColorImg, 0, 0, Framework.frameWidth,
				Framework.frameHeight, null);

		mountainsMoving.Draw(g2d);
		groundMoving.Draw(g2d);
		cloudLayer2Moving.Draw(g2d);

		isPlayerAlive(g2d);
		drawEnemyHelicopters(g2d);
		drawBulletLists(g2d);
		drawRocketLists(g2d);
		drawRocketSmokeLists(g2d);
		drawExplosionLists(g2d);
		setFonts(g2d);

		g2d.drawString(formatTime(gameTime), Framework.frameWidth / 2 - 45, 21);
		g2d.drawString("DESTROYED: " + destroyedEnemies, 10, 21);
		g2d.drawString("RUNAWAY: " + runAwayEnemies, 10, 41);
		g2d.drawString("ROCKETS: " + player.numberOfRockets, 10, 81);
		g2d.drawString("BULLETS: " + player.numberOfBullets, 10, 101);

		cloudLayer1Moving.Draw(g2d);

		if (isPlayerAlive())
			drawRotatedMouseCursor(g2d, mousePosition);
	}

	private void setFonts(Graphics2D g2d) {
		g2d.setFont(font);
		g2d.setColor(Color.darkGray);
	}

	private void drawExplosionLists(Graphics2D g2d) {
		for (int i = 0; i < explosionsList.size(); i++) {
			explosionsList.get(i).Draw(g2d);
		}
	}

	private void drawRocketSmokeLists(Graphics2D g2d) {
		for (int i = 0; i < rocketSmokeList.size(); i++) {
			rocketSmokeList.get(i).Draw(g2d);
		}
	}

	private void isPlayerAlive(Graphics2D g2d) {
		if (isPlayerAlive())
			player.Draw(g2d);
	}

	private void drawRocketLists(Graphics2D g2d) {
		for (int i = 0; i < rocketsList.size(); i++) {
			rocketsList.get(i).Draw(g2d);
		}
	}

	private void drawBulletLists(Graphics2D g2d) {
		for (int i = 0; i < bulletsList.size(); i++) {
			bulletsList.get(i).Draw(g2d);
		}
	}

	private void drawEnemyHelicopters(Graphics2D g2d) {
		for (int i = 0; i < enemyHelicopterList.size(); i++) {
			enemyHelicopterList.get(i).Draw(g2d);
		}
	}

	public void drawStatistic(Graphics2D g2d, long gameTime) {
		g2d.drawString("Time: " + formatTime(gameTime),
				Framework.frameWidth / 2 - 50, Framework.frameHeight / 3 + 80);
		g2d.drawString("Rockets left: " + player.numberOfRockets,
				Framework.frameWidth / 2 - 55, Framework.frameHeight / 3 + 105);
		g2d.drawString("Ammo left: " + player.numberOfBullets,
				Framework.frameWidth / 2 - 55, Framework.frameHeight / 3 + 125);
		g2d.drawString("Destroyed enemies: " + destroyedEnemies,
				Framework.frameWidth / 2 - 65, Framework.frameHeight / 3 + 150);
		g2d.drawString("Runaway enemies: " + runAwayEnemies,
				Framework.frameWidth / 2 - 65, Framework.frameHeight / 3 + 170);
		g2d.setFont(font);
		g2d.drawString("Statistics: ", Framework.frameWidth / 2 - 75,
				Framework.frameHeight / 3 + 60);
	}

	private void drawRotatedMouseCursor(Graphics2D g2d, Point mousePosition) {
		double RIGHT_ANGLE_RADIANS = Math.PI / 2;

		int pivotX = player.machineGunXcoordinate;
		int pivotY = player.machineGunYcoordinate;

		int a = pivotX - mousePosition.x;
		int b = pivotY - mousePosition.y;
		double ab = (double) a / (double) b;
		double alfaAngleRadians = Math.atan(ab);

		if (mousePosition.y < pivotY) // Above the helicopter.
			alfaAngleRadians = RIGHT_ANGLE_RADIANS - alfaAngleRadians
					- RIGHT_ANGLE_RADIANS * 2;
		else if (mousePosition.y > pivotY) // Under the helicopter.
			alfaAngleRadians = RIGHT_ANGLE_RADIANS - alfaAngleRadians;
		else
			alfaAngleRadians = 0;

		AffineTransform origXform = g2d.getTransform();
		AffineTransform newXform = (AffineTransform) (origXform.clone());

		newXform.rotate(alfaAngleRadians, mousePosition.x, mousePosition.y);
		g2d.setTransform(newXform);

		// We subtract half of the cursor image so that will be drawn in center
		// of the y mouse coordinate.
		g2d.drawImage(mouseCursorImg, mousePosition.x, mousePosition.y
				- mouseCursorImg.getHeight() / 2, null);

		g2d.setTransform(origXform);
	}

	/**
	 * Format given time into 00:00 format.
	 * 
	 * @param time
	 *            Time that is in nanoseconds.
	 * @return Time in 00:00 format.
	 */
	private static String formatTime(long time) {
		int sec = getSeconds(time);
		int min = getMinutes(sec);
		sec = sec - (min * 60);
		
		String minString, secString;
		minString = setClockMin(min);
		secString = setClockMin(sec);
		return minString + ":" + secString;
	}

	private static String setClockMin(int min) {
		String minString;
		if (min <= 9)
			minString = "0" + Integer.toString(min);
		else
			minString = "" + Integer.toString(min);
		return minString;
	}

	private static int getMinutes(int sec) {
		int min = sec / 60;
		return min;
	}

	private static int getSeconds(long time) {
		int sec = (int) (time / Framework.milisecInNanosec / 1000);
		return sec;
	}

	private boolean isPlayerAlive() {
		if (player.playerHealth <= 0)
			return false;
		return true;
	}

	private void isPlayerShooting(long gameTime, Point mousePosition) {
		if (player.isShooting(gameTime)) {
			Bullet.timeOfLastCreatedBullet = gameTime;
			player.numberOfBullets--;
			Bullet b = new Bullet(player.machineGunXcoordinate,
					player.machineGunYcoordinate, mousePosition);
			bulletsList.add(b);
		}
	}

	private void isPlayerFiredRocket(long gameTime) {
		if (player.isRocketFired(gameTime)) {
			Rocket.timeOfLastCreatedRocket = gameTime;
			player.numberOfRockets--;
			Rocket rocket = new Rocket();
			rocket.Initialize(player.rocketHolderXcoordinate,
					player.rocketHolderYcoordinate);
			rocketsList.add(rocket);
		}
	}

	private void createEnemyHelicopter(long gameTime) {
		if (gameTime - EnemyHelicopter.timeOfLastCreatedEnemy >= EnemyHelicopter.timeBetweenNewEnemies) {
			EnemyHelicopter enemyHelicopter = new EnemyHelicopter();
			int xCoordinate = Framework.frameWidth;
			int yCoordinate = random.nextInt(Framework.frameHeight
					- EnemyHelicopter.helicopterBodyImg.getHeight());
			enemyHelicopter.Initialize(xCoordinate, yCoordinate);

			enemyHelicopterList.add(enemyHelicopter);
			EnemyHelicopter.speedUp();
			EnemyHelicopter.timeOfLastCreatedEnemy = gameTime;
		}
	}

	private void updateEnemies() {
		for (int i = 0; i < enemyHelicopterList.size(); i++) {
			EnemyHelicopter enemyHelicopter = enemyHelicopterList.get(i);

			enemyHelicopter.Update();

			// Is crashed with player?
			Rectangle playerRectangle = createPlayerRectangle();
			Rectangle enemyRectangle = createEnemyRectangle(enemyHelicopter);

			if (playerRectangle.intersects(enemyRectangle)) {
				player.playerHealth = 0;
				enemyHelicopterList.remove(i);
				explosionOfPlayerHelicopter();
				explosionOfEnemyHelicopter(enemyHelicopter);

				break;
			}

			if (enemyHelicopter.enemyHealth <= 0) {
				// Add explosion of helicopter.
				Animation expAnim = new Animation(explosionAnimImg, 134, 134,
						12, 45, false, enemyHelicopter.xCoordinate,
						enemyHelicopter.yCoordinate
								- explosionAnimImg.getHeight() / 3, 0);
				explosionsList.add(expAnim);
				destroyedEnemies++;
				enemyHelicopterList.remove(i);
				continue;
			}

			if (enemyHelicopter.isLeftScreen()) {
				enemyHelicopterList.remove(i);
				runAwayEnemies++;
			}
		}
	}

	private void explosionOfEnemyHelicopter(EnemyHelicopter enemyHelicopter) {
		for (int exNum = 0; exNum < 3; exNum++) {
			Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12,
					45, false, enemyHelicopter.xCoordinate + exNum * 60,
					enemyHelicopter.yCoordinate - random.nextInt(100), exNum
							* 200 + random.nextInt(100));
			explosionsList.add(expAnim);
		}
	}

	private void explosionOfPlayerHelicopter() {
		for (int exNum = 0; exNum < 3; exNum++) {
			Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12,
					45, false, player.xCoordinate + exNum * 60,
					player.yCoordinate - random.nextInt(100), exNum * 200
							+ random.nextInt(100));
			explosionsList.add(expAnim);
		}
	}

	private Rectangle createEnemyRectangle(EnemyHelicopter enemyHelicopter) {
		Rectangle enemyRectangle = new Rectangle(enemyHelicopter.xCoordinate,
				enemyHelicopter.yCoordinate,
				EnemyHelicopter.helicopterBodyImg.getWidth(),
				EnemyHelicopter.helicopterBodyImg.getHeight());
		return enemyRectangle;
	}

	private Rectangle createPlayerRectangle() {
		Rectangle playerRectangle = new Rectangle(player.xCoordinate,
				player.yCoordinate, player.helicopterBodyImg.getWidth(),
				player.helicopterBodyImg.getHeight());
		return playerRectangle;
	}

	private void updateBullets() {
		for (int i = 0; i < bulletsList.size(); i++) {
			Bullet bullet = bulletsList.get(i);
			bullet.Update();
			if (bullet.isLeftScreen()) {
				bulletsList.remove(i);
				continue;
			}
			Rectangle bulletRectangle = createBulletRectangle(bullet);
			checkBulletHit(i, bulletRectangle);
		}
	}

	private Rectangle createBulletRectangle(Bullet bullet) {
		Rectangle bulletRectangle = new Rectangle((int) bullet.xCoordinate,
				(int) bullet.yCoordinate, Bullet.bulletImg.getWidth(),
				Bullet.bulletImg.getHeight());
		return bulletRectangle;
	}

	private void checkBulletHit(int i, Rectangle bulletRectangle) {
		for (int j = 0; j < enemyHelicopterList.size(); j++) {
			EnemyHelicopter eh = enemyHelicopterList.get(j);
			Rectangle enemyRectangel = createEnemyRectangle(eh);
			if (bulletRectangle.intersects(enemyRectangel)) {
				eh.enemyHealth -= Bullet.damagePower;
				bulletsList.remove(i);
				break;
			}
		}
	}

	private void updateRockets(long gameTime) {
		for (int i = 0; i < rocketsList.size(); i++) {
			Rocket rocket = rocketsList.get(i);
			rocket.Update();
			if (rocket.isLeftScreen()) {
				rocketsList.remove(i);
				continue;
			}

			RocketSmoke rocketSmoke = new RocketSmoke();
			int xCoordinate = setXCoordinateForRocketSmoke(rocket);
			int yCoordinte = setYCoordinateForRocketSmoke(rocket);
			initializeRocketSmoke(gameTime, rocket, rocketSmoke, xCoordinate,
					yCoordinte);

			int smokePositionX = 5 + random.nextInt(8);
			rocketSmoke = new RocketSmoke();
			xCoordinate = updateXCoordinate(rocket, smokePositionX);
			yCoordinte = updateYCoordinate(rocket);
			initializeRocketSmoke(gameTime, rocket, rocketSmoke, xCoordinate,
					yCoordinte);
			rocket.currentSmokeLifeTime *= 1.02;
			if (checkIfRocketHitEnemy(rocket))
				rocketsList.remove(i);
		}
	}

	private int updateYCoordinate(Rocket rocket) {
		int yCoordinte;
		yCoordinte = rocket.yCoordinate - 5 + random.nextInt(6);
		return yCoordinte;
	}

	private int updateXCoordinate(Rocket rocket, int smokePositionX) {
		int xCoordinate;
		xCoordinate = rocket.xCoordinate - RocketSmoke.smokeImg.getWidth()
				+ smokePositionX;
		return xCoordinate;
	}

	private void initializeRocketSmoke(long gameTime, Rocket rocket,
			RocketSmoke rocketSmoke, int xCoordinate, int yCoordinte) {
		rocketSmoke.Initialize(xCoordinate, yCoordinte, gameTime,
				rocket.currentSmokeLifeTime);
		rocketSmokeList.add(rocketSmoke);
	}

	private int setYCoordinateForRocketSmoke(Rocket rocket) {
		int yCoordinte = rocket.yCoordinate - 5 + random.nextInt(6);
		return yCoordinte;
	}

	private int setXCoordinateForRocketSmoke(Rocket rocket) {
		int xCoordinate = rocket.xCoordinate - RocketSmoke.smokeImg.getWidth();
		return xCoordinate;
	}

	private boolean checkIfRocketHitEnemy(Rocket rocket) {
		boolean didItHitEnemy = false;
		Rectangle rocketRectangle = new Rectangle(rocket.xCoordinate,
				rocket.yCoordinate, 2, Rocket.rocketImg.getHeight());
		for (int j = 0; j < enemyHelicopterList.size(); j++) {
			EnemyHelicopter eh = enemyHelicopterList.get(j);
			Rectangle enemyRectangel = createEnemyRectangle(eh);
			if (rocketRectangle.intersects(enemyRectangel)) {
				didItHitEnemy = true;
				eh.enemyHealth -= Rocket.damagePower;
				break;
			}
		}

		return didItHitEnemy;
	}

	private void updateRocketSmoke(long gameTime) {
		for (int i = 0; i < rocketSmokeList.size(); i++) {
			RocketSmoke rs = rocketSmokeList.get(i);
			if (rs.isSmokeDisapper(gameTime))
				rocketSmokeList.remove(i);
			rs.updateTransparency(gameTime);
		}
	}

	private void updateExplosions() {
		for (int i = 0; i < explosionsList.size(); i++) {
			if (!explosionsList.get(i).isActivated)
				explosionsList.remove(i);
		}
	}

	private void limitMousePosition(Point mousePosition) {
		int maxYcoordinateDistanceFromPlayerTop = 30;
		int maxYcoordinateDistanceFromPlayerBottom = 120;
		int mouseXcoordinate = player.machineGunXcoordinate + 250;

		int mouseYcoordinate = mousePosition.y;
		mouseYcoordinate = checkMachineGunPositions(mousePosition,
				maxYcoordinateDistanceFromPlayerTop,
				maxYcoordinateDistanceFromPlayerBottom, mouseYcoordinate);
		mouseYcoordinate += player.movingYspeed;
		robot.mouseMove(mouseXcoordinate, mouseYcoordinate);
	}

	private int checkMachineGunPositions(Point mousePosition,
			int maxYcoordinateDistanceFromPlayerTop,
			int maxYcoordinateDistanceFromPlayerBottom, int mouseYcoordinate) {
		if (mousePosition.y < player.machineGunYcoordinate) {
			if (mousePosition.y < player.machineGunYcoordinate
					- maxYcoordinateDistanceFromPlayerTop)
				mouseYcoordinate = player.machineGunYcoordinate
						- maxYcoordinateDistanceFromPlayerTop;
		} else { // Under the helicopter.
			if (mousePosition.y > player.machineGunYcoordinate
					+ maxYcoordinateDistanceFromPlayerBottom)
				mouseYcoordinate = player.machineGunYcoordinate
						+ maxYcoordinateDistanceFromPlayerBottom;
		}
		return mouseYcoordinate;
	}
}

