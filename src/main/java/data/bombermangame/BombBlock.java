/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents a BombBlock curse in the game.
 * This class is responsible for managing the BombBlock curse's effects on a player.
 * It extends the Curse class and overrides its methods to apply and remove the curse's effects.
 * 
 * @author lenovo
 */

public class BombBlock extends Curse {
    
    /**
     * Constructs a new BombBlock object with the specified duration.
     *
     * @param duration the duration of the curse
     */
    public BombBlock(long duration) {
        super(duration);
    }

    /**
     * Applies the BombBlock curse's effect to the specified player.
     * The effect is that the player cannot drop bombs.
     *
     * @param player the player to apply the curse's effect to
     */
    @Override
    public void applyEffect(Player player) {
      player.setCanDropBombs(false);
    }

    /**
     * Removes the BombBlock curse's effect from the specified player.
     * The effect is that the player can drop bombs again.
     *
     * @param player the player to remove the curse's effect from
     */
    @Override
    public void removeEffect(Player player) {
       player.setCanDropBombs(true);
    }
    
}
