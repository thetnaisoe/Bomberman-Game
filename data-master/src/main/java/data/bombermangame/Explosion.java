package data.bombermangame;

import java.util.List;
import java.util.ArrayList;

public class Explosion {
   
    private int rowIndex;
    private int colIndex;
    private int blastRange;

    public Explosion(int rowIndex, int colIndex, int blastRange) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.blastRange = blastRange;
    }
 
    public List<Explodable> getObjectsInRange(Tile[][] tiles) {
        List<Explodable> objectsInRange = new ArrayList<>();

        for (int step = 1; step <= blastRange; step++) {
            int newX = rowIndex;
            int newY = colIndex + step;
            if (!isValidAndExplodable(newX, newY, tiles, objectsInRange)) break;
        }

        for (int step = 1; step <= blastRange; step++) {
            int newX = rowIndex;
            int newY = colIndex - step;
            if (!isValidAndExplodable(newX, newY, tiles, objectsInRange)) break;
        }

        for (int step = 1; step <= blastRange; step++) {
            int newX = rowIndex + step;
            int newY = colIndex;
            if (!isValidAndExplodable(newX, newY, tiles, objectsInRange)) break;
        }

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
     
    public void detonate(Tile[][] tiles) {
        for (int step = 1; step <= blastRange; step++) {
            applyExplosionEffect(rowIndex, colIndex + step, tiles);
        }

        for (int step = 1; step <= blastRange; step++) {
            applyExplosionEffect(rowIndex, colIndex - step, tiles);
        }

        for (int step = 1; step <= blastRange; step++) {
            applyExplosionEffect(rowIndex + step, colIndex, tiles);
        }

        for (int step = 1; step <= blastRange; step++) {
            applyExplosionEffect(rowIndex - step, colIndex, tiles);
        }
    }

    private void applyExplosionEffect(int newX, int newY, Tile[][] tiles) {
        if (newX < 0 || newX >= tiles.length || newY < 0 || newY >= tiles[0].length) {
            return;
        }

        Tile tile = tiles[newX][newY];
        if (tile instanceof Wall) {
            return;
        }

        //if (tile instanceof Box) {
        //    Box box = (Box) tile;
        //    box.explode(tiles);
        //}
        
        // Assuming there are players and monsters in the game, handle their interaction with explosion
        for (Player player : BombermanComponent.players) {
            if (player.currentRow == newX && player.currentCol == newY) {
                player.isAlive = false;
            }
        }
        for (Monster monster : BombermanComponent.monsters) {
            if (monster.currentRow == newX && monster.currentCol == newY) {
                monster.isAlive = false;
            }
        }
    }

    private boolean isValidAndExplodable(int newX, int newY, Tile[][] tiles, List<Explodable> objectsInRange) {
        if (newX < 0 || newX >= tiles.length || newY < 0 || newY >= tiles[0].length) {
            return false;
        }
        Tile tile = tiles[newX][newY];
        if (tile instanceof Wall) {
            return false;
        } else if (tile instanceof Explodable) {
            objectsInRange.add((Explodable) tile);
            return !(tile instanceof Box);
        }
        return true;
    }
}
