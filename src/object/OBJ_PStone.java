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
public class OBJ_PStone extends SuperObject{

    GamePanel gp;
    
    public OBJ_PStone(GamePanel gp)
    {
        this.gp = gp;

        name = "Philosopher's Stone";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/philosopher's stone.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
                
        collision = true;
        
    }    
}
