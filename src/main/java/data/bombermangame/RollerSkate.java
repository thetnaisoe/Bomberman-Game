/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents a roller skate power-up in the Bomberman game.
 * This class extends the PowerUp class and provides a specific implementation of the applyEffect method.
 * When this power-up is applied, it increases the player's speed, if it hasn't already been increased.
 * The speed increase does not expire, so there is no removal logic.
 * The duration of the power-up is specified in the constructor.
 *
 * @author lenovo
 */
public class RollerSkate extends PowerUp implements Item {
    
    /**
     * Constructs a new RollerSkate power-up with the specified duration.
     *
     * @param duration the duration of the power-up
     */
    public RollerSkate(long duration) {
        super(duration);
    }
    
    /**
     * Applies the effect of the power-up to the specified player.
     * This method increases the player's speed, if it hasn't already been increased.
     *
     * @param player the player to whom to apply the effect
     */
     @Override
    public void applyEffect(Player player) {
        player.increaseSpeed(); // Only applies if speed hasn't already been increased
    }
    
    /**
     * Removes the effect of the power-up from the specified player.
     * This method is currently empty because the speed increase does not expire.
     *
     * @param player the player from whom to remove the effect
     */
    @Override
    public void removeEffect(Player player) {
        // Speed increase does not expire, no removal logic
    }
}
