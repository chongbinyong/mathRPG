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
public class OBJ_Seal extends SuperObject{
    
    GamePanel gp;
    
    public OBJ_Seal(GamePanel gp)
    {
        this.gp = gp;
        
        name = "Seal";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/seal.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }
}
