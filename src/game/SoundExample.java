package game;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class SoundExample {	
	public static void main(String[] args) throws Exception {
		while (true)		
		{			
			Clip clip = loadSound("/res/sound/leftright.wav");
			clip.start();
			Thread.sleep(10000);
		}
	}
	private static Clip loadSound(String name) 
	{
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataInputStream dis = new DataInputStream(SoundExample.class.getResourceAsStream(name));
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
		} 
		catch (Exception e) {
			return null;
		}
	}}