/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package data.bombermangame;

/**
 * Represents an item in the Bomberman game.
 * This interface defines the methods that all items must implement.
 * An item can be picked up by a player and can have an effect on the player.
 *
 * @author lenovo
 */
public interface Item {

    /**
     * Applies the effect of this item to the specified player.
     *
     * @param player the player to apply the effect to
     */
    void applyEffect(Player player);

    /**
     * Removes the effect of this item from the specified player.
     *
     * @param player the player to remove the effect from
     */
    void removeEffect(Player player);
    
}
