/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathrpg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import object.OBJ_Excalibur;
import object.OBJ_PStone;
import object.OBJ_HPotion;
import object.OBJ_DPotion;

/**
 *
 * @author Chong Bin Yong
 */
public class UI {
    
    GamePanel gp;
    Graphics2D g2;
//    Font arial_40, arial_80B;
    Font maruMonica;
    BufferedImage excaliburImage;
    BufferedImage pstoneImage;
    BufferedImage hpotionImage;
    String hpotionDesc;
    BufferedImage dpotionImage;
    String dpotionDesc;
    BufferedImage image;
    
    public int difficultyLvl = 0;
    
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0; // time display for message
    public boolean gameFinished = false;
    
    public boolean enemyDamageOn = false;
    public String enemyDamage = "";
    public boolean playerDamageOn = false;
    public String playerDamage = "";
    
    public String currentDialogue = "";
    
    public int commandNum = 0;
    public int titleScreenState = 0; // 0 : the first screen, 1 : the second screen
    public int combatScreenState = 0;
    public int optionScreenState = 0;
    
    public boolean questionOn = false;
    public String question = "";
    public int questionCounter = 0; // time display for question
    public String answer = "";
    
    public int inputCounter = 0; // types of questions (decimal or fraction)
    
    public int slotCol = 0;
    public int slotRow = 0;
    
    public double playTime;
    public double questionTime;
    public double givenTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    
    public UI(GamePanel gp)
    {
        this.gp = gp;
        
//        arial_40 = new Font("Arial", Font.PLAIN, 40);
//        arial_80B = new Font("Arial", Font.BOLD, 80);
        
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        OBJ_Excalibur excalibur = new OBJ_Excalibur(gp);
        excaliburImage = excalibur.image;

        OBJ_PStone pstone = new OBJ_PStone(gp);
        pstoneImage = pstone.image;
        
        OBJ_HPotion hpotion = new OBJ_HPotion(gp);
        hpotionImage = hpotion.image;
        hpotionDesc = hpotion.description;
        
        OBJ_DPotion dpotion = new OBJ_DPotion(gp);
        dpotionImage = dpotion.image;
        dpotionDesc = dpotion.description;
    }
    
    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }
    
    public void showEnemyDamage(String text)
    {
        enemyDamage = text;
        enemyDamageOn = true;
    }
    
    public void showPlayerDamage(String text)
    {
        playerDamage = text;
        playerDamageOn = true;
    }
    
    public void showQuestion()
    {
        if(gp.ui.difficultyLvl == 0)
        {
            questionTime = 90;
        }
        else if(gp.ui.difficultyLvl == 1)
        {
            questionTime = 135;
        }
        else if(gp.ui.difficultyLvl == 2)
        {
            questionTime = 180;
        }
        
        Random random = new Random();
        int i = random.nextInt(100)+1;
        int npcIndex = gp.cChecker.checkEntity(gp.player, gp.npc);
        if(npcIndex == 1 || npcIndex == 2 || npcIndex == 3) // 1 : BOSS 2 : DWARF 3 : ELF
        {
            if(npcIndex == 1)
            {
                if(i <= 25)
                {
                    question = gp.rng.generateDecMul();
                    answer = Float.toString(gp.rng.answerDec);
                    inputCounter = 0;
                }
                if(i > 25 && i <= 50)
                {
                    question = gp.rng.generateDecDiv();
                    answer = Float.toString(gp.rng.answerDec);
                    inputCounter = 1;
                }
                if(i > 50 && i <= 75)
                {
                    question = gp.rng.generateFracMul();
                    answer = Integer.toString(gp.rng.answerFracNume)
                            + "\n—"
                            + "\n" + Integer.toString(gp.rng.answerFracDeno);
                    inputCounter = 2;
                }
                if(i > 75 && i <= 100)
                {
                    question = gp.rng.generateFracDiv();
                    answer = Integer.toString(gp.rng.answerFracNume)
                            + "\n—"
                            + "\n" + Integer.toString(gp.rng.answerFracDeno);
                    inputCounter = 3;
                } 
                questionOn = true;
            }
            else if(npcIndex == 2)
            {
                if(i <= 50)
                {
                    question = gp.rng.generateFracMul();
                    answer = Integer.toString(gp.rng.answerFracNume)
                            + "\n—"
                            + "\n" + Integer.toString(gp.rng.answerFracDeno);
                    inputCounter = 2;
                }
                if(i > 50 && i <= 100)
                {
                    question = gp.rng.generateFracDiv();
                    answer = Integer.toString(gp.rng.answerFracNume)
                            + "\n—"
                            + "\n" + Integer.toString(gp.rng.answerFracDeno);
                    inputCounter = 3;
                }
                questionOn = true;  
            }
            else if(npcIndex == 3)
            {
                if(i <= 50)
                {
                    question = gp.rng.generateDecMul();
                    answer = Float.toString(gp.rng.answerDec);
                    inputCounter = 0;
                }
                if(i > 50 && i <= 100)
                {
                    question = gp.rng.generateDecDiv();
                    answer = Float.toString(gp.rng.answerDec);
                    inputCounter = 1;
                }
                questionOn = true;
            }
        }
    }
    
    public void showSpecQuestion(int i)
    {
        if(gp.ui.difficultyLvl == 0)
        {
            questionTime = 90;
        }
        else if(gp.ui.difficultyLvl == 1)
        {
            questionTime = 135;
        }
        else if(gp.ui.difficultyLvl == 2)
        {
            questionTime = 180;
        }
        
        int index = i;
        if(index == 0)
        {
            question = gp.rng.generateDecMul();
            answer = Float.toString(gp.rng.answerDec);
            inputCounter = 0;
        }
        else if(index == 1)
        {
            question = gp.rng.generateDecDiv();
            answer = Float.toString(gp.rng.answerDec);
            inputCounter = 1;
        }
        else if(index == 2)
        {
            question = gp.rng.generateFracMul();
            answer = Integer.toString(gp.rng.answerFracNume)
                    + "\n—"
                    + "\n" + Integer.toString(gp.rng.answerFracDeno);
            inputCounter = 2;
        }
        else if(index == 3)
        {
            question = gp.rng.generateFracDiv();
            answer = Integer.toString(gp.rng.answerFracNume)
                    + "\n—"
                    + "\n" + Integer.toString(gp.rng.answerFracDeno);
            inputCounter = 3;
        }
        questionOn = true;
    }
    
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        
        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
        
        // TITLE STATE
        if(gp.gameState == gp.titleState)
        {
            drawTitleScreen();
        }
        
        // PLAY STATE
        if(gp.gameState == gp.playState)
        {
            // Do playState stuff later
            drawPlayScreen();
            playTime += (double)1/60;
        }
        // PAUSE STATE
        if(gp.gameState == gp.optionState)
        {
            drawOptionScreen();
        }
        
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState)
        {
            drawDialogueScreen();
            playTime += (double)1/60;
        }
        
        // COMBAT STATE
        if(gp.gameState == gp.combatState)
        {
            drawCombatScreen();
            playTime += (double)1/60;
        }
        
        // CHARACTER STATE
        if(gp.gameState == gp.characterState)
        {
            drawCharacterScreen();
            drawInventoryScreen();
            playTime += (double)1/60;
        }
        
        // VICTORY STATE
        if(gp.gameState == gp.victoryState)
        {
            drawVictoryScreen();
        }
        
        // CHARACTER STATE
        if(gp.gameState == gp.gameoverState)
        {
            drawGameOverScreen();
        }
        
//        if(gameFinished == true)
//        {
//            g2.setFont(arial_40);
//            g2.setColor(Color.white);
//            
//            String text;
//            int textLength;
//            int x;
//            int y;
//            
//            text = "You are ready to fight the BOSS!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 - (gp.tileSize*3);
//            g2.drawString(text, x, y);
//            
//            text = "Your time is: " + dFormat.format(playTime) + "!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + (gp.tileSize*4);
//            g2.drawString(text, x, y);
//            
//            g2.setFont(arial_80B);
//            g2.setColor(Color.yellow);
//            
//            text = "Congratulations!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + (gp.tileSize*2);
//            g2.drawString(text, x, y);
//            
//            gp.gameThread = null; // stop the thread
//        }
//        else
//        {
//            g2.setFont(arial_40);
//            g2.setColor(Color.white);
    //        g2.drawString("Excalibur: " + gp.player.hasExcalibur, 25, 50);
    //        g2.drawString("Philosopher's Stone: " + gp.player.hasPStone, 25, 100);
//            g2.drawImage(excaliburImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
//            g2.drawString("x " + gp.player.hasExcalibur, 78, 65);
//            g2.drawImage(pstoneImage, gp.tileSize/2, gp.tileSize + gp.tileSize, gp.tileSize, gp.tileSize, null);
//            g2.drawString("x " + gp.player.hasPStone, 78, 135);
//            
//            // TIME
//            playTime += (double)1/60;
//            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);
//
//            // MESSAGE
//            if(messageOn == true)
//            {
//                g2.setFont(g2.getFont().deriveFont(30F));
//                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
//
//                messageCounter++;
//
//                if(messageCounter > 120)
//                {
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
//        }
    }
    
    public void drawTitleScreen()
    {
        if(titleScreenState == 0)
        {
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Math RPG";
            int x = getXforCenteredText(text);
            int y = gp.tileSize + 64;

            // SHADOW
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);

            // MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // IMAGE
            x = gp.screenWidth/2 - (gp.tileSize *2) / 2;
            y += gp.tileSize * 1.5;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
            
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/menu/decimal.png"));
            }catch(IOException e) {
                e.printStackTrace();
            }
            g2.drawImage(image, x-(gp.tileSize*6), y, gp.tileSize*2, gp.tileSize*2, null);
            
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/menu/multiplication.png"));
            }catch(IOException e) {
                e.printStackTrace();
            }
            g2.drawImage(image, x-(gp.tileSize*3), y, gp.tileSize*2, gp.tileSize*2, null);
            
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/menu/division.png"));
            }catch(IOException e) {
                e.printStackTrace();
            }
            g2.drawImage(image, x+(gp.tileSize*3), y, gp.tileSize*2, gp.tileSize*2, null);
            
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/menu/fraction.png"));
            }catch(IOException e) {
                e.printStackTrace();
            }
            g2.drawImage(image, x+(gp.tileSize*6), y, gp.tileSize*2, gp.tileSize*2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize*4;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "HOW TO PLAY";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 3)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            g2.setFont(g2.getFont().deriveFont(28F));
            x = gp.tileSize/2;
            y = (int) (gp.tileSize*9.5);
            int width = gp.tileSize;
            int height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawString("W", gp.tileSize/2+13, gp.tileSize*10+10);
            
            x = gp.tileSize/2+gp.tileSize;
            y = (int) (gp.tileSize*9.5);
            drawSubWindow(x, y, width, height);
            g2.drawString("S", gp.tileSize/2+gp.tileSize+15, gp.tileSize*10+10);
            
            x = gp.tileSize/2;
            y = (int) (gp.tileSize*10.5);
            width = gp.tileSize*2;
            drawSubWindow(x, y, width, height);
            g2.drawString("Enter", gp.tileSize/2+18, gp.tileSize*11+10);
        }
        else if(titleScreenState == 1)
        {
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            // DIFFICULTY SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            
            String text = "Select Difficulty:";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);
            
            text = "Easy";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2.5;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Normal";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Hard";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if(commandNum == 3)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if(titleScreenState == 2)
        {
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            // STORY INTRODUCTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(28F));
            
            String text = "This is a world that believe knowledge is power. One day, the "
                    + "\nDemon King kidnapped the princess to threaten the king. The king "
                    + "\nannounced that as long as someone could defeat the Demon King "
                    + "\nand save the princess, he would give great wealth and betroth the "
                    + "\nprincess to the hero. You accidentally heard that the Excalibur and "
                    + "\nthe Philosopher's Stone that can defeat the Demon King are in "
                    + "\nForest of Spirits and Dwarven Mine. As an adventurer, you embark "
                    + "\non the journey to defeat the Demon King.";
            int x = gp.tileSize;
            int y = 78;
//            g2.drawString(text, x, y);
            for(String line : text.split("\n"))
            {
//                x = getXforCenteredText(line);
                g2.drawString(line, x, y);
                y += 40;
            }
            
            try {
                    image = ImageIO.read(getClass().getResourceAsStream("/npc/boss.png"));
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
            g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2, null);
            text = "Demon King";
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            
            try {
                    image = ImageIO.read(getClass().getResourceAsStream("/npc/princess.png"));
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
            g2.drawImage(image, x+gp.tileSize*3, y-gp.tileSize*3, gp.tileSize*2, gp.tileSize*2, null);
            text = "Princess";
            x += gp.tileSize*3;
            g2.drawString(text, x, y);
            
            try {
                    image = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
            g2.drawImage(image, x+gp.tileSize*3, y-gp.tileSize*3, gp.tileSize*2, gp.tileSize*2, null);
            text = "Adventurer (You)";
            x += gp.tileSize*3;
            g2.drawString(text, x, y);
            
            text = "Press Enter "
                    + "\nto Continue";
            x = gp.tileSize * 13;
            y = gp.tileSize * 10;
            for(String line : text.split("\n"))
            {      
                g2.drawString(line, x, y);
                y += 40;
            }
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, gp.tileSize*10);
            }
        }
        else if(titleScreenState == 3)
        {
            // load game
        }
        else if(titleScreenState == 4)
        {
            // how to play
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            
            String text = "How To Play:";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);
            
            text = "Game Control";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2.5;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Question Examples";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if(commandNum == 2)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if(titleScreenState == 5)
        {
            // Game Control
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(38F));
            
            String text = "Game Control";
            int x = gp.tileSize * 2;
            int y = gp.tileSize;
            g2.drawString(text, x, y);
            
            g2.setFont(g2.getFont().deriveFont(32F));
            text = "Move Up";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.5;
            g2.drawString(text, x, y);
            text = "W";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Move Left";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "A";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Move Down";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "S";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Move Right";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "D";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Confirm / Fight";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "Enter";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Talk";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "E";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Character Screen";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "Q";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Options Menu";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "Esc";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            g2.setFont(g2.getFont().deriveFont(38F));
            text = "Back";
            x = gp.tileSize * 13;
            y = gp.tileSize * 11;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            g2.setFont(g2.getFont().deriveFont(24F));
            String note = "*Most controls are using keyboard and,\n mouse only uses when typing answer";
            x = gp.tileSize * 2;
            y = (int) (gp.tileSize * 10.5);
            for(String line : note.split("\n"))
            {
                g2.drawString(line, x, y);
                y += 30;
            }
        }
        else if(titleScreenState == 6)
        {
            // Question Examples
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(38F));
            
            String text = "Question Examples";
            int x = gp.tileSize * 2;
            int y = gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Easy";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.5;
            g2.drawString(text, x, y);
            text = "• 1.7 × 6.8 = 11.56";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "• 5.6 ÷ 6.3 = 0.89";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "    3      2       3";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.2;
            g2.drawString(text, x, y);
            text = "• — × —    = —";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "    8      5      20";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "    6      8       9";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.2;
            g2.drawString(text, x, y);
            text = "• — ÷ —    = —";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "    8      3      32";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Next";
            x = gp.tileSize * 13;
            y = gp.tileSize * 11;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            g2.setFont(g2.getFont().deriveFont(24F));
            String note = "*Final answer should round \nto the second decimal place";
            x = gp.tileSize * 10;
            y = (int) (gp.tileSize * 1.5);
            for(String line : note.split("\n"))
            {
                g2.drawString(line, x, y);
                y += 30;
            }
        }
        else if(titleScreenState == 7)
        {
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(38F));
            
            String text = "Question Examples";
            int x = gp.tileSize * 2;
            int y = gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Normal";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.5;
            g2.drawString(text, x, y);
            text = "• 1.7 × 6.8 × 2.2 = 25.43";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "• 15.6 ÷ 6.3 ÷ 2.1 = 1.18";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "    3      2      9      27";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.2;
            g2.drawString(text, x, y);
            text = "• — × — × —    = —";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "    8      5      4      80";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "    6      8      9      5";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.2;
            g2.drawString(text, x, y);
            text = "• — ÷ — ÷ —    = —";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "    8      3     10     16";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Next";
            x = gp.tileSize * 13;
            y = gp.tileSize * 11;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            g2.setFont(g2.getFont().deriveFont(24F));
            String note = "*Final answer should round \nto the second decimal place";
            x = gp.tileSize * 10;
            y = (int) (gp.tileSize * 1.5);
            for(String line : note.split("\n"))
            {
                g2.drawString(line, x, y);
                y += 30;
            }
        }
        else if(titleScreenState == 8)
        {
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(38F));
            
            String text = "Question Examples";
            int x = gp.tileSize * 2;
            int y = gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Hard";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.5;
            g2.drawString(text, x, y);
            text = "• 1.7 × 6.8 × 2.2 × 7.2 = 183.11";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "• 105.6 ÷ 6.3 ÷ 2.1 ÷ 2.0 = 3.99";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "    3      2      9      8      3";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.2;
            g2.drawString(text, x, y);
            text = "• — × — × — × —    = —";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "    8      5      4      9     10";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "    6      8      9      7      5";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.2;
            g2.drawString(text, x, y);
            text = "• — ÷ — ÷ — ÷ —    = —";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "    8      3     10      4     28";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Back";
            x = gp.tileSize * 13;
            y = gp.tileSize * 11;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            g2.setFont(g2.getFont().deriveFont(24F));
            String note = "*Final answer should round \nto the second decimal place";
            x = gp.tileSize * 10;
            y = (int) (gp.tileSize * 1.5);
            for(String line : note.split("\n"))
            {
                g2.drawString(line, x, y);
                y += 30;
            }
        }
    }
    
    public void drawPlayScreen() 
    {
        g2.setFont(g2.getFont().deriveFont(40F));
        g2.setColor(Color.white);
//        g2.drawString("Excalibur: " + gp.player.hasExcalibur, 25, 50);
//        g2.drawString("Philosopher's Stone: " + gp.player.hasPStone, 25, 100);
        g2.drawImage(excaliburImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasExcalibur, 78, 65);
        g2.drawImage(pstoneImage, gp.tileSize/2, gp.tileSize + gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasPStone, 78, 135);
        
        g2.setFont(g2.getFont().deriveFont(32F));
        int x = gp.tileSize*14;
        int y = gp.tileSize/2;
        int width = (int) (gp.tileSize*1.5);
        int height = (int) (gp.tileSize*1.2);
        drawSubWindow(x, y, width, height);
        g2.drawString("Esc", gp.tileSize*14+15, gp.tileSize+15);

//        // TIME
//        playTime += (double)1/60;
//        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);

        // MESSAGE
        if(messageOn == true)
        {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

            messageCounter++;

            if(messageCounter > 120)
            {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
    
    public void drawOptionScreen() 
    {
//        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
//        String text = "PAUSED";
//        int x = getXforCenteredText(text);
//        int y = gp.screenHeight/2;
//        
//        g2.drawString(text, x, y);
        if(optionScreenState == 0)
        {   
            // SUB WINDOW
            int frameX = gp.tileSize*4;
            int frameY = gp.tileSize;
            int frameWidth = gp.tileSize*8;
            int frameHeight = gp.tileSize*10;
            drawSubWindow(frameX, frameY, frameWidth, frameHeight);
            
            int textX;
            int textY;
            
            // TITLE
            g2.setColor(Color.black);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
            String text = "Options";
            textX = getXforCenteredText(text);
            textY = 42;
            g2.drawString(text, textX, textY);
            
            // SAVE
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            textX = frameX + gp.tileSize;
            textY = frameY + gp.tileSize;
            g2.drawString("Save", textX, textY);
            if(commandNum == 0)
            {
                g2.drawString(">", textX-25, textY);
            }
            
            // MUSIC
            textX = frameX + gp.tileSize;
            textY += gp.tileSize;
            g2.drawString("Music", textX, textY);
            if(commandNum == 1)
            {
                g2.drawString(">", textX-25, textY);
            }
            
            // SE
            textX = frameX + gp.tileSize;
            textY += gp.tileSize;
            g2.drawString("Sound Effect", textX, textY);
            if(commandNum == 2)
            {
                g2.drawString(">", textX-25, textY);
            }
            
            // DIFFICULTY
            textX = frameX + gp.tileSize;
            textY += gp.tileSize;
            g2.drawString("Difficulty Level", textX, textY);
            if(commandNum == 3)
            {
                g2.drawString(">", textX-25, textY);
            }
            
            // GAME STORY
            textX = frameX + gp.tileSize;
            textY += gp.tileSize;
            g2.drawString("Game Story", textX, textY);
            if(commandNum == 4)
            {
                g2.drawString(">", textX-25, textY);
            }
            
            // GAME CONTROL
            textX = frameX + gp.tileSize;
            textY += gp.tileSize;
            g2.drawString("Game Control", textX, textY);
            if(commandNum == 5)
            {
                g2.drawString(">", textX-25, textY);
            }
            
            // QUESTION EXAMPLES
            textX = frameX + gp.tileSize;
            textY += gp.tileSize;
            g2.drawString("Question Examples", textX, textY);
            if(commandNum == 6)
            {
                g2.drawString(">", textX-25, textY);
            }
            
            // END GAME
            textX = frameX + gp.tileSize;
            textY += gp.tileSize;
            g2.drawString("End Game", textX, textY);
            if(commandNum == 7)
            {
                g2.drawString(">", textX-25, textY);
            }
            
            // BACK
            textX = frameX + gp.tileSize;
            textY += (gp.tileSize+10);
            g2.drawString("Back", textX, textY);
            if(commandNum == 8)
            {
                g2.drawString(">", textX-25, textY);
            }
            
            textX = frameX + gp.tileSize*5;
            textY = frameY + 24;
            g2.setStroke(new BasicStroke(3));
            
            // MUSIC VOLUME
            textY += gp.tileSize;
            g2.drawRect(textX, textY, 120, 24);
            int volumeWidth = 24 * gp.music.volumeScale;
            g2.fillRect(textX, textY, volumeWidth, 24);
            
            // SE VOLUME
            textY += gp.tileSize;
            g2.drawRect(textX, textY, 120, 24);
            volumeWidth = 24 * gp.se.volumeScale;
            g2.fillRect(textX, textY, volumeWidth, 24);
            
            g2.setFont(g2.getFont().deriveFont(28F));
            int x = gp.tileSize/2+gp.tileSize-5;
            int y = (int) (gp.tileSize*8.5);
            int width = gp.tileSize;
            int height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawString("W", gp.tileSize/2+gp.tileSize+10, gp.tileSize*9+10);
            
            x = gp.tileSize/2-5;
            y = (int) (gp.tileSize*9.5);
            drawSubWindow(x, y, width, height);
            g2.drawString("A", gp.tileSize/2+13, gp.tileSize*10+10);
            
            x = gp.tileSize/2+gp.tileSize-5;
            y = (int) (gp.tileSize*9.5);
            drawSubWindow(x, y, width, height);
            g2.drawString("S", gp.tileSize/2+gp.tileSize+13, gp.tileSize*10+10);

            x = gp.tileSize/2+gp.tileSize*2-5;
            y = (int) (gp.tileSize*9.5);
            drawSubWindow(x, y, width, height);
            g2.drawString("D", gp.tileSize/2+gp.tileSize*2+13, gp.tileSize*10+10);
            
            x = gp.tileSize-gp.tileSize/2+15;
            y = (int) (gp.tileSize*10.5);
            width = gp.tileSize*2;
            drawSubWindow(x, y, width, height);
            g2.drawString("Enter", gp.tileSize+15, gp.tileSize*11+10);
        }
        else if(optionScreenState == 1)
        {
            // SAVE
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(38F));
            
            String text = "The progress has been saved.";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*5;
            g2.drawString(text, x, y);
            
            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Quit";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if(optionScreenState == 2)
        {
            // DIFFICULTY
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            // DIFFICULTY SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            
            String text = "Select Difficulty:";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);
            
            text = "Easy";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2.5;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Normal";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Hard";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if(commandNum == 3)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if(optionScreenState == 3)
        {
            // GAME STORY
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            // STORY INTRODUCTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(28F));
            
            String text = "This is a world that believe knowledge is power. One day, the "
                    + "\nDemon King kidnapped the princess to threaten the king. The king "
                    + "\nannounced that as long as someone could defeat the Demon King "
                    + "\nand save the princess, he would give great wealth and betroth the "
                    + "\nprincess to the hero. You accidentally heard that the Excalibur and "
                    + "\nthe Philosopher's Stone that can defeat the Demon King are in "
                    + "\nForest of Spirits and Dwarven Mine. As an adventurer, you embark "
                    + "\non the journey to defeat the Demon King.";
            int x = gp.tileSize;
            int y = 78;
//            g2.drawString(text, x, y);
            for(String line : text.split("\n"))
            {
//                x = getXforCenteredText(line);
                g2.drawString(line, x, y);
                y += 40;
            }
            
            try {
                    image = ImageIO.read(getClass().getResourceAsStream("/npc/boss.png"));
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
            g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2, null);
            text = "Demon King";
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            
            try {
                    image = ImageIO.read(getClass().getResourceAsStream("/npc/princess.png"));
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
            g2.drawImage(image, x+gp.tileSize*3, y-gp.tileSize*3, gp.tileSize*2, gp.tileSize*2, null);
            text = "Princess";
            x += gp.tileSize*3;
            g2.drawString(text, x, y);
            
            try {
                    image = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
            g2.drawImage(image, x+gp.tileSize*3, y-gp.tileSize*3, gp.tileSize*2, gp.tileSize*2, null);
            text = "Adventurer (You)";
            x += gp.tileSize*3;
            g2.drawString(text, x, y);
            
            text = "Back";
            x = gp.tileSize * 14;
            y = gp.tileSize * 11;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-25, y);
            }
        }
        else if(optionScreenState == 4)
        {
            // GAME CONTROL
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(38F));
            
            String text = "Game Control";
            int x = gp.tileSize * 2;
            int y = gp.tileSize;
            g2.drawString(text, x, y);
            
            g2.setFont(g2.getFont().deriveFont(32F));
            text = "Move Up";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.5;
            g2.drawString(text, x, y);
            text = "W";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Move Left";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "A";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Move Down";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "S";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Move Right";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "D";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Confirm / Fight";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "Enter";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Talk";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "E";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Character Screen";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "Q";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            text = "Options Menu";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "Esc";
            x = gp.tileSize * 10;
            g2.drawString(text, x, y);
            
            g2.setFont(g2.getFont().deriveFont(38F));
            text = "Back";
            x = gp.tileSize * 13;
            y = gp.tileSize * 11;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            g2.setFont(g2.getFont().deriveFont(24F));
            String note = "*Most controls are using keyboard and,\n mouse only uses when typing answer";
            x = gp.tileSize * 2;
            y = (int) (gp.tileSize * 10.5);
            for(String line : note.split("\n"))
            {
                g2.drawString(line, x, y);
                y += 30;
            }
        }
        else if(optionScreenState == 5)
        {
            // QUESTION EXAMPLES
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(38F));
            
            String text = "Question Examples";
            int x = gp.tileSize * 2;
            int y = gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Easy";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.5;
            g2.drawString(text, x, y);
            text = "• 1.7 × 6.8 = 11.56";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "• 5.6 ÷ 6.3 = 0.89";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "    3      2       3";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.2;
            g2.drawString(text, x, y);
            text = "• — × —    = —";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "    8      5      20";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "    6      8       9";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.2;
            g2.drawString(text, x, y);
            text = "• — ÷ —    = —";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "    8      3      32";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Next";
            x = gp.tileSize * 13;
            y = gp.tileSize * 11;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            g2.setFont(g2.getFont().deriveFont(24F));
            String note = "*Final answer should round \nto the second decimal place";
            x = gp.tileSize * 10;
            y = (int) (gp.tileSize * 1.5);
            for(String line : note.split("\n"))
            {
                g2.drawString(line, x, y);
                y += 30;
            }
        }
        else if(optionScreenState == 6)
        {
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(38F));
            
            String text = "Question Examples";
            int x = gp.tileSize * 2;
            int y = gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Normal";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.5;
            g2.drawString(text, x, y);
            text = "• 1.7 × 6.8 × 2.2 = 25.43";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "• 15.6 ÷ 6.3 ÷ 2.1 = 1.18";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "    3      2      9      27";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.2;
            g2.drawString(text, x, y);
            text = "• — × — × —    = —";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "    8      5      4      80";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "    6      8      9      5";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.2;
            g2.drawString(text, x, y);
            text = "• — ÷ — ÷ —    = —";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "    8      3     10     16";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Next";
            x = gp.tileSize * 13;
            y = gp.tileSize * 11;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            g2.setFont(g2.getFont().deriveFont(24F));
            String note = "*Final answer should round \nto the second decimal place";
            x = gp.tileSize * 10;
            y = (int) (gp.tileSize * 1.5);
            for(String line : note.split("\n"))
            {
                g2.drawString(line, x, y);
                y += 30;
            }
        }
        else if(optionScreenState == 7)
        {
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(38F));
            
            String text = "Question Examples";
            int x = gp.tileSize * 2;
            int y = gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Hard";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.5;
            g2.drawString(text, x, y);
            text = "• 1.7 × 6.8 × 2.2 × 7.2 = 183.11";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "• 105.6 ÷ 6.3 ÷ 2.1 ÷ 2.0 = 3.99";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "    3      2      9      8      3";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.2;
            g2.drawString(text, x, y);
            text = "• — × — × — × —    = —";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "    8      5      4      9     10";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "    6      8      9      7      5";
            x = gp.tileSize * 2;
            y += gp.tileSize * 1.2;
            g2.drawString(text, x, y);
            text = "• — ÷ — ÷ — ÷ —    = —";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            text = "    8      3     10      4     28";
            x = gp.tileSize * 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Back";
            x = gp.tileSize * 13;
            y = gp.tileSize * 11;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            g2.setFont(g2.getFont().deriveFont(24F));
            String note = "*Final answer should round \nto the second decimal place";
            x = gp.tileSize * 10;
            y = (int) (gp.tileSize * 1.5);
            for(String line : note.split("\n"))
            {
                g2.drawString(line, x, y);
                y += 30;
            }
        }
        else if(optionScreenState == 8)
        {
            // END GAME
            g2.setColor(new Color(30, 144, 255)); // dodgerblue
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(38F));
            
            String text = "Quit the game and return to the title screen?";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*5;
            g2.drawString(text, x, y);
            
            // YES
            text = "Yes";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "No";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
    }
    
    public void drawDialogueScreen()
    {
        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        
        for(String line : currentDialogue.split("\n"))
        {
            g2.drawString(line, x, y);
            y += 40;
        }
        
        g2.setFont(g2.getFont().deriveFont(28F));
        x = (int) (gp.tileSize*14.5);
        y = gp.tileSize/2+gp.tileSize*3;
        width = gp.tileSize;
        height = gp.tileSize;
        drawSubWindow(x, y, width, height);
        g2.drawString("E", gp.tileSize*15-gp.tileSize/2+18, gp.tileSize*4+10);
    }
    
    public void drawCombatScreen()
    {
        if(combatScreenState == 0)
        {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            // Health Point
            int playerCurrHP = gp.player.currentHP;
            int playerMaxHP = gp.player.maxHP;
            String playerHP = "HP " + String.valueOf(playerCurrHP) + " / " + String.valueOf(playerMaxHP);
            g2.drawString(playerHP, gp.tileSize, 408);
            
            int x = gp.tileSize;
            int y = gp.tileSize * 9;
            int width = gp.tileSize * 3;
            int height = gp.tileSize * 2;
            drawSubWindow(x, y, width, height);

            x += gp.tileSize * 3.7;
            drawSubWindow(x, y, width, height);

            x += gp.tileSize * 3.7;
            drawSubWindow(x, y, width, height);

            x += gp.tileSize * 3.7;
            drawSubWindow(x, y, width, height);

            String text = "Attack";
            int x1 = 72 ;
            int y1 = 490;
            g2.drawString(text, x1, y1);
            if(commandNum == 0)
            {
                g2.drawString("______", x1-5, y1+10);
            }

            text = "Skill";
            int x2 = 268;
            g2.drawString(text, x2, y1);
            if(commandNum == 1)
            {
                g2.drawString("______", x2-24, y1+10);
            }

            text = "Item";
            int x3 = 444;
            g2.drawString(text, x3, y1);
            if(commandNum == 2)
            {
                g2.drawString("______", x3-24, y1+10);
            }

            text = "Run";
            int x4 = 626;
            g2.drawString(text, x4, y1);
            if(commandNum == 3)
            {
                g2.drawString("______", x4-28, y1+10);
            }
            
            g2.setFont(g2.getFont().deriveFont(28F));
            x = (int) (gp.tileSize*13.5);
            y = (int) (gp.tileSize*5.5);
            width = gp.tileSize;
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawString("W", gp.tileSize*13+gp.tileSize/2+15, gp.tileSize*6+10);
            
            x = (int) (gp.tileSize*12.5);
            y = (int) (gp.tileSize*6.5);
            drawSubWindow(x, y, width, height);
            g2.drawString("A", gp.tileSize*12+gp.tileSize/2+18, gp.tileSize*7+10);
            
            x = (int) (gp.tileSize*13.5);
            y = (int) (gp.tileSize*6.5);
            drawSubWindow(x, y, width, height);
            g2.drawString("S", gp.tileSize*13+gp.tileSize/2+18, gp.tileSize*7+10);

            x = (int) (gp.tileSize*14.5);
            y = (int) (gp.tileSize*6.5);
            drawSubWindow(x, y, width, height);
            g2.drawString("D", gp.tileSize*14+gp.tileSize/2+18, gp.tileSize*7+10);
            
            x = (int) (gp.tileSize*13.5-gp.tileSize/2);
            y = (int) (gp.tileSize*7.5);
            width = gp.tileSize*2;
            drawSubWindow(x, y, width, height);
            g2.drawString("Enter", gp.tileSize*14-gp.tileSize/2, gp.tileSize*8+10);

            g2.setFont(g2.getFont().deriveFont(42F));
            int screenX = gp.screenWidth/2 - 5 * (gp.tileSize/2);
            int screenY = gp.tileSize * 2;
            int npcIndex = gp.cChecker.checkEntity(gp.player, gp.npc);
            if(npcIndex == 1 || npcIndex == 2 || npcIndex == 3) // 1 : BOSS 2 : DWARF 3 : ELF
            {
                if(npcIndex == 1)
                {
                    int enemyCurrHP = gp.npc[1].currentHP;
                    int enemyMaxHP = gp.npc[1].maxHP;
                    String enemyHP = "HP " + String.valueOf(enemyCurrHP) + " / " + String.valueOf(enemyMaxHP);
                    x = getXforCenteredText(enemyHP);
                    g2.drawString(enemyHP, x, 72);
                        
                    try {
                    image = ImageIO.read(getClass().getResourceAsStream("/combat/boss.png"));
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                    g2.drawImage(image, screenX, screenY, gp.tileSize*5, gp.tileSize*5, null);
                }
                else if(npcIndex == 2)
                {
                    int enemyCurrHP = gp.npc[2].currentHP;
                    int enemyMaxHP = gp.npc[2].maxHP;
                    String enemyHP = "HP " + String.valueOf(enemyCurrHP) + " / " + String.valueOf(enemyMaxHP);
                    x = getXforCenteredText(enemyHP);
                    g2.drawString(enemyHP, x, 72);
                    
                    try {
                    image = ImageIO.read(getClass().getResourceAsStream("/combat/dwarf.png"));
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                    g2.drawImage(image, screenX, screenY, gp.tileSize*5, gp.tileSize*5, null);
                }
                else if(npcIndex == 3)
                {
                    int enemyCurrHP = gp.npc[3].currentHP;
                    int enemyMaxHP = gp.npc[3].maxHP;
                    String enemyHP = "HP " + String.valueOf(enemyCurrHP) + " / " + String.valueOf(enemyMaxHP);
                    x = getXforCenteredText(enemyHP);
                    g2.drawString(enemyHP, x, 72);
                    
                    try {
                    image = ImageIO.read(getClass().getResourceAsStream("/combat/elf.png"));
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                    g2.drawImage(image, screenX + 36, screenY, 168, gp.tileSize*5, null);
                }
            }
            
            if(enemyDamageOn == true)
            {
                g2.setFont(g2.getFont().deriveFont(42F));
                g2.drawString(enemyDamage, gp.tileSize*2, 360);

                messageCounter++;

                if(messageCounter > 80)
                {
                    messageCounter = 0;
                    enemyDamageOn = false;
                }
            }
            
            if(playerDamageOn == true)
            {
                g2.setFont(g2.getFont().deriveFont(42F));
                g2.drawString(playerDamage, gp.tileSize*10, 72);

                messageCounter++;

                if(messageCounter > 80)
                {
                    messageCounter = 0;
                    playerDamageOn = false;
                }
            }
        }

        else if(combatScreenState == 1) // ATTACK
        {
            g2.setColor(new Color(0, 0, 0)); // black
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            int x = gp.tileSize*2;
            int y = gp.tileSize/2;
            int width = gp.screenWidth - (gp.tileSize * 4);
            int height = gp.tileSize * 4;
            drawSubWindow(x, y, width, height);

            int npcIndex = gp.cChecker.checkEntity(gp.player, gp.npc);
            
            if(questionOn = true)
            {
                g2.setFont(g2.getFont().deriveFont(48F));
                x = gp.tileSize*3;
                y = gp.tileSize*2;
//                g2.drawString(question, gp.tileSize*3, gp.tileSize*2);
                for(String question1 : question.split("\n"))
                {
                    x = getXforCenteredText(question1);
                    g2.drawString(question1, x, y);
                    y += 40;
                }
                
                // answer
//                y = gp.tileSize*8;
//                for(String answer1 : answer.split("\n"))
//                {
//                    x = getXforCenteredText(answer1);
//                    g2.drawString(answer1, x, y);
//                    y += 40;
//                }
                
                questionTime -= (double)1/60;
                g2.drawString("Remaining time: " + dFormat.format(questionTime), gp.tileSize, gp.tileSize*11);
                
                questionCounter++;

                if(gp.inputH.hitEnemy == 1) // Answer correctly
                {
                    questionCounter = 0;
                    questionOn=false;
                    
                    gp.ui.showPlayerDamage("-1");
                    gp.playSE(8);
                    gp.npc[npcIndex].currentHP -= 1;
//                    gp.player.currentHP -= 1;
                    
//                    if(gp.player.currentHP == 0)
//                    {
//                        combatScreenState = 5;
//                    }
                    
                    if(gp.npc[npcIndex].currentHP == 0)
                    {
                        if(npcIndex == 1)
                        {
                            gp.stopMusic();
                            combatScreenState = 0;
                            gp.inputH.hitEnemy = 2;
                            gp.player.exp += gp.npc[npcIndex].exp;
                            gp.player.checkLevelUp();
                            gp.player.clear = "Victory";
                            gp.gameState = gp.victoryState;
                            messageOn = false;
                            playerDamageOn = false;
                            enemyDamageOn = false;
//                            combatScreenState = 4;
                            gp.playMusic(6);
                            gp.database.updateDatabase();
                        }
                        else if(npcIndex == 2)
                        {
                            gp.stopMusic();
                            combatScreenState = 0;
                            gp.player.currentHP = gp.player.maxHP;
                            gp.inputH.hitEnemy = 2;
                            gp.player.exp += gp.npc[npcIndex].exp;
                            gp.player.checkLevelUp();
                            gp.gameState = gp.playState;
                            gp.npc[npcIndex] = null;
                            messageOn = false;
                            playerDamageOn = false;
                            enemyDamageOn = false;
                            gp.playSE(1);
                            gp.player.hasPStone++;
                            gp.ui.showMessage("You get the Philosopher's Stone!");
                            gp.playMusic(0);
//                            combatScreenState = 4;
                        }
                        else if(npcIndex == 3)
                        {
                            gp.stopMusic();
                            combatScreenState = 0;
                            gp.player.currentHP = gp.player.maxHP;
                            gp.inputH.hitEnemy = 2;
                            gp.player.exp += gp.npc[npcIndex].exp;
                            gp.player.checkLevelUp();
                            gp.gameState = gp.playState;
                            gp.npc[npcIndex] = null;
                            messageOn = false;
                            playerDamageOn = false;
                            enemyDamageOn = false;
                            gp.playSE(1);
                            gp.player.hasExcalibur++;
                            gp.ui.showMessage("You get the Excalibur!");
                            gp.playMusic(0);
//                            combatScreenState = 4;
                        }
                    }
                    
                    else
                    {
//                    System.out.println("enemy hp decrease");
//                    System.out.println("player hp decrease");
                        combatScreenState = 0;
                        gp.inputH.hitEnemy = 2;
                    }
                }
                else if(gp.inputH.hitEnemy == 0) // Answer wrongly
                {
                    questionCounter = 0;
                    questionOn=false;
                    
                    gp.ui.showEnemyDamage("-1");
                    gp.playSE(8);
                    gp.player.currentHP -= 1;
                    
                    if(gp.player.currentHP == 0)
                    {
                        gp.stopMusic();
                        combatScreenState = 0;
                        gp.inputH.hitEnemy = 2;
                        gp.player.clear = "Game Over";
                        gp.gameState = gp.gameoverState;
                        messageOn = false;
                        playerDamageOn = false;
                        enemyDamageOn = false;
//                        combatScreenState = 5;
                        gp.playMusic(7);
                        gp.database.updateDatabase();
                    }
                    else
                    {
//                    System.out.println("player hp decrease");
                        combatScreenState = 0;
                        gp.inputH.hitEnemy = 2;
                    }
                }
                else if(questionCounter > givenTime)
                {
                    // Easy: 30 seconds && Normal: 60 seconds && Hard: 90 seconds
                    questionCounter = 0;
                    questionOn=false;
                    gp.ui.showEnemyDamage("-1");
                    gp.playSE(8);
                    gp.player.currentHP -= 1;
                    combatScreenState = 0;
                    gp.inputH.hitEnemy = 2;
                }
            }
        }

        else if(combatScreenState == 2) // SKILL
        {
            String text;
            
            g2.setColor(new Color(0, 0, 0)); // black
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            int x = gp.tileSize*2;
            int y = gp.tileSize/2;
            int width = gp.screenWidth - (gp.tileSize * 4);
            int height = gp.tileSize * 10;
            drawSubWindow(x, y, width, height);
            
            text = "Multiplication • Decimal";
            x = gp.tileSize*4;
            y = gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Division • Decimal";
            x = gp.tileSize*4;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Multiplication • Fraction";
            x = gp.tileSize*4;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Division • Fraction";
            x = gp.tileSize*4;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 3)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Back";
            x = gp.tileSize*4;
            y = gp.tileSize * 9;
            g2.drawString(text, x, y);
            if(commandNum == 4)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }

        else if(combatScreenState == 3) // ITEM
        {
            String text;
            
            g2.setColor(new Color(0, 0, 0)); // black
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            int x = gp.tileSize*2;
            int y = gp.tileSize/2;
            int width = gp.screenWidth - (gp.tileSize * 4);
            int height = gp.tileSize * 10;
            drawSubWindow(x, y, width, height);

            text = (String) gp.player.inventory.get(0);
            x = gp.tileSize*4;
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            text = " × " + (String) gp.player.itemAmount.get(0);
            x = gp.tileSize*10;
            g2.drawString(text, x, y);
            
            text = (String) gp.player.inventory.get(1);
            x = gp.tileSize*4;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }
            text = " × " + (String) gp.player.itemAmount.get(1);
            x = gp.tileSize*10;
            g2.drawString(text, x, y);
            
            text = "Back";
            x = gp.tileSize*4;
            y = gp.tileSize * 9;
            g2.drawString(text, x, y);
            if(commandNum == 2)
            {
                g2.drawString(">", x-gp.tileSize, y);
            }           
        }
    }
    
    public void drawCharacterScreen()
    {
        // CREATE A FRAME
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        // TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;
        
        // NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight + 24;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        
        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        
        // RESET textY
        textY = frameY + gp.tileSize;
        String value;
        
        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.currentHP + "/" + gp.player.maxHP);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight + 24;
        
        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        g2.setFont(g2.getFont().deriveFont(28F));
        int x = (int) (gp.tileSize*14);
        int y = gp.tileSize/2+gp.tileSize*9;
        int width = gp.tileSize;
        int height = gp.tileSize;
        drawSubWindow(x, y, width, height);
        g2.drawString("Q", gp.tileSize*14+18, gp.tileSize*10+10);
    }
    
    public void drawInventoryScreen()
    {
        // FRAME
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;
        
        // DRAW PLAYER'S ITEMS
        for(int i = 0; i < gp.player.inventory.size(); i++)
        {
            if(i == 0 && gp.player.hasHPotion != 0)
            {
                g2.drawImage(hpotionImage, slotX, slotY, gp.tileSize, gp.tileSize, null);
            }
            if(i == 1 && gp.player.hasDPotion != 0)
            {
                g2.drawImage(dpotionImage, slotX, slotY, gp.tileSize, gp.tileSize, null);
            }
            
            slotX += slotSize;
            
            if(i == 4 || i == 9 || i == 14)
            {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
        
        // CURSOR
        int cursorX = slotXstart + (gp.tileSize * slotCol);
        int cursorY = slotYstart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        
        // DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        
        // DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;
        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
        
        // DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));
        
        int itemIndex = getItemIndexOnSlot();
        
        if(itemIndex < gp.player.inventory.size())
        {
            if(itemIndex == 0 && gp.player.hasHPotion != 0)
            {
                String text = hpotionDesc;
                for(String line : text.split("\n"))
                {
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
                String amount = "Amount : " + (String) gp.player.itemAmount.get(0);
                g2.drawString(amount, textX, textY);
            }
            if(itemIndex == 1 && gp.player.hasDPotion != 0)
            {
                String text = dpotionDesc;
                for(String line : text.split("\n"))
                {
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
                String amount = "Amount : " + (String) gp.player.itemAmount.get(1);
                g2.drawString(amount, textX, textY);
            }

        }
    }
    
    public void drawVictoryScreen()
    {
        g2.setColor(new Color(30, 144, 255)); // dodgerblue
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Victory";
        // Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*3;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(new Color(255, 223, 0));
        g2.drawString(text, x-4, y-4);
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));
        
        text = "Congratulations!";
        // Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y += (int)(gp.tileSize*1.5);
        g2.drawString(text, x, y);
        // Main
        g2.setColor(new Color(255, 223, 0));
        g2.drawString(text, x-4, y-4);
        
        text = "You saved the princess.";
        // Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(new Color(255, 223, 0));
        g2.drawString(text, x-4, y-4);
        
        text = "Time: " + dFormat.format(playTime) + " second(s).";
        g2.setColor(Color.white);
        x = getXforCenteredText(text);
        y += (int)(gp.tileSize*1.5);
        g2.drawString(text, x, y);
        
        // Back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 0)
        {
            g2.drawString(">", x-40, y);
        }
    }
    
    public void drawGameOverScreen()
    {
        g2.setColor(new Color(30, 144, 255)); // dodgerblue
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        // Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*3;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));
        
        text = "YOU DIED";
        // Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.red);
        g2.drawString(text, x-4, y-4);
        
        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.setColor(Color.white);
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        if(commandNum == 0)
        {
            g2.drawString(">", x-40, y);
        }
        
        // Back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1)
        {
            g2.drawString(">", x-40, y);
        }
    }
    
    public void drawSubWindow(int x, int y, int width, int height)
    {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    
    public int getXforCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    
    public int getXforAlignToRightText(String text, int tailX)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
    
    public int getItemIndexOnSlot()
    {
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
}
