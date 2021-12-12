package id.ac.its.fpgame.breakout;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Breakout extends JFrame implements Interface{

	static JPanel panel = new JPanel();
	static CardLayout cl = new CardLayout();
	static GamePanel game = new GamePanel();
	static MenuPanel mp = new MenuPanel();
	static AboutPanel ap = new AboutPanel();
	
	public Breakout() {
		panel.setLayout(cl);
		panel.add(mp, "MenuPanel");
		panel.add(game, "GamePanel");
		panel.add(ap, "AboutPanel");
		
		cl.show(panel, "MenuPanel");
		add(panel);
		
		setTitle("Breakout Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		setSize(Interface.WIDTH, Interface.HEIGHT + 20);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Breakout game = new Breakout();
	}
}
