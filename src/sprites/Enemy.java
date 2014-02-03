package sprites;


import game.EnemyBullet;
import game.Input;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Sprite {

	private double dy;
	private long count;
	private int tickCount;
	private Input input = new Input();
	private List<EnemyBullet> enemyBullets = new ArrayList<EnemyBullet>();
	private boolean alive;

	public Enemy(int x, int y, Image image, int damage) {
		super(x, y);
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
	
	public Rectangle getBounds()
	{
		Rectangle r;
		r = new Rectangle((int) (getX()), (int)(getY()), 40, 40);
		return r;
	}
	@Override
	///////////////////////////////////////////////////////////////
	public void tick(Input input) {
		setY((int) (-Math.sin(dy)  * 225 + 227));
		dy = dy + .002;
	}
	
	// Processes the graphics for enemy bullets
		public void processEnemyBullets(Graphics g) {
			for (int i = 0; i < getEnemyBullets().size(); i++) {
				EnemyBullet eBullet = getEnemyBullets().get(i);
				if (!eBullet.isRemoved()) {
					eBullet.tick(input);
					g.fillOval((int)eBullet.getX(), (int)eBullet.getY(), 5, 5);
				} else {
					getEnemyBullets().remove(eBullet);
				}
			}
		}
	
	
		
	//should remove(); here at shot
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
	
	public void drawBullets(Graphics g)
	{
		for (int i = 0; i < getEnemyBullets().size(); i++) 
		{
			EnemyBullet bullet = getEnemyBullets().get(i);
			g.fillOval((int)bullet.getX(), (int)bullet.getY() + 2, 5, 5);
//			System.out.println("drawBullets");
			
		}
		

	}

	/**
	 * @return the enemyBullets
	 */
	public List<EnemyBullet> getEnemyBullets() {
		return enemyBullets;
	}

	/**
	 * @param enemyBullets the enemyBullets to set
	 */
	public void setEnemyBullets(List<EnemyBullet> enemyBullets) {
		this.enemyBullets = enemyBullets;
	}
	
	
}
