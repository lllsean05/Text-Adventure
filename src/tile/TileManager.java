package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;// a tile array
    public int[][] mapTileNum;//the variable to read the map matrix and store it as the 2-d array

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];//creates 10 types of tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];//instatiate the TileNum

        getTileImage();
        loadMap("/maps/worldmap00.txt");
    }

    public void getTileImage(){
        try{//get the image from the /tiles res root directory
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){//the method to read the map matrix as map
        try{
            InputStream is = getClass().getResourceAsStream(filePath);//load the map.txt in the inputstream and
            BufferedReader br = new BufferedReader(new InputStreamReader(is));//read the inputstream name it as br

            int col = 0;//integer to start from upper left of the screen
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();//readLine() can read a line of text.
                //put the line of the txt into a string called line.

                while(col < gp.maxWorldCol){

                    String numbers[] = line.split(" ");//String.split(String) splits this string around matches
                    // of the given regular expression
                    //.split(" ") split the string at a space.

                    int num = Integer.parseInt(numbers[col]);//use col as an index for numbers[] array.

                    mapTileNum[col][row] = num;
                    col++; //Continue this until everything in the numbers[] is stored in the mapTileNum[][].
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }



    }
    public void draw(Graphics2D g2){
//SampleMap:
//        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
        int worldCol = 0;//set the initial position of drawing which is the first tile in the upper left of the screen
        int worldRow = 0;
//       TEST: int x = 0;
//        int y = 0;// the integer x and y are just for applying the draw method.

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];//Extract a tile number which is stored in the mapTileNum[0][0]
            //the map data has been stored in the mapTileNum[][].

            int worldX = worldCol * gp.tileSize;//the coordinate for the entire map
            int worldY = worldRow * gp.tileSize;//the coordinate for the entire map
            int screenX = worldX - gp.Player.worldX + gp.Player.screenX;//where we should draw the map on the screen
            int screenY = worldY - gp.Player.worldY + gp.Player.screenY;//where we should draw the map on the screen

            if(worldX + gp.tileSize > gp.Player.worldX - gp.Player.screenX &&
               worldX - gp.tileSize < gp.Player.worldX + gp.Player.screenX && //to minimize the workload of ram, we don't have to draw all the world map so that
               worldY + gp.tileSize > gp.Player.worldY - gp.Player.screenY && // we can only draw the map if it is within the screen(we take the range
               worldY - gp.tileSize < gp.Player.worldY + gp.Player.screenY){ // based on the player as the center and plus or minus the range to get the screen range and then draw the map

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);//draw the grass image on each column in one row
            }
            //TEST:g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);//draw the grass image on each column in one row
            worldCol++;
            //TEST:x += gp.tileSize;//gp.tileSize is the size of a single tile

            if (worldCol == gp.maxWorldCol){//when we draw the col in the last column of the row's tile, we want to go to the next row
                worldCol = 0;//go back to the first column
                //TEST:x = 0;
                worldRow ++;//switch to the next row
                //TEST:y += gp.tileSize;
            }
        }
    }
}
