/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents a ForcedBombDrop curse in the game.
 * This class is responsible for managing the ForcedBombDrop curse's effects on a player.
 * It extends the Curse class and overrides its methods to apply and remove the curse's effects.
 * 
 * @author lenovo
 */
public class ForcedBombDrop extends Curse {

    /**
     * Constructs a new ForcedBombDrop object with the specified duration.
     *
     * @param duration the duration of the curse
     */
    public ForcedBombDrop(long duration) {
        super(duration);
    }

    /**
     * Applies the ForcedBombDrop curse's effect to the specified player.
     * The effect is that the player is forced to drop bombs automatically.
     *
     * @param player the player to apply the curse's effect to
     */
    @Override
    public void applyEffect(Player player) {
        player.setForcedBombDrop(true); // Force player to drop bombs automatically
    }

    /**
     * Removes the ForcedBombDrop curse's effect from the specified player.
     * The effect is that the player is no longer forced to drop bombs automatically.
     *
     * @param player the player to remove the curse's effect from
     */
    @Override
    public void removeEffect(Player player) {
        player.setForcedBombDrop(false); // Stop forced bomb dropping
    }
}
