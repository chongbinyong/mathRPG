/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Random;
import mathrpg.GamePanel;

/**
 *
 * @author Chong Bin Yong
 */
public class NPC_Dwarf extends Entity{
    
    public NPC_Dwarf(GamePanel gp)
    {
         super(gp);
         
         direction = "down";
         speed = 1;
         
         getImage();
         setDialogue();
    }
    
    public void getImage()
    {   
        up1 = setup("/npc/dwarf");
        up2 = setup("/npc/dwarf");
        down1 = setup("/npc/dwarf");
        down2 = setup("/npc/dwarf");
        left1 = setup("/npc/dwarf");
        left2 = setup("/npc/dwarf");
        right1 = setup("/npc/dwarf");
        right2 = setup("/npc/dwarf");
    }
    
    public void setDialogue()
    {
        dialogues[0] = "Are you here for the Philosopher's Stone? \nAnswer my questions first.";
    }
    
    public void setAction()
    {
        actionLockCounter++;
        
        if(actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a number from 1 to 100

            if(i <= 25)
            {
                direction = "up";
            }
            if(i > 25 && i <= 50)
            {
                direction = "down";
            }
            if(i > 50 && i <= 75)
            {
                direction = "left";
            }
            if(i > 75 && i <= 100)
            {
                direction = "right";
            }
        }
        
        actionLockCounter = 0;
    }
    
    public void speak()
    {
        // Do this character specific stuff
        super.speak();
    }
}
