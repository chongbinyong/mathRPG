/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathrpg;

import entity.NPC_Bard;
import entity.NPC_Boss;
import entity.NPC_Dwarf;
import entity.NPC_Elf;
import entity.NPC_Princess;
import object.OBJ_Excalibur;
import object.OBJ_PStone;
import object.OBJ_Seal;
import object.OBJ_HPotion;
import object.OBJ_DPotion;

/**
 *
 * @author Chong Bin Yong
 */
public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }
    
    public void setObject()
    {
//        gp.obj[0] = new OBJ_Excalibur(gp);
//        gp.obj[0].worldX = 6 * gp.tileSize;
//        gp.obj[0].worldY = 28 * gp.tileSize;
//        
//        gp.obj[1] = new OBJ_PStone(gp);
//        gp.obj[1].worldX = 43 * gp.tileSize;
//        gp.obj[1].worldY = 21 * gp.tileSize;
//      
        gp.obj[2] = new OBJ_Seal(gp);
        gp.obj[2].worldX = 24 * gp.tileSize;
        gp.obj[2].worldY = 12 * gp.tileSize;
        
        gp.obj[3] = new OBJ_HPotion(gp);
        gp.obj[3].worldX = 6 * gp.tileSize;
        gp.obj[3].worldY = 37 * gp.tileSize;
        
        gp.obj[4] = new OBJ_DPotion(gp);
        gp.obj[4].worldX = 42 * gp.tileSize;
        gp.obj[4].worldY = 6 * gp.tileSize;
    }
    
    public void setNPC()
    {
        gp.npc[0] = new NPC_Bard(gp);
        gp.npc[0].worldX = gp.tileSize * 26;
        gp.npc[0].worldY = gp.tileSize * 23;
        
        gp.npc[1] = new NPC_Boss(gp);
        gp.npc[1].worldX = gp.tileSize * 25;
        gp.npc[1].worldY = gp.tileSize * 10;
        
        if(gp.player.hasPStone == 0)
        {
            gp.npc[2] = new NPC_Dwarf(gp);
            gp.npc[2].worldX = gp.tileSize * 37;
            gp.npc[2].worldY = gp.tileSize * 25;
        }
        
        if(gp.player.hasExcalibur == 0)
        {
            gp.npc[3] = new NPC_Elf(gp);
            gp.npc[3].worldX = gp.tileSize * 10;
            gp.npc[3].worldY = gp.tileSize * 25;
        }
        
        gp.npc[4] = new NPC_Princess(gp);
        gp.npc[4].worldX = gp.tileSize * 23;
        gp.npc[4].worldY = gp.tileSize * 10;
    }
}
