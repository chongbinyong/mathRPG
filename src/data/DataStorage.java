/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Chong Bin Yong
 */
public class DataStorage implements Serializable {
    
    // PLAYER STATS
    int level;
    int maxHP;
    int attack;
    int exp;
    int nextLevelExp;
    int hasExcalibur;
    int hasPStone;
    
    // PLAYER INVENTORY
    ArrayList itemNames = new ArrayList();
    ArrayList itemAmounts = new ArrayList();
    
    // OBJECTS
    int breakSeal;
    int getHPotion;
    int getDPotion;
    
    double playTime;
    int difficultyLvl;
    double givenTime;
    
    int correctMulDec;
    int totalMulDec;
    int correctDivDec;
    int totalDivDec;
    int correctMulFrac;
    int totalMulFrac;
    int correctDivFrac;
    int totalDivFrac;
    
    int hitEnemy;
}
