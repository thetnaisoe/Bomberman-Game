/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents a SlowCurse in the Bomberman game.
 * This class extends the Curse class and provides the functionality for slowing down a player.
 * The effect of the curse is applied when the applyEffect method is called and removed when the removeEffect method is called.
 * The duration of the curse is specified in the constructor.
 *
 * @author lenovo
 */
public class SlowCurse extends Curse {
     /**
     * Constructs a new SlowCurse with the specified duration.
     *
     * @param duration the duration of the curse
     */
    public SlowCurse(int duration){
        super(duration);
        
    }
    
    /**
     * Applies the effect of the curse to the specified player.
     * This method should be overridden in a subclass to provide the specific effect of the curse.
     *
     * @param player the player to apply the effect to
     */
    @Override
    public void applyEffect(Player player) {
     
    }
    
    /**
     * Removes the effect of the curse from the specified player.
     * This method should be overridden in a subclass to remove the specific effect of the curse.
     *
     * @param player the player to remove the effect from
     */
    @Override
    public void removeEffect(Player player) {
      
    }
    
}
