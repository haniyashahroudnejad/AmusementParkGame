package players;

import cards.CreateCards;
import graphics.Score;
import graphics.WalletCoins;
import main.GameWindow;
import main.StartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Players {
    public String Name;
    public static int Turn=0;
    public Score PlayerScore;
    public  Wallet wallet;
    public  int  PurchasedCardsNum;
    public  int  ReservedCardsNum;
    public JLabel PlayerProfile;
    public  CreateCards [] ReservedCards = new CreateCards[48];

    public static Players [] playerInfo=new Players[3];

    public Players(int playerNum){
        setName(playerNum);
        wallet=new Wallet(playerNum);
        if(playerNum==1) {
            PlayerScore = new Score(1);
            PlayerScore.setBounds(130, 0, 100, 100);
            GameWindow.Player1InformationsPanel.add(PlayerScore);
        }else {
            PlayerScore=new Score(2);
            PlayerScore.setBounds(580,1,100,100);
            GameWindow.Player2InformationsPanel.add(PlayerScore);
        }
        setCharacter(playerNum);
        playerInfo[playerNum]=this;
    }
    private void setName(int playerNum){
        if(playerNum==1) Name= StartPanel.PlayersName[1];
        else{
            if(StartPanel.ComputerMode) Name="Computer";
            else Name=StartPanel.PlayersName[2];
        }
    }
    private void setCharacter(int playerNum){
        String CharacterName;
        int x;
        if(playerNum==1){CharacterName="Player1.png";x=11;}
        else{
            if(StartPanel.ComputerMode) {CharacterName="computer.png";}
            else CharacterName="Player2.png";x=695;
        }

        if(StartPanel.CharacterName[playerNum]!=null) {
            PlayerProfile = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    Image playerIcon;
                    if(playerNum==1)  g.setColor(new Color(255,209,241));
                    else   g.setColor(new Color(209,255,179));
                    g.fillOval(1, 2, 90, 90);

                    playerIcon = new ImageIcon(StartPanel.CharacterName[playerNum]).getImage();
                    if(StartPanel.ComputerMode && playerNum==2) {playerIcon = new ImageIcon("computer.png").getImage();}
                    g.drawImage(playerIcon, 6, 5, null);
                }
            };
        }else {
            PlayerProfile = new JLabel();
            ImageIcon playerIcon = new ImageIcon(CharacterName);
            PlayerProfile.setIcon(playerIcon);
        }
        PlayerProfile.setBounds(x,2,100,100);

        if(playerNum==1)  GameWindow.Player1InformationsPanel.add(PlayerProfile);
        else  GameWindow.Player2InformationsPanel.add(PlayerProfile);

        PlayerProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                PlayerProfile.setToolTipText(Name);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                PlayerProfile.setToolTipText(null);
            }
        });
    }




}

