package slotMachine;

import graphics.ShowSelectedSlotMachines;
import main.Check;
import main.GameWindow;
import main.StartPanel;
import players.Players;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Setting {
    public static JButton SimilarCoin,differentCoin,Cancel,Ok;
    public static int NumberCoinCounter,ChoiceSlotCounter;
    public static boolean OK=false;
    public static ShowSelectedSlotMachines slotMachine1,slotMachine2,slotMachine3;
    public static String ChoiceSlot1,ChoiceSlot2,ChoiceSlot3;
    public static Timer ChangeSlotPanelColor;

    public Setting(){
        Ok = new JButton();
        Ok.setBounds(130,10,60,40);
        Ok.setText("OK 👍");
        Ok.setEnabled(false);
        GameWindow.SettingNumberSlotMachine.add(Ok);

        SimilarCoin = new JButton();
        SimilarCoin.setBounds(30,10,40,40);
        SimilarCoin.setText("2");
        GameWindow.SettingNumberSlotMachine.add(SimilarCoin);
        SimilarCoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!GameWindow.ChoiceCard ) {
                    ChoiceSlotCounter = 2;
                    Ok.setEnabled(true);
                    GameWindow.WarningText.setText("Press OK to get 2 coins of the same color ! 💜💜");
                    GameWindow.deleteWarnText.start();
                }
            }
        });
        SimilarCoin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                SimilarCoin.setToolTipText("get 2 coins of the same color  ");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SimilarCoin.setToolTipText(null);
            }
        });

        differentCoin = new JButton();
        differentCoin.setBounds(80,10,40,40);
        differentCoin.setText("3");
        GameWindow.SettingNumberSlotMachine.add(differentCoin);
        differentCoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!GameWindow.ChoiceCard) {
                    ChoiceSlotCounter = 3;
                    Ok.setEnabled(true);
                    GameWindow.WarningText.setText("Press Ok to get 3 coins with different colors ! 💜💚🩵 ");
                    GameWindow.deleteWarnText.start();
                }
            }
        });
        differentCoin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                differentCoin.setToolTipText("get 3 coins with different colors ");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                differentCoin.setToolTipText(null);
            }
        });
        Cancel = new JButton();
        Cancel.setBounds(200,10,70,40);
        Cancel.setText("Cancel👎");
        Cancel.setEnabled(false);
        GameWindow.SettingNumberSlotMachine.add(Cancel);
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InitialSettings();
            }
        });
        Ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random=new Random();
                SimilarCoin.setEnabled(false);
                differentCoin.setEnabled(false);
                Cancel.setEnabled(true);
                OK=true;
                GameWindow.ChoiceSlotMachine=true;
                ChangeSlotPanelColor = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GameWindow.SlotMachinesPanel1.setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    }
                });
                ChangeSlotPanelColor.start();
            }
        });

        slotMachine1=new ShowSelectedSlotMachines("questionCoin");
        slotMachine1.setBounds(60,4,45,45);
        GameWindow.SettingCoinSlotMachine.add(slotMachine1);
        slotMachine2=new ShowSelectedSlotMachines("questionCoin");
        slotMachine2.setBounds(130,4,45,45);
        GameWindow.SettingCoinSlotMachine.add(slotMachine2);
        slotMachine3=new ShowSelectedSlotMachines("questionCoin");
        slotMachine3.setBounds(200,4,45,45);
        GameWindow.SettingCoinSlotMachine.add(slotMachine3);
    }
    public static void GetThreeCoin(){
        if(ChoiceSlot1.equals("Black") || ChoiceSlot2.equals("Black") || ChoiceSlot3.equals("Black") ) {
            GameWindow.BlackSlotMachine.balance--;
            Players.playerInfo[Players.Turn].wallet.BlackCoin.NumberCoin++;
        }
        if(ChoiceSlot1.equals("Blue") || ChoiceSlot2.equals("Blue") || ChoiceSlot3.equals("Blue")) {
            GameWindow.BlueSlotMachine.balance--;
            Players.playerInfo[Players.Turn].wallet.BlueCoin.NumberCoin++;
        }
        if(ChoiceSlot1.equals("Red") || ChoiceSlot2.equals("Red") || ChoiceSlot3.equals("Red")){
            GameWindow.RedSlotMachine.balance--;
            Players.playerInfo[Players.Turn].wallet.RedCoin.NumberCoin++;
        }
        if(ChoiceSlot1.equals("White") || ChoiceSlot2.equals("White") || ChoiceSlot3.equals("White")){
            GameWindow.WhiteSlotMachine.balance--;
            Players.playerInfo[Players.Turn].wallet.WhiteCoin.NumberCoin++;
        }
        if(ChoiceSlot1.equals("Green") || ChoiceSlot2.equals("Green") || ChoiceSlot3.equals("Green")) {
            GameWindow.GreenSlotMachine.balance--;
            Players.playerInfo[Players.Turn].wallet.GreenCoin.NumberCoin++;
        }
        InitialSettings();
        if( !StartPanel.ComputerMode || Players.Turn==1 ) {
            Check.CheckCoinsNumber();
           // Check.UpdateTurn();
        }
    }
    public static void GetTwoCoin(String SlotName ){
        if(SlotName.equals("Black")) {
            GameWindow.BlackSlotMachine.balance-=2;
            Players.playerInfo[Players.Turn].wallet.BlackCoin.NumberCoin+=2;
        }
        if(SlotName.equals("Blue")) {GameWindow.BlueSlotMachine.balance-=2;
            Players.playerInfo[Players.Turn].wallet.BlueCoin.NumberCoin+=2;
        }
        if(SlotName.equals("Red")){GameWindow.RedSlotMachine.balance-=2;
            Players.playerInfo[Players.Turn].wallet.RedCoin.NumberCoin+=2;
        }
        if(SlotName.equals("White")){GameWindow.WhiteSlotMachine.balance-=2;
            Players.playerInfo[Players.Turn].wallet.WhiteCoin.NumberCoin+=2;
        }
        if(SlotName.equals("Green")) {GameWindow.GreenSlotMachine.balance-=2;
            Players.playerInfo[Players.Turn].wallet.GreenCoin.NumberCoin+=2;
        }
        Setting.InitialSettings();
        if( !StartPanel.ComputerMode || Players.Turn==1 ) {
            Check.CheckCoinsNumber();
           // Check.UpdateTurn();
        }
    }
    public static void InitialSettings(){
        Setting.NumberCoinCounter=0;
        Setting.ChoiceSlotCounter=0;
        Setting.OK=false;
        Setting.SimilarCoin.setEnabled(true);
        Setting.differentCoin.setEnabled(true);
        Setting.Cancel.setEnabled(false);

        if( !StartPanel.ComputerMode || Players.Turn==1 ) {
            Setting.ChangeSlotPanelColor.stop();
            GameWindow.SlotMachinesPanel1.setBackground(Color.GRAY);
            Setting.slotMachine1.SlotName="questionCoin";
            Setting.slotMachine1.repaint();
            Setting.slotMachine2.SlotName="questionCoin";
            Setting.slotMachine2.repaint();
            Setting.slotMachine3.SlotName="questionCoin";
            Setting.slotMachine3.repaint();
        }
        GameWindow.ChoiceSlotMachine=false;
    }
}
