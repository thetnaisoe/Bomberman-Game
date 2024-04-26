/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 *
 * @author lenovo
 */
public class ObstaclePowerUp extends PowerUp {
    
    public ObstaclePowerUp(long duration) {
        super(duration);
    }

    @Override
    public void applyEffect(Player player) {
         player.increaseObstacleCount(3);// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeEffect(Player player) {
       
    }
    
}
