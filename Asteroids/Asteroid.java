import java.awt.Color;
public class Asteroid extends Polygon {
    public Asteroid(Point [] shape, int x, int y) {
        super(shape, new Point(x, y), (int)(Math.random() * 361));
        this.setColor(Color.WHITE);
    }

    // public void move() {
    //     super.move(0);
    // }

    // public void rotate(int x) {
    //     super.rotate(x);
    // }
}
