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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Framework extends UserController {

	public static int frameWidth;
	public static int frameHeight;

	// Time of one second in nanoseconds. 1 second = 1 000 000 000 nanoseconds

	public static final long secInNanosec = 1000000000L;
	public static final long milisecInNanosec = 1000000L;

	private final int GAME_FPS = 60;

	private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;

	public static enum GameState {
		STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU, OPTIONS, PLAYING, GAMEOVER, DESTROYED, PAUSE
	}

	public static GameState gameState;
	private long gameTime;
	private long lastTimeElapsed;
	private boolean gamePaused = false;
	private Game game;
	private Font font;

	private BufferedImage gameTitleImg;
	private BufferedImage menuBorderImg;
	private BufferedImage skyColorImg;
	private BufferedImage cloudLayer1Img;
	private BufferedImage cloudLayer2Img;

	public Framework() {
		super();

		gameState = GameState.VISUALIZING;

		Thread gameThread = new Thread() {
			@Override
			public void run() {
				setGameLoop();
			}
		};
		gameThread.start();
	}

	private void initialize() {
		font = new Font("monospaced", Font.BOLD, 28);
	}

	private void loadContent() {
		try {

			menuBorderImg = ImageIO
					.read(new File(
							"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\menu_border.png"));
			skyColorImg = ImageIO
					.read(new File(
							"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\sky_color.jpg"));
			gameTitleImg = ImageIO
					.read(new File(
							"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\helicopter_battle_title.png"));
			cloudLayer1Img = ImageIO
					.read(new File(
							"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\cloud_layer_1.png"));
			cloudLayer2Img = ImageIO
					.read(new File(
							"C:\\Users\\erdikoch\\Desktop\\workspace\\helicopterbattle\\resources\\images\\cloud_layer_2.png"));

		} catch (IOException ex) {
			Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	private void setGameLoop() {
		// This two variables are used in VISUALIZING state of the game. We used
		// them to wait some time so that we get correct frame/window
		// resolution.
		long visualizingTime = 0, lastVisualizingTime = System.nanoTime();

		// This variables are used for calculating the time that defines for how
		// long we should put threat to sleep to meet the GAME_FPS.
		long beginTime, timeTaken, timeLeft;

		while (true) {
			beginTime = System.nanoTime();

			switch (gameState) {

			case PLAYING:
				break;
			case GAMEOVER:
				break;
			case MAIN_MENU:
				break;
			case OPTIONS:
				break;
			case GAME_CONTENT_LOADING:
				break;
			case PAUSE:
				break;
			case STARTING:

				initialize();
				loadContent();

				gameState = GameState.MAIN_MENU;
				break;
			case VISUALIZING:

				if (this.getWidth() > 1 && visualizingTime > secInNanosec) {
					frameWidth = this.getWidth();
					frameHeight = this.getHeight();

					gameState = GameState.STARTING;
				} else {
					visualizingTime += System.nanoTime() - lastVisualizingTime;
					lastVisualizingTime = System.nanoTime();
				}
				break;
			}

			repaint();

			// We calculate the time that defines for how long we should
			// put thread to sleep to meet the GAME_FPS.
			timeTaken = System.nanoTime() - beginTime;
			timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec;
			if (timeLeft < 10)
				timeLeft = 10;
			try {
				Thread.sleep(timeLeft);
			} catch (InterruptedException ex) {
			}
		}
	}

	@Override
	public void Draw(Graphics2D g2d) {

		switch (gameState) {
		case PLAYING:
			break;
		case GAMEOVER:
			setGameOverConditions(g2d);
			break;
		case MAIN_MENU:
			setMenuConditions(g2d);
			break;
		case OPTIONS:
			break;
		case GAME_CONTENT_LOADING:
			setLoadingConditions(g2d);
			break;
		}
	}

	private void setLoadingConditions(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.drawString("GAME IS LOADING", frameWidth / 2 - 50,
				frameHeight / 2);
	}

	private void setMenuConditions(Graphics2D g2d) {
		drawMenuBackground(g2d);
		g2d.drawImage(gameTitleImg,
				frameWidth / 2 - gameTitleImg.getWidth() / 2,
				frameHeight / 4, null);
		g2d.setColor(Color.black);
		g2d.drawString(
				"Use W, A, S, D or arrow keys to move the helicopter.",
				frameWidth / 2 - 117, frameHeight / 2 - 30);
		g2d.drawString(
				"Use left mouse button to fire bullets and right mouse button to fire rockets.",
				frameWidth / 2 - 180, frameHeight / 2);
		g2d.drawString("Press any key to start the game or ESC to exit.",
				frameWidth / 2 - 114, frameHeight / 2 + 30);
	}

	private void setGameOverConditions(Graphics2D g2d) {
	}

	private void newGame() {
		}

	private void restartGame() {
		}

	private Point mousePosition() {
		try {
			Point mp = this.getMousePosition();
			if (mp != null)
				return this.getMousePosition();
			else
				return new Point(0, 0);
		} catch (Exception ex) {
			return new Point(0, 0);
		}
	}

	@Override
	public void keyReleasedFramework(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);

		switch (gameState) {
		case GAMEOVER:
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				restartGame();
			break;
		case MAIN_MENU:
			newGame();
			break;
		case PAUSE:
			if (gamePaused) {
				if (e.getKeyCode() == KeyEvent.VK_P)
					while (true) {
						System.out.println("Game Paused.");
						if (e.getKeyCode() == KeyEvent.VK_P) {
							gamePaused = false;
							break;
						}
					}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	private void drawMenuBackground(Graphics2D g2d) {
		g2d.drawImage(skyColorImg, 0, 0, Framework.frameWidth,
				Framework.frameHeight, null);
		g2d.drawImage(cloudLayer1Img, 0, 0, Framework.frameWidth,
				Framework.frameHeight, null);
		g2d.drawImage(cloudLayer2Img, 0, 0, Framework.frameWidth,
				Framework.frameHeight, null);
		g2d.drawImage(menuBorderImg, 0, 0, Framework.frameWidth,
				Framework.frameHeight, null);
		g2d.setColor(Color.white);
		g2d.drawString("CRAZY COPTER BATTLE", 7, frameHeight - 5);
	}
}
