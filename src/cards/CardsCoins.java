package cards;


import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CardsCoins {
    Random random=new Random();
    private int price;
    private Color color;
    private int MaxScore,MinScore;
    public CardsCoins(int levelCard, int TotalCoinsNum, int CoinsNum, ArrayList<Color> colors, boolean SpecialCoins,int score){
        if(!SpecialCoins)  setPrice(levelCard,TotalCoinsNum,CoinsNum,score);
        else price=1;
        setColor(colors);
    }
    private void setMaxMinPrice(int levelCard,int score ){
        if  (levelCard==0) {
            if(score==4){MaxScore=12;MinScore=9;}
            else {MaxScore=10;MinScore=8;}
        }
        else  if(levelCard==1) {
            if(score==1){MaxScore=6;MinScore=4;}
            else {MaxScore=5;MinScore=4;}
        }
        else  if (levelCard==2){
            if(score==4){MaxScore=8;MinScore=7;}
            else {MaxScore=7;MinScore=6;}
        }
        else  if(levelCard==3) {
            if(score==5){ MaxScore = 10;  MinScore = 9;}
            else if(score==4) { MaxScore = 9;  MinScore = 8;}
            else { MaxScore = 8;  MinScore = 7;}
        }
    }

    private void setPrice(int levelCard,int TotalCoinsNum,int CoinsNum,int score) {
        int MinPrice=1 ;
        int MaxPrice ;
        setMaxMinPrice( levelCard, score );
        /*int MinScore=0 ;
        int MaxScore=0 ;
        if  (levelCard==0) {MaxScore=12;MinScore=8;}
        else  if(levelCard==1) {MaxScore=6;MinScore=4;}
        else  if(levelCard==2) {MaxScore=8;MinScore=6;}
        else  if(levelCard==3) {MaxScore=10;MinScore=7;}*/

        if(TotalCoinsNum==2) MaxPrice=MaxScore-1;
        else MaxPrice=MaxScore-2;

        if(CreateCards.CardValue!=0) {
            if(CreateCards.CardValue<MinScore) MinPrice=MinScore-CreateCards.CardValue;
            if(TotalCoinsNum==2)  MaxPrice=MaxScore-CreateCards.CardValue;
            else if(TotalCoinsNum==3 && CoinsNum==2 ) MaxPrice=MaxScore-CreateCards.CardValue-1;
            else if(TotalCoinsNum==3 && CoinsNum==3 ) MaxPrice=MaxScore-CreateCards.CardValue;
        }
        if(MaxPrice==0 && MinPrice==1) {MaxPrice=1 ; MinPrice=1;}
        price = random.nextInt(MaxPrice-MinPrice+1) + MinPrice;
        CreateCards.CardValue+=price;
    }
    public int getPrice() {
        return price;
    }
    private void setColor(ArrayList<Color> colors) {
        int RandomIndex=random.nextInt(colors.size());
        Color RandomColor=colors.get(RandomIndex);
        this.color=RandomColor;
    }
    public Color getColor() {
        return color;
    }
}

