/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;
import java.util.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JOptionPane;

/**
 * Represents a monster in the Bomberman game.
 * This class provides methods for moving the monster in a random direction and handling collisions with players.
 * The monster is represented by an image and is located on a grid of tiles.
 * The monster can collide with players, causing them to die.
 *
 * @author ThetNaingSoe
 */
public class Monster {

    public int currentRow;
    public int currentCol;
    public Image monsterImage;
    private static final int SQUARE_SIZE = 50;
    private Tile[][] tiles;
    private ArrayList<Player> players;
    public boolean isAlive;

    /**
     * Constructs a new Monster with the specified tiles, initial position, players, and image path.
     *
     * @param tiles the grid of tiles on which the monster is located
     * @param initialRow the initial row of the monster
     * @param initialCol the initial column of the monster
     * @param players the list of players in the game
     * @param imagePath the path to the image file representing the monster
     */   
    public Monster(Tile[][] tiles, int initialRow, int initialCol, ArrayList<Player> players, String imagePath) {
        // Store references
        this.tiles = tiles;
        this.currentRow = initialRow;
        this.currentCol = initialCol;
        this.players = players;
        isAlive = true;
        
        // Load the monster image
        try {
            Image image = ImageIO.read(new File(imagePath)); 
            this.monsterImage = image.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("Error loading monster image: " + imagePath); // More prominent error
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the bounding rectangle of the monster.
     *
     * @return the bounding rectangle of the monster
     */
    public Rectangle getBounds() {
        return new Rectangle(currentCol * SQUARE_SIZE, currentRow * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }
    
    /**
     * Returns the image of the monster.
     *
     * @return the image of the monster
     */
    public Image getMonsterImage() {
        return monsterImage;
    }

    /**
     * Moves the monster in a random direction.
     * The monster can move up, down, left, or right, but cannot move onto a non-passable tile or onto a tile occupied by a player.
     */
    public void moveToRandomDirection() {
        Random random = new Random();
        int direction = random.nextInt(4); // Generate a random direction (0: up, 1: down, 2: left, 3: right)

        int newRow = currentRow;
        int newCol = currentCol;

        // Calculate new position based on the direction
        switch (direction) {
            case 0: // Move up
                newRow = Math.max(0, currentRow - 1);
                break;
            case 1: // Move down
                newRow = Math.min(tiles.length - 1, currentRow + 1);     
                break;
            case 2: // Move left
                newCol = Math.max(0, currentCol - 1);       
                break;
            case 3: // Move right
                newCol = Math.min(tiles[0].length - 1, currentCol + 1);           
                break;
        }

        // Check if the new position is passable and doesn't overlap with a player
        if (tiles[newRow][newCol].isPassable() && !collidesWithPlayer(newRow, newCol)) {
            currentRow = newRow;
            currentCol = newCol;
        }
    } 
    
    /**
     * Checks if the monster collides with a player at the specified position.
     * If the monster collides with a player, it handles the collision by calling the handlePlayerCollision method.
     *
     * @param row the row of the position to check
     * @param col the column of the position to check
     * @return true if the monster collides with a player, false otherwise
     */    
    public boolean collidesWithPlayer(int row, int col) {
        for (Player player : players) { // Assuming you have access to the players list
            if (player.isAlive && player.currentRow == row && player.currentCol == col) {
                handlePlayerCollision(player); // Pass the collided player to the handler
                return true; 
            }
        }
        return false; 
    }

    /**
     * Handles a collision between the monster and a player.
     * This method is called when the monster collides with a player.
     * It kills the player by setting the player's isAlive property to false.
     *
     * @param player the player with whom the monster has collided
     */
    public void handlePlayerCollision(Player player) {
        player.isAlive = false;
    }
}
