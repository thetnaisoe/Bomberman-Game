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
    
    public Field(int row, int col) {
        super(row, col);
        tileType = TileType.FIELD;
        
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
    
}

