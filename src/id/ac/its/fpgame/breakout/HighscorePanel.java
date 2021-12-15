package id.ac.its.fpgame.breakout;

import javax.swing.*;
import id.ac.its.fpgame.breakout.AboutPanel.Click;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HighscorePanel extends JPanel implements Interface {

	private static int highscoreEasy;
	private static int highscoreMedium;
	private static int highscoreHard;
	
	static JLabel hsEasy = new JLabel();
	static JLabel hsMedium = new JLabel();
	static JLabel hsHard = new JLabel();
	JLabel rst = new JLabel("Reset");
	
	JButton back = new JButton();
	JButton reset = new JButton();
	ImageIcon bk = new ImageIcon(this.getClass().getResource(PIC_BACK));
	ImageIcon rt = new ImageIcon(this.getClass().getResource(PIC_RESET));
	Image bg = new ImageIcon(this.getClass().getResource(PIC_HIGHSCORE)).getImage();
	
	public static int getHighscoreEasy() {
		return highscoreEasy;
	}

	public static void setHighscoreEasy(int highscore) {
        hsEasy.setText("Easy: " + highscore);
		highscoreEasy = highscore;	
	}

	public static int getHighscoreMedium() {
		return highscoreMedium;
	}

	public static void setHighscoreMedium(int highscore) {
		hsMedium.setText("Medium: " + highscore);
		highscoreMedium = highscore;
	}

	public static int getHighscoreHard() {
		return highscoreHard;
	}

	public static void setHighscoreHard(int highscore) {
		hsHard.setText("Hard: " + highscore);
		highscoreHard = highscore;
	}

	public HighscorePanel() {
		setLayout(null);
		
        back.setIcon(bk);
        back.setBounds(10, 507, 118, 50);
        back.setFocusPainted(false);
        back.setVisible(true);
        add(back);
        
        rst.setFont(new Font("Roboto", Font.BOLD, 32));
        rst.setForeground(Color.WHITE);
        rst.setBounds(275, 415, 150, 50);
        rst.setVisible(true);
        add(rst);
        
        reset.setIcon(rt);
        reset.setBounds(370, 415, 50, 50);
        reset.setFocusPainted(false);
        reset.setVisible(true);
        add(reset);
        
        hsEasy.setBounds(140, 150, 250, 100);
        hsEasy.setVisible(true);
        hsEasy.setFont(new Font("Roboto", Font.BOLD, 42));
        hsEasy.setForeground(Color.WHITE);
        hsEasy.setText("Easy: " + getHighscoreEasy());
        add(hsEasy);
        
        hsMedium.setBounds(120, 230, 270, 100);
        hsMedium.setVisible(true);
        hsMedium.setFont(new Font("Roboto", Font.BOLD, 42));
        hsMedium.setForeground(Color.WHITE);
        hsMedium.setText("Medium: " + getHighscoreMedium());
        add(hsMedium);
        
        hsHard.setBounds(140, 310, 250, 100);
        hsHard.setVisible(true);
        hsHard.setFont(new Font("Roboto", Font.BOLD, 42));
        hsHard.setForeground(Color.WHITE);
        hsHard.setText("Hard: " + getHighscoreHard());
        add(hsHard);
        
        back.addMouseListener(new Click());
        reset.addMouseListener(new Click());
	}	
	
    class Click extends MouseAdapter{
        public void mouseClicked(MouseEvent me){
            if(me.getSource()== back){
                Breakout.cl.show(Breakout.panel, "MenuPanel");
            }
            if(me.getSource() == reset) {
            	setHighscoreEasy(0);
            	setHighscoreMedium(0);
            	setHighscoreHard(0);
            }
        }
    }
	
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        setFocusable(true);
       
        g2d.drawImage(bg, 0,0, null);
        repaint();
    }
}
