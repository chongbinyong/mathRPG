/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathrpg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Chong Bin Yong
 */
public class UtilityTool {
    
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        
         BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
         Graphics2D g2 = scaledImage.createGraphics();
         g2.drawImage(original, 0, 0, width, height, null);
         g2.dispose();
         
         return scaledImage;
    }
}
