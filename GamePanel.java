package main;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16;
    final int scale = 3;
    final int titleSize = originalTitleSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidht = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread GameThread;

    Thread gameThread;

    int player_x = 100;
    int player_y = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidht, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();

    }


 /*   @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){


            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }


    }*/
 @Override
 public void run() {
      double drawInterval = 1000000000./FPS;
      double delta = 0;
      long lastTime =System.nanoTime();
      long currentTime;

      while (gameThread != null){
          currentTime = System.nanoTime();
          delta += (currentTime - lastTime) / drawInterval;
          lastTime = currentTime;

          if(delta >= 1){
              update();
              repaint();
              delta--;
          }
      }

 }
    public void update(){
        if(keyH.upPressed == true){
            player_y -= playerSpeed;

        }
        else if(keyH.downPressed == true){
            player_y +=playerSpeed;
        }
        else if (keyH.leftPressed == true) {
            player_x -= playerSpeed;

        }
        else if (keyH.rightPressed == true) {
            player_x += playerSpeed;

        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(player_x,player_y,titleSize, titleSize);
        g2.dispose();


    }
}
