/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

 */

package data.bombermangame;
import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author lenovo
 */
public class Bomb extends Tile implements Explodable{
    Player player;
    int range;
    Timer timer;
    Tile[][] tiles;
    //Explosion explosion;
     private Explosion explosion;
    
    public Bomb(int row, int col) {
        super(row, col);
    }
    public Bomb( JButton[][] buttons,Tile[][] tiles,int row, int col,Player player,int range) {
        super(row, col);
        this.player=player;
        this.range=range;
        this.tiles=tiles;
        this.explosion = new Explosion(row, col, 10, range);
        this.timer = new Timer(3000, e -> detonate(buttons)); // Assuming a 3-second fuse
        this.timer.setRepeats(false); // The timer should only go off once
        this.timer.start();
    }
    
    
    
    
    @Override
    public boolean destroyed(){
        return this.destroyed;
    }
    
    @Override
    public void setDestroyed(boolean dest){
         if (dest && !this.destroyed) { // Additional check to prevent re-entry
        this.destroyed = true;
        // Stop the timer to prevent multiple detonations
        if (this.timer != null) {
            this.timer.stop();
        
    }
         }
    }
    
     /*
    private void detonate(JButton[][] buttons) {
        // Trigger the explosion logic here.
        // This could create an Explosion object, which then applies effects to surrounding Tiles.
        System.out.println("Bomb at (" + rowIndex + "," + colIndex + ") detonated!");
        
        // Call explosion logic to affect surrounding tiles and possibly chain-react other bombs.
        //Explosion explosion = new Explosion(rowIndex, colIndex,  other parameters );
        //explosion.detonate();
    }
*/
     private void detonate(JButton[][] buttons) {
        if (tiles != null) {
        this.explosion.detonate(buttons, tiles); // Pass tiles to detonate method
    } else {
        System.out.println("Tiles array is unexpectedly null.");
    } // Delegate detonation to the Explosion class
        this.setDestroyed(true);
    }

    @Override
    public void explode() {
        // Logic to explode the new bomb.
       
        
        this.setDestroyed(true); 
         detonate(this.player.buttons);
    }
        
    
}
