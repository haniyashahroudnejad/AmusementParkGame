package graphics;

import javax.swing.*;
import java.awt.*;
public class WheelRotation extends JLabel {
    private double rotationAngle=0;
    public WheelRotation() {
        new Timer(10, e -> {
            rotationAngle += 1;
            repaint();
        }).start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        Image image = new ImageIcon("color-wheel.png").getImage();
        super.paintComponent(g);
        g.setColor(new Color(143,2,80));
        g.fillRect(55,64,20,150);
        g.fillRect(35,166,60,20);
        int centerX = 64;
        int centerY = 64;

        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(Math.toRadians(rotationAngle), centerX, centerY);

        g2.drawImage(image, 0, 0, null);
    }
}
