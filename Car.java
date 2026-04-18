import javax.swing.ImageIcon;
import java.awt.*;

public class Car {

    private int lane;
    private double y;
    private static final int HEIGHT = 120;
    private static final int WIDTH = 100;
    private Image icon;

    private static int playerY = 750;

    public Car(double y,int lane,Image icon){
        this.y = y;
        this.lane = lane;
        this.icon = icon;
    }

    public int getLane() {
        return lane;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static int getPlayerY() {
        return playerY;
    }
}
