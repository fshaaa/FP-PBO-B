package id.ac.its.fpgame.breakout;

import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Breakout extends JFrame implements Interface{
	
	Image icon = new ImageIcon(this.getClass().getResource(PIC_BALL)).getImage();
	static JPanel panel = new JPanel();
	static CardLayout cl = new CardLayout();
	static GamePanel game = new GamePanel();
	static MenuPanel mp = new MenuPanel();
	static AboutPanel ap = new AboutPanel();
	static LevelPanel lp = new LevelPanel();
	static HighscorePanel hp = new HighscorePanel();
	
	public Breakout() {
		panel.setLayout(cl);
		panel.add(mp, "MenuPanel");
		panel.add(game, "GamePanel");
		panel.add(ap, "AboutPanel");
		panel.add(lp, "LevelPanel");
		panel.add(hp, "HighscorePanel");
		
		cl.show(panel, "MenuPanel");
		add(panel);
		
		setIconImage(icon);
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
