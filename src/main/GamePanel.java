package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class GamePanel extends JPanel implements Runnable {// in order to use Thread,
    // we must be using Runnable

    //Screen settings
    final int originalTileSize = 16;//16*16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48*48 Tile
    public final int maxScreenCol = 16;//16tile column
    public final int maxScreenRow = 12;//12 tile row
    public final int screenWidth = tileSize * maxScreenCol;//768 pixels
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels

    //in order to keep track of the time at 60FPS or other speed that it will refresh,
    // we need to have a in game clock and in JAVA we call it Thread.
    //Thread is something you can start and stop and once the thread started, it keeps your program
    // running until you stop it.

    //WORLD SETTING
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS = Frame per sec
    int FPS = 60;
    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();//set a new KeyHandler from the class and name it keyH
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player Player = new Player(this,keyH);

//    TEST://set player's default position
//    int playerX = 100;
//    int playerY = 100;
//    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);//if set to true then all the drawing from this component
        // will be done in an offscreen painting buffer
        this.addKeyListener(keyH);//so the GamePanel can recognize key input
        this.setFocusable(true);//with this, this GamePanel can be "focused" to receive key input

    }

    public void startGameThread(){//start the game Thread

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {//start the game loop
        long DrawInterval = 1000000000/FPS; //we use 1 sec (in nanosec)/FPS  0.16666sec
        long nextDrawTime = System.nanoTime() + DrawInterval;

        while(gameThread != null){
            //TEST:System.out.println("The game loop is running");

            //TEST: long currentTime = System.nanoTime();//System.nanoTime() returns the current value of the running
            //Java Virtual Machine's high-resolution time source in nanoseconds.
            //1 billion nanosec = 1 sec
            //We can also use System.currentTimeMillis() as millisec but nano is more precise.
            //TEST: System.out.println("Current Time:" + currentTime);

            try {
                double remainingTime = nextDrawTime - System.nanoTime();//we want to know the remaining time
                // and let the thread sleep for the remaining time.
                //because thread.sleep only takes in millisec so we have to convert remainingTime into millisec
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0){ //if the remainingTime is negative, we don't need to sleep in case of lag or glitch
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);//thread.sleep method only takes in long so we have to super long
                nextDrawTime += DrawInterval; //set a new next draw time

            } catch (InterruptedException e) {//we also need to add try and catch to use thread.sleep
                throw new RuntimeException(e);
            }

            //1. UPDATE: update information such as character positions
            update();
            //2 DRAW: draw the screen with the updated information
            repaint();//the paintComponent method
            // if FPS is 60, we will do this loop 60times per second
        }
    }
    public void update(){
       Player.update(); //we write the code in Player class
    }

    public void paintComponent(Graphics g){ // A class tha has many functions
        // to draw objects on the screen
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; //Graphics2D class extends the Graphics class to provide more
        // sophisticated control over geometry, coordinate transformations,
        // color management, and text layout
        //it has more function compared to Graphics

        tileM.draw(g2);//draw the tile before player so that it will not cover the player
        Player.draw(g2);//use the method in Player class

        g2.dispose();//Dispose of this graphics context and release any
        // system resources that it is using
        // the program will work without this line but we can use it to save some memories
    }
}
