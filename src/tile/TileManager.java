/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import mathrpg.GamePanel;
import mathrpg.UtilityTool;

/**
 *
 * @author Chong Bin Yong
 */
public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    
    public TileManager(GamePanel gp) {
        this.gp = gp;
        
        tile = new Tile[100]; // 20 kinds of tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        //loadMap("/maps/map01.txt");
        loadMap("/maps/worldV2.txt");
    }
    
    public void getTileImage() {
        
//        try {
            
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            // PLACEHOLDER
            setup(0, "grass00", false);
            setup(1, "grass00", false);
            setup(2, "grass00", false);
            setup(3, "grass00", false);
            setup(4, "grass00", false);
            setup(5, "grass00", false);
            setup(6, "grass00", false);
            setup(7, "grass00", false);
            setup(8, "grass00", false);
            setup(9, "grass00", false);
            // PLACEHOLDER
            
            setup(10, "bridge_lava00", false);
            setup(11, "bridge_lava01", false);
            setup(12, "bridge_water00", false);
            setup(13, "bridge_water01", false);
            setup(14, "castle", true);
            setup(15, "cave", true);
            setup(16, "earth00", false);
            setup(17, "earth01", false);
            setup(18, "earth02", false);
            setup(19, "earth03", false);
            setup(20, "earth04", false);
            setup(21, "grass00", false);
            setup(22, "earth05", false);
            setup(23, "earth06", false);
            setup(24, "earth07", false);
            setup(25, "earth08", false);
            setup(26, "earth09", false);
            setup(27, "earth10", false);
            setup(28, "earth11", false);
            setup(29, "earth12", false);
            setup(30, "evil_castle", true);
            setup(31, "grass00", false);
            setup(32, "grass01", false);
            setup(33, "house", true);
            setup(34, "lava", true);
            setup(35, "lava00", true);
            setup(36, "lava01", true);
            setup(37, "lava02", true);
            setup(38, "lava03", true);
            setup(39, "lava04", true);
            setup(40, "lava05", true);
            setup(41, "lava06", true);
            setup(42, "lava07", true);
            setup(43, "lava08", true);
            setup(44, "lava09", true);
            setup(45, "lava10", true);
            setup(46, "lava11", true);
            setup(47, "lava12", true);
            setup(48, "lava13", true);
            setup(49, "mountain", true);
            setup(50, "road00", false);
            setup(51, "road01", false);
            setup(52, "road02", false);
            setup(53, "road03", false);
            setup(54, "road04", false);
            setup(55, "road05", false);
            setup(56, "road06", false);
            setup(57, "road07", false);
            setup(58, "road08", false);
            setup(59, "road09", false);
            setup(60, "road10", false);
            setup(61, "road11", false);
            setup(62, "road12", false);
            setup(63, "tree", true);
            setup(64, "water00", true);
            setup(65, "water01", true);
            setup(66, "water02", true);
            setup(67, "water03", true);
            setup(68, "water04", true);
            setup(69, "water05", true);
            setup(70, "water06", true);
            setup(71, "water07", true);
            setup(72, "water08", true);
            setup(73, "water09", true);
            setup(74, "water10", true);
            setup(75, "water11", true);
            setup(76, "water12", true);
            setup(77, "water13", true);
            
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/castle.png"));
//            tile[1].collision = true;
//            
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cave.png"));
//            tile[2].collision = true;
//            
//            tile[3] = new Tile();
//            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
//            
//            tile[4] = new Tile();
//            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
//            tile[4].collision = true;
//            
//            tile[5] = new Tile();
//            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house.png"));
//            tile[5].collision = true;
//            
//            tile[6] = new Tile();
//            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/lava.png"));
//            tile[6].collision = true;
//            
//            tile[7] = new Tile();
//            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road.png"));
//            
//            tile[8] = new Tile();
//            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
//            tile[8].collision = true;
//            
//            tile[9] = new Tile();
//            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
//            tile[9].collision = true;
//            
//            tile[10] = new Tile();
//            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bridge.png"));
//            
//            tile[11] = new Tile();
//            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/evil_castle.png"));
//            tile[11].collision = true;
            
//        }catch(IOException e) {
//            e.printStackTrace();
//        }
    }
    
    public void setup(int index, String imageName, boolean collision) {
        
        UtilityTool uTool = new UtilityTool();
        
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName +".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath) {
        
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                
                String line = br.readLine();
                
                while(col < gp.maxWorldCol) {
                    
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
            
        }catch(Exception e) {
            
        }
    }
    
    public void draw(Graphics2D g2) {

//        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[3].image, 144, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[4].image, 192, 0, gp.tileSize, gp.tileSize, null);

        int worldCol = 0;
        int worldRow = 0;
//        int x = 0;
//        int y = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            // improve rendering efficiency
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
               
               g2.drawImage(tile[tileNum].image, screenX, screenY, null); 
//               g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;
//            x += gp.tileSize;
            
            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
//                x = 0;
                worldRow++;
//                y += gp.tileSize;
            }
        }
    }
}
