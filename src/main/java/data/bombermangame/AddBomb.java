/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 *
 * @author lenovo
 */
public class AddBomb extends PowerUp {

    public AddBomb(long duration) {
        super(duration);
    }

    @Override
    public void applyEffect(Player player) {
       player.incrementBombCount();
    }

    @Override
    public void removeEffect(Player player) {
      player.decrementBlastRange();
    }
    
}
