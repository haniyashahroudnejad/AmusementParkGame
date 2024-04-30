package players;

//import cards.BuyCard;
import cards.CardOperation;
import cards.CreateCards;
import main.Check;
import main.GameWindow;
import slotMachine.Setting;

import java.util.ArrayList;

public class Computer {
    public static boolean ComputerBuy,OpponentBuy,OpponentBuyCard;
    private boolean Done;
    private  ArrayList<Integer> OpponentCanBuy = new ArrayList<Integer>();
    private int SwGetSlotCoin=1;
    public Computer(){
        Done=false;ComputerBuy=false;OpponentBuy=false;OpponentBuyCard=false;CardOperation.SwReserve=false;CardOperation.SwBuy=false;
        CheckHighLevelCards();
        if(!Done) CheckLevel1();
        if(!Done){SwGetSlotCoin=0;GetSlotCoin();}
        Check.UpdateTurn();
        ComputerBuy=false;OpponentBuy=false;OpponentBuyCard=false;
    }
    private void CheckHighLevelCards(){
        int level=3;
        while (!Done) {
            for (int i = 4; i <= 48 ; i++) {
                if (!GameWindow.CardsInfo[i].out && GameWindow.CardsInfo[i].UsedCard && GameWindow.CardsInfo[i].LevelCard == level &&  !GameWindow.CardsInfo[i].ReservationPlayer1 ) {
                    CardOperation.SwBuy=true;
                    CardOperation.SwReserve=false;
                    new CardOperation(i, GameWindow.CardsInfo[i].xPositionCard, GameWindow.CardsInfo[i].yPositionCard);
                    if (ComputerBuy) {
                        System.out.print("buy myself   ");
                        Done=true;
                        break;
                    }
                    ComputerBuy=false;
                    for(int j=0;j<5;j++){
                        if(CardOperation.Deficiency[j]!=0 && CardOperation.Deficiency[j]<4) {GetSlotCoin();if(Done) break;}
                    }
                    OpponentBuy=true;
                    CardOperation.SwBuy=true;
                    CardOperation.SwReserve=false;
                    new CardOperation(i, GameWindow.CardsInfo[i].xPositionCard, GameWindow.CardsInfo[i].yPositionCard);
                    if(OpponentBuyCard){
                        OpponentCanBuy.add(i);
                        OpponentBuy=false;
                        OpponentBuyCard=false;
                    }
                }
            }
            if(level==2) break;
            if (!Done) level=2;
        }

        if (!Done && !OpponentCanBuy.isEmpty() && Players.playerInfo[2].ReservedCardsNum<3) {
            int MaxScore=-1,numCard=0;
            for(int i : OpponentCanBuy){
                if(GameWindow.CardsInfo[i].score>MaxScore){ MaxScore=GameWindow.CardsInfo[i].score;numCard=i;}
            }
            CardOperation.SwBuy=false;
            CardOperation.SwReserve=true;
            new CardOperation(numCard,GameWindow.CardsInfo[numCard].xPositionCard,GameWindow.CardsInfo[numCard].yPositionCard);
            Done=true;
            System.out.print("reserve  ");
            OpponentCanBuy.clear();

        }
    }
    private void CheckLevel1(){
        for (int i = 4; i <= 48; i++) {
            if (!GameWindow.CardsInfo[i].out && GameWindow.CardsInfo[i].UsedCard && GameWindow.CardsInfo[i].LevelCard == 1 &&  GameWindow.CardsInfo[i].score==1  &&  !GameWindow.CardsInfo[i].ReservationPlayer1){
                CardOperation.SwReserve=false;
                CardOperation.SwBuy=true;
                new CardOperation(i, GameWindow.CardsInfo[i].xPositionCard, GameWindow.CardsInfo[i].yPositionCard);
                if (ComputerBuy) {
                    System.out.print(i);
                    Done=true;
                    break;
                }
                for(int j=0;j<5;j++){
                    if( CardOperation.Deficiency[j]<3) {GetSlotCoin();if(Done)break;}
                }
            }
         }
    }
    private void GetSlotCoin() {
        int SwCanGetTowCoin = 1,SwCanGetThreeCoin = 1,SlotCoinNum=2,MinSlotBalance=4;
        String slotColor = "";
        int blackCoin=Players.playerInfo[2].wallet.BlackCoin.NumberCoin;
        int blueCoin=Players.playerInfo[2].wallet.BlueCoin.NumberCoin;
        int redCoin=Players.playerInfo[2].wallet.RedCoin.NumberCoin;
        int greenCoin=Players.playerInfo[2].wallet.GreenCoin.NumberCoin;
        int whiteCoin=Players.playerInfo[2].wallet.WhiteCoin.NumberCoin;
        int sum;
        sum=blackCoin+blueCoin+redCoin+greenCoin+whiteCoin;
        if(sum+3<=10) SwCanGetThreeCoin = 0;
        else if(sum+2<=10) SwCanGetTowCoin=0;

        while ( (SwCanGetThreeCoin==0 || SwCanGetTowCoin==0) && !Done) {
            int NumberCoin = 0;
            for (int j = 0; j < 5; j++) {
                if (CardOperation.Deficiency[j] >= SlotCoinNum || SwGetSlotCoin == 0) {
                    if ((j == 0 || SwGetSlotCoin == 0) && GameWindow.BlackSlotMachine.balance >= MinSlotBalance) {
                        slotColor = "Black";
                        NumberCoin++;
                    } else if ((j == 1 || SwGetSlotCoin == 0) && GameWindow.BlueSlotMachine.balance >= MinSlotBalance) {
                        slotColor = "Blue";
                        NumberCoin++;
                    } else if ((j == 2 || SwGetSlotCoin == 0) && GameWindow.RedSlotMachine.balance >= MinSlotBalance) {
                        slotColor = "Red";
                        NumberCoin++;
                    } else if ((j == 3 || SwGetSlotCoin == 0) && GameWindow.WhiteSlotMachine.balance >= MinSlotBalance) {
                        slotColor = "White";
                        NumberCoin++;
                    } else if ((j == 4 || SwGetSlotCoin == 0) && GameWindow.GreenSlotMachine.balance >= SlotCoinNum) {
                        slotColor = "Green";
                        NumberCoin++;
                    }
                    if (NumberCoin == 1 && SlotCoinNum==1) {
                        Setting.ChoiceSlot1 = slotColor;
                    } else if (NumberCoin == 2 && SlotCoinNum==1) {
                        Setting.ChoiceSlot2 = slotColor;
                    } else if (NumberCoin == 3 && SlotCoinNum==1) {
                        Setting.ChoiceSlot3 = slotColor;
                    }if (NumberCoin == 3) {
                        Setting.GetThreeCoin();
                        Done = true;
                        System.out.print("get 3 coin  ");
                        break;
                    }
                    if(SlotCoinNum==2 &&  NumberCoin==1 ) {
                        Setting.GetTwoCoin(slotColor);
                        Done = true;
                        System.out.print("get 2 coin  ");
                        break;
                    }
                }
            }
            if(!Done && SwCanGetThreeCoin==0) {SlotCoinNum=1;MinSlotBalance=1;}

        }
    }
}
