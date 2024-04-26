/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 *
 * @author lenovo
 */
public class ForcedBombDrop extends Curse {

    public ForcedBombDrop(long duration) {
        super(duration);
    }

    @Override
    public void applyEffect(Player player) {
        player.setForcedBombDrop(true); // Force player to drop bombs automatically
    }

    @Override
    public void removeEffect(Player player) {
        player.setForcedBombDrop(false); // Stop forced bomb dropping
    }
    
}
