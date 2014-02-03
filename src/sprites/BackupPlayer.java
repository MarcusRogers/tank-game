package sprites;

import game.EnemyBullet;
import game.Game;
import game.Input;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**Class: Enemy
 * @author Jason Aydelotte
 * @author Marcus Rogers
 * @version 1.0
 * Course : ITEC 3150-01 Spring 2013
 * Written: Feburary, 2013
 * Note: Received help from Dr. Im and most of the code here is written by Dr.Im.
 * 
 * This class- sets up the player characteristics- graphics, movement, collision
 * 
 * Purpose: Project 1
 */

public class BackupPlayer extends Sprite 
{

	private Image image;

	public BackupPlayer(int x, int y, Image image) 
	{
		super(x, y);
		this.image = image;
	}

	public  Image getImage()
	{
		return image;
	}
	
	@Override
	public void tick(Input input) {
		if (input.buttons[Input.LEFT]&& getX() > 0)
			setX(getX() - 1);
		if (input.buttons[Input.RIGHT]&& getX() < 445)
			setX(getX() + 1);
		if (input.buttons[Input.UP] && getY() > 0)
			setY(getY() - 1);
		if (input.buttons[Input.DOWN]&& getY() < 450)
			setY(getY() + 1);
	}
	
	public boolean tankHit(EnemyBullet b) 
	{
		if ((Math.abs(b.getY() - getY()) <= 20)
				&& (Math.abs(b.getX() - getX()) <= 20))
		{
			//System.out.println("bullet X position: " + b.getX());
			//System.out.println("bullet Y position: " + b.getY());
			return true;
		}
		else
		{
			return false;
		}
	}
}
