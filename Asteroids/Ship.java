import java.awt.*;
import java.util.*;

public class Ship extends Polygon {
    private ArrayList<Missile> missiles = new ArrayList<Missile>();
    private int lives;
    public Ship(Point[] shape, Point x, int l) {
        super(shape, x, 0);
        this.setColor(Color.BLUE);
        this.lives = l;
    }
}
