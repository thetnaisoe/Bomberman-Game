/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 *
 * @author lenovo
 */
public class RangeIncrease extends PowerUp implements Item{

    public RangeIncrease(int duration) {
        super(duration);
    }
     @Override
    public void applyEffect(Player player) {
        player.incrementBlastRange();
    }

    @Override
    public void removeEffect(Player player) {
        player.decrementBlastRange();
    }
  
   
    
}
