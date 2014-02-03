package game;
import java.awt.Rectangle;

import sprites.Sprite;



public class EnemyBullet extends Sprite {

	public int acceleration;

	public EnemyBullet(int x, int y, int acceleration) {
		super(x, y);
		this.acceleration = acceleration;
	}

	public void tick(Input input) {
		if (getX() >= 0 && getX() < 500) {
			setX(getX() + (acceleration-1));
		} else
			remove();
	}
	
	public Rectangle getBounds()
	{
		Rectangle r;
		r = new Rectangle((int) getX(), (int)getY(), 1, 1);
		return r;
	}
}