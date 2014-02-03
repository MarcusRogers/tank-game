package game;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Controls {

		 private JTextField firstTextField;
			private JTextField secondTextField;
			private JTextField thirdTextField;
			private JTextArea textArea;
			private JButton startButton;
			private JButton clearButton;
			Image tankControls;
			JPanel frame = new JPanel();

			public Controls() throws IOException 
			{
				
					tankControls = ImageIO.read(Game.class
							.getResource("/res/images/Images/Tank_controls.gif"));
//				JFrame frame = new JFrame("Tank");
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				
//				frame.add(makeTextAreaPanel(), BorderLayout.CENTER);
//				frame.add(makeButtonPanel(), BorderLayout.SOUTH);
//				frame.setSize(300, 300);
//				frame.setVisible(true);
//				System.out.println(SwingUtilities.isEventDispatchThread());
				
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
				
				g.fillRect(0, 0, Game.GAME_HEIGHT, Game.GAME_WIDTH);
				//g.drawImage(tankMenu, 0,0,500,500,1000,0,1500,500, null);
				g.drawImage(tankControls,0,0,Game.GAME_HEIGHT, Game.GAME_WIDTH,null);

//				g.drawString("Hello World", 100, 100);
//				frame.add(makeTextAreaPanel(), BorderLayout.CENTER);
//				frame.add(makeButtonPanel(), BorderLayout.SOUTH);
//				frame.setBackground(Color.GREEN);
//				frame.setSize(300, 300);
//				frame.setVisible(true);
				
			}
			
		// TODO Auto-generated method stub

	}


