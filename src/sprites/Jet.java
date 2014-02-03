package sprites;

import game.Input;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

public class Jet extends Enemy
{
	private double dy;
	private long count;
	private int tickCount;
	private Input input = new Input();
	public int acceleration;
	private boolean alive;
	// private List<EnemyBullet> enemyBullets = new ArrayList<EnemyBullet>();

	public Jet(int x, int y, Image image, int damage) 
	{
		super(x, y, image, damage);
		this.acceleration = 1;
		this.dy = .2;
		count = 1;
		setAlive(true);
	}

	public boolean getAlive() 
	{
		return alive;
	}

	public void setAlive(boolean live) 
	{
		this.alive = live;
	}

	public void tick(Input input)
	{
		int run = 1;
		setX((int)getX() - run);
		if((int)getX() < -45)
		{
			setAlive(false);
		}


		//  setY((int) (-Math.sin(dy)  * 50 + 50));
		//  dy = dy + .002;
	}

	public Rectangle getBounds()
	{
		Rectangle r;
		r = new Rectangle((int) (getX()), (int)(getY()), 40, 40);
		return r;
	}

//	public boolean shot(Bullet b) 
//	{
//		if ((Math.abs(b.getY() - (getY())) <= 20)
//				&& (Math.abs(b.getX() - getX()) <= 20))
//		{
//			//System.out.println("bullet X position: " + b.getX());
//			//System.out.println("bullet Y position: " + b.getY());
//			setAlive(false);
//			return true;
//		}
//		else
//		{
//			return false;
//		}
//	}

}