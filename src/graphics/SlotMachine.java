package graphics;


import inputs.SlotMachineMouseInputs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SlotMachine extends JLabel {
    public int  balance;
    private String IconName;
    private final String SlotMachineName;
    private void setIconName(String SlotMachineName){
        if(SlotMachineName.compareTo("Black")==0) this.IconName="BlackSlot.png";
        if(SlotMachineName.compareTo("White")==0) this.IconName="WhiteSlot.png";
        if(SlotMachineName.compareTo("Red")==0)   this.IconName= "RedSlot.png";
        if(SlotMachineName.compareTo("Green")==0) this.IconName= "GreenSlot.png";
        if(SlotMachineName.compareTo("Blue")==0)  this.IconName= "BlueSlot.png";
        if(SlotMachineName.compareTo("Gold")==0)  this.IconName= "GoldSlot.png";

    }

    public SlotMachine(String SlotMachineName){
        setIconName(SlotMachineName);
        this.SlotMachineName=SlotMachineName;

        if(SlotMachineName.compareTo("Gold")==0) balance=5;
        else balance=4;

        if(!SlotMachineName.equals("Gold")) {
            SlotMachineMouseInputs mouseInputs;
            mouseInputs = new SlotMachineMouseInputs(balance,SlotMachineName);
            addMouseListener(mouseInputs);
            addMouseMotionListener(mouseInputs);
        }
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();

            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image slotIcon=new ImageIcon(IconName).getImage();
        g.drawImage(slotIcon,15,15,null);
        g.drawString(String.valueOf(balance),50,65);

    }
}
