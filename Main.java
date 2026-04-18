import javax.swing.*;

public class Main {
    static void main() {
        JFrame frame = new JFrame("Endless Car Game");
        frame.setSize(1000, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}