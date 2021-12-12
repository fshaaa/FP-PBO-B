package id.ac.its.fpgame.breakout;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import id.ac.its.fpgame.breakout.MenuPanel.Click;

public class AboutPanel extends JPanel implements Interface{
	
		JLabel bkg = new JLabel(); 
	    JButton back = new JButton("Back");
	    
	    Image menubkg = new ImageIcon (this.getClass().getResource(PIC_ABOUT)).getImage();
	    
	    AboutPanel(){
	        setLayout(new FlowLayout()); 
	        add(back);
	        
	        back.addMouseListener(new Click());
	    }
	   
	    class Click extends MouseAdapter{
	        public void mouseClicked(MouseEvent me){
	            if(me.getSource()== back){
	                Breakout.cl.show(Breakout.panel, "MenuPanel");
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
