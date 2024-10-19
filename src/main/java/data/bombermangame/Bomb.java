/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Represents a bomb in the game.
 * This class is responsible for managing the bomb's properties and detonation.
 * It provides getter and setter methods to access and modify the bomb's properties.
 * 
 * @author Thet Naing Soe
 */
public class Bomb {
    private int row; // Row position of the bomb
    private int col; // Column position of the bomb
    private boolean exploded; // Indicates if the bomb has exploded
    public int explosionRadius; // Radius of the bomb's explosion (optional)

     /**
     * Constructs a new Bomb object at the specified row and column.
     * The bomb has not exploded yet when it is created.
     *
     * @param row the row position of the bomb
     * @param col the column position of the bomb
     * @param range the explosion radius of the bomb
     */
    public Bomb(int row, int col,int range) {
        this.row = row;
        this.col = col;
        this.exploded = false;
        // Set a default explosion radius or customize it as needed
        this.explosionRadius = range; // Example radius
    }

    // Getters and setters for row, col, and exploded fields
    

    /**
     * Returns the row position of the bomb.
     *
     * @return the row position of the bomb
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the row position of the bomb.
     *
     * @param row the new row position of the bomb
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Returns the column position of the bomb.
     *
     * @return the column position of the bomb
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the column position of the bomb.
     *
     * @param col the new column position of the bomb
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Returns whether the bomb has exploded.
     *
     * @return true if the bomb has exploded, false otherwise
     */
    public boolean isExploded() {
        return exploded;
    }

    /**
     * Sets whether the bomb has exploded.
     *
     * @param exploded true if the bomb has exploded, false otherwise
     */
    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    // Method to detonate the bomb
     /**
     * Detonates the bomb after a 2-second delay.
     * This method creates an explosion and applies its effects to the tiles.
     *
     * @param tiles the tiles of the game
     * @param range the explosion radius of the bomb
     */
    public void detonate(Tile[][] tiles, int range) {
        // Detonate the bomb after a 2-second delay
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Detonate the bomb
                exploded = true;
                System.out.println("Boom!");
                // Create explosions and apply effects
                Explosion explosion = new Explosion(row, col, range);
                explosion.detonate(tiles);
            }
        }, 2000); // 2000 milliseconds = 2 seconds
    }
    /**
     * Returns the explosion radius of the bomb.
     *
     * @return the explosion radius of the bomb
     */
    public int getExplosionRadius() {
        return explosionRadius;
    }

    // Optional method to set the explosion radius
    /**
     * Sets the explosion radius of the bomb.
     *
     * @param explosionRadius the new explosion radius of the bomb
     */
    public void setExplosionRadius(int explosionRadius) {
        this.explosionRadius = explosionRadius;
    }
}
