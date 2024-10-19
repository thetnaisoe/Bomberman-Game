package data.bombermangame;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimerTask;
import javax.swing.Timer;

/**
 * The Player class represents a player in the Bomberman game.
 * It handles player movements, actions (like dropping bombs), and interactions with power-ups and curses.
 * 
 * @author DATA
 */
public class Player {
    private int upKey, downKey, leftKey, rightKey, bombKey, obstacleKey;
    public String name;
    public int currentRow;
    public int currentCol;
    public boolean isAlive;
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
    public int gamesWon = 0;
    public ArrayList<Bomb> bombss = new ArrayList<>();
    private boolean canDropBombs = true; // New attribute to track if player can drop bombs
    private boolean forcedBombDrop = false; // New attribute to track if player is forced to drop bombs
    private boolean isGhost = false;
    private int obstacleCount = 0;
    private ArrayList<Monster> monsters;
    private Map<String, Integer> keyBindings;

    /**
     * Constructs a Player object with the specified parameters.
     * 
     * @param name the name of the player.
     * @param initialRow the initial row position of the player.
     * @param initialCol the initial column position of the player.
     * @param bombermanComponent the BombermanComponent associated with this player.
     * @param monsters the list of monsters in the game.
     * @param imagePath the path to the image representing the player.
     * @param keyBindings the key bindings for player controls.
     */
    public Player(String name, int initialRow, int initialCol, BombermanComponent bombermanComponent, ArrayList<Monster> monsters, String imagePath, Map<String, Integer> keyBindings) {
        this.keyBindings = keyBindings;
        this.name = name;
        this.currentRow = initialRow;
        this.currentCol = initialCol;
        this.monsters = monsters;
        isAlive = true;
        this.bombermanComponent = bombermanComponent;
        try {
            Image loadedImage = ImageIO.read(new File(imagePath));
            // Resize the image
            this.playerImage = loadedImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the bounding rectangle of the player.
     * 
     * @return a Rectangle representing the player's bounds.
     */
    public Rectangle getBounds() {
        return new Rectangle(currentCol * SQUARE_SIZE, currentRow * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    /**
     * Gets the number of games won by the player.
     * 
     * @return the number of games won.
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Sets the number of games won by the player.
     * 
     * @param gamesWon the number of games won.
     */
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * Gets the key bindings for the player.
     * 
     * @return a map of key bindings.
     */
    public Map<String, Integer> getKeyBindings() {
        return keyBindings;
    }

    /**
     * Gets the name of the player.
     * 
     * @return the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Increments the bomb count for the player.
     */
    public void incrementBombCount() {
        bombCount++;
    }

    /**
     * Decrements the bomb count for the player.
     */
    public void decrementBombCount() {
        // bombCount = Math.max(1, bombCount - 1); // Ensure bomb count doesn't drop below 1
    }

    /**
     * Increments the blast range for the player's bombs.
     */
    public void incrementBlastRange() {
        blastRange++;
    }

    /**
     * Decrements the blast range for the player's bombs.
     */
    public void decrementBlastRange() {
        blastRange = Math.max(1, blastRange - 1); // Ensure blast range doesn't drop below 1
    }

    /**
     * Increases the player's movement speed.
     */
    public void increaseSpeed() {
        if (!speedIncreased) {
            speedIncreased = true;
            // Increase movement speed, e.g., reduce movement delay or increase movement increment
        }
    }

    /**
     * Sets the player's invincibility status.
     * 
     * @param invincible true to make the player invincible, false otherwise.
     */
    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
        // Optionally add visual indicator
    }

    /**
     * Gets the player's invincibility status.
     * 
     * @return true if the player is invincible, false otherwise.
     */
    public boolean getInvincible() {
        return isInvincible;
    }

    /**
     * Sets the player's detonator status.
     * 
     * @param deto true to give the player a detonator, false otherwise.
     */
    public void setDetonator(boolean deto) {
        isDetonator = deto;
    }

    /**
     * Gets the player's detonator status.
     * 
     * @return true if the player has a detonator, false otherwise.
     */
    public boolean setDetonator() {
        return isDetonator;
    }

    /**
     * Sets whether the player can drop bombs.
     * 
     * @param canDrop true to allow the player to drop bombs, false otherwise.
     */
    public void setCanDropBombs(boolean canDrop) {
        this.canDropBombs = canDrop;
    }

    /**
     * Gets whether the player can drop bombs.
     * 
     * @return true if the player can drop bombs, false otherwise.
     */
    public boolean getCanDropBombs() {
        return this.canDropBombs;
    }

    /**
     * Sets whether the player is forced to drop bombs.
     * 
     * @param forced true to force the player to drop bombs, false otherwise.
     */
    public void setForcedBombDrop(boolean forced) {
        this.forcedBombDrop = forced;
    }

    /**
     * Gets whether the player is forced to drop bombs.
     * 
     * @return true if the player is forced to drop bombs, false otherwise.
     */
    public boolean getForcedBombDrop() {
        return this.forcedBombDrop;
    }

    /**
     * Sets the player's ghost status.
     * 
     * @param isGhost true to make the player a ghost, false otherwise.
     */
    public void setGhost(boolean isGhost) {
        this.isGhost = isGhost;
    }

    /**
     * Increases the player's obstacle count.
     * 
     * @param count the number of obstacles to add.
     */
    public void increaseObstacleCount(int count) {
        obstacleCount += count;
        System.out.println("Obstacle count for " + name + ": " + obstacleCount); // Debugging output
    }

    /**
     * Places an obstacle at the player's current position.
     * 
     * @param tiles the game area represented by a 2D array of tiles.
     */
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
        } else {
            System.out.println("Failed to place obstacle by " + name + ": Either no obstacles left or not on a Field");
        }
    }

    /**
     * Checks if the player has moved from the obstacle tile.
     * If the player is still on the obstacle tile after a delay, the player dies.
     */
    private void checkIfMovedFromObstacle() {
        Tile currentTile = BombermanComponent.tiles[currentRow][currentCol];
        if (currentTile instanceof Obstacle) {
            isAlive = false;  // Player dies if still on the obstacle tile
            // Additional logic to handle player death, e.g., updating the game state or UI
            bombermanComponent.repaint();  // Assuming there is a reference to update the UI
        }
    }

    /**
     * Moves the player up.
     * 
     * @param tiles the game area represented by a 2D array of tiles.
     */
    public void moveUp(Tile[][] tiles) {
        if (!isAlive) return;
        int targetRow = currentRow - 1;
        if (targetRow >= 0 && (tiles[targetRow][currentCol].isPassable()) || isGhost) {
            currentRow = targetRow;
            updatePowerUpsAndCurses(); // Check for active power-ups and curses
            if (checkForCollisionWithMonsters()) { // Check for collision with monsters
                handleMonsterCollision();
            }
            requestRepaint();
            if (forcedBombDrop) {
                dropBomb(tiles, BombermanComponent.bombs);  // Use the centralized bomb list for consistency
            }
        }
    }

    /**
     * Moves the player down.
     * 
     * @param tiles the game area represented by a 2D array of tiles.
     */
    public void moveDown(Tile[][] tiles) {
        if (!isAlive) return;
        int targetRow = currentRow + 1;
        if (targetRow < tiles.length && (tiles[targetRow][currentCol].isPassable()) || isGhost) {
            currentRow = targetRow;
            updatePowerUpsAndCurses();
            if (checkForCollisionWithMonsters()) { // Check for collision with monsters
                handleMonsterCollision();
            }
            requestRepaint();
            if (forcedBombDrop) {
                dropBomb(tiles, BombermanComponent.bombs);  // Use the centralized bomb list for consistency
            }
        }
    }

    /**
     * Moves the player left.
     * 
     * @param tiles the game area represented by a 2D array of tiles.
     */
    public void moveLeft(Tile[][] tiles) {
        if (!isAlive) return;
        int targetCol = currentCol - 1;
        if (targetCol >= 0 && (tiles[currentRow][targetCol].isPassable()) || isGhost) {
            currentCol = targetCol;
            updatePowerUpsAndCurses();
            if (checkForCollisionWithMonsters()) { // Check for collision with monsters
                handleMonsterCollision();
            }
            requestRepaint();
            if (forcedBombDrop) {
                dropBomb(tiles, BombermanComponent.bombs);  // Use the centralized bomb list for consistency
            }
        }
    }

    /**
     * Moves the player right.
     * 
     * @param tiles the game area represented by a 2D array of tiles.
     */
    public void moveRight(Tile[][] tiles) {
        if (!isAlive) return;
        int targetCol = currentCol + 1;
        if (targetCol < tiles[0].length && (tiles[currentRow][targetCol].isPassable()) || isGhost) {
            currentCol = targetCol;
            updatePowerUpsAndCurses();
            if (checkForCollisionWithMonsters()) { // Check for collision with monsters
                handleMonsterCollision();
            }
            requestRepaint();
            if (forcedBombDrop) {
                dropBomb(tiles, BombermanComponent.bombs);  // Use the centralized bomb list for consistency
            }
        }
    }

    /**
     * Checks if the player has collided with any monsters.
     * 
     * @return true if the player has collided with a monster, false otherwise.
     */
    public boolean checkForCollisionWithMonsters() {
        for (Monster monster : monsters) { // Assuming you have access to the monsters list
            if (monster.currentRow == currentRow && monster.currentCol == currentCol) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles the player's collision with a monster.
     * Sets the player's alive status to false.
     */
    public void handleMonsterCollision() {
        isAlive = false;
    }

    Tile[][] tls;

    /**
     * Drops a bomb at the player's current position.
     * 
     * @param tiles the game area represented by a 2D array of tiles.
     * @param bombs the list of bombs in the game.
     */
    public void dropBomb(Tile[][] tiles, ArrayList<Bomb> bombs) {
        this.tls = tiles;
        if (!isAlive || !canDropBombs || bombs.size() >= bombCount) return;

        Bomb bomb = new Bomb(currentRow, currentCol, blastRange); // Assume Bomb constructor can take blastRange
        bombs.add(bomb);
        dropped++;
        if (!this.isDetonator) {
            bomb.detonate(tiles, blastRange); // Ensure bomb detonation logic uses the blastRange
            dropped--;
        } else {
            bombss.add(bomb);
        }
    }

    /**
     * Picks up a curse and applies its effect to the player.
     * 
     * @param curse the curse to be picked up.
     */
    public void pickUpCurse(Curse curse) {
        long expiryTime = System.currentTimeMillis() + curse.duration;
        activeCurses.put(curse, expiryTime);
        curse.applyEffect(this);
    }

    /**
     * Picks up a power-up and applies its effect to the player.
     * 
     * @param powerUp the power-up to be picked up.
     */
    public void pickUpPowerUp(PowerUp powerUp) {
        long expiryTime = System.currentTimeMillis() + powerUp.duration;
        activePowerUps.put(powerUp, expiryTime);
        powerUp.applyEffect(this);
    }

    /**
     * Updates the active power-ups, removing expired ones.
     * Call this method periodically, e.g., every frame or on a timer.
     */
    public void updatePowerUps() {
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<PowerUp, Long>> it = activePowerUps.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<PowerUp, Long> entry = it.next();
            if (isDetonator && entry.getKey() instanceof Detonator && dropped == bombCount) {
                entry.getKey().removeEffect(this);
                for (Bomb bmb : bombss) {
                    bmb.detonate(tls, blastRange);
                }
                dropped = 0;
                it.remove();
            }

            if (currentTime > entry.getValue()) {
                entry.getKey().removeEffect(this);
                it.remove();
            }
        }
    }

    /**
     * Sets the player's current row position.
     * 
     * @param row the new row position.
     */
    public void setCurrentRow(int row) {
        this.currentRow = row;
    }

    /**
     * Sets the player's current column position.
     * 
     * @param col the new column position.
     */
    public void setCurrentCol(int col) {
        this.currentCol = col;
    }

    /**
     * Gets the player's current row position.
     * 
     * @return the current row position.
     */
    public int getCurrentRow() {
        return currentRow;
    }

    /**
     * Gets the player's current column position.
     * 
     * @return the current column position.
     */
    public int getCurrentCol() {
        return currentCol;
    }

    /**
     * Updates the active curses, removing expired ones.
     * Call this method periodically, e.g., every frame or on a timer.
     */
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

    /**
     * Updates both active power-ups and curses, removing expired ones.
     * Call this method periodically, e.g., in the game loop.
     */
    public void updatePowerUpsAndCurses() {
        updatePowerUps();
        updateCurses();
    }

    /**
     * Requests a repaint of the BombermanComponent to update the UI.
     */
    private void requestRepaint() {
        bombermanComponent.repaint();
    }

    /**
     * Gets the image representing the player.
     * 
     * @return the player's image.
     */
    public Image getPlayerImage() {
        return playerImage;
    }

    // Key bindings getters and setters

    /**
     * Gets the key code for moving up.
     * 
     * @return the key code for moving up.
     */
    public int getUpKey() { return upKey; }

    /**
     * Sets the key code for moving up.
     * 
     * @param key the key code for moving up.
     */
    public void setUpKey(int key) { this.upKey = key; }

    /**
     * Gets the key code for moving down.
     * 
     * @return the key code for moving down.
     */
    public int getDownKey() { return downKey; }

    /**
     * Sets the key code for moving down.
     * 
     * @param key the key code for moving down.
     */
    public void setDownKey(int key) { this.downKey = key; }

    /**
     * Gets the key code for moving left.
     * 
     * @return the key code for moving left.
     */
    public int getLeftKey() { return leftKey; }

    /**
     * Sets the key code for moving left.
     * 
     * @param key the key code for moving left.
     */
    public void setLeftKey(int key) { this.leftKey = key; }

    /**
     * Gets the key code for moving right.
     * 
     * @return the key code for moving right.
     */
    public int getRightKey() { return rightKey; }

    /**
     * Sets the key code for moving right.
     * 
     * @param key the key code for moving right.
     */
    public void setRightKey(int key) { this.rightKey = key; }

    /**
     * Gets the key code for dropping a bomb.
     * 
     * @return the key code for dropping a bomb.
     */
    public int getBombKey() { return bombKey; }

    /**
     * Sets the key code for dropping a bomb.
     * 
     * @param key the key code for dropping a bomb.
     */
    public void setBombKey(int key) { this.bombKey = key; }

    /**
     * Gets the key code for placing an obstacle.
     * 
     * @return the key code for placing an obstacle.
     */
    public int getObstacleKey() { return obstacleKey; }

    /**
     * Sets the key code for placing an obstacle.
     * 
     * @param key the key code for placing an obstacle.
     */
    public void setObstacleKey(int key) { this.obstacleKey = key; }
}
