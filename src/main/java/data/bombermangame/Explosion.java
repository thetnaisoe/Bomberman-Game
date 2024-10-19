package data.bombermangame;

/**
 * The Explosion class represents an explosion in the Bomberman game.
 * It handles the logic for detonation and applying explosion effects to tiles, players, and monsters.
 * The explosion propagates in four directions (up, down, left, right) and is stopped by walls, boxes, and obstacles.
 * 
 * @author lenovo
 */
public class Explosion {

    public int rowIndex;
    public int colIndex;
    public int blastRange;

    /**
     * Constructs an Explosion with the specified initial position and blast range.
     * 
     * @param rowIndex the row index where the explosion starts.
     * @param colIndex the column index where the explosion starts.
     * @param blastRange the range of the explosion.
     */
    public Explosion(int rowIndex, int colIndex, int blastRange) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.blastRange = blastRange;
    }

    /**
     * Gets the blast radius of the explosion.
     * 
     * @return the blast radius.
     */
    public int getBlastRadius() {
        return blastRange;
    }

    /**
     * Detonates the explosion, propagating it in four directions (up, down, left, right).
     * The explosion is stopped by walls, boxes, and obstacles.
     * 
     * @param tiles the game area represented by a 2D array of tiles.
     */
    public void detonate(Tile[][] tiles) {
        for (int step = 1; step <= blastRange; step++) {
            if (!applyExplosionEffect(rowIndex, colIndex + step, tiles)) {
                break; // Stop if we encounter a wall, box, or obstacle
            }
        }

        for (int step = 1; step <= blastRange; step++) {
            if (!applyExplosionEffect(rowIndex, colIndex - step, tiles)) {
                break; // Stop if we encounter a wall, box, or obstacle
            }
        }

        for (int step = 1; step <= blastRange; step++) {
            if (!applyExplosionEffect(rowIndex + step, colIndex, tiles)) {
                break; // Stop if we encounter a wall, box, or obstacle
            }
        }

        for (int step = 1; step <= blastRange; step++) {
            if (!applyExplosionEffect(rowIndex - step, colIndex, tiles)) {
                break; // Stop if we encounter a wall, box, or obstacle
            }
        }
    }

    /**
     * Applies the explosion effect to the specified tile.
     * The explosion will stop if it encounters a wall, box, or obstacle.
     * 
     * @param newX the row index of the tile being affected.
     * @param newY the column index of the tile being affected.
     * @param tiles the game area represented by a 2D array of tiles.
     * @return true if the explosion can continue, false otherwise.
     */
    private boolean applyExplosionEffect(int newX, int newY, Tile[][] tiles) {
        if (newX < 0 || newX >= tiles.length || newY < 0 || newY >= tiles[0].length) {
            return false; // Ensure we are within the bounds of the game area
        }

        Tile tile = tiles[newX][newY];
        if (tile instanceof Wall) {
            return false; // Stop the explosion if it reaches a wall
        }

        if (tile instanceof Box) {
            // Destroy the box if it's a Box tile
            return false; // Stop the explosion if it reaches a box
        }

        if (tile instanceof Obstacle) {
            return false; // Stop the explosion if it reaches an obstacle
        }

        // Check if the explosion hits a player
        for (Player player : BombermanComponent.players) {
            if (player.currentRow == newX && player.currentCol == newY && !player.getInvincible()) {
                player.isAlive = false;
            }
        }

        // Check if the explosion hits a monster
        for (Monster monster : BombermanComponent.monsters) {
            if (monster.currentRow == newX && monster.currentCol == newY) {
                monster.isAlive = false;
            }
        }

        return true; // Continue the explosion if no obstacles were encountered
    }
}
