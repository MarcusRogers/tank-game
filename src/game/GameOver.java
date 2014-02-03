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

public class GameOver {

	Image tankLose;
	JPanel frame = new JPanel();
	private String shotsFired;
	private String enemiesHit;
	private String accuracy;

	public GameOver() throws IOException {

		tankLose = ImageIO.read(Game.class
				.getResource("/res/images/Images/Tank_lose.gif"));
	}

	public void render(Graphics g) {
		shotsFired = ("" + Game.getShotsFired());
		enemiesHit = ("" + Game.getEnemiesHit());
		accuracy = ("" + Game.getAccuracy() + "%");

		g.fillRect(0, 0, Game.GAME_HEIGHT, Game.GAME_WIDTH);
		g.drawImage(tankLose, 0, 0, null);
		g.setFont(new Font(null, Font.BOLD, 20));
		g.drawString(shotsFired, 312, 293);
		g.setFont(new Font(null, Font.BOLD, 20));
		g.drawString(enemiesHit, 312, 323);
		g.setFont(new Font(null, Font.BOLD, 20));
		g.drawString(accuracy, 312, 353);

	}

}
