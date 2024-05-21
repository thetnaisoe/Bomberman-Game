/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents a curse in the game.
 * This class is an abstract class that implements the Item interface.
 * It provides a framework for creating different types of curses with different effects.
 * 
 * @author lenovo
 */
public abstract class Curse implements Item {

    protected long duration; // Duration in milliseconds

    /**
     * Constructs a new Curse object with the specified duration.
     *
     * @param duration the duration of the curse in milliseconds
     */
    public Curse(long duration){
        this.duration = duration;
    }

    /**
     * Applies the curse's effect to the specified player.
     * This method is abstract and must be overridden by subclasses.
     *
     * @param player the player to apply the curse's effect to
     */
    @Override
    public abstract void applyEffect(Player player);

    /**
     * Removes the curse's effect from the specified player.
     * This method is abstract and must be overridden by subclasses.
     *
     * @param player the player to remove the curse's effect from
     */
    @Override
    public abstract void removeEffect(Player player);
}
