package id.ac.its.fpgame.breakout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LevelPanel extends JPanel implements Interface{
	
	    JButton back = new JButton();
	    JButton easy = new JButton();
	    JButton medium = new JButton();
	    JButton hard = new JButton();
	    ImageIcon bk = new ImageIcon(this.getClass().getResource(PIC_BACK));
	    ImageIcon es = new ImageIcon(this.getClass().getResource(PIC_EASY));
	    ImageIcon md = new ImageIcon(this.getClass().getResource(PIC_MEDIUM));
	    ImageIcon hd = new ImageIcon(this.getClass().getResource(PIC_HARD));
	    Image menubkg = new ImageIcon (this.getClass().getResource(PIC_LEVEL)).getImage();
	    
	    LevelPanel(){
	        setLayout(null);
	        
	        back.setIcon(bk);
	        back.setBounds(10, 507, 118, 50);
	        back.setFocusPainted(false);
	        back.setVisible(true);
	        add(back);
	        
	        easy.setIcon(es);
	        easy.setBounds(153, 220, 165, 55);
	        easy.setFocusPainted(false);
	        easy.setVisible(true);
	        add(easy);
	        
	        medium.setIcon(md);
	        medium.setBounds(153, 285, 165, 55);
	        medium.setFocusPainted(false);
	        medium.setVisible(true);
	        add(medium);
	        
	        hard.setIcon(hd);
	        hard.setBounds(153, 350, 165, 55);
	        hard.setFocusPainted(false);
	        hard.setVisible(true);
	        add(hard);
	        
	        back.addMouseListener(new Click());
	        easy.addMouseListener(new Click());
	        medium.addMouseListener(new Click());
	        hard.addMouseListener(new Click());
	    }
	   
	    class Click extends MouseAdapter{
	        public void mouseClicked(MouseEvent me){
	            if(me.getSource()== back){
	                Breakout.cl.show(Breakout.panel, "MenuPanel");
	            }
	            if(me.getSource()== easy){
	            	GamePanel.setLevel(1);
	                Breakout.cl.show(Breakout.panel, "GamePanel");
	            }
	            if(me.getSource()== medium){
	            	GamePanel.setLevel(2);
	                Breakout.cl.show(Breakout.panel, "GamePanel");
	            }
	            if(me.getSource()== hard){
	            	GamePanel.setLevel(3);
	                Breakout.cl.show(Breakout.panel, "GamePanel");
	            }
	        }
	    }
	    
	    public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D)g;
	        setFocusable(true);
	       
	        g2d.drawImage(menubkg, 0,0, null);
	        repaint();
	    }
}
