/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 *
 * @author lenovo
 */
public class Field extends Tile{
     private Item item; // Holds an item if one is present on this tile

    
    public Field(int row, int col) {
        super(row, col);
        tileType = TileType.FIELD;
        this.item = null;
        
    }
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
    
    @Override
    public boolean canDrop(){
        return false; 
        
    }
    
    @Override
    public boolean destroyed(){
        return false;
        
    }
  
    @Override
    public boolean isPassable(){
        return true;
    }
      public Item takeItem() {
        Item temp = this.item;
        this.item = null;
        return temp;
    }
    
}

