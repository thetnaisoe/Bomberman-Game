/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents a range decrease curse in the Bomberman game.
 * This class extends the Curse class and provides a specific implementation of the applyEffect and removeEffect methods.
 * When this curse is applied, it decreases the player's blast range.
 * When this curse is removed, it increases the player's blast range.
 * The duration of the curse is specified in the constructor.
 *
 * @author lenovo
 */
public class RangeDecrease extends Curse {
    
    /**
     * Constructs a new RangeDecrease curse with the specified duration.
     *
     * @param duration the duration of the curse
     */
    public RangeDecrease(long duration) {
        super(duration);
    }
    
    /**
     * Applies the effect of the curse to the specified player.
     * This method decreases the player's blast range.
     *
     * @param player the player to whom to apply the effect
     */
    @Override
    public void applyEffect(Player player) {
        player.decrementBlastRange();
    }
    
    /**
     * Removes the effect of the curse from the specified player.
     * This method increases the player's blast range.
     *
     * @param player the player from whom to remove the effect
     */
    @Override
    public void removeEffect(Player player) {
        player.incrementBlastRange();
    }
    
}
