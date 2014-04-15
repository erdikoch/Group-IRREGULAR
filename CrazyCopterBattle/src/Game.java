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


public class Game {

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
