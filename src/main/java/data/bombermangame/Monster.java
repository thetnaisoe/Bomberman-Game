/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.io.*;
import java.util.List; // Import only List from java.util
import java.util.ArrayList;
import javax.swing.Timer;  
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
/**
 *
 * @author ThetNaingSoe
 */
public class Monster {

    public int currentRow;
    public int currentCol;
    private BombermanComponent bombermanComponent; 
    public Image monsterImage;
    private static final int SQUARE_SIZE = 50;
    private Tile[][] tiles;
    private ArrayList<Player> players;
    
    public Monster(Tile[][] tiles, int initialRow, int initialCol, ArrayList<Player> players, String imagePath) {
        // Store references
        this.tiles = tiles;
        this.currentRow = initialRow;
        this.currentCol = initialCol;
        this.players = players;
        
        // Load the monster image
        try {
            Image image = ImageIO.read(new File(imagePath)); 
            this.monsterImage = image.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("Error loading monster image: " + imagePath); // More prominent error
            e.printStackTrace();
        }

 
    }
    
    public Image getMonsterImage() {
        return monsterImage;
    }
    
    public void moveToRandomDirection() {
        Random random = new Random();
        int direction = random.nextInt(4); // Generate a random direction (0: up, 1: down, 2: left, 3: right)

        int newRow = currentRow;
        int newCol = currentCol;

        // Calculate new position based on the direction
        switch (direction) {
            case 0: // Move up
                newRow = Math.max(0, currentRow - 1);
                System.out.println("Current position: " + currentRow + ", " + currentCol);
                System.out.println("New position: " + newRow + ", " + newCol);
                System.out.println("Passable: " + tiles[newRow][newCol].isPassable()); 
                break;
            case 1: // Move down
                newRow = Math.min(tiles.length - 1, currentRow + 1);
                System.out.println("Current position: " + currentRow + ", " + currentCol);
                System.out.println("New position: " + newRow + ", " + newCol);
                System.out.println("Passable: " + tiles[newRow][newCol].isPassable());                 
                break;
            case 2: // Move left
                newCol = Math.max(0, currentCol - 1);
                System.out.println("Current position: " + currentRow + ", " + currentCol);
                System.out.println("New position: " + newRow + ", " + newCol);
                System.out.println("Passable: " + tiles[newRow][newCol].isPassable());                
                break;
            case 3: // Move right
                newCol = Math.min(tiles[0].length - 1, currentCol + 1);
                System.out.println("Current position: " + currentRow + ", " + currentCol);
                System.out.println("New position: " + newRow + ", " + newCol);
                System.out.println("Passable: " + tiles[newRow][newCol].isPassable());                 
                break;
        }

        // Check if the new position is passable and doesn't overlap with a player
        if (tiles[newRow][newCol].isPassable() && !collidesWithPlayer(newRow, newCol)) {
            currentRow = newRow;
            currentCol = newCol;
        }
    } 
    
    private boolean collidesWithPlayer(int row, int col) {
        for (Player player : players) { // Assuming you have access to the players list
            if (player.isAlive && player.currentRow == row && player.currentCol == col) {
                handlePlayerCollision(player); // Pass the collided player to the handler
                return true; 
            }
        }
        return false; 
    }

    private void handlePlayerCollision(Player player) {
        player.isAlive = false;
        if (!player.isAlive) { 
            //endGame(); 
        }
    }
    
    


           
}
