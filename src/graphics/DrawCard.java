package graphics;

import inputs.CardsMouseInputs;
import main.Check;
import main.GameWindow;
import javax.swing.*;
import java.awt.*;
import cards.CardsCoins;


public class DrawCard extends JPanel {
    public  CardsCoins SpecialCoin,coin1,coin2,coin3;
    public  int TotalCoinsNum,Score;
    public  String iconName;
    public int NumCard;
    public int x,y;
    CardsMouseInputs mouseInputs;
    public DrawCard(int x, int y,int NumCard){
        this.NumCard=NumCard;
        if(!GameWindow.CardsInfo[NumCard].BuyReservedCard && NumCard>3) {
            mouseInputs = new CardsMouseInputs(x, y);
            addMouseListener(mouseInputs);
            addMouseMotionListener(mouseInputs);
        }
        this.x=x;
        this.y=y;
        SpecialCoin = GameWindow.CardsInfo[NumCard].SpecialCoin;
        coin1 = GameWindow.CardsInfo[NumCard].coin1;
        coin2 = GameWindow.CardsInfo[NumCard].coin2;
        coin3 = GameWindow.CardsInfo[NumCard].coin3;
        Score = GameWindow.CardsInfo[NumCard].score;
        iconName=GameWindow.CardsInfo[NumCard].iconName;
        TotalCoinsNum=GameWindow.CardsInfo[NumCard].TotalCoinsNum;
    }
    private String setCoinImageName(Color color){
        if(color.equals(Color.BLACK)){return "BlackCoin.png";}
        if(color.equals(Color.WHITE)){return "WhiteCoin.png";}
        if(color.equals(Color.BLUE)){return  "BlueCoin.png";}
        if(color.equals(Color.RED)){return   "RedCoin.png";}
        if(color.equals(Color.GREEN)){return "GreenCoin.png";}
        return null;
    }
    private Color setCoinImageBackground(Color color){
        if(color.equals(Color.BLACK)){return new Color(158,164,158);}
        else if(color.equals(Color.WHITE)){return new Color(238,242,243);}
        else if(color.equals(Color.BLUE)) {return new Color(132,208,243);}
        else if(color.equals(Color.RED))  {return new Color(255,185,187);}
        else if(color.equals(Color.GREEN)){return new Color(155,225,149);}
        return null;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

      if(!GameWindow.finishCard ) {
          Image icon = new ImageIcon(iconName).getImage();
          if(NumCard>3) mouseInputs.NumCard = NumCard;
          // Draw  background

          if(NumCard<=3  )   g.drawImage(icon, 0, 0, null);
          else {
              g.setColor(new Color(0, 0, 139));
              g.fillRect(0, 0, getWidth(), getHeight());

              g.setColor(Color.WHITE);
              g.fillRect(15, 15, getWidth() - 30, getHeight() - 30);

              g.setColor(new Color(0, 0, 139));
              g.fillOval(5, 6, 25, 25);
          }
          //write Score
          g.setFont(new Font("Arial", Font.BOLD, 20));
          g.setColor(Color.WHITE);
          g.drawString(String.valueOf(Score), 10, 25);

          //Draw Special Coin
          if(NumCard>3) {
              Image Special = new ImageIcon(setCoinImageName(SpecialCoin.getColor())).getImage();
              g.drawImage(Special, 110, 0, null);
          }
          //Draw Coin1
          if(NumCard<4) y=120;
          else y=130;

          Image CoinOne = new ImageIcon(setCoinImageName(coin1.getColor())).getImage();
          g.drawImage(CoinOne, 4, y, null);

          g.setColor(setCoinImageBackground(coin1.getColor()));
          g.fillOval(17, y+12, 20, 20);

          int Price;
          g.setColor(Color.BLACK);
          g.setFont(new Font("Arial", Font.BOLD, 20));
          Price=coin1.getPrice();
          if (Price<10) g.drawString(String.valueOf(Price), 22, y+30);
          else g.drawString(String.valueOf(Price), 17, y+30);

          //Draw Coin2
          Image CoinTwo = new ImageIcon(setCoinImageName(coin2.getColor())).getImage();
          g.drawImage(CoinTwo, 52, y, null);

          g.setColor(setCoinImageBackground(coin2.getColor()));
          g.fillOval(65, y+12, 20, 20);

          g.setColor(Color.BLACK);
          g.setFont(new Font("Arial", Font.BOLD, 20));
          Price=coin2.getPrice();
          if(Price<10) g.drawString(String.valueOf(Price), 70, y+30);
          else  g.drawString(String.valueOf(Price), 65, y+30);

          //Draw Coin3
          if (TotalCoinsNum == 3) {
              Image CoinThree = new ImageIcon(setCoinImageName(coin3.getColor())).getImage();
              g.drawImage(CoinThree, 100, y, null);

              g.setColor(setCoinImageBackground(coin3.getColor()));
              g.fillOval(113, y+12, 20, 20);

              g.setColor(Color.BLACK);
              g.setFont(new Font("Arial", Font.BOLD, 20));
              g.drawString(String.valueOf(coin3.getPrice()), 118, y+30);
          }


          if(NumCard>3)  g.drawImage(icon, 43, 48, null);

      }else  {
          GameWindow.cardPanel.remove(this);
          g.setColor(new Color(114,237,252));
          g.fillRect(0, 0, getWidth(), getHeight());
          GameWindow.finishCard=false;

      }

    }

}



