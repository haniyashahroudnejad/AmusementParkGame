package cards;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import main.GameWindow;
import main.StartPanel;


public class CreateCards {
    Random random=new Random();
    ArrayList<Color> colors = new ArrayList<>();
    public static ArrayList<String> iconsLevel1 = new ArrayList<>();
    public static ArrayList<String> iconsLevel2 = new ArrayList<>();
    public static ArrayList<String> iconsLevel3 = new ArrayList<>();
    public int score;
    public String iconName;
    public CardsCoins coin1,coin2,coin3,SpecialCoin;
    public int xPositionCard,yPositionCard,LevelCard,TotalCoinsNum;
    public static int CardValue; //To determine the value of the cards so that they are in range
    public int NumCardPanel;
    public static int zeroScoreCounter;
    public Boolean ReservationPlayer1=false,ReservationPlayer2=false,UsedCard=false,out=false,BuyReservedCard=false;


    public CreateCards(int LevelCard ){
            if(LevelCard==0) PrizeClawCardProduction();
            if(LevelCard==1) LevelOneCardProduction();
            if(LevelCard==2) LevelTwoCardProduction();
            if(LevelCard==3) LevelThreeCardProduction();
    }
    private void setColorList(){ //Creating a list of coin colors so that duplicate colors are not created
            colors.add(Color.BLACK);
            colors.add(Color.BLUE);
            colors.add(Color.RED);
            colors.add(Color.WHITE);
            colors.add(Color.GREEN);
        }
    public static void setIconsLevel1(){
        iconsLevel1.add("1-1.png"); iconsLevel1.add("1-2.png"); iconsLevel1.add("1-3.png"); iconsLevel1.add("1-4.png");
        iconsLevel1.add("1-5.png"); iconsLevel1.add("1-6.png"); iconsLevel1.add("1-7.png"); iconsLevel1.add("1-8.png");
        iconsLevel1.add("1-9.png"); iconsLevel1.add("1-10.png"); iconsLevel1.add("1-11.png"); iconsLevel1.add("1-12.png");
        iconsLevel1.add("1-13.png"); iconsLevel1.add("1-14.png"); iconsLevel1.add("1-15.png");
    }
    public static void setIconsLevel2(){
        iconsLevel2.add("2-1.png"); iconsLevel2.add("2-2.png"); iconsLevel2.add("2-3.png"); iconsLevel2.add("2-4.png");
        iconsLevel2.add("2-5.png"); iconsLevel2.add("2-6.png"); iconsLevel2.add("2-7.png"); iconsLevel2.add("2-8.png");
        iconsLevel2.add("2-9.png"); iconsLevel2.add("2-10.png"); iconsLevel2.add("2-11.png"); iconsLevel2.add("2-12.png");
        iconsLevel2.add("2-13.png"); iconsLevel2.add("2-14.png"); iconsLevel2.add("2-15.png");
    }
    public static void setIconsLevel3(){
        iconsLevel3.add("3-1.png"); iconsLevel3.add("3-2.png"); iconsLevel3.add("3-3.png"); iconsLevel3.add("3-4.png");
        iconsLevel3.add("3-5.png"); iconsLevel3.add("3-6.png"); iconsLevel3.add("3-7.png"); iconsLevel3.add("3-8.png");
        iconsLevel3.add("3-9.png"); iconsLevel3.add("3-10.png"); iconsLevel3.add("3-11.png"); iconsLevel3.add("3-12.png");
        iconsLevel3.add("3-13.png"); iconsLevel3.add("3-14.png"); iconsLevel3.add("3-15.png");
    }

    private void PrizeClawCardProduction(){ //creat cards game
        setColorList();
        score=random.nextInt(2)+3;
        TotalCoinsNum=random.nextInt(2)+2;
        coin1=new CardsCoins(0,2,1,colors,false,score);
        colors.remove(coin1.getColor());
        coin2=new CardsCoins(0,2,2,colors,false,score);
        if(TotalCoinsNum==3){
             colors.remove(coin2.getColor());
             coin3=new CardsCoins(0,TotalCoinsNum,3,colors,false,score);
        }
        LevelCard=0;
        xPositionCard=0;yPositionCard=0;
        GameWindow.CardsInfo[GameWindow.AllCardsCounter]=this;
        GameWindow.AllCardsCounter++;
        iconName="claw.jpg";
        CardValue=0;

    }
    private void LevelOneCardProduction(){
        int zeroCounter;
        setColorList();
        SpecialCoin=new CardsCoins(1,2,0,colors,true,score);
        if(StartPanel.ComputerMode) zeroCounter=4;
        else zeroCounter=3;
        if(zeroScoreCounter<zeroCounter) score=random.nextInt(2);
        else score=1;
        if(score==0) zeroScoreCounter++;
        TotalCoinsNum=random.nextInt(2)+2;
        coin1=new CardsCoins(1,TotalCoinsNum,1,colors,false,score);
        colors.remove(coin1.getColor());
        coin2=new CardsCoins(1,TotalCoinsNum,2,colors,false,score);
        if(TotalCoinsNum==3){
            colors.remove(coin2.getColor());
            coin3=new CardsCoins(1,TotalCoinsNum,3,colors,false,score);
        }
        LevelCard=1;
        xPositionCard=0;yPositionCard=0;
        GameWindow.CardsInfo[GameWindow.AllCardsCounter]=this;
        GameWindow.AllCardsCounter++;
        CardValue=0;
        Collections.shuffle(iconsLevel1);
        String randIconName = iconsLevel1.getFirst();
        iconName= randIconName;
        iconsLevel1.remove(randIconName);

    }
    private void LevelTwoCardProduction(){
        setColorList();
        score=random.nextInt(3)+2;
        if(score==4) TotalCoinsNum=3;
        else  TotalCoinsNum=random.nextInt(2)+2;

        SpecialCoin=new CardsCoins(2,TotalCoinsNum,0,colors,true,score);
        coin1=new CardsCoins(2,TotalCoinsNum,1,colors,false,score);
        colors.remove(coin1.getColor());
        coin2=new CardsCoins(2,TotalCoinsNum,2,colors,false,score);
        if(TotalCoinsNum==3){
            colors.remove(coin2.getColor());
            coin3=new CardsCoins(2,TotalCoinsNum,3,colors,false,score);
        }
        LevelCard=2;
        xPositionCard=0;yPositionCard=0;
        GameWindow.CardsInfo[GameWindow.AllCardsCounter]=this;
        GameWindow.AllCardsCounter++;
        CardValue=0;
        Collections.shuffle(iconsLevel2);
        String randIconName = iconsLevel2.getFirst();
        iconName= randIconName;
        iconsLevel2.remove(randIconName);

    }
    private void LevelThreeCardProduction(){
        setColorList();
        score=random.nextInt(3)+3;
        if(score==5) TotalCoinsNum=3;
        else TotalCoinsNum=random.nextInt(2)+2;
        SpecialCoin=new CardsCoins(3,TotalCoinsNum,0,colors,true,score);
        coin1=new CardsCoins(3,TotalCoinsNum,1,colors,false,score);
        colors.remove(coin1.getColor());
        coin2=new CardsCoins(3,TotalCoinsNum,2,colors,false,score);
        if(TotalCoinsNum==3){
            colors.remove(coin2.getColor());
            coin3=new CardsCoins(3,TotalCoinsNum,3,colors,false,score);
        }
        LevelCard=3;
        xPositionCard=0;yPositionCard=0;
        GameWindow.CardsInfo[GameWindow.AllCardsCounter]=this;
        GameWindow.AllCardsCounter++;
        CardValue=0;
        Collections.shuffle(iconsLevel3);
        String randIconName = iconsLevel3.getFirst();
        iconName= randIconName;
        iconsLevel3.remove(randIconName);

    }
}
