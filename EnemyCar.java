import java.awt.*;
import java.util.ArrayList;

public class EnemyCar extends Car {

    private boolean passed;
    private static double speed = 3;

    public EnemyCar(int y, int lane, Image icon){
        super(y,lane,icon);
        passed = false;
    }

    public void move(){ super.setY(super.getY()+speed); }

    public void checkPassed(MyCar player){
        if(super.getY() > Car.getPlayerY() && !passed){
            passed = true;
            player.addPassedCars();
        }
    }

    public static void incriseSpeed(){ if(speed < 8) speed += 0.1; }

    public boolean isPassed() {
        return passed;
    }

    public static boolean checkCollision(MyCar player, ArrayList<EnemyCar> enemies){
        int l;
        double y;
        for(EnemyCar e : enemies){
            l = e.getLane();
            y = e.getY();
            if(l == player.getLane()){
                if (e.getY() + Car.getHEIGHT()/2 > player.getY() && e.getY() < player.getY() + Car.getHEIGHT()/2) {
                    player.saveDatas();
                    return true;
                }
            }
        }
        return false;
    }
}
