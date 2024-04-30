package graphics;

//import cards.BuyCard;
import inputs.CardsMouseInputs;
import inputs.CoinsMouseInputs;
import main.StartPanel;
import players.Players;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WalletCoins extends JLabel {
    public int NumberCoin;
    public int NumberSpecialCoin=0;
    private String IconName,CoinName;
    private Color ColorCoin;
    private CoinsMouseInputs mouseInputs;
    public JLabel CoinLable;
    public WalletCoins(String CoinName){
        this.CoinName=CoinName;
        if( StartPanel.ComputerMode) NumberCoin=3;
        CoinLable=this;
        setIconName();
        mouseInputs = new CoinsMouseInputs(NumberCoin,NumberSpecialCoin,CoinName);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    repaint();

            }
        });
        timer.start();
    }
    private void setIconName(){
        if(CoinName.compareTo("Black")==0) {this.IconName="BlackCoin80.png";ColorCoin=Color.BLACK;}
        if(CoinName.compareTo("White")==0) {this.IconName="WhiteCoin80.png";ColorCoin=Color.WHITE;}
        if(CoinName.compareTo("Red")==0)   {this.IconName= "RedCoin80.png";ColorCoin=Color.RED;}
        if(CoinName.compareTo("Green")==0) {this.IconName= "GreenCoin80.png";ColorCoin=Color.GREEN;}
        if(CoinName.compareTo("Blue")==0)  {this.IconName= "BlueCoin80.png";ColorCoin=Color.BLUE;}
        if(CoinName.compareTo("Gold")==0)  {this.IconName= "GoldCoin80.png";ColorCoin=Color.YELLOW;}

    }
    private Color setCoinImageBackground(Color color){
        if(color.equals(Color.BLACK)){return new Color(158,164,158);}
        if(color.equals(Color.WHITE)){return new Color(238,242,243);}
        if(color.equals(Color.BLUE)) {return new Color(132,208,243);}
        if(color.equals(Color.RED))  {return new Color(255,185,187);}
        if(color.equals(Color.GREEN)){return new Color(155,225,149);}
        if(color.equals(Color.YELLOW)){return new Color(255,224,130);}
        return null;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Image slotIcon=new ImageIcon(IconName).getImage();
        g.drawImage(slotIcon,0,0,null);
        g.setColor(setCoinImageBackground(ColorCoin));
        g.fillOval(20,20,40,40);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        if(NumberCoin<10)  g.drawString(String.valueOf(NumberCoin+NumberSpecialCoin),32,46);
        else g.drawString(String.valueOf(NumberCoin+NumberSpecialCoin),25,47);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(CoinName.compareTo("Gold")==0) { CoinLable.setToolTipText("Number Of Coin : "+NumberCoin);}
                else CoinLable.setToolTipText("Ordinary Coins : "+NumberCoin+"   "+"Special Coins : "+NumberSpecialCoin);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                CoinLable.setToolTipText(null);
            }
        });


    }
}
