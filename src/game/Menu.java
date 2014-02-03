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



public class Menu {
	
			Image tankMenu;
			JPanel frame = new JPanel();
			private String shotsFired;
			private String enemiesHit;
			private String accuracy;

			public Menu() throws IOException 
			{
				
					tankMenu = ImageIO.read(Game.class
							.getResource("/res/images/Images/Tank_menu.gif"));

				
			}

//			private JScrollPane makeTextAreaPanel() {
//				JPanel panel = new JPanel();
//				textArea = new JTextArea();
//				panel.setLayout(new BorderLayout());
//				panel.add(textArea);
//				JScrollPane scrollPane = new JScrollPane(panel);
//				return scrollPane;
//			}
//
//			private JPanel makeButtonPanel() {
//				JPanel panel = new JPanel();
//				startButton = new JButton("Start");
//				clearButton = new JButton("Clear");
//				panel.add(startButton);
//				panel.add(clearButton);
//
//				return panel;
//			}
			public void render(Graphics g)
			{
				
				shotsFired = ("" + Game.getShotsFired());
			    enemiesHit = ("" + Game.getEnemiesHit());
			    accuracy = ("" + Game.getAccuracy() +"%");
			    
			 				
				g.fillRect(0, 0, Game.GAME_HEIGHT, Game.GAME_WIDTH);
				//g.drawImage(tankMenu, 0,0,500,500,1000,0,1500,500, null);
				g.drawImage(tankMenu,0,0,Game.GAME_HEIGHT, Game.GAME_WIDTH,null);

			    g.setFont(new Font(null, Font.BOLD, 20));
			    g.drawString(shotsFired, 312, 293);
			    g.setFont(new Font(null, Font.BOLD, 20));
			    g.drawString(enemiesHit, 312, 323);
			    g.setFont(new Font(null, Font.BOLD, 20));
			    g.drawString(accuracy, 312, 353);

				
			}
			

	}


