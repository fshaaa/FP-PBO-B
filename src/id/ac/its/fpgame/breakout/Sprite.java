package id.ac.its.fpgame.breakout;

import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {
	
	protected int x;
	protected int y;
	protected int dx;
	protected int dy;
	protected Image image;
	protected int width;
	protected int height;
	
	public Sprite() {
		x = y = 0;
		dx = dy = 0;
		width = height = 0;
		image = null;
	}
	
	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		dx = dy = 0;
		width = height = 0;
		image = null;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	
	public Image getImage() {
		return image;		
	}

	public void setImage(Image img) {
		image = img;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
	}
}
