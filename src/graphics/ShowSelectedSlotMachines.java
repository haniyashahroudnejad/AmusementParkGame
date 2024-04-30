package graphics;

import inputs.CoinsMouseInputs;

import javax.swing.*;
import java.awt.*;

public class ShowSelectedSlotMachines extends JLabel {
    public String SlotName;

    public ShowSelectedSlotMachines(String SlotName){
        this.SlotName=SlotName;
    }
    private String setIconName(String SlotName){
        if(SlotName.compareTo("Black")==0) {return "BlackCoin.png";}
        if(SlotName.compareTo("White")==0) {return "WhiteCoin.png";}
        if(SlotName.compareTo("Red")==0)   {return  "RedCoin.png";}
        if(SlotName.compareTo("Green")==0) {return  "GreenCoin.png";}
        if(SlotName.compareTo("Blue")==0)  {return  "BlueCoin.png";}
        if(SlotName.compareTo("questionCoin")==0)  {return  "questionCoin.png";}

        return null;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Image slotIcon=new ImageIcon(setIconName(SlotName)).getImage();
        g.drawImage(slotIcon,0,0,null);

    }
}
