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

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public abstract class UserController extends JPanel implements KeyListener,
		MouseListener {

	private static final int NUM_OF_KEYBOARD_FUNCTIONS = 525;
	private static final int NUM_OF_MOUSE_FUNCTIONS = 3;

	private static boolean[] keyboardState = new boolean[NUM_OF_KEYBOARD_FUNCTIONS];
	private static boolean[] mouseState = new boolean[NUM_OF_MOUSE_FUNCTIONS];

	public UserController() {

		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setBackground(Color.black);

		// If you will draw your own mouse cursor or if you just want that mouse
		// cursor disappear,
		// insert "true" into if condition and mouse cursor will be
		// removed.(taken from " www.stackoverflow.com ")
		if (true) {
			BufferedImage blankCursorImg = new BufferedImage(16, 16,
					BufferedImage.TYPE_INT_ARGB);
			Cursor blankCursor = Toolkit.getDefaultToolkit()
					.createCustomCursor(blankCursorImg, new Point(0, 0), null);
			this.setCursor(blankCursor);
		}

		this.addKeyListener(this);
		this.addMouseListener(this);
	}

	public abstract void Draw(Graphics2D g2d);

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		Draw(g2d);
	}

	public static boolean keyboardKeyState(int key) {
		return keyboardState[key];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyboardState[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyboardState[e.getKeyCode()] = false;
		keyReleasedFramework(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public abstract void keyReleasedFramework(KeyEvent e);

	public static boolean mouseButtonState(int button) {
		return mouseState[button - 1];
	}

	private void mouseKeyStatus(MouseEvent e, boolean status) {

		if (e.getButton() == MouseEvent.BUTTON1)
			mouseState[0] = status;
		else if (e.getButton() == MouseEvent.BUTTON2)
			mouseState[1] = status;
		else if (e.getButton() == MouseEvent.BUTTON3)
			mouseState[2] = status;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseKeyStatus(e, true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseKeyStatus(e, false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
