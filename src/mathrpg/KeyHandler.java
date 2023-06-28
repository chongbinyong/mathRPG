/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathrpg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Chong Bin Yong
 */
public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, talkPressed, enterPressed;
    
    // DEBUG
    boolean checkDrawTime = false;
    
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        // TITLE STATE
        if(gp.gameState == gp.titleState)
        {
            if(gp.ui.titleScreenState == 0)
            {
                if(code == KeyEvent.VK_W)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0 )
                    {
                        gp.ui.commandNum = 3;
                    }
                }
                if(code == KeyEvent.VK_S)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3)
                    {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
//                        gp.gameState = gp.playState;
//                        gp.playMusic(0);
                        gp.ui.titleScreenState = 1;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 1)
                    {
                        // load game add later
                        gp.saveLoad.load();
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                        gp.ui.commandNum = 0;
//                        gp.ui.titleScreenState = 3;
//                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 2)
                    {
                        // show how to play
                        gp.ui.titleScreenState = 4;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 3)
                    {
                        System.exit(0);
                    }
                }
            }
            else if(gp.ui.titleScreenState == 1)
            {
                if(code == KeyEvent.VK_W)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0 )
                    {
                        gp.ui.commandNum = 3;
                    }
                }
                if(code == KeyEvent.VK_S)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3)
                    {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        // EASY LEVEL STUFF
                        gp.ui.difficultyLvl = 0;
                        gp.ui.givenTime = 90 * 60;
                        gp.ui.titleScreenState = 2;
                        gp.ui.commandNum = 0;
//                        gp.gameState = gp.playState;
//                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 1)
                    {
                        // NORMAL LEVEL STUFF
                        gp.ui.difficultyLvl = 1;
                        gp.ui.givenTime = 135 * 60;
                        gp.ui.titleScreenState = 2;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 2)
                    {
                        // HARD LEVEL STUFF
                        gp.ui.difficultyLvl = 2;
                        gp.ui.givenTime = 180 * 60;
                        gp.ui.titleScreenState = 2;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 3)
                    {
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.titleScreenState == 2)
            {
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                }
            }
            else if(gp.ui.titleScreenState == 3)
            {
                // load game
            }
            else if(gp.ui.titleScreenState == 4)
            {
                // how to play
                if(code == KeyEvent.VK_W)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0 )
                    {
                        gp.ui.commandNum = 2;
                    }
                }
                if(code == KeyEvent.VK_S)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2)
                    {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.ui.titleScreenState = 5;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 1)
                    {
                        gp.ui.titleScreenState = 6;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 2)
                    {
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.titleScreenState == 5)
            {
                // game control
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.ui.titleScreenState = 4;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.titleScreenState == 6)
            {
                // question examples
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.ui.titleScreenState = 7;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.titleScreenState == 7)
            {
                // question examples
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.ui.titleScreenState = 8;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.titleScreenState == 8)
            {
                // question examples
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.ui.titleScreenState = 4;
                        gp.ui.commandNum = 0;
                    }
                }
            }
        }
        
        // PLAY STATE
        else if(gp.gameState == gp.playState)
        {
            if(code == KeyEvent.VK_W)
            {
                upPressed = true;
            }
            if(code == KeyEvent.VK_S)
            {
                downPressed = true;
            }
            if(code == KeyEvent.VK_A)
            {
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D)
            {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_ESCAPE)
            {
//                if(gp.gameState == gp.playState)
//                {
                gp.gameState = gp.optionState;
                gp.stopMusic();
//                }
//                else if(gp.gameState == gp.pauseState)
//                {
//                    gp.gameState = gp.playState;
//                }
            }
            if(code == KeyEvent.VK_Q)
            {
                gp.gameState = gp.characterState;
                gp.stopMusic();
            }
            if(code == KeyEvent.VK_E)
            {
                talkPressed = true;
            }
            if(code == KeyEvent.VK_ENTER)
            {
                enterPressed = true;
            }

            // DEBUG
//            if(code == KeyEvent.VK_T)
//            {
//                if(checkDrawTime == false)
//                {
//                    checkDrawTime = true;
//                }
//                else if(checkDrawTime == true)
//                {
//                    checkDrawTime = false;
//                }
//            }
        }
        
        // PAUSE STATE
        else if(gp.gameState == gp.optionState)
        {
            if(gp.ui.optionScreenState == 0)
            {
                if(code == KeyEvent.VK_ESCAPE)
                {
                    gp.ui.commandNum = 0;
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }

                if(code == KeyEvent.VK_W)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0)
                    {
                        gp.ui.commandNum = 8;
                    }
                }
                if(code == KeyEvent.VK_S)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 8)
                    {
                        gp.ui.commandNum = 0;
                    }
                }
                
                if(code == KeyEvent.VK_A)
                {
//                    gp.playSE(1);
                    if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0)
                    {
                        gp.music.volumeScale--;
                        gp.music.checkVolume();
                    }
                    if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0)
                    {
                        gp.se.volumeScale--;
//                        gp.se.checkVolume();
                    }
                }
                if(code == KeyEvent.VK_D)
                {
//                    gp.playSE(1);
                    if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5)
                    {
                        gp.music.volumeScale++;
                        gp.music.checkVolume();
                    }
                    if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5)
                    {
                        gp.se.volumeScale++;
//                        gp.music.checkVolume();
                    }
                }
                
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        // SAVE
                        gp.saveLoad.save();
                        gp.ui.optionScreenState = 1;
                        gp.ui.commandNum = 0;
//                        gp.ui.optionScreenState = 1;
//                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 3)
                    {
                        // DIFFICULTY
                        gp.ui.optionScreenState = 2;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 4)
                    {
                        // GAME STORY
                        gp.ui.optionScreenState = 3;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 5)
                    {
                        // GAME CONTROL
                        gp.ui.optionScreenState = 4;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 6)
                    {
                        // QUESTION EXAMPLES
                        gp.ui.optionScreenState = 5;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 7)
                    {
                        // END GAME
                        gp.ui.optionScreenState = 8;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 8)
                    {
                        // BACK
                        gp.ui.commandNum = 0;
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                }
            }
            else if(gp.ui.optionScreenState == 1)
            {
                // SAVE
                if(code == KeyEvent.VK_W)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0)
                    {
                        gp.ui.commandNum = 1;
                    }
                }
                if(code == KeyEvent.VK_S)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 1)
                    {
                        gp.ui.commandNum = 0;
                    }
                }
                
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.ui.optionScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 1)
                    {
                        gp.restart();
                        gp.ui.messageOn = false;
                        gp.ui.playerDamageOn = false;
                        gp.ui.enemyDamageOn = false;
                        gp.gameState = gp.titleState;
                        gp.ui.titleScreenState = 0;
                        gp.ui.optionScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.optionScreenState == 2)
            {
                // DIFFICULTY
                if(code == KeyEvent.VK_W)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0)
                    {
                        gp.ui.commandNum = 3;
                    }
                }
                if(code == KeyEvent.VK_S)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3)
                    {
                        gp.ui.commandNum = 0;
                    }
                }
                
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        // EASY LEVEL STUFF
                        gp.ui.difficultyLvl = 0;
                        gp.ui.givenTime = 90 * 60;
                        gp.ui.optionScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 1)
                    {
                        // NORMAL LEVEL STUFF
                        gp.ui.difficultyLvl = 1;
                        gp.ui.givenTime = 135 * 60;
                        gp.ui.optionScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 2)
                    {
                        // HARD LEVEL STUFF
                        gp.ui.difficultyLvl = 2;
                        gp.ui.givenTime = 180 * 60;
                        gp.ui.optionScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 3)
                    {
                        gp.ui.optionScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.optionScreenState == 3)
            {
                // GAME STORY
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.ui.optionScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.optionScreenState == 4)
            {
                // GAME CONTROL
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.ui.optionScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.optionScreenState == 5)
            {
                // QUESTION EXAMPLES
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.ui.optionScreenState = 6;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.optionScreenState == 6)
            {
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.ui.optionScreenState = 7;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.optionScreenState == 7)
            {
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.ui.optionScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.optionScreenState == 8)
            {
                // END GAME
                if(code == KeyEvent.VK_W)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0)
                    {
                        gp.ui.commandNum = 1;
                    }
                }
                if(code == KeyEvent.VK_S)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 1)
                    {
                        gp.ui.commandNum = 0;
                    }
                }
                
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.restart();
                        gp.ui.messageOn = false;
                        gp.ui.playerDamageOn = false;
                        gp.ui.enemyDamageOn = false;
                        gp.gameState = gp.titleState;
                        gp.ui.titleScreenState = 0;
                        gp.ui.optionScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 1)
                    {
                        gp.ui.optionScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
        }
        
        // DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState)
        {
            if(code == KeyEvent.VK_E)
            {
                gp.gameState = gp.playState;
            }
        }
        
        // COMBAT STATE
        else if(gp.gameState == gp.combatState)
        {
            if(gp.ui.combatScreenState == 0)
            {
                if(code == KeyEvent.VK_A)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0 )
                    {
                        gp.ui.commandNum = 3;
                    }
                }
                if(code == KeyEvent.VK_D)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3)
                    {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        // ATTACK
                        gp.ui.combatScreenState = 1;
                        gp.ui.showQuestion();
                        
                        gp.inputH.compareAnswer();
                        
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 1)
                    {
                        // SKILL
                        gp.ui.combatScreenState = 2;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 2)
                    {
                        // ITEM
                        gp.ui.combatScreenState = 3;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 3)
                    {
                        // RUN
                        int npcIndex = gp.cChecker.checkEntity(gp.player, gp.npc);
                        gp.npc[npcIndex].currentHP = gp.npc[npcIndex].maxHP;
                        gp.player.currentHP = gp.player.maxHP;
                        gp.player.hasHPotion = gp.player.b0;
                        gp.player.hasDPotion = gp.player.b1;
                        gp.player.itemAmount.set(0, gp.player.a0);
                        gp.player.itemAmount.set(1, gp.player.a1);
                        gp.ui.messageOn = false;
                        gp.ui.playerDamageOn = false;
                        gp.ui.enemyDamageOn = false;
                        
                        gp.stopMusic();
                        gp.gameState = gp.playState;
                        gp.ui.commandNum = 0;
                        gp.playMusic(0);
                    }
                }   
            }
            else if(gp.ui.combatScreenState == 1) // ATTACK
            {
//                gp.ui.combatScreenState = 0;
            }
            else if(gp.ui.combatScreenState == 2) // SKILL
            {
//                gp.ui.combatScreenState = 0;
//                gp.ui.commandNum = 1;
                if(code == KeyEvent.VK_W)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0 )
                    {
                        gp.ui.commandNum = 4;
                    }
                }
                if(code == KeyEvent.VK_S)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 4)
                    {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        gp.ui.combatScreenState = 1;
                        gp.ui.showSpecQuestion(0);
                        
                        gp.inputH.compareAnswer();
                        
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 1)
                    {
                        gp.ui.combatScreenState = 1;
                        gp.ui.showSpecQuestion(1);
                        
                        gp.inputH.compareAnswer();
                        
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 2)
                    {
                        gp.ui.combatScreenState = 1;
                        gp.ui.showSpecQuestion(2);
                        
                        gp.inputH.compareAnswer();
                        
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 3)
                    {
                        gp.ui.combatScreenState = 1;
                        gp.ui.showSpecQuestion(3);
                        
                        gp.inputH.compareAnswer();
                        
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 4)
                    {
                        gp.ui.combatScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            else if(gp.ui.combatScreenState == 3) // ITEM
            {
                if(code == KeyEvent.VK_W)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0 )
                    {
                        gp.ui.commandNum = 2;
                    }
                }
                if(code == KeyEvent.VK_S)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2)
                    {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum == 0)
                    {
                        if(gp.player.currentHP != gp.player.maxHP && gp.player.itemAmount.get(0) != "0")
                        {
                            String a0 = (String) gp.player.itemAmount.get(0);
                            gp.player.b0 = Integer.parseInt(a0);
                            int amount0 = Integer.parseInt(a0);
                            amount0--;
                            a0 = String.valueOf(amount0);
                            gp.player.itemAmount.set(0, a0);
                            gp.ui.showEnemyDamage("+1");
                            gp.playSE(9);
                            gp.player.currentHP += 1;
                            String hasHPotion = (String) gp.player.itemAmount.get(0);
                            gp.player.hasHPotion = Integer.parseInt(hasHPotion);
                            gp.ui.combatScreenState = 0;
                            gp.ui.commandNum = 0;
                        }
                        else
                        {
                            gp.ui.combatScreenState = 0;
                            gp.ui.commandNum = 0;
                        }
                    }
                    if(gp.ui.commandNum == 1)
                    {
                        if(gp.player.itemAmount.get(1) != "0")
                        {
                            String a1 = (String) gp.player.itemAmount.get(1);
                            gp.player.b1 = Integer.parseInt(a1);
                            int amount1 = Integer.parseInt(a1);
                            amount1--;
                            a1 = String.valueOf(amount1);
                            gp.player.itemAmount.set(1, a1);
                            int npcIndex = gp.cChecker.checkEntity(gp.player, gp.npc);
                            gp.playSE(10);
                            gp.ui.showPlayerDamage("-1");
                            gp.npc[npcIndex].currentHP -= 1;
                            String hasDPotion = (String) gp.player.itemAmount.get(1);
                            gp.player.hasDPotion = Integer.parseInt(hasDPotion);
                            if(gp.npc[npcIndex].currentHP == 0)
                            {
                                if(npcIndex == 1)
                                {
                                    gp.stopMusic();
                                    gp.ui.combatScreenState = 0;
                                    gp.inputH.hitEnemy = 2;
                                    gp.player.exp += gp.npc[npcIndex].exp;
                                    gp.player.checkLevelUp();
                                    gp.player.clear = "Victory";
                                    gp.gameState = gp.victoryState;
                                    gp.ui.commandNum = 0;
                                    gp.ui.messageOn = false;
                                    gp.ui.playerDamageOn = false;
                                    gp.ui.enemyDamageOn = false;
//                                    gp.ui.combatScreenState = 4;
                                    gp.playMusic(6);
                                    gp.database.updateDatabase();
                                }
                                else if(npcIndex == 2)
                                {
                                    gp.stopMusic();
                                    gp.ui.combatScreenState = 0;
                                    gp.ui.commandNum = 0;
                                    gp.player.currentHP = 3;
                                    gp.inputH.hitEnemy = 2;
                                    gp.player.exp += gp.npc[npcIndex].exp;
                                    gp.player.checkLevelUp();
                                    gp.gameState = gp.playState;
                                    gp.npc[npcIndex] = null;
                                    gp.ui.messageOn = false;
                                    gp.ui.playerDamageOn = false;
                                    gp.ui.enemyDamageOn = false;
                                    gp.playSE(1);
                                    gp.player.hasPStone++;
                                    gp.ui.showMessage("You get the Philosopher's Stone!");
                                    gp.playMusic(0);
        //                            combatScreenState = 4;
                                }
                                else if(npcIndex == 3)
                                {
                                    gp.stopMusic();
                                    gp.ui.combatScreenState = 0;
                                    gp.ui.commandNum = 0;
                                    gp.player.currentHP = 3;
                                    gp.inputH.hitEnemy = 2;
                                    gp.player.exp += gp.npc[npcIndex].exp;
                                    gp.player.checkLevelUp();
                                    gp.gameState = gp.playState;
                                    gp.npc[npcIndex] = null;
                                    gp.ui.messageOn = false;
                                    gp.ui.playerDamageOn = false;
                                    gp.ui.enemyDamageOn = false;
                                    gp.playSE(1);
                                    gp.player.hasExcalibur++;
                                    gp.ui.showMessage("You get the Excalibur!");
                                    gp.playMusic(0);
        //                            combatScreenState = 4;
                                }
                            }
                            else
                            {
                                gp.ui.combatScreenState = 0;
                                gp.ui.commandNum = 0;
                            }
                        }
                        else
                        {
                            gp.ui.combatScreenState = 0;
                            gp.ui.commandNum = 0;
                        }
                    }
                    if(gp.ui.commandNum == 2)
                    {
                        gp.ui.combatScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
        }
        
        // CHARACTER STATE
        else if(gp.gameState == gp.characterState)
        {
            if(code == KeyEvent.VK_Q)
            {
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            
            if(code == KeyEvent.VK_W)
            {
                if(gp.ui.slotRow != 0)
                {
                    gp.ui.slotRow--;
                }
            }
            if(code == KeyEvent.VK_A)
            {
                if(gp.ui.slotCol != 0)
                {
                    gp.ui.slotCol--;
                }
            }
            if(code == KeyEvent.VK_S)
            {
                if(gp.ui.slotRow != 3)
                {
                    gp.ui.slotRow++;
                }
            }
            if(code == KeyEvent.VK_D)
            {
                if(gp.ui.slotCol != 4)
                {
                    gp.ui.slotCol++;
                }
            }
        }
        
        // VICTORY STATE
        else if(gp.gameState == gp.victoryState)
        {
            if(code == KeyEvent.VK_ENTER)
            {
                if(gp.ui.commandNum == 0)
                {
                    gp.stopMusic();
                    gp.gameState = gp.titleState;
                    gp.ui.titleScreenState = 0;
                    gp.ui.commandNum = 0;
                    gp.restart();
                }
            }
        }
        
        // GAMEOVER STATE
        else if(gp.gameState == gp.gameoverState)
        {
            if(code == KeyEvent.VK_W)
            {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0 )
                {
                    gp.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_S)
            {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1)
                {
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER)
            {
                if(gp.ui.commandNum == 0)
                {
                    gp.stopMusic();
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                    gp.retry();
                }
                else if(gp.ui.commandNum == 1)
                {
                    gp.stopMusic();
                    gp.gameState = gp.titleState;
                    gp.ui.titleScreenState = 0;
                    gp.ui.commandNum = 0;
                    gp.restart();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W)
        {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
    }
    
}
