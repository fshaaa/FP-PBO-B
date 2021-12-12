package id.ac.its.fpgame.breakout;

import javax.swing.ImageIcon;

public class Ball extends Sprite implements Interface{
	
	public Ball() {
		dx = 2;
		dy = 2;
		
		setImage(new ImageIcon(
                this.getClass().getResource(Interface.PIC_BALL)).getImage());
		reset();
	}

	private void reset() {
		x = Interface.WIDTH / 5;
		y = Interface.HEIGHT - 45;
	}
	
	public void move() {
		x += dx;
		y += dy;
		
		if(x <= 0) {
			x = 0;
			dx = -dx;
		} 
		else if(x >= Interface.WIDTH - width) {
			x = Interface.WIDTH - width;
			dx = -dx;
		}
		
		if(y <= 0) {
			y = 0;
			dy = -dy;
		}
	}
}
