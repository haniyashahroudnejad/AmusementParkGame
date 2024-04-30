package inputs;


//import cards.BuyCard;
import cards.CardOperation;
import cards.CreateCards;
import graphics.DrawCard;
import main.Check;
import main.GameWindow;
import players.Players;
import slotMachine.Setting;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardsMouseInputs implements MouseListener, MouseMotionListener {
    private  int x;
    private  int y;
    public int   NumCard;

    public CardsMouseInputs(int x,int y){
        this.x=x;
        this.y=y;

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        GameWindow.ChoiceCard=true;
        Setting.Ok.setEnabled(false);
        if(!GameWindow.ChoiceSlotMachine && !GameWindow.ChoiceCoin) {
            int i;
            int SwEnabled = 1;
            if( (GameWindow.CardsInfo[NumCard].ReservationPlayer2 && Players.Turn==1)  ||  (GameWindow.CardsInfo[NumCard].ReservationPlayer1 && Players.Turn==2) ){
                GameWindow.WarningText.setText("                 The selected card is reserved! 🙄🤭");
                GameWindow.deleteWarnText.start();
                GameWindow.ChoiceCard=false;
                SwEnabled = 0;
            }
            if(GameWindow.CardsInfo[NumCard].BuyReservedCard){
                GameWindow.WarningText.setText("                  The Card has been Sold! 🫣");
                GameWindow.deleteWarnText.start();
                GameWindow.ChoiceCard=false;
                SwEnabled = 0;
            }

            if (SwEnabled == 1) {
                if (GameWindow.CardsInfo[NumCard].ReservationPlayer2 || GameWindow.CardsInfo[NumCard].ReservationPlayer1) {
                    int choice = JOptionPane.showOptionDialog(null, "Do you want to purchase?", "Confirmation",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Purchase 🛒", "Cancel 😑"}, "Purchase 🛒");
                    if (choice == 0) {
                        CardOperation.SwBuy=true;
                        CardOperation.SwReserve=false;
                        new CardOperation(NumCard, x, y);

                    }else GameWindow.ChoiceCard=false;
                } else {
                    int choice=0,choice2=1;
                    if( Players.playerInfo[Players.Turn].ReservedCardsNum<3) {
                        choice = JOptionPane.showOptionDialog(null, "Do you want to reserve or purchase?", "Confirmation",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Reserve 😌", "Purchase 🛒", "Cancel 😑"}, "Cancel 😑");
                    }else{
                        choice2 = JOptionPane.showOptionDialog(null, "Do you want to reserve or purchase?", "Confirmation",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{ "Purchase 🛒", "Cancel 😑"}, "Purchase 🛒");
                    }
                    if (choice == 0 &&  Players.playerInfo[Players.Turn].ReservedCardsNum<3) {
                        CardOperation.SwBuy=false;
                        CardOperation.SwReserve=true;
                       new CardOperation(NumCard, x, y);
                    }
                    else if (choice == 1 || choice2==0) {
                        CardOperation.SwReserve=false;
                        CardOperation.SwBuy=true;
                        new CardOperation(NumCard, x, y);
                    }else if(choice == 2 || choice2==1){
                        GameWindow.ChoiceCard=false;
                    }
                }
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
