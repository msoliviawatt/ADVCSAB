import java.awt.*;
import java.util.*;

public class Ship extends Polygon {
    private ArrayList<Missile> missiles = new ArrayList<Missile>();
    private Color purple = new Color(69, 28, 255);
    private int lives;
    public Ship(Point[] shape, int l) {
        super(shape, new Point(Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT/2), 0);
        this.lives = l;
        this.setColor(purple);
    }

    //for moving the ship
    public void move() {
        super.move(Game.SHIP_SPEED);
    }

    //resets position of ship, to be used when a life is lost
    public void resetPositioin(Ship x) {
        x.position = new Point(Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT/2);
    }

    //allows the ship to fire a missile
    public void fire() {
        Point[] newShape = {new Point(0,0), new Point(0, 12), new Point(4, 12), new Point(4,0)};
        missiles.add(new Missile(newShape, this.position.x, this.position.y, this.rotation));
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Missile m : missiles) {
            m.move();
            m.paint(g);
        }
    }

    //returns missles
    public ArrayList<Missile> getMissiles() {
        return missiles;
    }

    //removes missiles
    public void removeMissile(Object m) {
        missiles.remove(m);
    }

    //adds missiles
    public void addMissile(Missile m) {
        missiles.add(m);
    }

    //sets lives
    public void setLives(int l) {
        this.lives = l;
    }

    //gets lives
    public int getLives() {
        return lives;
    }
}
