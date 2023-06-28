/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import mathrpg.GamePanel;

/**
 *
 * @author Chong Bin Yong
 */
public class SaveLoad {
    
    GamePanel gp;
    
    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }
    
    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            
            DataStorage ds = new DataStorage();
            
            ds.level = gp.player.level;
            ds.maxHP = gp.player.maxHP;
            ds.attack = gp.player.attack;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.hasExcalibur = gp.player.hasExcalibur;
            ds.hasPStone = gp.player.hasPStone;
            
            for(int i = 0; i < gp.player.inventory.size(); i++)
            {
                ds.itemNames.add(gp.player.inventory.get(i));
            }
            
            for(int i = 0; i < gp.player.itemAmount.size(); i++)
            {
                ds.itemAmounts.add(gp.player.itemAmount.get(i));
            }
            
            ds.breakSeal = gp.player.breakSeal;
            ds.getHPotion = gp.player.getHPotion;
            ds.getDPotion = gp.player.getDPotion;
            
            ds.playTime = gp.ui.playTime;
            ds.difficultyLvl = gp.ui.difficultyLvl;
            ds.givenTime = gp.ui.givenTime;
            
            ds.correctMulDec = gp.player.correctMulDec;
            ds.totalMulDec = gp.player.totalMulDec;
            ds.correctDivDec = gp.player.correctDivDec;
            ds.totalDivDec = gp.player.totalDivDec;
            ds.correctMulFrac = gp.player.correctMulFrac;
            ds.totalMulFrac = gp.player.totalMulFrac;
            ds.correctDivFrac = gp.player.correctDivFrac;
            ds.totalDivFrac = gp.player.totalDivFrac;
            
            ds.hitEnemy = gp.inputH.hitEnemy;
              
            // Write the DataStorage object
            oos.writeObject(ds);
            
        }catch(Exception e){
            System.out.println("Save Exception!");
        }
    }
    
    public void load() {
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            
            // Read the DataStorage object
            DataStorage ds = (DataStorage)ois.readObject();
            
            gp.player.level = ds.level;
            gp.player.maxHP = ds.maxHP;
            gp.player.attack = ds.attack;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.hasExcalibur = ds.hasExcalibur;
            gp.player.hasPStone = ds.hasPStone;
            if(gp.player.hasExcalibur == 1)
            {
                gp.npc[3] = null;
            }
            if(gp.player.hasPStone == 1)
            {
                gp.npc[2] = null;
            }
            
            gp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size(); i++)
            {
                gp.player.inventory.add(ds.itemNames.get(i));
            }
            
            gp.player.itemAmount.clear();
            for(int i = 0; i < ds.itemAmounts.size(); i++)
            {
                gp.player.itemAmount.add(ds.itemAmounts.get(i));
            }
            
            gp.player.breakSeal = ds.breakSeal;
            if(gp.player.breakSeal == 1)
            {
                gp.obj[2] = null;
            }
            gp.player.getHPotion = ds.getHPotion;
            if(gp.player.getHPotion == 1)
            {
                gp.obj[3] = null;
            }
            gp.player.getDPotion = ds.getDPotion;
            if(gp.player.getDPotion == 1)
            {
                gp.obj[4] = null;
            }
            
            gp.ui.playTime = ds.playTime;
            gp.ui.difficultyLvl = ds.difficultyLvl;
            gp.ui.givenTime = ds.givenTime;
            
            gp.player.correctMulDec = ds.correctMulDec;
            gp.player.totalMulDec = ds.totalMulDec;
            gp.player.correctDivDec = ds.correctDivDec;
            gp.player.totalDivDec = ds.totalDivDec;
            gp.player.correctMulFrac = ds.correctMulFrac;
            gp.player.totalMulFrac = ds.totalMulFrac;
            gp.player.correctDivFrac = ds.correctDivFrac;
            gp.player.totalDivFrac = ds.totalDivFrac;
            
            gp.inputH.hitEnemy = ds.hitEnemy;
            
        }catch(Exception e){
            System.out.println("Load Exception!");
        }
    }
}
