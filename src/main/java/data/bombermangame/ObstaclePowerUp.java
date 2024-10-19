/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents an obstacle power-up in the Bomberman game.
 * This class extends the PowerUp class and provides a specific implementation of the applyEffect method.
 * When this power-up is applied, it increases the player's obstacle count by 3.
 * The duration of the power-up is specified in the constructor.
 *
 * @author lenovo
 */
public class ObstaclePowerUp extends PowerUp {
    
    /**
     * Constructs a new ObstaclePowerUp with the specified duration.
     *
     * @param duration the duration of the power-up
     */
    public ObstaclePowerUp(long duration) {
        super(duration);
    }
    
    /**
     * Applies the effect of the power-up to the specified player.
     * This method increases the player's obstacle count by 3.
     *
     * @param player the player to whom to apply the effect
     */
    @Override
    public void applyEffect(Player player) {
         player.increaseObstacleCount(3);// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Removes the effect of the power-up from the specified player.
     * This method is currently empty because the effect of this power-up is not reversible.
     *
     * @param player the player from whom to remove the effect
     */
    @Override
    public void removeEffect(Player player) {
       
    }
    
}
