/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author lenovo
 */
public class Box extends Tile implements Explodable{
     //Tile[][] tiles;
    
    public Box(int row, int col,Tile[][] tiles) {
        super(row, col);
        this.destroyed=false;
        //this.tiles = tiles;
        
    }
    
    @Override
    public boolean canDrop(){
         return destroyed;
    }
    
    @Override
     public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
        
        
        
    }

    
    @Override
    public void explode(JButton[][] buttons,Tile[][] tiles) {
       // Logic to handle what happens to a box when it explodes.
        // either drop powerup , curse or none.
         tiles[rowIndex][colIndex] = new Field(rowIndex, colIndex); // Assuming Field represents a passable tile
        buttons[rowIndex][colIndex].setBackground(Color.GRAY);
        this.setDestroyed(true);
        System.out.println("Box at [" + rowIndex + "," + colIndex + "] exploded and became a Field.");
    }
     @Override
    public boolean isPassable(){
        return false;
    }
  
    
    
}
