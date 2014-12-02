import java.awt.*;

/**
 * Created by Olga on 02.12.2014.
 */
public class CleverRobot extends Component {

    private int x;
    private int y;

    public CleverRobot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.setColor(Color.BLACK);
        graphics.drawOval(this.x - 5, this.y - 5, 10, 10);
    }
}
