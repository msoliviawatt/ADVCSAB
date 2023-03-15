/*
CLASS: Game
DESCRIPTION:  Game is mostly in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
*/
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.util.ArrayList;

public class Game extends Canvas {

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

  //APCS - Once you have created your Ship class, switch theShip from a Polygon to a Ship variable
  private Polygon theShip;
  //private Ship theShip;
  //APCS - Once you have created your Asteroid class, uncomment this section
  //private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
  
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

    //Once you create the Ship class, change this to add a new Ship object to the spaceThings list.
    //The ship is always going to be the first thing in the list.
    //APCS - After you have created your Ship class, change this to create a Ship object, not a Polygon
	 theShip = new Polygon(newShape, new Point(SCREEN_WIDTH/2, SCREEN_HEIGHT/2), 0);
    //theShip = new Ship(newShape);
        
    Point[] aShape;
    /* APCS - Uncomment this section when you are ready to create some asteroids (feel free to make changes
     *   if you want to create your asteroids differently!


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
*/    
        // APCS - End of the part to create asteroids
    
    //Update the window and add key listener for ship and timer listener for asteroids
    render();
	 //PART ONE - UNCOMMENT THE NEXT LINE TO USE THE KEY LISTENER
    //addKeyListener(this);
    
    //Need timer events to make asteroids move - SEH
    //  Removed calls to render from the keyPress events but needed more frequent rendering by timer
    int delay = 100; //milliseconds - started at 1000 
    ActionListener taskPerformer = new ActionListener() { 
       public void actionPerformed(ActionEvent evt) { 
          //...Perform a task... 
          render();
       } 
    }; 
    new Timer(delay, taskPerformer).start();

    requestFocusInWindow();
  }
  
  
  
	public void paint(Graphics brush) {
      
     if (theShip != null)
         theShip.paint(brush);
         
	  /* APCS - This loop deals with the ship, the asteroids and their movement.  Review it and uncomment it
      * once you have your Asteroid class working.
      
      for (int i = 0; i < asteroids.size(); i++)
      {
         //This is necessary because sometimes paint is called before objects are fully created.
         if (asteroids.get(i) == null)
            continue;

         asteroids.get(i).move();

         asteroids.get(i).paint(brush);
      }
      */
      
  }
  
    	
   //Thanks to Mattias D for this method to help with performance on our old laptops! 
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
//SEH - This line being here is the cause of the disappearing graphics on my laptops.  I moved it down but don't know
//  if this will destroy the fact that this made it work on the school laptops.
//  Perhaps the working or not working depends on whether the processor is fast enough to repaint before the dispose
//  (or garbage collector?) actually clears the Graphics object g?
                //g.dispose();
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