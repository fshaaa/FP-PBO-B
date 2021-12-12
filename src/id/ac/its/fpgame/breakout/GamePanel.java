package id.ac.its.fpgame.breakout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.sound.sampled.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel implements Interface {
    Timer timer;
    String message;
    Ball ball;
    Paddle paddle;
    Brick bricks[];
    int bricksRemaining;
    boolean playing = true;
    
    
    public GamePanel() {
        setBackground(new Color(64, 64, 64));
        setDoubleBuffered(true);
        setFocusable(true);
		//setLocationRelativeTo(null);
		//setResizable(false);
		//setSize(Interface.WIDTH + 10, Interface.HEIGHT + 10);
		//setIgnoreRepaint(true);
		//setVisible(true);
        initGame();
    }
    
    void initGame() {
        ball = new Ball(); 
        paddle = new Paddle(); 
        
        addMouseMotionListener(paddle.getMouseHandler());

        bricks = new Brick[63];
        bricksRemaining = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                bricks[bricksRemaining++] = new Brick(55 + j * 40, 48 + i * 12);
            }
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new GameLoopTask(), 1000, 17);
    }
    
    void stopGame() {
        playing = false;
        timer.cancel();
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
                
                if (brick.getRect().contains(right + 1, top)) {
                    int dx = ball.getDx();
                    ball.setDx(dx < 0 ? dx : -dx); 
                } else if (brick.getRect().contains(left - 1, top)) {
                    int dx = ball.getDx();
                    ball.setDx(dx < 0 ? -dx : dx); 
                }
                
                if (brick.getRect().contains(left, top - 1)) {
                    int dy = ball.getDy();
                    ball.setDy(dy < 0 ? -dy : dy);
                } else if (brick.getRect().contains(left, bottom + 1)) {
                    int dy = ball.getDy();
                    ball.setDy(dy < 0 ? dy : -dy); 
                }
                brick.destroyed = true;
                bricksRemaining--;
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
            Font font = new Font("Arial", Font.ITALIC, 20);
            
            g2d.setColor(Color.WHITE);
            g2d.setFont(font);
            g2d.drawString(message,
                    Interface.WIDTH / 2 -
                    this.getFontMetrics(font).stringWidth(message) / 2,
                    Interface.HEIGHT / 2);
        }
        g2d.dispose();
    }
    
    class GameLoopTask extends TimerTask {
        @Override
        public void run() {
            ball.move(); 
            paddle.move(); 
            
            if (ball.getY() > Interface.HEIGHT) {
                message = "Game Over";
                stopGame();
            }
            else if (bricksRemaining == 0) {
                message = "Congratulations! You won!";
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
}
