package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {//Entity class stores variables that will be used in player, monster, and NPC classes.

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    //BufferedImage describes an Image with an accessible bugger of
    // image data(we use this to store out image files)
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea; // create an invisible or abstract rectangle
    public boolean collisionOn = false; //switch for collision
}
