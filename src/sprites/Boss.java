package sprites;

import game.EnemyBullet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Boss extends Enemy {

	
	private double dy;
	private long count;
	private boolean alive;
	private List<EnemyBullet> enemyBullets = new ArrayList<EnemyBullet>();

	public Boss(int x, int y, Image image, int damage) {
		super(x, y, image, damage);
		this.dy = .2;
		count = 1;
		this.alive = alive;
	}
	
	public boolean getAlive() 
	{
		return alive;
	}
	
	public void setAlive(boolean live)
	{
		this.alive = live;
	}
	
	public Rectangle getBounds()
	{
		Rectangle r;
		r = new Rectangle((int)getX(), (int)getY(), 10,60);
		return r;
	}

	
	 public boolean shot(Bullet b)
	 {
	 if ((Math.abs(b.getY() - (getY()) - 20) <= 10)
	 && (Math.abs(b.getX() - getX()) <= 10))
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

	public void bossDrawBullets(Graphics g) {
		for (int i = 0; i < getEnemyBullets().size(); i++) {
			EnemyBullet bullet1 = getEnemyBullets().get(i);
			g.fillOval((int)bullet1.getX(), (int)bullet1.getY(), 5, 5);
		}
	}
}
