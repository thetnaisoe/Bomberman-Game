package data.bombermangame;

/**
 * The Detonator class represents a power-up item in the Bomberman game that gives a player the ability to manually detonate bombs.
 * It extends the PowerUp class and implements the Item interface.
 * 
 * @author lenovo
 */
public class Detonator extends PowerUp implements Item {

    /**
     * Constructs a Detonator power-up with the specified duration.
     * 
     * @param duration the duration for which the power-up is active.
     */
    public Detonator(long duration) {
        super(duration);
    }

    /**
     * Applies the Detonator effect to the specified player, allowing the player to manually detonate bombs.
     * 
     * @param player the player to which the effect is applied.
     */
    @Override
    public void applyEffect(Player player) {
        player.setDetonator(true);
    }

    /**
     * Removes the Detonator effect from the specified player, disabling manual bomb detonation.
     * 
     * @param player the player from which the effect is removed.
     */
    @Override
    public void removeEffect(Player player) {
        player.setDetonator(false);
    }
}
