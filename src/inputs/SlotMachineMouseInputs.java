package inputs;

import main.Check;
import main.GameWindow;
import players.Players;
import slotMachine.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SlotMachineMouseInputs implements MouseListener, MouseMotionListener {
    private final String SlotName;
    private int balance;

    public SlotMachineMouseInputs(int balance,String SlotName){
        this.SlotName=SlotName;
        this.balance=balance;
    }
    private void UpdateBalance(){
        if(SlotName.equals("Black")) balance=GameWindow.BlackSlotMachine.balance;
        if(SlotName.equals("Blue")) balance=GameWindow.BlueSlotMachine.balance;
        if(SlotName.equals("Red"))  balance=GameWindow.RedSlotMachine.balance;
        if(SlotName.equals("Green")) balance=GameWindow.GreenSlotMachine.balance;
        if(SlotName.equals("White")) balance=GameWindow.WhiteSlotMachine.balance;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
            if(Setting.OK && Setting.NumberCoinCounter<3){
                UpdateBalance();
                if(balance<4 && Setting.ChoiceSlotCounter==2) {JOptionPane.showMessageDialog(null,"The balance is insufficient 🤯😵!");Setting.InitialSettings();}
                else if(Setting.ChoiceSlotCounter==2){
                    Setting.slotMachine1.SlotName=this.SlotName;
                    Setting.slotMachine1.repaint();
                    Setting.slotMachine2.SlotName=this.SlotName;
                    Setting.slotMachine2.repaint();
                    int choice = JOptionPane.showOptionDialog(null, "Press OK to get 2 coins ", "Confirmation",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"OK 👍", "Cancel 👎"}, "OK 👍");
                    if(choice==0){
                       Setting.GetTwoCoin(SlotName);
                    }else{
                        Setting.InitialSettings();
                    }
                }else if(balance>=1 && Setting.ChoiceSlotCounter==3){
                    if(Setting.NumberCoinCounter==0){
                        Setting.slotMachine1.SlotName=this.SlotName;
                        Setting.slotMachine1.repaint();
                        Setting.NumberCoinCounter++;
                        Setting.ChoiceSlot1=SlotName;
                    }else if(Setting.NumberCoinCounter==1 ){
                        if(Setting.ChoiceSlot1.equals(SlotName)){
                            GameWindow.WarningText.setText("Your selection is duplicated, please select again 😬!");
                            GameWindow.deleteWarnText.start();
                        }else {
                            Setting.slotMachine2.SlotName = this.SlotName;
                            Setting.slotMachine2.repaint();
                            Setting.NumberCoinCounter++;
                            Setting.ChoiceSlot2=SlotName;
                        }
                    }else if(Setting.NumberCoinCounter==2){
                        if(Setting.ChoiceSlot1.equals(SlotName) || Setting.ChoiceSlot2.equals(SlotName)){
                            GameWindow.WarningText.setText("Your selection is duplicated, please select again 😮‍💨 !");
                            GameWindow.deleteWarnText.start();
                        }else {
                            Setting.slotMachine3.SlotName = this.SlotName;
                            Setting.slotMachine3.repaint();
                            Setting.NumberCoinCounter++;
                            Setting.ChoiceSlot3=SlotName;
                            int choice = JOptionPane.showOptionDialog(null, "Press OK to get 3 coins ", "Confirmation",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"OK 👍", "Cancel 👎"}, "OK 👍");
                            if(choice==0){Setting.InitialSettings();Setting.GetThreeCoin();}
                            else Setting.InitialSettings();
                        }
                    }
                }else if(balance==0){
                    {JOptionPane.showMessageDialog(null,"The balance is insufficient ! 🥴");Setting.InitialSettings();}
                }
            }
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

