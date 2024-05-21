/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents a Field tile in the Bomberman game.
 * This class extends the Tile class and provides the functionality specific to a field tile.
 * A field tile is passable, cannot be destroyed, and bombs cannot be dropped on it.
 * A field tile can also hold an item.
 *
 * @author ThetNaingSoe
 */
public class Field extends Tile{
     private Item item; // Holds an item if one is present on this tile

    /**
     * Constructs a new Field tile at the specified row and column.
     *
     * @param row the row of the tile
     * @param col the column of the tile
     */
    public Field(int row, int col) {
        super(row, col);
        tileType = TileType.FIELD;
        this.item = null;
        
    }
    
    /**
     * Sets the item on this field.
     *
     * @param item the new item on this field
     */
     public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Gets the item on this field.
     * @return the item, or null if no item is present.
     */
    public Item getItem() {
        return this.item;
    }
    
    /**
     * Returns whether a bomb can be dropped on this tile.
     * For a Field tile, this method always returns false.
     *
     * @return false
     */    
    @Override
    public boolean canDrop(){
        return false; 
        
    }
    
    /**
     * Returns whether this tile can be destroyed.
     * For a Field tile, this method always returns false.
     *
     * @return false
     */   
    @Override
    public boolean destroyed(){
        return false;
        
    }
    
    /**
     * Returns whether this tile is passable.
     * For a Field tile, this method always returns true.
     *
     * @return true
     */
    @Override
    public boolean isPassable(){
        return true;
    }
    
    /**
     * Removes the item from this field and returns it.
     *
     * @return the item that was on this field, or null if no item was present
     */
    public Item takeItem() {
        Item temp = this.item;
        this.item = null;
        return temp;
    }
    
}

