package inputs;

import main.Check;
import main.GameWindow;
import players.Players;

import javax.swing.*;
import java.awt.event.*;

public class CoinsMouseInputs implements MouseListener, MouseMotionListener {

    public int NumberCoin,NumberSpecialCoin;
    private final String CoinName;


    public CoinsMouseInputs(int NumberCoin,int NumberSpecialCoin,String CoinName){
        this.NumberCoin=NumberCoin;
        this.NumberSpecialCoin=NumberSpecialCoin;
        this.CoinName=CoinName;

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(GameWindow.ChoiceCoin && !CoinName.equals("Gold")){
            if(CoinName.equals("Black") ) {
                if(Players.playerInfo[Players.Turn].wallet.BlackCoin.NumberCoin!=0)Players.playerInfo[Players.Turn].wallet.BlackCoin.NumberCoin--;
            }
            if(CoinName.equals("Blue") ) {
                if(Players.playerInfo[Players.Turn].wallet.BlueCoin.NumberCoin!=0)Players.playerInfo[Players.Turn].wallet.BlueCoin.NumberCoin--;
            }
            if(CoinName.equals("Red") ){
                if(Players.playerInfo[Players.Turn].wallet.RedCoin.NumberCoin!=0)Players.playerInfo[Players.Turn].wallet.RedCoin.NumberCoin--;
            }
            if(CoinName.equals("White")){
                if(Players.playerInfo[Players.Turn].wallet.WhiteCoin.NumberCoin!=0)Players.playerInfo[Players.Turn].wallet.WhiteCoin.NumberCoin--;
            }
            if(CoinName.equals("Green") ) {
                if(Players.playerInfo[Players.Turn].wallet.GreenCoin.NumberCoin!=0)Players.playerInfo[Players.Turn].wallet.GreenCoin.NumberCoin--;
            }
            Check.CheckCoinsNumber();
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
