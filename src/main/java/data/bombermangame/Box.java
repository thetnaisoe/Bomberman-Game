/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 *
 * @author lenovo
 */
public class Box extends Tile implements Explodable{
    
    public Box(int row, int col) {
        super(row, col);
        this.destroyed=false;
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
    public void explode() {
       // Logic to handle what happens to a box when it explodes.
        // either drop powerup , curse or none.
        this.setDestroyed(true);
    }
    
    
}
