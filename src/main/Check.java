package main;

import players.Computer;
import players.Players;
import slotMachine.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Check {
    public static int givePrizeCounter;
    public static void CheckCoinsNumber(){
        int blackCoin=Players.playerInfo[Players.Turn].wallet.BlackCoin.NumberCoin;
        int blueCoin=Players.playerInfo[Players.Turn].wallet.BlueCoin.NumberCoin;
        int redCoin=Players.playerInfo[Players.Turn].wallet.RedCoin.NumberCoin;
        int greenCoin=Players.playerInfo[Players.Turn].wallet.GreenCoin.NumberCoin;
        int whiteCoin=Players.playerInfo[Players.Turn].wallet.WhiteCoin.NumberCoin;
        int sum;
        sum=blackCoin+blueCoin+redCoin+greenCoin+whiteCoin;
        if(sum>10){
            Setting.Ok.setEnabled(false);
            Setting.SimilarCoin.setEnabled(false);
            Setting.differentCoin.setEnabled(false);
            if(!GameWindow.ChoiceCoin) {
                JOptionPane.showMessageDialog(null, " The maximum number of normal Coins allowed in your wallet is 10 \uD83D\uDE31 " + "\n Please return it to the SlotMachine by clicking on " + (sum - 10) + " coins you want. \uD83E\uDD28 ");
                GameWindow.ChoiceCoin=true;
            }else {
                GameWindow.WarningText.setText("Please return "+(sum - 10)+" coins by clicking your coins  \uD83D\uDE14.");
                GameWindow.deleteWarnText.start();
            }
        }
        else{
            GameWindow.WarningText.setText("                            OK 👌 \uD83D\uDE18 🫶          ");
            GameWindow.deleteWarnText.start();
            GameWindow.ChoiceCoin=false;
            Setting.InitialSettings();
            UpdateTurn();
        }
    }
    public static void CheckPrizeClaw(){
        int Coin1price,Coin2price,Coin3price=0,TotalCoinsNum,CoinPrice,SwNum;
        Color Coin1Color, Coin2Color, Coin3Color=null,CoinColor;

        for (int prizeNum=1 ;prizeNum<4;prizeNum++ ){
            if(!GameWindow.CardsInfo[prizeNum].out) {
                SwNum=0;
                TotalCoinsNum = GameWindow.CardsInfo[prizeNum].TotalCoinsNum;
                Coin1price = GameWindow.CardsInfo[prizeNum].coin1.getPrice();
                Coin1Color = GameWindow.CardsInfo[prizeNum].coin1.getColor();
                Coin2price = GameWindow.CardsInfo[prizeNum].coin2.getPrice();
                Coin2Color = GameWindow.CardsInfo[prizeNum].coin2.getColor();
                if (TotalCoinsNum == 3) {
                    Coin3price = GameWindow.CardsInfo[prizeNum].coin3.getPrice();
                    Coin3Color = GameWindow.CardsInfo[prizeNum].coin3.getColor();
                }
                for (int i = 0; i < 3; i++) {
                    if (i == 0) {
                        CoinColor = Coin1Color;
                        CoinPrice = Coin1price;
                    } else if (i == 1) {
                        CoinColor = Coin2Color;
                        CoinPrice = Coin2price;
                    } else {
                        if (TotalCoinsNum == 3) {
                            CoinColor = Coin3Color;
                            CoinPrice = Coin3price;
                        } else break;
                    }
                    if (CoinColor.equals(Color.BLACK)) {
                        if (Players.playerInfo[Players.Turn].wallet.BlackCoin.NumberSpecialCoin >= CoinPrice) {
                            SwNum++;
                        }
                    } else if (CoinColor.equals(Color.BLUE)) {
                        if (Players.playerInfo[Players.Turn].wallet.BlueCoin.NumberSpecialCoin >= CoinPrice) {
                            SwNum++;
                        }
                    } else if (CoinColor.equals(Color.RED)) {
                        if (Players.playerInfo[Players.Turn].wallet.RedCoin.NumberSpecialCoin >= CoinPrice) {
                            SwNum++;
                        }
                    } else if (CoinColor.equals(Color.WHITE)) {
                        if (Players.playerInfo[Players.Turn].wallet.WhiteCoin.NumberSpecialCoin >= CoinPrice) {
                            SwNum++;
                        }
                    } else if (CoinColor.equals(Color.GREEN)) {
                        if (Players.playerInfo[Players.Turn].wallet.GreenCoin.NumberSpecialCoin >= CoinPrice) {
                            SwNum++;
                        }
                    }
                }
                if ((TotalCoinsNum == 2 && SwNum == 2) || (TotalCoinsNum == 3 && SwNum == 3)) {
                    GameWindow.CardsInfo[prizeNum].out=true;
                    if(prizeNum==1) {
                        givePrizeCounter++;
                        GameWindow.WarningText.setText("Congratulations,you have received first prize 🥳");
                        GameWindow.prize1Name.setText(Players.playerInfo[Players.Turn].Name);
                        GameWindow.prize1Name.setVisible(true);

                    }
                    else if(prizeNum==2) {
                        givePrizeCounter++;
                        GameWindow.WarningText.setText("Congratulations,you have received second prize 🥳");
                        GameWindow.prize2Name.setText(Players.playerInfo[Players.Turn].Name);
                        GameWindow.prize2Name.setVisible(true);
                    }
                    else {
                        givePrizeCounter++;
                        GameWindow.WarningText.setText("Congratulations,you have received third prize 🥳");
                        GameWindow.prize3Name.setText(Players.playerInfo[Players.Turn].Name);
                        GameWindow.prize3Name.setVisible(true);
                    }
                    GameWindow.deleteWarnText.start();
                    Players.playerInfo[Players.Turn].PlayerScore.score+=GameWindow.CardsInfo[prizeNum].score;
                    break;
                }
            }
        }
    }
    public static void UpdateTurn(){
        if(!GameWindow.ChoiceCoin) {
            if(givePrizeCounter<4) CheckPrizeClaw();
            GameWindow.ChoiceCard=false;
            if (Players.Turn == 1) {
                Players.Turn = 2;
                if(StartPanel.ComputerMode) {new Computer();}
            } else {CheckWinner();Players.Turn = 1;}
            GameWindow.TurnText.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
            GameWindow.TurnText.setText(Players.playerInfo[Players.Turn].Name + "'s turn ");

        }
    }
    public static void CheckWinner(){
        int Winner=0;
        if(Players.playerInfo[1].PlayerScore.score>=15 && Players.playerInfo[2].PlayerScore.score<15)   Winner=1;
        if(Players.playerInfo[2].PlayerScore.score>=15 && Players.playerInfo[1].PlayerScore.score<15)   Winner=2;
        else if(Players.playerInfo[1].PlayerScore.score==Players.playerInfo[2].PlayerScore.score && Players.playerInfo[1].PlayerScore.score>=15){
            if(Players.playerInfo[1].PurchasedCardsNum<Players.playerInfo[2].PurchasedCardsNum) Winner=1;
            else if(Players.playerInfo[2].PurchasedCardsNum<Players.playerInfo[1].PurchasedCardsNum) Winner=2;
            else Winner=3;
        }
        if(Winner==1 || Winner==2 || Winner==3){
            GameWindow.jFrame.setVisible(false);
            JFrame finishFrame = new JFrame();
            finishFrame.setBounds(300,0,1024,1024);
            JLabel finishPanel = new JLabel();

            finishPanel.setBounds(0,0,1000,1000);
            finishPanel.setIcon(new ImageIcon("finish.JPG"));
            finishFrame.add(finishPanel);


            JPanel WinnerNamePanel = new JPanel();
            WinnerNamePanel.setBounds(320,820,400,80);
            WinnerNamePanel.setBackground(new Color(248,247,205));
            finishPanel.add(WinnerNamePanel);

            JLabel WinnerNameLabel = new JLabel();
            WinnerNameLabel.setForeground(Color.BLACK);
            WinnerNameLabel.setFont(new Font("Arial",Font.BOLD,50));
            if(Winner==1)   WinnerNameLabel.setText(Players.playerInfo[1].Name);
            else if (Winner==2) WinnerNameLabel.setText(Players.playerInfo[2].Name);
            else {WinnerNameLabel.setBounds(350,840,370,70); WinnerNameLabel.setText(Players.playerInfo[1].Name+"  "+Players.playerInfo[2].Name);}
            WinnerNamePanel.add(WinnerNameLabel);

            JLabel reset = new JLabel();
            reset.setIcon(new ImageIcon("heartReset.png"));
            reset.setBounds(950,15,64,64);
            finishPanel.add(reset);
            finishFrame.setVisible(true);

            reset.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    finishFrame.setVisible(false);
                    new StartPanel(new JFrame());
                }
            });
        }
    }

}
