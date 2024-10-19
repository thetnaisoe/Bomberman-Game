/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents a power-up item in the Bomberman game.
 * This class implements the Item interface and provides a base for specific types of power-ups.
 * Each power-up has a duration, specified in milliseconds, and can be applied to and removed from a player.
 *
 * @author lenovo
 */
public abstract class PowerUp implements Item {
    
    protected long duration; // Duration in milliseconds

    /**
     * Constructs a new PowerUp with the specified duration.
     *
     * @param duration the duration of the power-up in milliseconds
     */
    public PowerUp(long duration) {
        this.duration = duration;
    }
  
    /**
     * Applies the effect of the power-up to the specified player.
     * This method is abstract and must be implemented by subclasses.
     *
     * @param player the player to whom to apply the effect
     */
    @Override
    public abstract void applyEffect(Player player) ;

    
    /**
     * Removes the effect of the power-up from the specified player.
     * This method is abstract and must be implemented by subclasses.
     *
     * @param player the player from whom to remove the effect
     */
    @Override
    public abstract void removeEffect(Player player) ;
       
   
}
