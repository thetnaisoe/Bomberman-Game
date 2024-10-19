/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents a range increase power-up in the Bomberman game.
 * This class extends the PowerUp class and provides a specific implementation of the applyEffect and removeEffect methods.
 * When this power-up is applied, it increases the player's blast range.
 * When this power-up is removed, it decreases the player's blast range.
 * The duration of the power-up is specified in the constructor.
 *
 * @author lenovo
 */
public class RangeIncrease extends PowerUp implements Item{
    
    /**
     * Constructs a new RangeIncrease power-up with the specified duration.
     *
     * @param duration the duration of the power-up
     */
    public RangeIncrease(long duration) {
        super(duration);
    }
    
    /**
     * Applies the effect of the power-up to the specified player.
     * This method increases the player's blast range.
     *
     * @param player the player to whom to apply the effect
     */
     @Override
    public void applyEffect(Player player) {
        player.incrementBlastRange();
    }
    
    /**
     * Removes the effect of the power-up from the specified player.
     * This method decreases the player's blast range.
     *
     * @param player the player from whom to remove the effect
     */
    @Override
    public void removeEffect(Player player) {
        player.decrementBlastRange();
    }
  
   
    
}
