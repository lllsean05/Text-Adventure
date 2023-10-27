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
    Tile[] tile;// a tile array
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];//creates 10 types of tiles
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];//instatiate the TileNum

        getTileImage();
    }

    public void getTileImage(){
        try{//get the image from the /tiles res root directory
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/maps/map00.txt");//load the map.txt in the inputstream and
            BufferedReader br = new BufferedReader(new InputStreamReader(is));//read the inputstream name it as br

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow){

                String line = br.readLine();//readLine() can read a line of text.
                //put the line of the txt into a string called line.

                while(col < gp.maxScreenCol){

                    String numbers[] = line.split(" ");//String.split(String) splits this string around matches
                    // of the given regular expression
                    //.split(" ") split the string at a space.

                    int num = Integer.parseInt(numbers[col]);
                }
            }

        }catch (Exception e){

        }



    }
    public void draw(Graphics2D g2){
//SampleMap:
//        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row ++;
                y += gp.tileSize;
            }
        }
    }
}
