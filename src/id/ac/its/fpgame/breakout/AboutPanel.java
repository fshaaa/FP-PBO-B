package id.ac.its.fpgame.breakout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import id.ac.its.fpgame.breakout.MenuPanel.Click;

public class AboutPanel extends JPanel implements Interface{
	
	    JButton back = new JButton("Back");
	    ImageIcon bk = new ImageIcon(this.getClass().getResource(PIC_BACK));
	    Image menubkg = new ImageIcon (this.getClass().getResource(PIC_ABOUT)).getImage();
	    
	    AboutPanel(){
	        setLayout(null);
	        back.setIcon(bk);
	        back.setBounds(10, 507, 118, 50);
	        back.setFocusPainted(false);
	        back.setVisible(true);
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
