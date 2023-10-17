package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
//Screen settings
    final int originalTileSize = 16;//16*16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; //48*48 Tile
    final int maxScreenCol = 16;//16tile column
    final int maxScreenRow = 12;//12 tile row
    final int screenWidth = tileSize * maxScreenCol;//768 pixels
    final int screenHeight = tileSize * maxScreenRow;//576 pixels

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);//if set to true then all the drawing from this component
        // will be done in an offscreen painting buffer

    }

}
