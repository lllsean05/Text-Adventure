package main;

import entity.Entity;

public class CollisionChecker {
//this entire class is to check if the player character gets to the tile that we set collison=true.
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
// get the coordinate of the rectangle that is solid.
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
//convert the coordinate into the rectangle that is solid in the number of tiles.
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1;
        int tileNum2;

        switch (entity.direction){//if we hit any moving key, we will put the corresponding row or the column to get the corresponding boundary
            // of the solid rectangle and to check if the tile that the boundary hits is solid or not.
            //if the tile is solid then we set the entity.collisionOn = true.

            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;//get the boundary of the top solid rectangle
                //Why do we add or subtract the moving speed? Because we want to make it look like 3D so there is a shadowing visual effect making it can move
                //into the tile a little.
                //this is why you will see that when moving up you will stop even before hitting the tile but moving down you will not hit until you go into part of the tile
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];//get the tile at topleft corner of the rectangle
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//get the tile at topright corner of the rectangle
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//check if the top two corners tile is solid or not
                    entity.collisionOn = true;//if the two tile are solid or one of them are solid, then we set collisionOn = true
                }
                break;
                //same logic applies below except we add or subtract the speed to make the visual effect, and we want to get the corresponding boundaries as well
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn =true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn =true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn =true;
                }
                break;
        }
    }
}
