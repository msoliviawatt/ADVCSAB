/*
CLASS: Game
DESCRIPTION:  Game is mostly in the paint method.
*/
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.util.ArrayList;

public class Game extends Canvas implements KeyListener {

   protected boolean on = true;
   protected int width, height;
   protected Image buffer;

//Game settings
   final static int INIT_ASTEROIDS = 15;
   final static int ASTEROID_SPEED = 3;
   final static int SHIP_SPEED = 8;
   final static int ROTATION_SPEED = 5;
   final static int MAX_LIVES = 3;
   final static int FREE_LIFE_THRESHOLD = 3;
   final static int SCREEN_WIDTH = 1000;
   final static int SCREEN_HEIGHT = 750;
   private boolean up = false;
   private boolean down = false;
   private boolean left = false;
   private boolean right = false;
   private int lives = 3;
   private Ship theShip;
   private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
   
   private int collisionCt = 0;
   private int newCollisionWith = -1;
   private BufferStrategy strategy;
      
   public Game() {

      //Code from Game that creates the window for the display
      JFrame frame = new JFrame("Asteroids Game");
      frame.add(this);
      frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
      frame.getContentPane().setBackground(Color.BLACK);
      frame.setVisible(true);
      frame.setResizable(false);
      frame.addWindowListener(new WindowAdapter() { 
         public void windowClosing(WindowEvent e) {System.exit(0);} 
      });
      
      
      //Create the ship - these points make a triangular ship shape
      Point[] newShape = {new Point(10,10), new Point(0, 25), new Point(0, 35), new Point(20,35), new Point(20,25)};
      theShip = new Ship(newShape, lives);
      Point[] aShape;
      //Create some asteroids
      for (int i = 0; i < INIT_ASTEROIDS; i++)
      {
         //Set up asteroid shapes - four different shapes randomly chosen.
         int numPts = (int)(Math.random() * 4) + 5;
         if (numPts == 5)
         {
            Point[] tempShape = {new Point(10, 10), new Point(5,15), new Point(12,20), new Point(19,14), new Point(12,13)};
            aShape = tempShape;
         }
         else if (numPts == 6)
         {
            Point[] tempShape = {new Point(10,10), new Point(22,4), new Point(24,18), new Point(33,23), new Point(7,38), new Point(15,25)};
            aShape = tempShape;
         }
         else if (numPts == 7)
         {
            Point[] tempShape = {new Point(3,17), new Point(12,2), new Point(22,4), new Point(18, 13), new Point(25,15), new Point(11,31), new Point(12,18)};
            aShape = tempShape;
         }
         else 
         {
            Point[] tempShape = {new Point(15, 5), new Point(24,15), new Point(35,10), new Point(28,27), new Point(23,45), new Point(17,32), new Point(6,30), new Point(14,21)};
            aShape = tempShape;
         } 
         
         //Start the asteroid at a random location, but not within 100 of the center 
         int xLoc = ((int)(Math.random() * (SCREEN_WIDTH/2 - 109)) + 10) + ((SCREEN_WIDTH/2 + 110) * ((int)(Math.random() * 2)));
         //Same for yLoc
         int yLoc = ((int)(Math.random() * (SCREEN_HEIGHT/2 - 109)) + 10) + ((SCREEN_HEIGHT/2 + 110) * ((int)(Math.random() * 2)));

         asteroids.add(new Asteroid(aShape, xLoc, yLoc));        
      }   
      // APCS - End of the part to create asteroids
      
      //Update the window and add key listener for ship and timer listener for asteroids
      render();
      addKeyListener(this);
      
      //Need timer events to make asteroids move - SEH
      //Removed calls to render from the keyPress events but needed more frequent rendering by timer
      int delay = 100; //milliseconds - started at 1000 
      ActionListener taskPerformer = new ActionListener() { 
         public void actionPerformed(ActionEvent evt) { 
            //...Perform a task... 
            if(up) {
               theShip.move(SHIP_SPEED + 4); 
            }
            if(down) {
               theShip.move(-SHIP_SPEED - 4);
            }
            if(left) {
               theShip.rotate(-ROTATION_SPEED * 2); 
            } else if (right) {
               theShip.rotate(ROTATION_SPEED * 2); 
            }
            render();
         }
          
      };
      new Timer(delay, taskPerformer).start();

      requestFocusInWindow();}

      public void keyPressed(KeyEvent e) { 
         if(e.getKeyCode() == KeyEvent.VK_UP) { 
            up = true;
         } 
         if(e.getKeyCode() == KeyEvent.VK_DOWN) { 
            down = true;
         } 
         if(e.getKeyCode() == KeyEvent.VK_LEFT) { 
            left = true; 
         }
         if(e.getKeyCode() == KeyEvent.VK_RIGHT) { 
            right = true;
         }   
      }

      public void keyTyped(KeyEvent e) { } 

      public void keyReleased(KeyEvent e) { 
         if(e.getKeyCode() == KeyEvent.VK_UP) { 
            up = false;
         } 
         if(e.getKeyCode() == KeyEvent.VK_DOWN) { 
            down = false;
         } 
         if(e.getKeyCode() == KeyEvent.VK_LEFT) { 
            left = false; 
         }
         if(e.getKeyCode() == KeyEvent.VK_RIGHT) { 
            right = false;
         }   
      }
   
      public void paint(Graphics brush) {
      if (theShip != null)
            theShip.paint(brush);
            for (int i = 0; i < asteroids.size(); i++)
            {
               if (asteroids.get(i) == null)
                  continue;
               asteroids.get(i).move();
               asteroids.get(i).paint(brush);
               if(theShip.collides(asteroids.get(i)) || asteroids.get(i).collides(theShip)) {
                  theShip.resetPositioin(theShip);
                  lives--;
                  System.out.println("Lives: " + lives);
                  if(lives <= 0) {
                     System.out.println("Game Over");
                     System.exit(0);
                  }
               }
            }
      }
   
         
      public void render(){
         //renders # of frames in the background then shows them in order
         //the parameter is the number of frames that are cycled through
         createBufferStrategy(2);
         strategy = getBufferStrategy();
         Graphics g = null;
         do {
            try{
               g =  strategy.getDrawGraphics();

            } finally {
               paint(g);
            }
            strategy.show();
            g.dispose();
         } while (strategy.contentsLost());
         Toolkit.getDefaultToolkit().sync();
   }

   public static void main (String[] args) {
   new Game();
   }
}
