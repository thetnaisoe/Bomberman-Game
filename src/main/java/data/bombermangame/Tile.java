/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

import java.awt.Image;

/**
 * Represents a Tile in the Bomberman game.
 * This class provides the common functionality for all types of tiles, including walls, boxes, and fields.
 * Each tile has a row and column index, a flag indicating whether a bomb can be dropped on it, a flag indicating whether it has been destroyed, and a type.
 * The type of the tile determines whether it is passable.
 *
 * @author ThetNaingSoe
 */
public abstract class Tile {
    protected int rowIndex;
    protected int colIndex;
    protected boolean canDrop;
    protected boolean destroyed;
    protected TileType tileType;
    private Image image;
    
    /**
     * Constructs a new Tile at the specified row and column.
     *
     * @param row the row of the tile
     * @param col the column of the tile
     */
    public Tile(int row,int col){
        this.rowIndex=row;
        this.colIndex =col;
        this.canDrop=false;
        this.destroyed=false;
    }
    
     /**
     * Returns the image of this tile.
     *
     * @return the image of this tile
     */   
    public Image getImage() {
        return image;
    }
    
    /**
     * Sets the image of this tile.
     *
     * @param image the new image of this tile
     */
    public void setImage(Image image) {
        this.image = image;
    }
    
     /**
     * Returns whether a bomb can be dropped on this tile.
     *
     * @return true if a bomb can be dropped on this tile, false otherwise
     */   
    public boolean canDrop(){
        return canDrop;
    }
    
    /**
     * Returns whether this tile has been destroyed.
     *
     * @return true if this tile has been destroyed, false otherwise
     */
    public boolean destroyed(){
        return destroyed;
    }
    
    /**
     * Returns the row index of this tile.
     *
     * @return the row index of this tile
     */
    public int getRow(){
        return rowIndex;
    }
    
    /**
     * Returns the column index of this tile.
     *
     * @return the column index of this tile
     */
     public int getCol(){
        return colIndex;
     }
     
    /**
     * Sets whether a bomb can be dropped on this tile.
     *
     * @param a true if a bomb can be dropped on this tile, false otherwise
     */
     public void setCanDrop(boolean a){
         this.canDrop=a;
     }
     
    /**
     * Sets whether this tile has been destroyed.
     *
     * @param a true if this tile has been destroyed, false otherwise
     */
     public void setDestroyed(boolean a){
         this.destroyed=a;
         
     }
    /**
     * Returns whether this tile is passable.
     * A tile is passable if it is not a wall or a box.
     *
     * @return true if this tile is passable, false otherwise
     */
    public boolean isPassable() {
        switch(tileType) {
            case WALL: return false;
            case BOX: return false;
            default: return true; // Example: Field is passable
        }
    }
    
}
