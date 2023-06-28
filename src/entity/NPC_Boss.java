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
public class NPC_Boss extends Entity{
    
    public NPC_Boss(GamePanel gp)
    {
         super(gp);
         
         direction = "down";
         speed = 1;
         
         getImage();
         setDialogue();
    }
    
    public void getImage()
    {   
        up1 = setup("/npc/boss");
        up2 = setup("/npc/boss");
        down1 = setup("/npc/boss");
        down2 = setup("/npc/boss");
        left1 = setup("/npc/boss");
        left2 = setup("/npc/boss");
        right1 = setup("/npc/boss");
        right2 = setup("/npc/boss");
    }
    
    public void setDialogue()
    {
        dialogues[0] = "You want to save the princess? \nDefeat me to prove your strength.";
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
