import java.awt.*;

public class Missile extends Polygon {

    //constructor
    public Missile(Point[] shape, double x, double y, double rotation) {
        super(shape, new Point(x, y), rotation); //calls the constructor of the Polygon class
        this.setColor(Color.RED); //sets the color of the missile to red
        this.setWrap(false); //sets the missile to not wrap around the screen
    }

    //moves the missile
    public void move() {
        super.move(Game.MISSILE_SPEED);
    }
}
