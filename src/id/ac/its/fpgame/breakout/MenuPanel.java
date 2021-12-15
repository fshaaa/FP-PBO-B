package id.ac.its.fpgame.breakout;

import java.io.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 
class MenuPanel extends JPanel implements Interface{
	
    JButton play = new JButton("");
    JButton about = new JButton("");
    JButton highscore = new JButton("");
    JButton exit = new JButton("");
    
    Image menubkg = new ImageIcon(this.getClass().getResource(PIC_MENU)).getImage();
    ImageIcon playbtn = new ImageIcon(this.getClass().getResource (PIC_PLAYBUTTON));
    ImageIcon aboutbtn = new ImageIcon(this.getClass().getResource (PIC_ABOUTBUTTON));
    ImageIcon highscorebtn = new ImageIcon(this.getClass().getResource(PIC_HIGHSCOREBUTTON));
    ImageIcon exitbtn = new ImageIcon(this.getClass().getResource(PIC_EXIT));
    
    MenuPanel(){
    	setLayout(null);
    	
        play.setIcon(playbtn);
        about.setIcon(aboutbtn);
        highscore.setIcon(highscorebtn);
        exit.setIcon(exitbtn);
        
        play.setBounds(170, 280, 130, 50);
        play.setVisible(true);
        about.setBounds(170, 335, 130, 50);
        about.setVisible(true);
        highscore.setBounds(170, 390, 130, 50);
        highscore.setVisible(true);
        exit.setBounds(170, 445, 130, 50);
        exit.setVisible(true);
        
        add(play);
        add(about);
        add(highscore);
        add(exit);
                
        play.addMouseListener(new Click());
        about.addMouseListener(new Click());
        highscore.addMouseListener(new Click());
        exit.addMouseListener(new Click());
    }

	class Click extends MouseAdapter{
        public void mouseClicked(MouseEvent me){
            if(me.getSource()== play){
                Breakout.cl.show(Breakout.panel, "LevelPanel");
            }
            if(me.getSource()== about){
            	Breakout.cl.show(Breakout.panel, "AboutPanel");
            }
            if(me.getSource()== highscore){
            	Breakout.cl.show(Breakout.panel, "HighscorePanel");
            }
            if(me.getSource()== exit){
            	System.exit(0);
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
