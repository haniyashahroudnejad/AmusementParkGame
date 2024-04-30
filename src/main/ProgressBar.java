package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProgressBar {
    private final JLabel backGround ;
    public JProgressBar bar = new JProgressBar();
    int counter=0;
    Timer LoadTime;
    JLabel LoadingLabel;
    JPanel LoadingPanel;
    public ProgressBar(JFrame jFrame){
        backGround = new JLabel();
        ImageIcon firstPage = new ImageIcon("StartPage.jpg");
        backGround.setIcon(firstPage);
        backGround.setBounds(0,0,1024,1024);
        jFrame.add(backGround);
        bar.setValue(0); //start

        bar.setBounds(250,960,500,20);
        bar.setStringPainted(true);
        bar.setFont(new Font("MV Boli",Font.BOLD,10));
        backGround.add(bar);

        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setResizable(false); //prevent frame from being resized
        jFrame.setSize(1024,1024);
        jFrame.setBounds(300,0,1024,1024);
        jFrame.setLayout(null);
        jFrame.setVisible(true);

        LoadingPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                g.setColor(new Color(95,235,163));
                g.fillRect(2,0,230,40);
                g.setColor(new Color(41,196,133));
                g.fillRect(0,33,230,55);
            }
        };
        LoadingPanel.setBounds(400,835,230,75);
        backGround.add(LoadingPanel);

        LoadingLabel= new JLabel();
        LoadingLabel.setBounds(20,22,200,40);
        LoadingLabel.setForeground(Color.WHITE);
        LoadingLabel.setFont(new Font("Arial",Font.BOLD,40));
        LoadTime = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(counter==101) LoadingLabel.setText("START");
                else if(counter%2==0)  LoadingLabel.setText("Loading..");
                else LoadingLabel.setText("Loading...");

            }

        });
        LoadTime.start();
        LoadingPanel.add(LoadingLabel);

        fill(jFrame);

    }
    public  void fill(JFrame jFrame){
        while (counter<=100){
            bar.setValue(counter);

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter+=1;
        }
        counter=101;
        bar.setString("Done! :)");
        LoadingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jFrame.setVisible(false);
                JFrame gameFrame = new JFrame();
                new StartPanel(gameFrame);
            }
        });

    }
}
