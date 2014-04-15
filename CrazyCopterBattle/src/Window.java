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

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Window extends JFrame {

	private Window() {
		this.setTitle("Crazy Copter Battle");

		if (true) {
			this.setUndecorated(true);
			this.setExtendedState(this.MAXIMIZED_BOTH);
		} else {
			this.setSize(1024, 768);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setContentPane(new Framework());
		this.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Window();
			}
		});
	}
}
