package graphics;

import players.Players;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Score extends JLabel {
    private int  player;
    public int score=13;
    public Score (int player){
        this.player=player;
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
        String scoreName;
        if(player==1) scoreName="scoreboardP1.png";
        else scoreName="scoreboardP2.png";
        Image scoreIcon = new ImageIcon(scoreName).getImage();
        g.drawImage(scoreIcon, 0, 0, null);
        if(player==1){
            g.setColor(new Color(248,187,244));
            g.fillRect(8,32,41,40);
            g.setColor(new Color(163,204,164));
            g.fillRect(55,32,41,40);

        }else{
            g.setColor(new Color(226,234,135));
            g.fillRect(8,32,41,40);
            g.setColor(new Color(173,138,197));
            g.fillRect(55,32,41,40);
        }
        //int score= Players.playerInfo[Players.Turn].PlayerScore;
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(String.valueOf(score%10),65,70);
        g.drawString(String.valueOf(score/10),18,70);
    }
}
