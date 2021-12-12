package id.ac.its.fpgame.breakout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;

public class Paddle extends Sprite implements Interface {

	private MouseHandler mouseHandler = null;
	
	public Paddle() {
		setImage(new ImageIcon(this.getClass().getResource(PIC_PADDLEEASY)).getImage());
		reset();
	}

	private void reset() {
		 x = (int)(Interface.WIDTH / 5);
		 y = Interface.HEIGHT - 45;
	}
	
	public void move() {
		x += dx;
		if(x <= -width/2) x = -width/2;
		if(x >= Interface.WIDTH - width/2) x = Interface.WIDTH - width/2;
	}
	
	class MouseHandler extends MouseAdapter{
		@Override 
		public void mouseMoved(MouseEvent e) {
			int mouseX = e.getX();
			setX(mouseX - width/2);
		}
	}
	
	public MouseHandler getMouseHandler() {
		if(mouseHandler == null) mouseHandler = new MouseHandler();
		return mouseHandler;
	}
}
