/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

import java.util.Random;
/**
 *
 * @author lenovo
 */
public class Box extends Tile{
    private static final Random random = new Random();
     private Item item;
    
    private static final Item[] items = {
        new RangeIncrease(1000), new Detonator(1000), new RollerSkate(1000),
        // Add curse items here
    };
     //Tile[][] tiles;
    
    public Box(int row, int col) {
        super(row, col);
        tileType = TileType.BOX;
        this.maybeDropItem();
    }
    
    @Override
    public boolean canDrop(){
         return destroyed;
    }
//    public Item drop() {
//        if (random.nextDouble() < 0.6) {  // 60% chance to drop something
//            return items[random.nextInt(items.length)];  // Randomly select an item
//        }
//        return null;  // No item dropped
//    }
        
   public void maybeDropItem() {
    double dropChance = 0.9; // High chance to verify functionality
    System.out.println("Attempting to drop an item with a chance of " + dropChance);
    if (Math.random() < dropChance) {
        Item[] possibleItems = {
            new RangeIncrease(10), new Detonator(10), new RollerSkate(10),
            // Add other items or curses here
        };
        this.item = possibleItems[(int) (Math.random() * possibleItems.length)];
        System.out.println("Dropped item: " + item.getClass().getSimpleName());
    } else {
        System.out.println("No item was dropped.");
    }
}
    
    
    
    @Override
     public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
    @Override
    public boolean isPassable(){
        return false;
    }
     public Item getItem() {
            return item;
        }
      public void clearItem() {
        this.item = null;
    }
    public Item destroyAndGetItem() {
    System.out.println("Destroying box. Item present: " + (item != null ? item.getClass().getSimpleName() : "None"));
    return item;  // Return the item to be handled by the game logic
}
}
