/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author lenovo
 */
public class Explosion {
   
    private int rowIndex;
    private int colIndex;
    private int duration; // Duration of the explosion display
    private int blastRange; // Range of the explosion
    private int explosionRadius; // Radius of the explosion (usually the same as range)
    private int timeToExplosion; // Time until the explosion occurs

    public Explosion(int rowIndex, int colIndex, int duration, int blastRange) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.duration = duration;
        this.blastRange = blastRange;
        this.explosionRadius = blastRange;
        this.timeToExplosion = duration;
    }
    /* public List<Explodable> getObjectsInRange(JButton[][] buttons,Tile[][] tiles) {
    
    List<Explodable> objectsInRange = new ArrayList<>();
    for (int dx = -blastRange; dx <= blastRange; dx++) {
        int newX = rowIndex + dx;
        for (int dy = -blastRange; dy <= blastRange; dy++) {
            int newY = colIndex + dy;
            // Ensure newX and newY are within the bounds of the game board
            if (newX >= 0 && newX < tiles.length && newY >= 0 && newY < tiles[0].length) {
                Tile tile = tiles[newX][newY];
                // Check if the tile is explodable
                if (tile instanceof Explodable) {
                    System.out.println("Explodable found at: " + newX + ", " + newY); // Debug line
                    objectsInRange.add((Explodable) tile);
                }
            }
        }
    }
    return objectsInRange;
}*/
    public List<Explodable> getObjectsInRange(JButton[][] buttons, Tile[][] tiles) {
    List<Explodable> objectsInRange = new ArrayList<>();

    // Helper method to check a direction until an obstacle is encountered
    for (int step = 1; step <= blastRange; step++) {
        int newX = rowIndex;
        int newY = colIndex + step;
        if (!isValidAndExplodable(newX, newY, tiles, objectsInRange)) break;
    }

    // Left
    for (int step = 1; step <= blastRange; step++) {
        int newX = rowIndex;
        int newY = colIndex - step;
        if (!isValidAndExplodable(newX, newY, tiles, objectsInRange)) break;
    }

    // Down
    for (int step = 1; step <= blastRange; step++) {
        int newX = rowIndex + step;
        int newY = colIndex;
        if (!isValidAndExplodable(newX, newY, tiles, objectsInRange)) break;
    }

    // Up
    for (int step = 1; step <= blastRange; step++) {
        int newX = rowIndex - step;
        int newY = colIndex;
        if (!isValidAndExplodable(newX, newY, tiles, objectsInRange)) break;
    }
    return objectsInRange;
}
     public int getBlastRadius() {
        return blastRange;
    }
     public void detonate(JButton[][] buttons, Tile[][] tiles) {
    List<JButton> affectedButtons = new ArrayList<>();

    // Apply the explosion effect in each direction and collect affected buttons
    // Right
    for (int step = 1; step <= blastRange; step++) {
        if (!applyExplosionEffect(rowIndex, colIndex + step, buttons, tiles, affectedButtons)) break;
    }
    // Left
    for (int step = 1; step <= blastRange; step++) {
        if (!applyExplosionEffect(rowIndex, colIndex - step, buttons, tiles, affectedButtons)) break;
    }
    // Down
    for (int step = 1; step <= blastRange; step++) {
        if (!applyExplosionEffect(rowIndex + step, colIndex, buttons, tiles, affectedButtons)) break;
    }
    // Up
    for (int step = 1; step <= blastRange; step++) {
        if (!applyExplosionEffect(rowIndex - step, colIndex, buttons, tiles, affectedButtons)) break;
    }

    // Timer to clear the explosion effect
    SwingUtilities.invokeLater(() -> {
        Timer clearExplosionTimer = new Timer(400, e -> {
            for (JButton button : affectedButtons) {
                button.setBackground(Color.GRAY); // Reset to default color after explosion
            }
        });
        clearExplosionTimer.setRepeats(false);
        clearExplosionTimer.start();
    });
}

private boolean applyExplosionEffect(int newX, int newY, JButton[][] buttons, Tile[][] tiles, List<JButton> affectedButtons) {
    if (newX < 0 || newX >= tiles.length || newY < 0 || newY >= tiles[0].length) {
        return false; // Out of bounds, stop explosion in this direction
    }
    Tile tile = tiles[newX][newY];
    if (tile instanceof Wall) {
        return false; // Stop explosion at a wall
    }
    // Apply visual effect to explodable objects and continue explosion path
    if (!(tile instanceof Wall)) { // Assuming Wall is not explodable
        JButton button = buttons[newX][newY];
        button.setBackground(Color.ORANGE);
        affectedButtons.add(button);
        if (tile instanceof Box) {
            return false; // Stop explosion at the first box
        }
    }
    return true; // Continue explosion in this direction
}
    

    private boolean isValidAndExplodable(int newX, int newY, Tile[][] tiles, List<Explodable> objectsInRange) {
        if (newX < 0 || newX >= tiles.length || newY < 0 || newY >= tiles[0].length) {
        return false; // Out of bounds
    }
    Tile tile = tiles[newX][newY];
    if (tile instanceof Wall) {
        return false; // Stop at walls
    } else if (tile instanceof Explodable) {
        objectsInRange.add((Explodable) tile);
        return !(tile instanceof Box); // Continue if not a box; stop if it's a box
    }
    return true;
    }
    }
    

