import java.awt.Color;

//class for the asteroids
public class Asteroid extends Polygon {

    //generates a random color for the asteroids
    Color randomColor = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));

    //constructor
    public Asteroid(Point [] shape, int x, int y) {
        super(shape, new Point(x, y), (int)(Math.random() * 361));
        this.setColor(randomColor);
    }

    //moves the asteroids
    public void move() {
        super.move(Game.ASTEROID_SPEED);
    }
}
