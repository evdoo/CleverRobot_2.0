import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Olga on 02.12.2014.
 */
public class RobotFrame extends Frame {
    private final int frameWidth = 1000;
    private final int frameHeight = 700;

    public RobotFrame(String title) {
        //Создание видимого фрейма с подписью и нужных размеров, закрывающегося.
        this.setTitle(title);
        this.setSize(frameWidth, frameHeight);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                dispose();
                System.exit(0);
            }
        });

        //Создание объекта канвас, добавление его во фрейм.
        FieldSurfaceView canvas = new FieldSurfaceView();
        canvas.setSize(frameWidth, frameHeight);
        canvas.setVisible(true);
        this.add(canvas);

        //Создание объекта-робота с координатами в центре канвас, его рисование.
        CleverRobot robot = new CleverRobot(canvas.getWidth() / 2, canvas.getHeight() / 2);
        Graphics g = getGraphics();
        g.setColor(Color.BLACK);
        robot.drawRobot(g, robot.getX(), robot.getY());
    }

    public static void main (String[] args) {
        RobotFrame frame = new RobotFrame("CleverRobot 2.0");
    }
}