package id.ac.its.fpgame.breakout;

import javax.swing.ImageIcon;

public class Brick extends Sprite implements Interface{
	
	public boolean destroyed;
	
	public Brick(int x, int y) {
		super(x,y);
		setImage(new ImageIcon(this.getClass().getResource(PIC_BRICK)).getImage());
		destroyed = false;
	}
}
