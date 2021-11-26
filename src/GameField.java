
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener{

    private final int  MAX_SIZE_SNAKE = 81;

    private int sizeSnake = 1;
    private final int sizeCell = 10;

    private boolean gameOver = false;

    private int appleX;
    private int appleY;

    private int[] snakeX = new int[MAX_SIZE_SNAKE];
    private int[] snakeY = new int[MAX_SIZE_SNAKE];

    private Image imageApple;
    private Image imageSnake;

    private boolean leftKey = false;
    private boolean rightKey = true;
    private boolean downKey = false;
    private boolean upKey = false;

    public GameField(){
       setSnake();
       setApple();
       initPictures();
       initStart();
       addKeyListener(new FieldKeyListener());
       setFocusable(true);
    }
    private void setSnake(){
        snakeX [0] = new Random().nextInt(25)*10;
        snakeY [0] = new Random().nextInt(25)*10;
    }
    private void setApple(){
        appleX = new Random().nextInt(25)*10;
        appleY = new Random().nextInt(25)*10;
    }
    private void initPictures(){
        ImageIcon imageIconApple = new ImageIcon("apple.png");
        imageApple = imageIconApple.getImage();
        ImageIcon imageIconSnake = new ImageIcon("snake.png");
        imageSnake = imageIconSnake.getImage();
    }
    private void initStart(){
        Timer timer = new Timer(250, this);
        timer.start();
    }
     private void relocatedSnake(){
        if(rightKey){
            snakeX[0] = snakeX[0]+sizeCell;
        }
         if(leftKey){
             snakeX[0] = snakeX[0]-sizeCell;
         }
         if(upKey){
             snakeY[0] = snakeY[0]-sizeCell;
         }
         if(downKey){
             snakeY[0] = snakeY[0]+sizeCell;
         }
         for(int i=sizeSnake;i>0; i--){
            snakeX[i]=snakeX[i-1];
            snakeY[i]=snakeY[i-1];
         }
        }
   public void checkCollisions(){
       if(snakeX[0]>250){
           gameOver = true;
       }
       if(snakeX[0]<0){
           gameOver = true;
       }
       if(snakeY[0]>250){
           gameOver = true;
       }
       if(snakeY[0]<0){
           gameOver = true;
       }
       //check apple
       if(snakeX[0]==appleX&&snakeY[0]==appleY)
       {   sizeSnake++;
           setApple();
       }
   }
   @Override
   public void actionPerformed(ActionEvent e) {
        if(!gameOver){
           checkCollisions();
           relocatedSnake();
        }
        repaint();
    }
   @Override
   protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imageApple,appleX,appleY,this);
        for(int i=0;i<sizeSnake;i++){
            g.drawImage(imageSnake, snakeX[i], snakeY[i], this);
            }
        }
   class FieldKeyListener extends KeyAdapter {
       @Override
       public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key==KeyEvent.VK_LEFT && !rightKey){
                leftKey = true;
                upKey = false;
                downKey = false;
            }
           if(key==KeyEvent.VK_RIGHT &&! leftKey){
               rightKey = true;
               upKey = false;
               downKey = false;
           }
           if(key==KeyEvent.VK_UP &&! downKey){
               upKey = true;
               leftKey = false;
               rightKey = false;
           }
           if(key==KeyEvent.VK_DOWN &&! upKey){
               downKey = true;
               rightKey = false;
               leftKey = false;
           }
       }
   }
}
