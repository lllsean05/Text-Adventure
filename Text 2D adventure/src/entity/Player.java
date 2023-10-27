package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {//we can basically do all of these in the GamePanel paintcomponent method but it will be too
    // complicated since it can be many lines of code so we do them here.
    GamePanel gp;
    KeyHandler keyH;
    public Player(GamePanel gp,KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValue();
        getPlayerImage();//get the image in the constructor
    }
    public void setDefaultValue(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";//set default direction = down
    }

    public void getPlayerImage(){

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    //we can basically do all of these in the GamePanel paintcomponent method but it will be too
    // complicated since it can be many lines of code so we do them here.
    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) { //the spritecounter can only increase if we press the key to make the player move
            if(keyH.upPressed == true){
                direction = "up";
                y -= speed;//in Java the upper left corner is X:0,Y:0
                //X values increases to the right and Y value increases as they go down
            }
            else if(keyH.downPressed == true){
                direction = "down";
                y += speed;
            }
            else if(keyH.leftPressed == true){
                direction = "left";
                x -= speed;
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                x += speed;
            }
            spriteCounter ++; //Every time the loop goes, the counter will increase and when it hits 12,
            //we will switch the spriteNum which in turn we change the image.
            if (spriteCounter > 10){
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter =0;
            }
        }
    }
    public void draw(Graphics2D g2){
//        The code below is a initial developing default character--white square
//        g2.setColor(Color.WHITE);//setColor(Color c): set a color to use for drawing objects
//
//        g2.fillRect(x,y,gp.tileSize,gp.tileSize);//fillRect(intx, inty, intwidth, intheight):Draw a rectangle and
//        // fills it with the specified color
//        //this is our character size
//        //we can change the player's location by changing the PLayerX, playerY in this line

        BufferedImage image = null;

        switch(direction){//based on the direction, we pick the image coordinately
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {//to use both of the images to make the character moving(switching from 2 images)
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);//drawImage():Draw an image on the screen
        //the last argument is called image observer which is not be used in this case.
    }
}

