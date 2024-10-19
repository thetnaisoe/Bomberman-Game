/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents the Invincibility power-up in the Bomberman game.
 * This class extends the PowerUp class and overrides its methods to apply and remove the invincibility effect on a player.
 * When a player picks up this power-up, they become invincible for a certain duration.
 *
 * @author lenovo
 */
public class Invincibility extends PowerUp{

    /**
     * Constructs a new Invincibility object with the specified duration.
     *
     * @param duration the duration of the invincibility effect in milliseconds
     */
    public Invincibility(long duration) {
        super(duration);
    }

    /**
     * Applies the invincibility effect to the specified player.
     * This method sets the player's invincible status to true.
     *
     * @param player the player to apply the effect to
     */
    @Override
    public void applyEffect(Player player) {
        player.setInvincible(true);
    }

    /**
     * Removes the invincibility effect from the specified player.
     * This method sets the player's invincible status to false.
     *
     * @param player the player to remove the effect from
     */
    @Override
    public void removeEffect(Player player) {
        player.setInvincible(false);
    }
    
}
