/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 *
 * @author lenovo
 */
public abstract class PowerUp implements Item {
    
    protected long duration; // Duration in milliseconds

    public PowerUp(long duration) {
        this.duration = duration;
    }
  

    @Override
    public abstract void applyEffect(Player player) ;

    

    @Override
    public abstract void removeEffect(Player player) ;
       
    
    
    
}
