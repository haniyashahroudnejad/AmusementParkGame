package main;

import players.Players;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartPanel  {
    JButton [] SubmitButton = new JButton[4];
    JTextField [] textField = new JTextField[4];
    public static String [] PlayersName = new String[3];
    public static String [] CharacterName = new String[3];
    public static boolean ComputerMode;

    int PlayerNum;
    JPopupMenu popupMenuP1,popupMenuP2;
    JMenuItem [] menuItems1  = new JMenuItem [8];
    JMenuItem [] menuItems2  = new JMenuItem [8];
    JLabel startPage,name;
    JFrame jFrame;
    JRadioButton Computer,Player;
    public StartPanel(JFrame jFrame){
        this.jFrame=jFrame;
        CharacterName[1]=null;
        CharacterName[2]=null;
        ComputerMode=false;

        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setResizable(false); //prevent frame from being resized
        jFrame.setSize(2000,1100);

        startPage = new JLabel();
        startPage.setLayout(null);
        startPage.setBounds(0,0,2000,1100);
        jFrame.add(startPage);
        ImageIcon AmusementPark = new ImageIcon("b4.jpg");
        startPage.setIcon(AmusementPark);


        name = new JLabel();
        name.setBounds(120,20,100,20);
        name.setFont(new Font("Times New Roman",Font.ITALIC,30));
        name.setText("Name");
        startPage.add(name);
        ////////////////////////////////////////////////// Name
        int y;
        for(PlayerNum=1;PlayerNum<=2;PlayerNum++) {
            if(PlayerNum==1){PlayersName[PlayerNum]="Player1";y=50;}
            else {PlayersName[PlayerNum] = "Player2";y=110;}

            SubmitButton[PlayerNum] = new JButton("Submit");
            SubmitButton[PlayerNum].setBounds(305, y, 60, 55);

            textField[PlayerNum] = new JTextField();
            textField[PlayerNum].setPreferredSize(new Dimension(240, 40));
            textField[PlayerNum].setBounds(2, y, 300, 50);
            textField[PlayerNum].setFont(new Font("Consolas", Font.PLAIN, 35));
            textField[PlayerNum].setForeground(Color.BLACK);
            textField[PlayerNum].setBackground(Color.WHITE);
            textField[PlayerNum].setCaretColor(Color.BLACK);
            textField[PlayerNum].setText( PlayersName[PlayerNum]);

            startPage.add(textField[PlayerNum]);
            startPage.add(SubmitButton[PlayerNum]);
             ///////////////////////////////////////////////////////////////////////
            SubmitButton[PlayerNum].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == SubmitButton[1]) {
                        PlayerNum=1;
                    }else { PlayerNum=2;}
                        SubmitButton[PlayerNum].setEnabled(false);
                        textField[PlayerNum].setEditable(false);
                        PlayersName[PlayerNum]=textField[PlayerNum].getText();
                }
            });
        }
        ///////////////////////////////////////////////////////////////////////

        JLabel Character = new JLabel();
        Character.setBounds(85,175,150,20);
        Character.setFont(new Font("Times New Roman",Font.ITALIC,30));
        Character.setText("Character");
        startPage.add(Character);

        popupMenuP1=new JPopupMenu();
        popupMenuP2=new JPopupMenu();
        setMenuItem1();
        setMenuItem2();

        JButton CharacterP1 = new JButton();
        CharacterP1.setBounds(2,205,150,50);
        CharacterP1.setText("Player 1");
        startPage.add(CharacterP1);

        CharacterP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenuP1.show(CharacterP1,0,CharacterP1.getHeight());
            }
        });

        JButton CharacterP2 = new JButton();
        CharacterP2.setBounds(155,205,150,50);
        CharacterP2.setText("Player 2");
        startPage.add(CharacterP2);

        CharacterP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenuP2.show(CharacterP2,0,CharacterP2.getHeight());
            }
        });
        for(int i=1;i<8;i++) {
            menuItems1[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource()== menuItems1[1])  CharacterName[1]="1-1A.png";
                    else  if(e.getSource()== menuItems1[2])  CharacterName[1]="1-2A.png";
                    else  if(e.getSource()== menuItems1[3])  CharacterName[1]="1-3A.png";
                    else  if(e.getSource()== menuItems1[4])  CharacterName[1]="1-4A.png";
                    else  if(e.getSource()== menuItems1[5])  CharacterName[1]="1-5A.png";
                    else  if(e.getSource()== menuItems1[6])  CharacterName[1]="1-6A.png";
                    else CharacterName[1]="1-7A.png";
                }
            });
        }
        for(int i=1;i<8;i++) {
            menuItems2[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource()== menuItems2[1])  CharacterName[2]="2-1B.png";
                    else  if(e.getSource()== menuItems2[2])  CharacterName[2]="2-2B.png";
                    else  if(e.getSource()== menuItems2[3])  CharacterName[2]="2-3B.png";
                    else  if(e.getSource()== menuItems2[4])  CharacterName[2]="2-4B.png";
                    else  if(e.getSource()== menuItems2[5])  CharacterName[2]="2-5B.png";
                    else  if(e.getSource()== menuItems2[6])  CharacterName[2]="2-6B.png";
                    else CharacterName[2]="2-7B.png";
                }
            });
        }
        JLabel GameMode = new JLabel();
        GameMode.setBounds(75,268,150,20);
        GameMode.setFont(new Font("Times New Roman",Font.ITALIC,30));
        GameMode.setText("Game Mode");
        startPage.add(GameMode);

        Computer = new JRadioButton("Computer");
        Computer.setFont(new Font("Arial",Font.PLAIN,15));
        Computer.setBounds(50,310,100,20);

        Player = new JRadioButton("2 Players");
        Player.setFont(new Font("Arial",Font.PLAIN,15));
        Player.setBounds(150,310,100,20);

        ButtonGroup group = new ButtonGroup();
        group.add(Computer);
        group.add(Player);

        startPage.add(Computer);
        startPage.add(Player);

        Computer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComputerMode=true;
            }
        });

        Player.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComputerMode=false;
            }
        });
        StartGame();
        jFrame.setVisible(true);
    }
    private void setMenuItem1(){
       menuItems1[1] = new JMenuItem("",new ImageIcon("1-1A.png"));
       menuItems1[2] = new JMenuItem("",new ImageIcon("1-2A.png"));
       menuItems1[3] = new JMenuItem("",new ImageIcon("1-3A.png"));
       menuItems1[4] = new JMenuItem("",new ImageIcon("1-4A.png"));
       menuItems1[5] = new JMenuItem("",new ImageIcon("1-5A.png"));
       menuItems1[6] = new JMenuItem("",new ImageIcon("1-6A.png"));
       menuItems1[7] = new JMenuItem("",new ImageIcon("1-7A.png"));
       for(int i=1;i<8;i++){
           popupMenuP1.add(menuItems1[i]);
       }
    }
    private void setMenuItem2(){
        menuItems2[1] = new JMenuItem("",new ImageIcon("2-1B.png"));
        menuItems2[2] = new JMenuItem("",new ImageIcon("2-2B.png"));
        menuItems2[3] = new JMenuItem("",new ImageIcon("2-3B.png"));
        menuItems2[4] = new JMenuItem("",new ImageIcon("2-4B.png"));
        menuItems2[5] = new JMenuItem("",new ImageIcon("2-5B.png"));
        menuItems2[6] = new JMenuItem("",new ImageIcon("2-6B.png"));
        menuItems2[7] = new JMenuItem("",new ImageIcon("2-7B.png"));
        for(int i=1;i<8;i++){
            popupMenuP2.add(menuItems2[i]);
        }
    }
    private void StartGame(){
        JLabel start =new JLabel ();
        start.setBounds(113,340,80,80);
        start.setIcon(new ImageIcon("Start.png"));
        startPage.add(start);
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.getContentPane().remove(startPage);
                GameWindow gameWindow = new GameWindow(jFrame);
            }
        });
    }
}
