package id.ac.its.fpgame.breakout;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
 
class MenuPanel extends JPanel implements Interface{
 
	JLabel bkg = new JLabel(); 
    JButton play = new JButton("");
    JButton about = new JButton("");
    JButton highscore = new JButton("");
   
    Image menubkg = new ImageIcon (this.getClass().getResource(PIC_MENU)).getImage();
   
    ImageIcon playbtn = new ImageIcon (this.getClass().getResource (PIC_PLAYBUTTON));
    ImageIcon aboutbtn = new ImageIcon (this.getClass().getResource (PIC_ABOUTBUTTON));
    ImageIcon highscorebtn = new ImageIcon(this.getClass().getResource(PIC_HIGHSCOREBUTTON));
    
    MenuPanel(){
    	setLayout(new FlowLayout());
    	setFocusable(true);
    	
        play.setIcon(playbtn);
        about.setIcon(aboutbtn);
        highscore.setIcon(highscorebtn);
        
        add(play);
        add(about);
        add(highscore);
        
        play.setVerticalTextPosition(SwingConstants.CENTER);
        
        play.addMouseListener(new Click());
        about.addMouseListener(new Click());
        highscore.addMouseListener(new Click());
    }
   
    class Click extends MouseAdapter{
        public void mouseClicked(MouseEvent me){
            if(me.getSource()== play){
                Breakout.cl.show(Breakout.panel, "GamePanel");
            }
            if(me.getSource()== about){
            	Breakout.cl.show(Breakout.panel, "AboutPanel");
            }
            if(me.getSource()== highscore){
            	Breakout.cl.show(Breakout.panel, "HighscorePanel");
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
