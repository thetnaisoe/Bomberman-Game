package data.bombermangame;

import java.util.Random;

/**
 * The Box class represents a destructible tile in the Bomberman game that may drop an item when destroyed.
 * It extends the Tile class and adds functionality for item drops.
 * 
 * @author lenovo
 */
public class Box extends Tile {
    private static final Random random = new Random();
    private Item item;

    private static final Item[] items = {
        new ObstaclePowerUp(1000)
        // Add other items here as needed
    };

    /**
     * Constructs a Box at the specified row and column.
     * 
     * @param row the row position of the Box.
     * @param col the column position of the Box.
     */
    public Box(int row, int col) {
        super(row, col);
        tileType = TileType.BOX;
        this.maybeDropItem();
    }

    /**
     * Determines if the Box can drop an item when destroyed.
     * 
     * @return true if the Box is destroyed and can drop an item, false otherwise.
     */
    @Override
    public boolean canDrop() {
        return destroyed;
    }

    /**
     * Randomly decides whether the Box will drop an item when destroyed.
     * The drop chance is currently set to 80% for testing purposes.
     */
    public void maybeDropItem() {
        double dropChance = 0.8; // High chance to verify functionality
        System.out.println("Attempting to drop an item with a chance of " + dropChance);
        if (Math.random() < dropChance) {
            Item[] possibleItems = {
                new Detonator(10000), new Invincibility(10000), new Ghost(10000), 
                new ObstaclePowerUp(1000), new RangeIncrease(10000), new AddBomb(10000),
                new BombBlock(10000), new ForcedBombDrop(10000), new RangeDecrease(10000)
                // Add other items or curses here
            };
            this.item = possibleItems[(int) (Math.random() * possibleItems.length)];
            System.out.println("Dropped item: " + item.getClass().getSimpleName());
        } else {
            System.out.println("No item was dropped.");
        }
    }

    /**
     * Sets the destroyed status of the Box.
     * 
     * @param destroyed true if the Box is destroyed, false otherwise.
     */
    @Override
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * Checks if the Box is passable.
     * 
     * @return false, as Boxes are not passable.
     */
    @Override
    public boolean isPassable() {
        return false;
    }

    /**
     * Gets the item contained in the Box, if any.
     * 
     * @return the item in the Box, or null if no item is present.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Clears the item from the Box.
     */
    public void clearItem() {
        this.item = null;
    }

    /**
     * Destroys the Box and returns the item it contained, if any.
     * 
     * @return the item in the Box, or null if no item was present.
     */
    public Item destroyAndGetItem() {
        System.out.println("Destroying box. Item present: " + (item != null ? item.getClass().getSimpleName() : "None"));
        return item;  // Return the item to be handled by the game logic
    }
}
