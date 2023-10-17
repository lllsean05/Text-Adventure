package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {// in order to use Thread, we must be using Runnable

    //Screen settings
    final int originalTileSize = 16;//16*16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; //48*48 Tile
    final int maxScreenCol = 16;//16tile column
    final int maxScreenRow = 12;//12 tile row
    final int screenWidth = tileSize * maxScreenCol;//768 pixels
    final int screenHeight = tileSize * maxScreenRow;//576 pixels

    //in order to keep track of the time at 60FPS or other speed that it will refresh,
    // we need to have a in game clock and in JAVA we call it Thread.
    //Thread is something you can start and stop and once the thread started, it keeps your program
    // running until you stop it.

    Thread gameThread;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);//if set to true then all the drawing from this component
        // will be done in an offscreen painting buffer

    }

    @Override
    public void run() {

    }
}
