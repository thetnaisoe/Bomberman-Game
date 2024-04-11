/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 *
 * @author lenovo
 */
public class Box extends Tile{
     //Tile[][] tiles;
    
    public Box(int row, int col,Tile[][] tiles) {
        super(row, col);
        this.destroyed=false;
        //this.tiles = tiles;
        tileType = TileType.BOX;
        
    }
    
    @Override
    public boolean canDrop(){
         return destroyed;
    }
    
    @Override
     public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
