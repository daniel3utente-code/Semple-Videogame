import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    private static final int WIDHT = 1000;
    private static final int HEIGHT = 900;
    private static final int MARGIN = 50;
    private MyCar player;
    private Image playerCar;
    private Image enemyCar;
    private boolean leftPressed;
    private boolean rightPressed;
    private ArrayList<EnemyCar> enemies;
    private ArrayList<Integer> lanes;
    private Random random;
    private int cont;
    private int max;


    public GamePanel(){
        setFocusable(true);
        cont = 0;
        max = 90;
        enemies = new ArrayList<>();
        lanes = new ArrayList<>();
        random = new Random();
        playerCar = new ImageIcon("/home/dans/Documents/Schiva_le_Macchine/myCar.png").getImage();
        enemyCar = new ImageIcon("/home/dans/Documents/Schiva_le_Macchine/enemyCar.png").getImage();
        player = new MyCar(2,playerCar);

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftPressed");
        getActionMap().put("leftPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { leftPressed = true; }
        });

        // freccia sinistra rilasciata
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), "leftReleased");
        getActionMap().put("leftReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { leftPressed = false; }
        });

        // freccia destra premuta
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "rightPressed");
        getActionMap().put("rightPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { rightPressed = true; }
        });

        // freccia destra rilasciata
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), "rightReleased");
        getActionMap().put("rightReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { rightPressed = false; }
        });

        Timer timer = new Timer(16, e -> {
            gameLoop();
            repaint();
        });
        timer.start();
    }

    public void gameLoop(){
            if(leftPressed && player.getLane() > 0) {
                player.setLane(player.getLane()-1);
                leftPressed = false;
            }
            if(rightPressed && player.getLane() < 4) {
                player.setLane(player.getLane()+1);
                rightPressed = false;
            }
            if(cont == max){
                lanes.clear();
                int numSpawn = random.nextInt(4)+1;
                int temp;
                for(int i=0;i<numSpawn;i++){
                    temp = random.nextInt(5);
                    if(!lanes.contains((Integer)temp))lanes.add(temp);
                    else i--;
                }
                for(int i=0;i<numSpawn;i++){ enemies.add(new EnemyCar(50,lanes.get(i),enemyCar)); }
                cont = 0;
                if(max > 30) max-= 1;
                EnemyCar.incriseSpeed();
            }else cont++;

            for(EnemyCar e : enemies){
                e.move();
            }
            enemies.removeIf(e -> {
                e.checkPassed(player);
                return e.isPassed();
            });

            if(EnemyCar.checkCollision(player,enemies)) System.exit(0);

            java.awt.Toolkit.getDefaultToolkit().sync();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        printLanes(g);
        printMyCar(g);
        printEnemyCar(g);
    }

    public void printLanes(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(MARGIN,0,Lane.getROAD_WIDHT(),HEIGHT);

        g.setColor(Color.WHITE);
        for (int i = 1; i < 5; i++) {
            int x = MARGIN + i * Lane.getLANE_WIDHT();
            g.fillRect(x - 1, 0, 2, getHeight());
        }
    }

    public void printMyCar(Graphics g){
        int playerX = MARGIN + Lane.getLANE_WIDHT()/2 + player.getLane() * Lane.getLANE_WIDHT() - Car.getWIDTH()/2;
        g.drawImage(playerCar,playerX,(int)player.getY(),Car.getWIDTH(),Car.getHEIGHT(),this);
    }

    public void printEnemyCar(Graphics g){
        for (EnemyCar e : enemies) {
            int carX = MARGIN + Lane.getLANE_WIDHT() / 2 + e.getLane() * Lane.getLANE_WIDHT() - Car.getWIDTH() / 2;
            g.drawImage(enemyCar, carX, (int)e.getY(), Car.getWIDTH(), Car.getHEIGHT(), this);
        }
    }
}