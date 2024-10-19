/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents a Ghost power-up in the game.
 * This class is responsible for managing the Ghost power-up's effects on a player.
 * It extends the PowerUp class and overrides its methods to apply and remove the power-up's effects.
 * 
 * @author lenovo
 */
public class Ghost extends PowerUp {

    /**
     * Constructs a new Ghost object with the specified duration.
     *
     * @param duration the duration of the power-up
     */
    public Ghost(long duration) {
        super(duration);
    }

    /**
     * Applies the Ghost power-up's effect to the specified player.
     * The effect is that the player becomes a ghost.
     *
     * @param player the player to apply the power-up's effect to
     */
    @Override
    public void applyEffect(Player player) {
        player.setGhost(true);
    }

    /**
     * Removes the Ghost power-up's effect from the specified player.
     * The effect is that the player is no longer a ghost.
     * If the player is inside a wall or a box when the effect is removed, the player dies.
     *
     * @param player the player to remove the power-up's effect from
     */
    @Override
    public void removeEffect(Player player) {
        player.setGhost(true);
        Tile currentTile = BombermanComponent.tiles[player.currentRow][player.currentCol];
        if (currentTile instanceof Wall || currentTile instanceof Box) {
            player.isAlive=false;
        }
    }
}
