/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 *
 * @author lenovo
 */
public class RollerSkate extends PowerUp implements Item {

    public RollerSkate(long duration) {
        super(duration);
    }
     @Override
    public void applyEffect(Player player) {
        player.increaseSpeed(); // Only applies if speed hasn't already been increased
    }

    @Override
    public void removeEffect(Player player) {
        // Speed increase does not expire, no removal logic
    }
}
