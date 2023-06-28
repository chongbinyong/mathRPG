/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import mathrpg.GamePanel;

/**
 *
 * @author Chong Bin Yong
 */
public class OBJ_HPotion extends SuperObject {
    
    GamePanel gp;
    
    public OBJ_HPotion(GamePanel gp)
    {
        this.gp = gp;

        name = "Health Potion";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/health_potion.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            description = "[" + name + "]\n+1 HP (Player)."; 
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }
}
