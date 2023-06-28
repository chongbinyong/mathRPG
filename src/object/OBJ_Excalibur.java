/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import mathrpg.GamePanel;

/**
 *
 * @author Chong Bin Yong
 */
public class OBJ_Excalibur extends SuperObject {
    
    GamePanel gp;
    
    public OBJ_Excalibur(GamePanel gp)
    {
        this.gp = gp;

        name = "Excalibur";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/excalibur.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        collision = true;
    }
}
