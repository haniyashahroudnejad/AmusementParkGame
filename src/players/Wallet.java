package players;

import graphics.WalletCoins;
import main.GameWindow;

public class Wallet {
    public  WalletCoins BlackCoin,BlueCoin,GreenCoin,WhiteCoin,RedCoin,GoldCoin;
    public Wallet(int playerNum){
        setCoinsPanel(playerNum);
    }
    private void setCoinsPanel(int playerNum){
        int xCoin,yCoin,gap;
        if(playerNum==1)  {xCoin=320;yCoin=4;gap=80;}
        else  {xCoin=5;yCoin=4;gap=80;}

        BlackCoin = new WalletCoins("Black");
        BlackCoin.setBounds(xCoin,yCoin,80,80);

        BlueCoin = new WalletCoins("Blue");
        BlueCoin.setBounds(xCoin+gap,yCoin,80,80);

        RedCoin = new WalletCoins("Red");
        RedCoin.setBounds(xCoin+2*gap,yCoin,80,80);

        WhiteCoin = new WalletCoins("White");
        WhiteCoin.setBounds(xCoin+3*gap,yCoin,80,80);

        GreenCoin = new WalletCoins("Green");
        GreenCoin.setBounds(xCoin+4*gap,yCoin,80,80);

        GoldCoin = new WalletCoins("Gold");
        GoldCoin.setBounds(xCoin+5*gap,yCoin,80,80);

        if(playerNum==1){
            GameWindow.Player1InformationsPanel.add(BlackCoin);
            GameWindow.Player1InformationsPanel.add(BlueCoin);
            GameWindow.Player1InformationsPanel.add(RedCoin);
            GameWindow.Player1InformationsPanel.add(WhiteCoin);
            GameWindow.Player1InformationsPanel.add(GreenCoin);
            GameWindow.Player1InformationsPanel.add(GoldCoin);
        }else{
            GameWindow.Player2InformationsPanel.add(BlackCoin);
            GameWindow.Player2InformationsPanel.add(BlueCoin);
            GameWindow.Player2InformationsPanel.add(RedCoin);
            GameWindow.Player2InformationsPanel.add(WhiteCoin);
            GameWindow.Player2InformationsPanel.add(GreenCoin);
            GameWindow.Player2InformationsPanel.add(GoldCoin);
        }
    }

}
