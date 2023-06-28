/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import mathrpg.GamePanel;
import mathrpg.KeyHandler;
import mathrpg.UI;
import mathrpg.UtilityTool;

/**
 *
 * @author Chong Bin Yong
 */
public class Player extends Entity{
    
//    GamePanel gp;
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    
    public int hasExcalibur = 0;
    public int hasPStone = 0;
    public int hasHPotion = 1;
    public int hasDPotion = 1;
    
    public int breakSeal = 0;
    public int getHPotion = 0;
    public int getDPotion = 0;
    
    int standCounter = 0;
    
    public ArrayList inventory = new ArrayList();
    public ArrayList itemAmount = new ArrayList();
//    public final int inventorySize = 10;
    
    public String a0 = "";
    public String a1 = "";
    public int b0 = 1;
    public int b1 = 1;
    
    public String clear;
    public int correctMulDec = 0;
    public int totalMulDec = 0;
    public int correctDivDec = 0;
    public int totalDivDec = 0;
    public int correctMulFrac = 0;
    public int totalMulFrac = 0;
    public int correctDivFrac = 0;
    public int totalDivFrac = 0;
    
    public Player(GamePanel gp, KeyHandler keyH)
    {
        super(gp);
//        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        setDefaultValues();
        getPlayerImage();
        setItems();
    }
    
    public void setDefaultValues()
    {
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 44;
        speed = 4;
        direction = "up";
        
        // Player Status
        maxHP = 3;
        currentHP = maxHP;
        
        level = 1;
        attack = 1;
        exp = 0;
        nextLevelExp = 5;
        
        hasExcalibur = 0;
        hasPStone = 0;
        
        breakSeal = 0;
        getHPotion = 0;
        getDPotion = 0;
    }
    
    public void setDefaultPositions()
    {
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 44;
        direction = "up";
    }
    
    public void restoreLife()
    {
        currentHP = maxHP;
    }
    
    public void setItems()
    {
        inventory.clear();
        itemAmount.clear();
        inventory.add("Health Potion");
        itemAmount.add("1");
        inventory.add("Damage Potion");
        itemAmount.add("1");
    }
    
    public void getPlayerImage()
    {
//        try {
//            
//            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
//            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
//            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
//            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
//            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
//            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
//            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
//            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
//            
//        }catch(IOException e) {
//            e.printStackTrace();
//        }
        
        up1 = setup("/player/player_up_1");
        up2 = setup("/player/player_up_2");
        down1 = setup("/player/player_down_1");
        down2 = setup("/player/player_down_2");
        left1 = setup("/player/player_left_1");
        left2 = setup("/player/player_left_2");
        right1 = setup("/player/player_right_1");
        right2 = setup("/player/player_right_2");
    }
    
//    public BufferedImage setup(String imageName) 
//    {
//        
//        UtilityTool uTool = new UtilityTool();
//        BufferedImage image = null;
//        
//        try {
//            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
//            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
//            
//        }catch(IOException e) {
//            e.printStackTrace();
//        }
//        
//        return image;
//    }
    
    public void update()
    {
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.talkPressed == true || keyH.enterPressed == true)
        {
            if(keyH.upPressed == true)
            {
                direction = "up";
            }else if(keyH.downPressed == true)
            {
                direction = "down";
            }else if(keyH.leftPressed == true)
            {
                direction = "left";
            }else if(keyH.rightPressed == true)
            {
                direction = "right";
            }
            
            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            // check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            // CHECK npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            if(npcIndex == 1 || npcIndex == 2 || npcIndex == 3)
            {
                fightNPC(npcIndex);
            }

            // if collision is false, player can move
            if(collisionOn == false && keyH.talkPressed == false && keyH.enterPressed == false) 
            {
                switch(direction)
                {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            
            gp.keyH.talkPressed = false;
            gp.keyH.enterPressed = false;
            
            spriteCounter++;
            if(spriteCounter > 12) // 10 frames
            {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else
        {
            standCounter++;
            
            if(standCounter == 20)
            {
                spriteNum = 1;
                standCounter = 0;
            }
        }
    }
    
    public void pickUpObject(int i)
    {
        if(i != 999)
        {
//            gp.obj[i] = null;
            String objectName = gp.obj[i].name;
            
            switch(objectName)
            {
//                case "Excalibur":
//                    gp.playSE(1);
//                    hasExcalibur++;
//                    gp.obj[i] = null;
////                    System.out.println("You get the Excalibur!");
//                    gp.ui.showMessage("You get the Excalibur!");
//                    break;
//                case "Philosopher's Stone":
//                    gp.playSE(1);
//                    hasPStone++;
//                    gp.obj[i] = null;
////                    System.out.println("You get the Philosopher's Stone!");
//                    gp.ui.showMessage("You get the Philosopher's Stone!");
//                    break;
                case "Seal":
                    if(hasExcalibur == 1 && hasPStone == 1)
                    {
                        gp.playSE(2);
                        gp.obj[i] = null;
                        breakSeal = 1;
//                        System.out.println("You break the seal.");
                        gp.ui.showMessage("You break the seal.");
//                        gp.ui.gameFinished = true;
//                        gp.stopMusic();
//                        gp.playSE(2);
                    }
                    else
                    {
                        gp.ui.showMessage("You need to get the Excalibur and Philosopher's Stone.");
                    }
                    break;
                case "Health Potion":
                    gp.playSE(1);
                    gp.obj[i] = null;
                    getHPotion = 1;
                    String a0 = (String) gp.player.itemAmount.get(0);
                    int amount0 = Integer.parseInt(a0);
                    amount0++;
                    a0 = String.valueOf(amount0);
                    gp.player.itemAmount.set(0, a0);
                    String hasHPotion = (String) gp.player.itemAmount.get(0);
                    gp.player.hasHPotion = Integer.parseInt(hasHPotion);
                    gp.ui.showMessage("You get a health potion!");
                    break;
                case "Damage Potion":
                    gp.playSE(1);
                    gp.obj[i] = null;
                    getDPotion = 1;
                    String a1 = (String) gp.player.itemAmount.get(1);
                    int amount1 = Integer.parseInt(a1);
                    amount1++;
                    a1 = String.valueOf(amount1);
                    gp.player.itemAmount.set(1, a1);
                    String hasDPotion = (String) gp.player.itemAmount.get(1);
                    gp.player.hasDPotion = Integer.parseInt(hasDPotion);
                    gp.ui.showMessage("You get a damage potion!");
                    break;
            }
        }
    }
    
    public void interactNPC(int i)
    {
        if(i != 999)
        {
//            System.out.println("you are hitting an npc!");
            if(gp.keyH.talkPressed == true)
            {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }         
        }
        gp.keyH.talkPressed = false;
    }
    
    public void fightNPC(int i)
    {
        if(i != 999)
        {
            if(gp.keyH.enterPressed == true)
            {
                gp.gameState = gp.combatState;
                a0 = (String) gp.player.itemAmount.get(0);
                a1 = (String) gp.player.itemAmount.get(1);
                gp.stopMusic();
                if(i == 1)
                {
                    gp.playMusic(3);
                }
                else if(i == 2)
                {
                    gp.playMusic(4);
                }
                else if(i == 3)
                {
                    gp.playMusic(5);
                }
//                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
    }
    
    public void checkLevelUp()
    {
        if(exp >= nextLevelExp)
        {
            level++;
            nextLevelExp = nextLevelExp*2;
            maxHP += 2;
            attack++;
            
            if(gp.npc[2].currentHP == 0 || gp.npc[3].currentHP == 0)
            {
                gp.playSE(1);
                gp.gameState = gp.dialogueState;
                gp.ui.currentDialogue = "You are level " + level + " now!\n" + "You feel stronger!";
            }
        }
    }
    
    public void draw(Graphics2D g2)
    {
//        g2.setColor(Color.white);
//        
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        
        BufferedImage image = null;
        
        switch(direction)
        {
            case "up":
                if(spriteNum == 1)
                {
                    image = up1;
                }
                if(spriteNum == 2)
                {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1)
                {
                    image = down1;
                }
                if(spriteNum == 2)
                {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1)
                {
                    image = left1;
                }
                if(spriteNum == 2)
                {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1)
                {
                    image = right1;
                }
                if(spriteNum == 2)
                {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
//        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        
        // show collision area
//        g2.setColor(Color.red);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
