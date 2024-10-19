/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package data.bombermangame;

/**
 * Represents an object that can explode in the game.
 * This interface provides a method for exploding the object and applying its effects to the tiles.
 * 
 * @author lenovo
 */
public interface Explodable {

    /**
     * Explodes the object and applies its effects to the specified tiles.
     *
     * @param tiles the tiles to apply the explosion's effects to
     */
    void explode(Tile[][] tiles);
}
