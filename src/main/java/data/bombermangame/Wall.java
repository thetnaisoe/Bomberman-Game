/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 *
 * @author lenovo
 */
public class Wall extends Tile {
    
    public Wall(int row, int col) {
        super(row, col);
        tileType = TileType.WALL;
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
        return false;
    }
    
    
}
