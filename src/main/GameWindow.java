package main;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
//import cards.BuyCard;
import cards.CardOperation;
import cards.CreateCards;
import players.Players;
import slotMachine.Setting;
//import slotMachine.SlotMachine;
import graphics.*;
public class GameWindow {
    Random random=new Random();
    public static JFrame jFrame;
    private final JPanel SlotPanel;
    public static JPanel cardPanel,PrizeClawPanel,SettingCoinSlotMachine,SettingNumberSlotMachine,SlotMachinesPanel1, Player1InformationsPanel, Player2InformationsPanel;
    private final int yLevel1= 410,yLevel2= 600,yLevel3= 790;

    private final DrawCard [] cardPanels =new DrawCard[16];

    public static CreateCards[] CardsInfo =new CreateCards[50]; //all of cards
    public static int AllCardsCounter;
    private int level1Counter,level2Counter,level3Counter;
    public static JLabel TurnText,WarningText;
    public static SlotMachine BlackSlotMachine,GreenSlotMachine,RedSlotMachine,BlueSlotMachine,WhiteSlotMachine,GoldSlotMachine;
    private final SlotMachine[] slotMachines = new SlotMachine[7];
    public static boolean ChoiceSlotMachine=false,ChoiceCard=false,ChoiceCoin=false,finishCard=false;
    public static JLabel prize1Name,prize2Name,prize3Name;
    private int X = 580; //ghost
    private int sw=1;
    public static Timer deleteWarnText = new Timer(6000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            WarningText.setText("");
        }

    });

    public GameWindow(JFrame MainjFrame){

        jFrame=MainjFrame;
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setResizable(false); //prevent frame from being resized
        jFrame.setSize(2000,1100);
        jFrame.setTitle("Amusement park");

        InitializationOfVariables();


        jFrame.setLayout(new FlowLayout(FlowLayout.LEFT));

        cardPanel=new JPanel();
        cardPanel.setPreferredSize(new Dimension(960, 1100));
        cardPanel.setLayout(null);
        cardPanel.setBackground(new Color(114,237,252));

        WarningText = new JLabel();
        WarningText.setFont(new Font("Papyrus", Font.BOLD, 20));
        WarningText.setBounds(150,340, 800, 80);
        cardPanel.add(WarningText);

        TurnText = new JLabel();
        TurnText.setBounds(280,280, 600, 80);
        TurnText.setFont(new Font("Papyrus", Font.BOLD, 60));
        cardPanel.add(TurnText);


        ///////////////////////////////////////////////////////////////////////////// colorfulSquares
        JPanel colorfulSquaresPanel=new ColorfulSquaresPanel(1);
        colorfulSquaresPanel.setBounds(0,188,80,1100);
        cardPanel.add(colorfulSquaresPanel);

        colorfulSquaresPanel=new ColorfulSquaresPanel(2);
        colorfulSquaresPanel.setBounds(80,188,880,80);
        cardPanel.add(colorfulSquaresPanel);

        colorfulSquaresPanel=new ColorfulSquaresPanel(3);
        colorfulSquaresPanel.setBounds(880,188,80,1100);
        colorfulSquaresPanel.setBackground(new Color(114,237,252));
        cardPanel.add(colorfulSquaresPanel);

        colorfulSquaresPanel=new ColorfulSquaresPanel(4);
        colorfulSquaresPanel.setBounds(80,988,880,80);
        cardPanel.add(colorfulSquaresPanel);

        ///////////////////////////////////////////////////////////////////////////////
        JPanel RightPanel=new JPanel();
        RightPanel.setPreferredSize(new Dimension(1000, 1100));
        RightPanel.setLayout(null);
        RightPanel.setBackground(new Color(255,192,203));

        ///////////////////////////////////////////////////////////////////////////////
        PrizeClawPanel=new JPanel();
        PrizeClawPanel.setBounds(0,210,960,180);
        PrizeClawPanel.setBackground(new Color(255,192,203));
        PrizeClawPanel.setLayout(null);
        RightPanel.add(PrizeClawPanel);

        /////////////////////////////////////////////////////////////////////////////// Create cards and card's panel
        CreateCards.setIconsLevel1();
        CreateCards.setIconsLevel2();
        CreateCards.setIconsLevel3();
        ProductionPlayingCards();
        setCardsPanel();

        /////////////////////////////////////////////////////////////////////////////// SlotMachine panel
        SlotMachinesPanel1=new JPanel();
        SlotMachinesPanel1.setBounds(90,410,650,200);
        SlotMachinesPanel1.setLayout(null);
        SlotMachinesPanel1.setBackground(Color.GRAY);
        // SlotMachinesPanel1.setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        RightPanel.add(SlotMachinesPanel1);

        JPanel SlotMachinesPanel2=new JPanel();
        SlotMachinesPanel2.setBounds(15,15,620,170);
        SlotMachinesPanel2.setLayout(null);
        SlotMachinesPanel2.setBackground(new Color(192,192,192));
        SlotMachinesPanel1.add(SlotMachinesPanel2);

        SlotPanel=new JPanel();
        SlotPanel.setBounds(10,10,600,150);
        SlotPanel.setLayout(null);
        SlotPanel.setBackground(Color.WHITE);
        setSlotMachinesPanel();
        SlotMachinesPanel2.add(SlotPanel);

        /////////////////////////////////////////////////////////////////////////////// SlotMachine SettingPanels
        SettingCoinSlotMachine = new JPanel();
        SettingCoinSlotMachine.setBounds(100,638,305,60);
        SettingCoinSlotMachine.setLayout(null);
        SettingCoinSlotMachine.setBackground(Color.GRAY);
        RightPanel.add(SettingCoinSlotMachine);

        SettingNumberSlotMachine = new JPanel();
        SettingNumberSlotMachine.setBounds(430,638,290,60);
        SettingNumberSlotMachine.setLayout(null);
        SettingNumberSlotMachine.setBackground(Color.GRAY);
        RightPanel.add(SettingNumberSlotMachine);

        new Setting();
        /////////////////////////////////////////////////////////////////////////////////////////////park
        for(int i=-10;i<1000;i+=87) {
            JLabel ground = new JLabel();
            ground.setIcon(new ImageIcon("grounds.png"));
            ground.setBounds(i, 900, 100, 100);
            RightPanel.add(ground);
        }
        JPanel groundPanel = new JPanel();
        groundPanel.setBounds(0,950,1000,200);
        groundPanel.setBackground(new Color(119,57,0));
        RightPanel.add(groundPanel);

        JLabel colorWheel = new WheelRotation();
        colorWheel.setBounds(220,760,355,400);
        //colorWheel.setIcon(new ImageIcon("color-wheel.png"));
        RightPanel.add(colorWheel);

        JLabel circus = new JLabel();
        circus.setBounds(80,820,128,128);
        circus.setIcon(new ImageIcon("circus.png"));
        RightPanel.add(circus);

        JLabel coaster = new JLabel();
        coaster.setBounds(700,745,300,300);
        coaster.setIcon(new ImageIcon("roller-coaster.png"));
        RightPanel.add(coaster);

        JLabel castle = new JLabel();
        castle.setBounds(580,730,300,300);
        castle.setIcon(new ImageIcon("castle.png"));
        RightPanel.add(castle);

        JLabel balloon = new JLabel();
        balloon.setBounds(440,650,300,300);
        balloon.setIcon(new ImageIcon("balloon.png"));
        RightPanel.add(balloon);

        JLabel carousel = new JLabel();
        carousel.setBounds(340,730,300,300);
        carousel.setIcon(new ImageIcon("carousel-2.png"));
        RightPanel.add(carousel);

        JLabel train = new JLabel();
        train.setBounds(460,770,300,300);
        train.setIcon(new ImageIcon("train.png"));
        RightPanel.add(train);

        JLabel wheel = new JLabel();
        wheel.setBounds(0,800,200,200);
        wheel.setIcon(new ImageIcon("lion.png"));
        RightPanel.add(wheel);

        JLabel ghost = new JLabel();
        ghost.setBounds(580,720,100,100);
        ghost.setIcon(new ImageIcon("gost.png"));
        RightPanel.add(ghost);


        Timer ghostMove =  new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(X==560)   sw=0;
                if(X==700)   sw=1;
                if(sw==0){
                    X+=5;
                }else  X-=5;



                ghost.setBounds(X,720,100,100);

            }
        });
        ghostMove.start();
        /////////////////////////////////////////////////////////////////////////////////////////// Time
        Timer ReplaceCard = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CardOperation.replace){
                    int i;
                    for(i=1;i<13;i++){
                        if(CardsInfo[CardOperation.NumReplaceCard].NumCardPanel==i ) break; //find which panel
                    }
                    if(i<5) {if(level1Counter==15){finishCard=true;}}
                    else if(i<9) {if(level2Counter==15){finishCard=true;}}
                    else {if(level3Counter==15){finishCard=true;}}

                    if(!finishCard){
                        {cardPanels[i].NumCard=FindRandCard(CardsInfo[CardOperation.NumReplaceCard].LevelCard,CardOperation.xReplaceCard,CardOperation.yReplaceCard);}
                        ReplacementCardInfo(cardPanels[i].NumCard,i);
                    }
                    cardPanels[i].repaint();
                    CardOperation.replace=false;}
            }
        });
        ReplaceCard.start();
        ////////////////////////////////////////////////////////////////////////////
        Timer ReplaceCardComputer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CardOperation.replaceComputer){
                    int i;
                    for(i=1;i<13;i++){
                        if(CardsInfo[CardOperation.NumReplaceCardComputer].NumCardPanel==i ) break; //find which panel
                    }
                    if(i<5) {if(level1Counter==15){finishCard=true;}}
                    else if(i<9) {if(level2Counter==15){finishCard=true;}}
                    else {if(level3Counter==15){finishCard=true;}}

                    if(!finishCard){
                        {cardPanels[i].NumCard=FindRandCard(CardsInfo[CardOperation.NumReplaceCardComputer].LevelCard,CardOperation.xReplaceCardComputer,CardOperation.yReplaceCardComputer);}
                        ReplacementCardInfo(cardPanels[i].NumCard,i);
                    }
                    cardPanels[i].repaint();
                    CardOperation.replaceComputer=false;}
            }
        });
        if(StartPanel.ComputerMode)ReplaceCardComputer.start();
        ///////////////////////////////////////////////////////////////////////////////
        JPanel PlayersInformationsPanel = new JPanel();
        PlayersInformationsPanel.setBounds(0,0,960,190);
        PlayersInformationsPanel.setLayout(null);
        PlayersInformationsPanel.setBackground(Color.WHITE);
        RightPanel.add(PlayersInformationsPanel);

        Player1InformationsPanel = new JPanel();
        Player1InformationsPanel.setBounds(0,0,960,93);
        Player1InformationsPanel.setLayout(null);
        Player1InformationsPanel.setBackground(new Color(255,105,180));
        PlayersInformationsPanel.add(Player1InformationsPanel);

        Player2InformationsPanel = new JPanel();
        Player2InformationsPanel.setBounds(0,93,960,94);
        Player2InformationsPanel.setLayout(null);
        Player2InformationsPanel.setBackground(new Color(144,238,144));
        PlayersInformationsPanel.add(Player2InformationsPanel);

        jFrame.add(cardPanel);
        jFrame.add(RightPanel);
        GameWindow.WarningText.setText("             Hi 👋, Welcom to the game 🎡 🎢 🎠            ");
        GameWindow.deleteWarnText.start();
        new Players(1);
        new Players(2);

        prize1Name = new JLabel();
        prize1Name.setBounds(200,176,100,52);
        prize1Name.setFont(new Font("Arial",Font.BOLD,18));
        prize1Name.setVisible(false);
        RightPanel.add(prize1Name);
        prize2Name = new JLabel();
        prize2Name.setBounds(380,176,100,52);
        prize2Name.setFont(new Font("Arial",Font.BOLD,18));
        prize2Name.setVisible(false);
        RightPanel.add(prize2Name);
        prize3Name = new JLabel();
        prize3Name.setBounds(560,176,100,52);
        prize3Name.setFont(new Font("Arial",Font.BOLD,18));
        prize3Name.setVisible(false);
        RightPanel.add(prize3Name);

        Players.Turn=1;
        TurnText.setFont(new Font("Arial",Font.BOLD,60));
        TurnText.setText(Players.playerInfo[Players.Turn].Name + "'s turn ");


        jFrame.setLocationRelativeTo(null); //window in center
        jFrame.setVisible(true);

    }
    private void InitializationOfVariables(){
        AllCardsCounter=1;
        ChoiceSlotMachine=false;ChoiceCard=false;ChoiceCoin=false ;finishCard=false;
        CardOperation.replace=false;
    }
    private void setSlotMachinesPanel(){
        int xSlot=10,ySlot=10,gap=96;
        slotMachines[1]= new SlotMachine("Black");
        BlackSlotMachine=slotMachines[1];
        slotMachines[2]= new SlotMachine("Green");
        GreenSlotMachine=slotMachines[2];
        slotMachines[3]= new SlotMachine("Red");
        RedSlotMachine=slotMachines[3];
        slotMachines[4]= new SlotMachine("Blue");
        BlueSlotMachine=slotMachines[4];
        slotMachines[5]= new SlotMachine("White");
        WhiteSlotMachine=slotMachines[5];
        slotMachines[6]= new SlotMachine("Gold");
        GoldSlotMachine=slotMachines[6];
        for(int i=1;i<7;i++){
            slotMachines[i].setBounds(xSlot+(i-1)*gap,ySlot,92,ySlot+100);
            slotMachines[i].setBackground(new Color(249,247,164));
            SlotPanel.add(slotMachines[i]);
        }
    }
    private void ProductionPlayingCards(){  //createCards
        for (int Number=0 ; Number<3 ; Number++){ //create PrizeClawCard
            new CreateCards(0);
        }
        for(int Row=1 ; Row<=3 ; Row++){ //create Level 1,2,3 Cards
            for (int Number=1 ; Number<=15 ; Number++){
                new CreateCards(Row);
            }
        }
    }
    private int FindRandCard(int LevelCard,int x,int y){
        int xColumn1= 150,xColumn2= 316,xColumn3= 482,xColumn4= 648;
        int randCard=0;
        if(LevelCard==1){
            do{
                randCard=random.nextInt(15)+4;
            }while (CardsInfo[randCard].UsedCard);
            level1Counter++;
        }else if(LevelCard==2){
            do{
                randCard=random.nextInt(15)+19;
            }while (CardsInfo[randCard].UsedCard);
            level2Counter++;
        }else if(LevelCard==3) {
            do {
                randCard = random.nextInt(15) + 34;
            } while (CardsInfo[randCard].UsedCard);
            level3Counter++;
        }
        CardsInfo[randCard].UsedCard=true;  ////////////////////////// used card
        CardsInfo[randCard].xPositionCard = x;
        CardsInfo[randCard].yPositionCard = y;

        if(x==xColumn1 && y==yLevel1) CardsInfo[randCard].NumCardPanel=1;
        else if(x==xColumn2 && y==yLevel1) CardsInfo[randCard].NumCardPanel=2;
        else if(x==xColumn3 && y==yLevel1) CardsInfo[randCard].NumCardPanel=3;
        else if(x==xColumn4 && y==yLevel1) CardsInfo[randCard].NumCardPanel=4;
        else if(x==xColumn1 && y==yLevel2) CardsInfo[randCard].NumCardPanel=5;
        else if(x==xColumn2 && y==yLevel2) CardsInfo[randCard].NumCardPanel=6;
        else if(x==xColumn3 && y==yLevel2) CardsInfo[randCard].NumCardPanel=7;
        else if(x==xColumn4 && y==yLevel2) CardsInfo[randCard].NumCardPanel=8;
        else if(x==xColumn1 && y==yLevel3) CardsInfo[randCard].NumCardPanel=9;
        else if(x==xColumn2 && y==yLevel3) CardsInfo[randCard].NumCardPanel=10;
        else if(x==xColumn3 && y==yLevel3) CardsInfo[randCard].NumCardPanel=11;
        else if(x==xColumn4 && y==yLevel3) CardsInfo[randCard].NumCardPanel=12;
        return randCard;
    }
    private void setCardsPanel(){
        int CardWidth = 156,CardHeight = 180, NumCard;
        int levelCard, y;
        for(int i=1,x=150;i<13;i++,x+=166){
            if(i<5){levelCard=1;y=yLevel1;}
            else if(i<9){levelCard=2;y=yLevel2;}
            else {levelCard=3;y=yLevel3;}
            NumCard=FindRandCard( levelCard,x,y);
            cardPanels[i] = new DrawCard(x,y,NumCard);
            cardPanels[i].setVisible(true);
            cardPanels[i].setBounds(x,y,CardWidth,CardHeight);
            cardPanel.add(cardPanels[i]);
            if(i%4==0) x=-16;
        }
        int x=160;
        for(int i=13;i<16;i++,x+=180){
            cardPanels[i] = new DrawCard(x,12,i-12);
            cardPanels[i].setBounds(x,12,155,194);
            PrizeClawPanel.add(cardPanels[i]);
        }
    }
    private void ReplacementCardInfo(int randCard,int CardPanelNum){
        int i;
        for( i=1;i<13;i++){
            if(i==CardPanelNum) break;
        }
        cardPanels[i].SpecialCoin = CardsInfo[randCard].SpecialCoin;
        cardPanels[i].coin1 = CardsInfo[randCard].coin1;
        cardPanels[i].coin2 = CardsInfo[randCard].coin2;
        cardPanels[i].coin3 = CardsInfo[randCard].coin3;
        cardPanels[i].Score = CardsInfo[randCard].score;
        cardPanels[i].NumCard=randCard;
        cardPanels[i].iconName=CardsInfo[randCard].iconName;
        cardPanels[i].TotalCoinsNum = CardsInfo[randCard].TotalCoinsNum;
    }

}
