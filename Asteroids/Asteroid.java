import java.awt.Color;

public class Asteroid extends Polygon {
    public Asteroid(Point [] shape, int x, int y) {
        super(shape, new Point(x, y), (int)(Math.random() * 361));
        this.setColor(Color.GRAY);
    }

    public void move() {
        super.move(Game.ASTEROID_SPEED);
    }
}
