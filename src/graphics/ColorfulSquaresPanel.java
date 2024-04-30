package graphics;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

public class ColorfulSquaresPanel extends JPanel {
    private Random random = new Random();
    int side;

    public ColorfulSquaresPanel(int side){
        this.side=side;
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int squareSize = 80;
        int squareX = 0;
        int squareY = 0;
        int num = 0;
        //  for(int i=squareX;)
        if(side==1 || side==3) {
            while (num <11) {
                g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                g.fillRect(squareX, squareY + num * squareSize, squareSize, squareSize);
                num++;
            }
        }
        else if(side==4 || side==2) {
            num = 0;
            while (num < 11) {
                g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                g.fillRect(squareX + num * squareSize, squareX, squareSize, squareSize);
                num++;
            }
        }
    }
}
