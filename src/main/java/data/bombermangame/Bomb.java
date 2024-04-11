/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;
/**
 *
 * @author aryan
 */
public class Bomb {
    private int row; // Row position of the bomb
    private int col; // Column position of the bomb
    private boolean exploded; // Indicates if the bomb has exploded
    public int explosionRadius; // Radius of the bomb's explosion (optional)

    public Bomb(int row, int col) {
        this.row = row;
        this.col = col;
        this.exploded = false;
        // Set a default explosion radius or customize it as needed
        this.explosionRadius = 3; // Example radius
    }

    // Getters and setters for row, col, and exploded fields

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    // Method to detonate the bomb
    public void detonate(Tile[][] tiles) {
        // Detonate the bomb
        exploded = true;
        System.out.println("booom");
        // Create explosions and apply effects
        Explosion explosion = new Explosion(this.row, this.col, explosionRadius);
        explosion.detonate(tiles);
    }
    
    public int getExplosionRadius() {
        return explosionRadius;
    }

    // Optional method to set the explosion radius
    public void setExplosionRadius(int explosionRadius) {
        this.explosionRadius = explosionRadius;
    }
}
