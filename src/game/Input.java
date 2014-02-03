package game;

import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
/**Class: Input
 * @author Jason Aydelotte
 * @author Marcus Rogers
 * @version 1.0
 * Course : ITEC 3150-01 Spring 2013
 * Written: Feburary, 2013
 * Note: Received help from Dr. Im and most of the code here is written by Dr.Im.
 * 
 * This class- contains the methods for the user to control the tank and 
 * the sounds that are associated with the input
 * 
 * Purpose: Project 1
 */
public class Input 
{
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int SPACE = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	public static final int SONG = 5;
	public static final int endGame = 6;
	public static final int newGame = 7;
	public static final int endSong = 8;
	private static Clip song;
	private static Clip tankSound;
	
	public static boolean[] buttons = new boolean[9];
	public boolean[] oldButtons = new boolean[9];
	public boolean isMenuOn;
	public boolean oldMenu;
	public boolean isControlsOn;
	public boolean oldControls;

	public Input()
	{
		
	}
	//code from Dr. Im for sound
	private static Clip loadSound(String name) 
	{
		try 
      {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataInputStream dis = new DataInputStream(Game.class.getResourceAsStream(name));
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = dis.read(buffer)) >= 0) 
          {
				baos.write(buffer, 0, read);
			}
			dis.close();
			byte[] data = baos.toByteArray();
			baos.close();
			Clip clip = (Clip) AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(data)));
			return clip;
		} catch (Exception e) 
		{
			return null;
		}
	}

	public void setMenu()
	{
	
			if(isMenuOn==false)
			{
				isMenuOn=true;
			}
			else{
				isMenuOn=false;
			}
			System.out.println(isMenuOn);
		
	}
	
	public void setControls()
	{
	
			if(isControlsOn==false)
			{
				isControlsOn=true;
			}
			else{
				isControlsOn=false;
			}
			System.out.println(isControlsOn);
		
	}
	
	
//	public static void endGameMusic()
//	{
//		
//		Clip clip = loadSound("/res/sound/Sounds/Tank_one.wav");
//		clip.start();
//	}
	
	public static void set(int key, boolean down) 
	{
		int button = -1;
		
		if (key == KeyEvent.VK_F12)
		{
			button = endGame;
			song.stop();
			song = null;
			TankGameFrame.gameOver();
			
		}
		
		if (key == KeyEvent.VK_F4 && song == null)
		{
			button = SONG;
			song = loadSound("/res/sound/Sounds/Tank_one.wav");
			song.start();
		}
		

		if(key == KeyEvent.VK_F5 && song != null)
		{
			button = endSong;
			song.stop();
			song = null;
		}
		
		
		if (key == KeyEvent.VK_LEFT)
		{
			button = LEFT;
			tankSound = loadSound("/res/sound/Sounds/Tank_sound.wav");
			tankSound.start();
		}
		if (key == KeyEvent.VK_RIGHT)
		{
			button = RIGHT;
			tankSound = loadSound("/res/sound/Sounds/Tank_sound.wav");
			tankSound.start();
		}
		if (key == KeyEvent.VK_SPACE)
			button = SPACE;
		if (key == KeyEvent.VK_UP)
		{
			button = UP;
			tankSound = loadSound("/res/sound/Sounds/Tank_sound.wav");
			tankSound.start();
		}
		if (key == KeyEvent.VK_DOWN)
		{
			button = DOWN;
			tankSound = loadSound("/res/sound/Sounds/Tank_sound.wav");
			tankSound.start();
		}
		if (button >= 0)
			buttons[button] = down;
	}

	public void tick() {
		for (int i = 0; i < buttons.length; i++) {
			oldButtons[i] = buttons[i];
		}
	}
	
}
