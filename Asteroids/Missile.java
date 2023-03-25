import java.awt.*;

public class Missile extends Polygon {

    public Missile(Point[] shape, double x, double y, double rotation) {
        super(shape, new Point(x, y), rotation);
        this.setColor(Color.RED);
        this.setWrap(false);
    }

    public void move() {
        super.move(Game.MISSILE_SPEED);
    }
}
