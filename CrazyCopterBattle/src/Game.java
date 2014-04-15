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

	private void LoadContent() {
		loadBackgroundImages();
		loadEnemyImages();
		loadRocketImages();
		loadRocketSmokeImages();
		loadMouseCursorImage();
		loadBulletImage();
		loadExplosionImages();
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



	private void loadBackgroundImages() {
		// TODO Auto-generated method stub

	}

	private void loadEnemyImages() {
		// TODO Auto-generated method stub

	}

	private void loadRocketImages() {
		// TODO Auto-generated method stub

	}

	private void loadRocketSmokeImages() {
		// TODO Auto-generated method stub

	}

	private void loadMouseCursorImage() {
		// TODO Auto-generated method stub

	}

	private void loadBulletImage() {
		// TODO Auto-generated method stub

	}

	private void loadExplosionImages() {
		// TODO Auto-generated method stub

	}
}
