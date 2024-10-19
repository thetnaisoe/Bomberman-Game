/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents the AddBomb power-up in the game.
 * When applied, it increases the bomb count of a player.
 * When removed, it decreases the blast range of a player.
 *
 * @author lenovo
 */
public class AddBomb extends PowerUp {

    /**
     * Constructs a new AddBomb power-up with the specified duration.
     *
     * @param duration the duration of the power-up in milliseconds
     */
    public AddBomb(long duration) {
        super(duration);
    }

    /**
     * Applies the effect of the power-up to the specified player.
     * This increases the bomb count of the player.
     *
     * @param player the player to apply the effect to
     */
    @Override
    public void applyEffect(Player player) {
       player.incrementBombCount();
    }

    /**
     * Removes the effect of the power-up from the specified player.
     * This decreases the blast range of the player.
     *
     * @param player the player to remove the effect from
     */
    @Override
    public void removeEffect(Player player) {
      player.decrementBlastRange();
    }
}
