import java.awt.*;

/**
 * Created by Olga on 02.12.2014.
 */
public class CleverRobot extends Component {

    public static final double DEFAULT_SPEED = 0.5;
    public static final double DEFAULT_TURNING_SPEED = Math.PI / 1000;
    public static final int RADIUS = 50;
    public static final int UPDATE_PERIOD = 50;

    private double direction;
    private double speed;
    private double turnSpeed;
    private int x;
    private int y;
    private int mControlSignal = 0;

    public CleverRobot(int initX, int initY) {
        this.x = initX;
        this.y = initY;
        this.direction = 0;
        this.speed = 0;
        this.turnSpeed = 0;
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

    private void move(long elapsedTime) {
        int controlSignal = (int) (Math.random()*6);
        setAction(controlSignal);
        this.x += Math.round(speed * elapsedTime * Math.cos(direction));
        this.y += Math.round(speed * elapsedTime * Math.sin(direction));
        this.direction += turnSpeed * elapsedTime;
    }

    public void forceMove(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setAction(int controlSignal) {
        mControlSignal = controlSignal;
        switch (controlSignal) {
            case 0:
                this.turnSpeed = -DEFAULT_TURNING_SPEED;
                this.speed = DEFAULT_SPEED;
                break;
            case 1:
                this.turnSpeed = 0;
                this.speed = DEFAULT_SPEED;
                break;
            case 2:
                this.turnSpeed = DEFAULT_TURNING_SPEED;
                this.speed = DEFAULT_SPEED;
                break;
            case 3:
                this.turnSpeed = -DEFAULT_TURNING_SPEED;
                this.speed = 0;
                break;
            case 4:
                this.turnSpeed = 0;
                this.speed = 0;
                break;
            case 5:
                this.turnSpeed = DEFAULT_TURNING_SPEED;
                this.speed = 0;
                break;
        }
    }

    public void drawRobot(Graphics graphics, int initX, int initY) {
        graphics.drawOval(initX - RADIUS / 2, initY - RADIUS / 2, RADIUS, RADIUS);
        graphics.drawLine(initX, initY, (int) (RADIUS * 2 * Math.cos(direction)) + initX, (int) (RADIUS * 2 * Math.sin(direction)) + initY);
    }

    private class MoveThread extends Thread {
        private CleverRobot mRobot;
        private long prevTime;
        private boolean mRun;

        public MoveThread(CleverRobot robot) {
            mRobot = robot;
        }

        @Override
        public void run() {
            prevTime = System.currentTimeMillis();
            mRun = true;
            while (mRun) {
                if(System.currentTimeMillis() - prevTime < 1/DEFAULT_SPEED) {
                    continue;
                }
                long time = System.currentTimeMillis();
                mRobot.move(time - prevTime);
                prevTime = time;
            }
        }

        public void interrupt() {
            mRun = false;
        }
    }

    private class UpdateThread extends Thread {
        private CleverRobot mRobot;
        private boolean mRun;

        public UpdateThread(CleverRobot robot) {
            mRobot = robot;
            mRun = true;
        }

        @Override
        public void run() {
            long prevTime = System.currentTimeMillis();
            while(mRun) {
                if(System.currentTimeMillis() - prevTime < UPDATE_PERIOD) {
                    continue;
                }
                prevTime = System.currentTimeMillis();
            }
        }

        public void interrupt() {
            mRun = false;
        }
    }
}
