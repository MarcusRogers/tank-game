package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WinGame {

	Image tankWin;
	JPanel frame = new JPanel();
	private String shotsFired;
	private String enemiesHit;
	private String accuracy;
	private double scrollTick;
	private int backgroundScrollY;
	private boolean stopScrolling = false;
	private double scrollVar = 0.2;

	public WinGame() throws IOException {

		tankWin = ImageIO.read(Game.class
				.getResource("/res/images/Images/Tank_win.gif"));
	}

	public void render(Graphics g) {
	
		scrollTick += scrollVar;
		shotsFired = ("" + Game.getShotsFired());
		enemiesHit = ("" + Game.getEnemiesHit());
		accuracy = ("" + Game.getAccuracy() + "%");
			
		
		if (scrollTick > 1) {
			backgroundScrollY--;
			g.fillRect(0, backgroundScrollY, Game.GAME_HEIGHT, Game.GAME_WIDTH);
			g.drawImage(tankWin, 0, backgroundScrollY, null);
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString(shotsFired, 312, (backgroundScrollY + 293));
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString(enemiesHit, 312, backgroundScrollY + 323);
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString(accuracy, 312, backgroundScrollY + 353);
			scrollTick = 0.0;
			if (backgroundScrollY <= -1000) {
				scrollVar = 0.0;
			}
			
		}

	}
}
