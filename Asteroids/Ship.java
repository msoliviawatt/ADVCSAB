import java.awt.*;
import java.util.*;

public class Ship extends Polygon {
    // private ArrayList<Missile> missiles = new ArrayList<Missile>();
    private Color purple = new Color(69, 28, 255);
    private int lives;
    public Ship(Point[] shape, int l) {
        super(shape, new Point(Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT/2), 0);
        this.lives = l;
        this.setColor(purple);
    }

    public void move() {
        super.move(Game.SHIP_SPEED);
    }

    public void resetPositioin(Ship x) {
        x.position = new Point(Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT/2);
    }

    public void setLives(int l) {
        this.lives = l;
    }

    public int getLives() {
        return lives;
    }
}
