/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimerTask;
import javax.swing.Timer;
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
     private Map<Curse, Long> activeCurses = new HashMap<>();
    private int bombCount = 1;
    public int blastRange = 1;
    private boolean isInvincible = false;
    private boolean isDetonator = false;
    private boolean speedIncreased = false;
    public int dropped = 0;
     public ArrayList<Bomb> bombss = new ArrayList<>() ;
       private boolean canDropBombs = true; // New attribute to track if player can drop bombs
        private boolean forcedBombDrop = false; // New attribute to track if player is forced to drop bombs
        private boolean isGhost = false;
        private int obstacleCount = 0;

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
            public void setCanDropBombs(boolean canDrop) {
        this.canDropBombs = canDrop;
    }

    public boolean getCanDropBombs() {
        return this.canDropBombs;
    }
     public void setForcedBombDrop(boolean forced) {
        this.forcedBombDrop = forced;
    }

    public boolean getForcedBombDrop() {
        return this.forcedBombDrop;
    }
    
    //for ghost powerup
    public void setGhost(boolean isGhost) {
    this.isGhost = isGhost;
}
    public void increaseObstacleCount(int count) {
    obstacleCount += count;
      System.out.println("Obstacle count for " + name + ": " + obstacleCount); // Debugging output
}

public void placeObstacle(Tile[][] tiles) {
    System.out.println("Attempting to place obstacle: Current obstacle count = " + obstacleCount); // Debug output
    if (obstacleCount > 0 && tiles[currentRow][currentCol] instanceof Field) { // Ensure it's a field before placing an obstacle
        tiles[currentRow][currentCol] = new Obstacle(currentRow, currentCol);
        obstacleCount--;
        System.out.println("Obstacle placed by " + name + " at (" + currentRow + ", " + currentCol + ")");
        
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkIfMovedFromObstacle();
                ((Timer)e.getSource()).stop();  // Stop the timer after the check
            }
        });
        timer.setRepeats(false);  // Ensure the timer only runs once
        timer.start();
    }
     else {
        System.out.println("Failed to place obstacle by " + name + ": Either no obstacles left or not on a Field");
    }
}

private void checkIfMovedFromObstacle() {
    Tile currentTile = BombermanComponent.tiles[currentRow][currentCol];
    if (currentTile instanceof Obstacle) {
        isAlive = false;  // Player dies if still on the obstacle tile
        // Additional logic to handle player death, e.g., updating the game state or UI
        bombermanComponent.repaint();  // Assuming there is a reference to update the UI
    }
}
    
    
    public void moveUp(Tile[][] tiles) {
        if (!isAlive) return;
        int targetRow = currentRow - 1;
        if (targetRow >= 0 && (tiles[targetRow][currentCol].isPassable())||isGhost) {
            currentRow = targetRow;
            updatePowerUpsAndCurses();
            requestRepaint();
             if (forcedBombDrop) {
            dropBomb(tiles, BombermanComponent.bombs);  // Use the centralized bomb list for consistency
        }
        }
        
        

    }
int drp=0;
    public void moveDown(Tile[][] tiles) {
        if (!isAlive) return;
        int targetRow = currentRow + 1;
        if (targetRow < tiles.length && tiles[targetRow][currentCol].isPassable()||isGhost) {
            currentRow = targetRow; 
            updatePowerUpsAndCurses();
            requestRepaint(); 
             if (forcedBombDrop) {
            dropBomb(tiles, BombermanComponent.bombs);  // Use the centralized bomb list for consistency
        }

    }
    }

    public void moveLeft(Tile[][] tiles) {
        if (!isAlive) return;
        int targetCol = currentCol - 1;
        if (targetCol >= 0 && tiles[currentRow][targetCol].isPassable()||isGhost) {
            currentCol = targetCol;
            updatePowerUpsAndCurses();
            requestRepaint(); 
              if (forcedBombDrop) {
            dropBomb(tiles, BombermanComponent.bombs);  // Use the centralized bomb list for consistency
        }
        }
 
    }

    public void moveRight(Tile[][] tiles) {
        if (!isAlive) return;
        int targetCol = currentCol + 1;
        if (targetCol < tiles[0].length && tiles[currentRow][targetCol].isPassable()||isGhost) {
            currentCol = targetCol;
            updatePowerUpsAndCurses();
            requestRepaint(); 
             if (forcedBombDrop) {
            dropBomb(tiles, BombermanComponent.bombs);  // Use the centralized bomb list for consistency
        }
             
        }

    }
   Tile[][] tls;
     public void dropBomb(Tile[][] tiles, ArrayList<Bomb> bombs) {
         this.tls=tiles;
        if (!isAlive || !canDropBombs|| bombs.size() >= bombCount) return;
        
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
    public void pickUpCurse(Curse curse) {
    long expiryTime = System.currentTimeMillis() + curse.duration;
      //  System.out.println("Piched it up");
    activeCurses.put(curse, expiryTime);
    curse.applyEffect(this);
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
            dropped=0;
             it.remove();
             
            
        }
        
        if (currentTime > entry.getValue()) {
           // System.out.println("Duration done");
            entry.getKey().removeEffect(this);
            it.remove();
        }
    }
}
 public void updateCurses() {
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<Curse, Long>> iterator = activeCurses.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Curse, Long> entry = iterator.next();
            if (currentTime > entry.getValue()) { // Check if the curse has expired
                entry.getKey().removeEffect(this); // Remove the effect of the curse
                iterator.remove(); // Remove the curse from the map
            }
        }
    }

    // Method to be called periodically, e.g., in the game loop
    public void updatePowerUpsAndCurses() {
        updatePowerUps();
        updateCurses();
    }



    
    private void requestRepaint() {
        bombermanComponent.repaint();
    }

    public Image getPlayerImage() {
        return playerImage;
    }
}

