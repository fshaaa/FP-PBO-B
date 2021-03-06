package id.ac.its.fpgame.breakout;

import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;

public class GamePanel extends JPanel implements Interface {

	JLabel score;
	JLabel highscore;
	JButton oneClick;
	JButton back;
	Timer timer;
    String message = "";
    String hs = "";
    Ball ball;
    Paddle paddle;
    Brick bricks[];
    int bricksRemaining;
    boolean playing;
    int scoreCount = 0;
    boolean start = false;
    static int level = 1;
    
    public GamePanel() {
    	setLayout(null);
    	setFocusable(true);
        setBackground(new Color(64, 64, 64));
        setDoubleBuffered(true);
        
        score = new JLabel ("Score: " + scoreCount);
        score.setBounds(20, 10, 100, 20);
        score.setForeground(Color.WHITE);
        score.setFont(new Font("Roboto", Font.BOLD, 16));
        add(score);
        
        highscore = new JLabel ("Highscore: " + getHighScore());
        highscore.setBounds(320, 10, 150, 20);
        highscore.setForeground(Color.WHITE);
        highscore.setFont(new Font("Roboto", Font.BOLD, 16));
        add(highscore);
        
        oneClick = new JButton("Click to Start");
        oneClick.setFocusPainted(false);
        oneClick.setBorderPainted(false);
        oneClick.setContentAreaFilled(false);
        oneClick.setForeground(Color.GRAY);
        oneClick.setFont(new Font("Roboto", Font.BOLD, 20));
        oneClick.setBounds(30, 0, 400, 600);
        add(oneClick);
        oneClick.addMouseListener(new Click());
    }
    
    void initGame() {
	    ball = new Ball();
	    paddle = new Paddle(level);
	    playing = true;
	    scoreCount = 0;
	    
	    addMouseMotionListener(paddle.getMouseHandler());
	        
	    bricks = new Brick[63];
	      bricksRemaining = 0;
	      for (int i = 0; i < 7; i++) {
	        for (int j = 0; j < 9; j++) {
	            bricks[bricksRemaining++] = new Brick(55 + j * 40, 48 + i * 12);
	        }
	      }
	     
	    if(start) {
	        timer = new Timer();
	        timer.scheduleAtFixedRate(new GameLoopTask(), 1000, 17);
    	}
    }
    
    void stopGame() {
    	score.setText("Score: 0");
    	score.setVisible(false);
    	highscore.setVisible(false);
        playing = false; 
        timer.cancel();
        back = new JButton("Click for Menu");
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setForeground(Color.GRAY);
        back.setFont(new Font("Roboto", Font.BOLD, 20));
        back.setBounds(30, 300, 400, 500);
        back.setVisible(true);
        add(back);
        back.addMouseListener(new Click());
    }
    
    void handleCollisions() {
        if (ball.getRect().intersects(paddle.getRect())) {
            playSound(Interface.SOUND_BUMP);
            ball.setDy(-ball.getDy());
            int segment = paddle.getWidth() / 5;
            int first  = paddle.getX() + segment;
            int second = paddle.getX() + segment * 2;
            int third  = paddle.getX() + segment * 3;
            int fourth = paddle.getX() + segment * 4;
            int center = ball.getX() + ball.getWidth() / 2;
                    
            if (center < first) { ball.setDx(-2); }
            else if (center >= first && center < second) { ball.setDx(-1); }
            else if (center >= second && center < third) { ball.setDx(0); }
            else if (center >= third && center < fourth) { ball.setDx(1); }
            else if (center > fourth) { ball.setDx(2); }
            ball.setY(paddle.getY() - ball.getHeight());
        }
        
        for (Brick brick : bricks) {
            if ((brick.destroyed == false) &&
                (brick.getRect().intersects(ball.getRect()))) {
                playSound(Interface.SOUND_PING);
                
                int top = ball.getY();
                int bottom = ball.getY() + ball.getHeight();
                int left = ball.getX();
                int right = ball.getX() + ball.getWidth();
                
                if (brick.getRect().contains(right, top)) {
                    int dx = ball.getDx();
                    ball.setDx(dx < 0 ? dx : -dx); 
                } else if (brick.getRect().contains(left, top)) {
                    int dx = ball.getDx();
                    ball.setDx(dx < 0 ? -dx : dx); 
                }
                
                if (brick.getRect().contains(left, top)) {
                    int dy = ball.getDy();
                    ball.setDy(dy < 0 ? -dy : dy);
                } else if (brick.getRect().contains(left, bottom)) {
                    int dy = ball.getDy();
                    ball.setDy(dy < 0 ? dy : -dy); 
                }
                brick.destroyed = true;
                bricksRemaining--;
                scoreCount += 10;
                score.setText("Score: " + scoreCount);
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (playing) {
            g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                    ball.getWidth(), ball.getHeight(), this);
            
            g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                    paddle.getWidth(), paddle.getHeight(), this);
            
            for (Brick brick : bricks) {
                if (brick.destroyed) continue;
                
                g2d.drawImage(brick.getImage(), brick.getX(), brick.getY(),
                        brick.getWidth(), brick.getHeight(), this);
            }
        }

        else {
            Font font = new Font("Roboto", Font.ITALIC, 24);
            
            g2d.setColor(Color.WHITE);
            g2d.setFont(font);
            g2d.drawString(message,
            		Interface.WIDTH / 2 -
                    this.getFontMetrics(font).stringWidth(message) / 2 - 10,
                    Interface.HEIGHT / 2 + 15);
            g2d.drawString(hs,
            		Interface.WIDTH / 2 -
                    this.getFontMetrics(font).stringWidth(hs) / 2 - 10,
                    Interface.HEIGHT / 2 - 15);
        }
        g2d.dispose();
    }
    
    class GameLoopTask extends TimerTask {
        @Override
        public void run() {
            ball.move(); 
            paddle.move(); 
            
            if (ball.getY() > Interface.HEIGHT) {
            	if(scoreCount > getHighScore()) {
            		setHighScore(scoreCount);
            		message = "Your Score: " + scoreCount;
            		hs = "Congratulation! You Get the Highscore!";
            	} 
            	else {
            		hs = "Your Score: " + scoreCount;
            		message = "Try Again";
            	}
                stopGame();
            }
            else if (bricksRemaining == 0) {
            	message = "Your Score: " + scoreCount;
                hs = "Congratulations! You Get the Highscore!";
                stopGame();
            }
            handleCollisions();
            repaint();
        }
    }
    
    public void playSound(String filename) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource(filename));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	class Click extends MouseAdapter{
        public void mouseClicked(MouseEvent me){
            if(me.getSource()== oneClick){
            	start = true;
            	initGame();
            	oneClick.setVisible(false);
            	highscore.setText("Highscore: " + getHighScore());
            }
            if(me.getSource()== back){
            	playing = true;
            	oneClick.setVisible(true);
                score.setVisible(true);
            	back.setVisible(false);
            	highscore.setVisible(true);
            	Breakout.cl.show(Breakout.panel, "MenuPanel");
            }
        }
    }
	
	public static void setLevel(int newlevel) {
		level = newlevel; 
	}
	
	public static void setHighScore(int score) {
		if(level == 1)
			HighscorePanel.setHighscoreEasy(score);
		else if(level == 2)
			HighscorePanel.setHighscoreMedium(score);
		else if(level == 3)
			HighscorePanel.setHighscoreHard(score);
	}
	
	public static int getHighScore() {
		if(level == 1)
			return HighscorePanel.getHighscoreEasy();
		else if(level == 2)
			return HighscorePanel.getHighscoreMedium();
		else if(level == 3)
			return HighscorePanel.getHighscoreHard();
		else 
			return -1;
	}
}
