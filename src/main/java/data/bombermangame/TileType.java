/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

/**
 * Represents the type of a tile in the Bomberman game.
 * This enum includes the following types:
 * FIELD - an open space where players can move
 * WALL - a solid structure that players cannot pass through
 * BOX - a destructible object that may contain power-ups
 * OBSTACLE - a non-destructible object that players cannot pass through
 *
 * @author ThetNaingSoe
 */
public enum TileType {
    FIELD, WALL, BOX, OBSTACLE
}
