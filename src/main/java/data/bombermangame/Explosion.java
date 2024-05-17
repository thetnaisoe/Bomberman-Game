package data.bombermangame;

public class Explosion {
   
    public int rowIndex;
    public int colIndex;
    public int blastRange;

    public Explosion(int rowIndex, int colIndex, int blastRange) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.blastRange = blastRange;
    }
     
    public int getBlastRadius() {
        return blastRange;
    }
     
    public void detonate(Tile[][] tiles) {
        for (int step = 1; step <= blastRange; step++) {
            if (!applyExplosionEffect(rowIndex, colIndex + step, tiles)) {
                break; // Stop if we encounter a wall or box
            }
        }

        for (int step = 1; step <= blastRange; step++) {
            if (!applyExplosionEffect(rowIndex, colIndex - step, tiles)) {
                break; // Stop if we encounter a wall or box
            }
        }

        for (int step = 1; step <= blastRange; step++) {
            if (!applyExplosionEffect(rowIndex + step, colIndex, tiles)) {
                break; // Stop if we encounter a wall or box
            }
        }

        for (int step = 1; step <= blastRange; step++) {
            if (!applyExplosionEffect(rowIndex - step, colIndex, tiles)) {
                break; // Stop if we encounter a wall or box
            }
        }
    }

    private boolean applyExplosionEffect(int newX, int newY, Tile[][] tiles) {
        if (newX < 0 || newX >= tiles.length || newY < 0 || newY >= tiles[0].length) {
            return false; // Ensure we are within the bounds of the game area
        }

        Tile tile = tiles[newX][newY];
        if (tile instanceof Wall) {
            // Stop the explosion if it reaches a wall
            return false;
        }

        if (tile instanceof Box) {
            // Destroy the box if it's a Box tile
//            tile.Drop();
            
            return false; // Stop the explosion if it reaches a box
        }
         if (tile instanceof Obstacle) {
           
            return false; // Stop the explosion if it reaches a obstacle
        }

        for (Player player : BombermanComponent.players) {
            if (player.currentRow == newX && player.currentCol == newY && !player.getInvincible()) {
                player.isAlive = false;
            }
        }
        for (Monster monster : BombermanComponent.monsters) {
            if (monster.currentRow == newX && monster.currentCol == newY) {
                monster.isAlive = false;
            }
        }

        return true; // Continue the explosion if no obstacles were encountered
    }


}
