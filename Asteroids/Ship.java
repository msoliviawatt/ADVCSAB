import java.awt.*;
import java.util.*;

public class Ship extends Polygon {
    private ArrayList<Missile> missiles = new ArrayList<Missile>();
    private Color purple = new Color(69, 28, 255);
    public Ship(Point[] shape) {
        super(shape, new Point(Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT/2), 0);
        this.setColor(purple);
    }
}
