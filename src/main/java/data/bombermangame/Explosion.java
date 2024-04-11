package data.bombermangame;

import java.util.List;
import java.util.ArrayList;

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
        if (tile instanceof Wall || tile instanceof Box) {
            // Destroy the box if it's a Box tile
            if (tile instanceof Box box) {
                box.destroyed = true;
            }
            return;
        }

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
}
