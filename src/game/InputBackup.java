package game;


import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class InputBackup {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int SPACE = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	public boolean[] buttons = new boolean[5];
	public boolean[] oldButtons = new boolean[5];
	
	private static Clip loadSound(String name) {
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

	public void set(int key, boolean down) {
		int button = -1;
		if (key == KeyEvent.VK_LEFT)
		{
			button = 0;
			Clip clip = loadSound("/res/sound/Sounds/tank_sound_1sec.wav");
			clip.start();
		}
		if (key == KeyEvent.VK_RIGHT)
		{
			button = 1;
			Clip clip = loadSound("/res/sound/Sounds/tank_sound_1sec.wav");
			clip.start();
		}
		if (key == KeyEvent.VK_SPACE)
			button = 2;
		if (key == KeyEvent.VK_UP)
		{
			button = 3;
			Clip clip = loadSound("/res/sound/Sounds/tank_sound_1sec.wav");
			clip.start();
		}
		if (key == KeyEvent.VK_DOWN)
		{
			button = 4;
			Clip clip = loadSound("/res/sound/Sounds/tank_sound_1sec.wav");
			clip.start();
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
