package cards;

import graphics.DrawCard;
import main.Check;
import main.GameWindow;
import main.StartPanel;
import players.Computer;
import players.Players;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardOperation {
    private final int xSelectedCard , ySelectedCard ;
    int CardScore,Coin1price,Coin2price,Coin3price=0,TotalCoinsNum,LevelCard;
    private int GoldForBlack,GoldForRed,GoldForBlue,GoldForWhite,GoldForGreen;
    Color Coin1Color,Coin2Color,Coin3Color,SpecialCoinColor;
    public static boolean replace=false,replaceComputer=false;
    public static int NumReplaceCard,xReplaceCard,yReplaceCard;
    public static int NumReplaceCardComputer,xReplaceCardComputer,yReplaceCardComputer;
    public Timer MoveUp,MoveLeft,MoveRight;
    public int y1,Minx,MaxX,yDelta,xDelta;
    public static boolean SwBuy=false,SwReserve=false;
    public static int[] Deficiency=new int[6];
    public CardOperation(int NumSelectedCard,int xSelectedCard,int ySelectedCard){
        this.xSelectedCard=xSelectedCard;
        this.ySelectedCard=ySelectedCard;
        if(SwBuy)CardSelectedForPurchase(NumSelectedCard);
        if(SwReserve){ReserveCard(NumSelectedCard);}
        SwBuy=false;SwReserve=false;
    }
    private void CardSelectedForPurchase(int NumCard){  //Selected card number=NumCard
        CardScore= GameWindow.CardsInfo[NumCard].score;
        Coin1Color = GameWindow.CardsInfo[NumCard].coin1.getColor();
        Coin1price = GameWindow.CardsInfo[NumCard].coin1.getPrice();
        Coin2Color = GameWindow.CardsInfo[NumCard].coin2.getColor();
        Coin2price = GameWindow.CardsInfo[NumCard].coin2.getPrice();
        if(NumCard>3)SpecialCoinColor = GameWindow.CardsInfo[NumCard].SpecialCoin.getColor();
        LevelCard =  GameWindow.CardsInfo[NumCard].LevelCard;
        TotalCoinsNum=GameWindow.CardsInfo[NumCard].TotalCoinsNum;
        if(TotalCoinsNum==3) {
            Coin3Color = GameWindow.CardsInfo[NumCard].coin3.getColor();
            Coin3price = GameWindow.CardsInfo[NumCard].coin3.getPrice();
        }
        CheckWalletForBuy(NumCard);

    }
    private void CheckWalletForBuy(int NumCard){
        int turn;
        int SwNum=0;
        if(Players.Turn==1 || Computer.OpponentBuy)turn=1;
        else turn=2;

        int BlackCoin,SpecialBlackCoin;
        int BlueCoin,SpecialBlueCoin;
        int RedCoin,SpecialRedCoin;
        int WhiteCoin,SpecialWhiteCoin;
        int GreenCoin,SpecialGreenCoin;
        int GoldCoin;
        int GoldCoinMain;

        int decreaseGold;

        Color CoinColor;
        int CoinPrice;


        BlackCoin = Players.playerInfo[turn].wallet.BlackCoin.NumberCoin;
        BlueCoin = Players.playerInfo[turn].wallet.BlueCoin.NumberCoin;
        RedCoin = Players.playerInfo[turn].wallet.RedCoin.NumberCoin;
        WhiteCoin = Players.playerInfo[turn].wallet.WhiteCoin.NumberCoin;
        GreenCoin = Players.playerInfo[turn].wallet.GreenCoin.NumberCoin;
        GoldCoin = Players.playerInfo[turn].wallet.GoldCoin.NumberCoin;

        GoldCoinMain=GoldCoin;
        SpecialBlackCoin = Players.playerInfo[turn].wallet.BlackCoin.NumberSpecialCoin;
        SpecialBlueCoin = Players.playerInfo[turn].wallet.BlueCoin.NumberSpecialCoin;
        SpecialRedCoin = Players.playerInfo[turn].wallet.RedCoin.NumberSpecialCoin;
        SpecialWhiteCoin = Players.playerInfo[turn].wallet.WhiteCoin.NumberSpecialCoin;
        SpecialGreenCoin = Players.playerInfo[turn].wallet.GreenCoin.NumberSpecialCoin;
        /////////////////////////////////check can buy card


        for(int i=0;i<3;i++){
            if(i==0) {CoinColor=Coin1Color;CoinPrice=Coin1price;}
            else if(i==1) {CoinColor=Coin2Color;CoinPrice=Coin2price;}
            else{
                if(TotalCoinsNum==3) {CoinColor=Coin3Color;CoinPrice=Coin3price;}
                else break;
            }
            if(CoinColor.equals(Color.BLACK)){
                if(BlackCoin+SpecialBlackCoin>=CoinPrice) { SwNum++;}
                else if(BlackCoin+SpecialBlackCoin+GoldCoin>=CoinPrice) {SwNum++;decreaseGold=(CoinPrice-(BlackCoin+SpecialBlackCoin)); GoldCoin-=decreaseGold;GoldForBlack=decreaseGold;}
                if(StartPanel.ComputerMode) Deficiency[0]=CoinPrice-(BlackCoin+SpecialBlackCoin+GoldCoin);
            }else if(CoinColor.equals(Color.BLUE)){
                if(BlueCoin+SpecialBlueCoin>=CoinPrice) { SwNum++;}
                else if(BlueCoin+SpecialBlueCoin+GoldCoin>=CoinPrice) {SwNum++;decreaseGold=(CoinPrice-(BlueCoin+SpecialBlueCoin)); GoldCoin-=decreaseGold;GoldForBlue=decreaseGold;}
                if(StartPanel.ComputerMode) Deficiency[1]=CoinPrice-(BlueCoin+SpecialBlueCoin+GoldCoin);
            }else if(CoinColor.equals(Color.RED)){
                if(RedCoin+SpecialRedCoin>=CoinPrice) { SwNum++;}
                else if(RedCoin+SpecialRedCoin+GoldCoin>=CoinPrice) {SwNum++;decreaseGold=(CoinPrice-(RedCoin+SpecialRedCoin)); GoldCoin-=decreaseGold;GoldForRed=decreaseGold;}
                if(StartPanel.ComputerMode) Deficiency[2]=CoinPrice-(RedCoin+SpecialRedCoin+GoldCoin);
            }else if(CoinColor.equals(Color.WHITE)){
                if(WhiteCoin+SpecialWhiteCoin>=CoinPrice) { SwNum++;}
                else if(WhiteCoin+SpecialWhiteCoin+GoldCoin>=CoinPrice) { SwNum++;decreaseGold=(CoinPrice-(WhiteCoin+SpecialWhiteCoin)); GoldCoin-=decreaseGold;GoldForWhite=decreaseGold;}
                if(StartPanel.ComputerMode) Deficiency[3]=CoinPrice-(WhiteCoin+SpecialWhiteCoin+GoldCoin);
            }else if(CoinColor.equals(Color.GREEN)){
                if(GreenCoin+SpecialGreenCoin>=CoinPrice) { SwNum++;}
                else if(GreenCoin+SpecialGreenCoin+GoldCoin>=CoinPrice) { SwNum++;decreaseGold=(CoinPrice-(GreenCoin+SpecialGreenCoin)); GoldCoin-=decreaseGold;GoldForGreen=decreaseGold;}
                if(StartPanel.ComputerMode) Deficiency[4]=CoinPrice-(GreenCoin+SpecialGreenCoin+GoldCoin);
            }
        }
        if( (TotalCoinsNum==2 && SwNum==2) || (TotalCoinsNum==3 && SwNum==3) ){ ////////////////////// buy card
            for(int i=0;i<3 && !Computer.OpponentBuy;i++) {
                if(i==0) {CoinColor=Coin1Color;CoinPrice=Coin1price;}
                else if(i==1) {CoinColor=Coin2Color;CoinPrice=Coin2price;}
                else{
                    if(TotalCoinsNum==3) {CoinColor=Coin3Color;CoinPrice=Coin3price;}
                    else break;
                }
                if(CoinColor.equals(Color.BLACK) && SpecialBlackCoin< CoinPrice) {
                    Players.playerInfo[turn].wallet.BlackCoin.NumberCoin-=(CoinPrice-SpecialBlackCoin);
                    if(Players.playerInfo[turn].wallet.BlackCoin.NumberCoin<0) Players.playerInfo[turn].wallet.BlackCoin.NumberCoin=0;
                }else if(CoinColor.equals(Color.BLUE) && SpecialBlueCoin< CoinPrice) {
                    Players.playerInfo[turn].wallet.BlueCoin.NumberCoin-=(CoinPrice-SpecialBlueCoin);
                    if(Players.playerInfo[turn].wallet.BlueCoin.NumberCoin<0) Players.playerInfo[turn].wallet.BlueCoin.NumberCoin=0;
                }else if(CoinColor.equals(Color.RED) && SpecialRedCoin< CoinPrice) {
                    Players.playerInfo[turn].wallet.RedCoin.NumberCoin-=(CoinPrice-SpecialRedCoin);
                    if(Players.playerInfo[turn].wallet.RedCoin.NumberCoin<0) Players.playerInfo[turn].wallet.RedCoin.NumberCoin=0;
                }else if(CoinColor.equals(Color.WHITE) && SpecialWhiteCoin< CoinPrice) {
                    Players.playerInfo[turn].wallet.WhiteCoin.NumberCoin-=(CoinPrice-SpecialWhiteCoin);
                    if(Players.playerInfo[turn].wallet.WhiteCoin.NumberCoin<0) Players.playerInfo[turn].wallet.WhiteCoin.NumberCoin=0;
                }else if(CoinColor.equals(Color.GREEN) && SpecialGreenCoin< CoinPrice) {
                    Players.playerInfo[turn].wallet.GreenCoin.NumberCoin-=(CoinPrice-SpecialGreenCoin);
                    if(Players.playerInfo[turn].wallet.GreenCoin.NumberCoin<0) Players.playerInfo[turn].wallet.GreenCoin.NumberCoin=0;
                }
            }
            if(!Computer.OpponentBuy) {
                GameWindow.WarningText.setText("                            Done \uD83D\uDC4D 👻 \uD83D\uDE0E                        ");
                GameWindow.deleteWarnText.start();
                GameWindow.ChoiceCard = false;
                GameWindow.CardsInfo[NumCard].out = true; /////out card
                IncreaseCoinsSlotMachine(GoldCoinMain - GoldCoin);
                Players.playerInfo[turn].wallet.GoldCoin.NumberCoin -= GoldCoinMain - GoldCoin;
                CalculateCardSpecialCoin();
                CalculateCardScore(NumCard);
            }else  Computer.OpponentBuyCard=true;
        }else {
            GameWindow.ChoiceCard=false;
            if(!StartPanel.ComputerMode || Players.Turn==1) {
                GameWindow.WarningText.setText("Your balance is not enough to buy this card \uD83D\uDE29!");
                GameWindow.deleteWarnText.start();
            }

        }
    }
    private void CalculateCardSpecialCoin(){
        if(SpecialCoinColor.equals(Color.BLACK))   Players.playerInfo[Players.Turn].wallet.BlackCoin.NumberSpecialCoin++;
        else if(SpecialCoinColor.equals(Color.BLUE))   Players.playerInfo[Players.Turn].wallet.BlueCoin.NumberSpecialCoin++;
        else if(SpecialCoinColor.equals(Color.GREEN))   Players.playerInfo[Players.Turn].wallet.GreenCoin.NumberSpecialCoin++;
        else if(SpecialCoinColor.equals(Color.WHITE))   Players.playerInfo[Players.Turn].wallet.WhiteCoin.NumberSpecialCoin++;
        else if(SpecialCoinColor.equals(Color.RED))   Players.playerInfo[Players.Turn].wallet.RedCoin.NumberSpecialCoin++;
    }
    private void CalculateCardScore(int NumCard){
        Players.playerInfo[Players.Turn].PlayerScore.score += GameWindow.CardsInfo[NumCard].score;
        Players.playerInfo[Players.Turn].PurchasedCardsNum++;
        //for ReplaceCard
        if(!GameWindow.CardsInfo[NumCard].ReservationPlayer1 && !GameWindow.CardsInfo[NumCard].ReservationPlayer2) {
            if (!StartPanel.ComputerMode || Players.Turn == 1) {
                xReplaceCard = xSelectedCard;
                yReplaceCard = ySelectedCard;
                NumReplaceCard = NumCard;
                replace = true;
            } else {
                xReplaceCardComputer = xSelectedCard;
                yReplaceCardComputer = ySelectedCard;
                NumReplaceCardComputer = NumCard;
                replaceComputer = true;
            }
        }
        if(GameWindow.CardsInfo[NumCard].ReservationPlayer1 || GameWindow.CardsInfo[NumCard].ReservationPlayer2 ) {GameWindow.CardsInfo[NumCard].BuyReservedCard=true;GameWindow.CardsInfo[NumCard].out=true;}
        if(StartPanel.ComputerMode && Players.Turn==2) {Computer.ComputerBuy=true;}
        else  Check.UpdateTurn();
    }
    private void IncreaseCoinsSlotMachine(int GoldCoin){

        if(Coin1Color.equals(Color.BLACK)) {GameWindow.BlackSlotMachine.balance+=Coin1price-GoldForBlack;}
        else if(Coin1Color.equals(Color.BLUE)){GameWindow.BlueSlotMachine.balance+=Coin1price-GoldForBlue;}
        else if(Coin1Color.equals(Color.RED)) {GameWindow.RedSlotMachine.balance+=Coin1price-GoldForRed;}
        else if(Coin1Color.equals(Color.WHITE)) {GameWindow.WhiteSlotMachine.balance+=Coin1price-GoldForWhite;}
        else if(Coin1Color.equals(Color.GREEN)){GameWindow.GreenSlotMachine.balance+=Coin1price-GoldForGreen;}

        if(Coin2Color.equals(Color.BLACK)){GameWindow.BlackSlotMachine.balance+=Coin2price-GoldForBlack;}
        else if(Coin2Color.equals(Color.BLUE)){GameWindow.BlueSlotMachine.balance+=Coin2price-GoldForBlue;}
        else if(Coin2Color.equals(Color.RED)) {GameWindow.RedSlotMachine.balance+=Coin2price-GoldForRed;}
        else if(Coin2Color.equals(Color.WHITE)) {GameWindow.WhiteSlotMachine.balance+=Coin2price-GoldForWhite;}
        else if(Coin2Color.equals(Color.GREEN)){GameWindow.GreenSlotMachine.balance+=Coin2price-GoldForGreen;}

        if(TotalCoinsNum==3){
            if(Coin3Color.equals(Color.BLACK)){GameWindow.BlackSlotMachine.balance+=Coin3price-GoldForBlack;}
            else if(Coin3Color.equals(Color.BLUE)){GameWindow.BlueSlotMachine.balance+=Coin3price-GoldForBlue;}
            else if(Coin3Color.equals(Color.RED)) {GameWindow.RedSlotMachine.balance+=Coin3price-GoldForRed;}
            else if(Coin3Color.equals(Color.WHITE)) {GameWindow.WhiteSlotMachine.balance+=Coin3price-GoldForWhite;}
            else if(Coin3Color.equals(Color.GREEN)){GameWindow.GreenSlotMachine.balance+=Coin3price-GoldForGreen;}
        }
        if(GoldCoin!=0){GameWindow.GoldSlotMachine.balance+=GoldCoin;}
    }
    private void ReserveCard(int NumCard){
        if(Players.Turn==1 ) GameWindow.CardsInfo[NumCard].ReservationPlayer1 = true;
        else {GameWindow.CardsInfo[NumCard].ReservationPlayer2 = true;}
        Players.playerInfo[Players.Turn].ReservedCardsNum++;
        if (GameWindow.GoldSlotMachine.balance > 0) {
            Players.playerInfo[Players.Turn].wallet.GoldCoin.NumberCoin++;
            GameWindow.GoldSlotMachine.balance--;
        }
        if(Players.Turn==1){
            if(Players.playerInfo[1].ReservedCardsNum==1) Minx=3;
            if(Players.playerInfo[1].ReservedCardsNum==2) Minx=150;
            if(Players.playerInfo[1].ReservedCardsNum==3) Minx=290;
        }else if(Players.Turn==2){
            if(Players.playerInfo[2].ReservedCardsNum==1) MaxX=800;
            if(Players.playerInfo[2].ReservedCardsNum==2) MaxX=650;
            if(Players.playerInfo[2].ReservedCardsNum==3) MaxX=525;
        }

        DrawCard copyCard=new DrawCard(xSelectedCard,ySelectedCard,NumCard);
        yDelta=ySelectedCard;
        MoveUp = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yDelta -=27; // Move the panel up by 1 pixel
                copyCard.setBounds(xSelectedCard,yDelta, 156, 180);
                if (yDelta < 8) {
                    y1=yDelta;
                    MoveUp.stop(); // Stop the timer when panel reaches the top
                }
            }
        });
        xDelta=xSelectedCard;
        MoveLeft = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xDelta -= 1;
                copyCard.setBounds(xDelta, y1, 156, 180);
                if ( xDelta <= Minx) {
                    MoveLeft.stop(); // Stop the timer when panel reaches the top
                }
            }
        });

        MoveRight = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xDelta += 1; // Move the panel up by 1 pixel
                copyCard.setBounds(xDelta, y1, 156, 180);
                if (xDelta >= MaxX) {
                    MoveRight.stop(); // Stop the timer when panel reaches the top
                }
            }
        });

        if(Players.Turn==1 )  {
            MoveUp.start();
            if(Players.playerInfo[1].ReservedCardsNum==3 && GameWindow.CardsInfo[NumCard].xPositionCard==150){MaxX=290;MoveRight.start();}
            else{ MoveLeft.start();}
            GameWindow.cardPanel.add(copyCard);

        }
        else if( Players.Turn==2 ) {
            MoveUp.start();
            if(Players.playerInfo[2].ReservedCardsNum==3 && GameWindow.CardsInfo[NumCard].xPositionCard==648){Minx=510; MoveLeft.start();}
            else{ MoveRight.start();}
            GameWindow.cardPanel.add(copyCard);
        }
        if(!StartPanel.ComputerMode || Players.Turn==1) {
            xReplaceCard = xSelectedCard;
            yReplaceCard = ySelectedCard;
            NumReplaceCard = NumCard;
            replace = true;
        }else {
            xReplaceCardComputer = xSelectedCard;
            yReplaceCardComputer = ySelectedCard;
            NumReplaceCardComputer = NumCard;
            replaceComputer = true;
        }
        GameWindow.WarningText.setText("                            Done👌 🤪😇               ");
        GameWindow.deleteWarnText.start();
        if(!StartPanel.ComputerMode || Players.Turn==1) {
            Check.UpdateTurn();
        }
    }

}


