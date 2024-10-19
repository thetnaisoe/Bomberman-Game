/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author lenovo
 */


/**
 * Obstacle class represents an unbreakable block that does not contain any items.
 */
public class Obstacle extends Tile {
    private static final String OBSTACLE_IMAGE_PATH = "assets/fields/block.png"; // Define the path to the obstacle image

    public Obstacle(int row, int col) {
        super(row, col);
        this.tileType = TileType.OBSTACLE; // Assuming TileType is an enum you have defined for different types of tiles
        try {
            this.setImage(ImageIO.read(new File(OBSTACLE_IMAGE_PATH))); // Load the obstacle image
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions if the image cannot be loaded
        }
    }

    @Override
    public boolean isPassable() {
        return false; // Obstacles are not passable
    }

    @Override
    public boolean canDrop() {
        return false; // Obstacles do not allow drops
    }

    @Override
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed; // Optionally add additional logic for when an obstacle is destroyed
    }

    @Override
    public boolean destroyed() {
        return this.destroyed; // Return the destruction state
    }
}
