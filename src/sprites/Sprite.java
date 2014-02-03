package sprites;
import game.Input;



public abstract class Sprite {

	private int x;
	private int y;
	private boolean removed;
	//
	private boolean shift;
	//

	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		removed = false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isRemoved() {
		return removed;
	}
	public void shiftDir()
	{
		shift = true;
	}

	public void remove() {
		removed = true;
	}

	/*
	 * Each sprite will change its state in every "tick"
	 */
	public abstract void tick(Input input);
}
