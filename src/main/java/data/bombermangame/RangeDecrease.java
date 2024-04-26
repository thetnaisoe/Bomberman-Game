/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 *
 * @author lenovo
 */
public class RangeDecrease extends Curse {

    public RangeDecrease(long duration) {
        super(duration);
    }

    @Override
    public void applyEffect(Player player) {
        player.decrementBlastRange();
    }

    @Override
    public void removeEffect(Player player) {
        player.incrementBlastRange();
    }
    
}
