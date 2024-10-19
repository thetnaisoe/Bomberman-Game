/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents a Wall tile in the Bomberman game.
 * This class extends the Tile class and provides the functionality specific to a wall tile.
 * A wall tile is not passable, cannot be destroyed, and bombs cannot be dropped on it.
 *
 * @author lenovo
 */
public class Wall extends Tile {
   
    /**
     * Constructs a new Wall tile at the specified row and column.
     *
     * @param row the row of the tile
     * @param col the column of the tile
     */
    public Wall(int row, int col) {
        super(row, col);
        tileType = TileType.WALL;
    }
    
     /**
     * Returns whether a bomb can be dropped on this tile.
     * For a Wall tile, this method always returns false.
     *
     * @return false
     */   
    @Override 
    public boolean canDrop(){
        return false;
    }
    
    /**
     * Returns whether this tile can be destroyed.
     * For a Wall tile, this method always returns false.
     *
     * @return false
     */    
     @Override 
    public boolean destroyed(){
        return false;
    }
    
     /**
     * Returns whether this tile is passable.
     * For a Wall tile, this method always returns false.
     *
     * @return false
     */   
    @Override
    public boolean isPassable(){
        return false;
    }
    
    
}
