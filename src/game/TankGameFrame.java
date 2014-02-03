package game;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**Class: GameFrame
 * @author Jason Aydelotte
 * @author Marcus Rogers
 * @version 1.0
 * Course : ITEC 3150-01 Spring 2013
 * Written: Feburary, 2013
 * Note: Received help from Dr. Im and most of the code here is written by Dr.Im.
 * 
 * This class- Sets up the frame in which the game is displayed
 * 
 * Purpose: Project 1
 */

public class TankGameFrame {

	private static Image controls;
	private static Game game;
	private static Thread t;
	private static JFrame frame;


	public static void main(String[] args) throws IOException {
		frame = new JFrame("Tank");
		JPanel panel = new JPanel();
		game = new Game();
		//t = new Thread(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 530);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		//frame.add(panel, BorderLayout.NORTH);
		frame.setVisible(true);
		frame.setResizable(false);
		game.start();
	} 

	/*Method: gameOver
	 * 
	 * This method will close the frame upon choosing the end game option.
	 */
	public static void gameOver()
	{
		frame.dispose();
	}

	public static Game getGame() {
		return game;
	}

}
