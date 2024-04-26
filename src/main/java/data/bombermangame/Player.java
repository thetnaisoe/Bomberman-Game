/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 *
 * @author DATA
 */
public class Player {
    public String name;
    public int currentRow;
    public int currentCol;
    public boolean isAlive = true;
    private BombermanComponent bombermanComponent; 
    private Image playerImage;
    private static final int SQUARE_SIZE = 50;
    private Map<PowerUp, Long> activePowerUps = new HashMap<>();
    private int bombCount = 1;
    public int blastRange = 1;
    private boolean isInvincible = false;
    private boolean isDetonator = false;
    private boolean speedIncreased = false;
    public int dropped = 0;
     public ArrayList<Bomb> bombss = new ArrayList<>() ;

    // Constructor
    public Player(String name, int initialRow, int initialCol, BombermanComponent bombermanComponent, String imagePath ) {
        this.name = name;
        this.currentRow = initialRow;
        this.currentCol = initialCol;
        this.bombermanComponent = bombermanComponent;
        try {
            Image loadedImage = ImageIO.read(new File(imagePath));
            // Resize the image
            this.playerImage = loadedImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getName(){
        return name;
    }
    public void incrementBombCount() {
        bombCount++;
    }
     public void decrementBombCount() {
       // bombCount = Math.max(1, bombCount - 1); // Ensure bomb count doesn't drop below 1
    }
      public void incrementBlastRange() {
        blastRange++;
    }
      public void decrementBlastRange() {
        blastRange = Math.max(1, blastRange - 1); // Ensure blast range doesn't drop below 1
    }

    public void increaseSpeed() {
        if (!speedIncreased) {
            speedIncreased = true;
            // Increase movement speed, e.g., reduce movement delay or increase movement increment
        }
    }
     public void setInvincible(boolean invincible) {
        isInvincible = invincible;
        // Optionally add visual indicator
    }
          public boolean getInvincible() {
        return isInvincible;
        // Optionally add visual indicator
    }
          public void setDetonator(boolean deto){
              isDetonator = deto;
          }
            public boolean setDetonator(){
              return isDetonator;
          }

    
    
    public void moveUp(Tile[][] tiles) {
        if (!isAlive) return;
        int targetRow = currentRow - 1;
        if (targetRow >= 0 && tiles[targetRow][currentCol].isPassable()) {
            currentRow = targetRow;
            updatePowerUps();
            requestRepaint();
        }

    }

    public void moveDown(Tile[][] tiles) {
        if (!isAlive) return;
        int targetRow = currentRow + 1;
        if (targetRow < tiles.length && tiles[targetRow][currentCol].isPassable()) {
            currentRow = targetRow; 
            updatePowerUps();
            requestRepaint(); 
        }

    }

    public void moveLeft(Tile[][] tiles) {
        if (!isAlive) return;
        int targetCol = currentCol - 1;
        if (targetCol >= 0 && tiles[currentRow][targetCol].isPassable()) {
            currentCol = targetCol;
            updatePowerUps();
            requestRepaint(); 
        }
 
    }

    public void moveRight(Tile[][] tiles) {
        if (!isAlive) return;
        int targetCol = currentCol + 1;
        if (targetCol < tiles[0].length && tiles[currentRow][targetCol].isPassable()) {
            currentCol = targetCol;
            updatePowerUps();
            requestRepaint(); 
        }

    }
   Tile[][] tls;
     public void dropBomb(Tile[][] tiles, ArrayList<Bomb> bombs) {
         this.tls=tiles;
        if (!isAlive || bombs.size() >= bombCount) return;
        int b= bombCount;
        Bomb bomb = new Bomb(currentRow, currentCol, blastRange); // Assume Bomb constructor can take blastRange
        bombs.add(bomb);
        dropped++;
        if(!this.isDetonator){ 
        bomb.detonate(tiles, blastRange); // Ensure bomb detonation logic uses the blastRange
        dropped--;
        }
        else {
            bombss.add(bomb);
            
           // bomb.detonate(tiles, blastRange);
            
       }
    }
    public void pickUpPowerUp(PowerUp powerUp) {
    long expiryTime = System.currentTimeMillis() + powerUp.duration;
      //  System.out.println("Piched it up");
    activePowerUps.put(powerUp, expiryTime);
    powerUp.applyEffect(this);
}

// Call this method periodically, e.g., every frame or on a timer
public void updatePowerUps() {
    //System.out.println("Now time is: " +System.currentTimeMillis());
    
    long currentTime = System.currentTimeMillis();
    Iterator<Map.Entry<PowerUp, Long>> it = activePowerUps.entrySet().iterator();
   
    while (it.hasNext()) {
        Map.Entry<PowerUp, Long> entry = it.next();
        // System.out.println("Duration of PU is: "+ entry.getKey().duration);
        System.out.println("BCOunt is: "+ bombCount + "dropped count: "+ dropped);
        
        if(isDetonator&&entry.getKey()  instanceof Detonator  && dropped==bombCount){
            entry.getKey().removeEffect(this);
            for(Bomb bmb : bombss){
            bmb.detonate(tls, blastRange);}
             it.remove();
             
            
        }
        
        if (currentTime > entry.getValue()) {
           // System.out.println("Duration done");
            entry.getKey().removeEffect(this);
            it.remove();
        }
    }
}



    
    private void requestRepaint() {
        bombermanComponent.repaint();
    }

    public Image getPlayerImage() {
        return playerImage;
    }
}

