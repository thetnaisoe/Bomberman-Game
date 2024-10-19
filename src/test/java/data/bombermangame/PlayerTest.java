/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package data.bombermangame;

import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author ThetNaingSoe
 */
public class PlayerTest {
    
    Player player;
    Monster monster;
    Tile[][] tiles;
    BombermanComponent bombermanComponent;
    ArrayList<Bomb> bombs;
    ArrayList<Monster> monsters;
    Map<String, Integer> keyBindings;

    @BeforeEach
    public void setUp() {
        bombermanComponent = new BombermanComponent();
        tiles = new Tile[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = new Field(i,j); 
            }
        }
        bombs = new ArrayList<>();
        monsters = new ArrayList<>();
        player = new Player("TestPlayer", 5, 5, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindings);
    }
    

    /**
     * Test of moveUp method, of class Player.
     */
    @org.junit.jupiter.api.Test
    public void testMoveUp() {
        player.moveUp(tiles);
        assertEquals(4, player.currentRow);
    }
    
    @Test
    public void testMoveUpIntoWall() {
        tiles[4][5] = new Wall(4, 5); // Make the tile above the player a Wall
        player.moveUp(tiles);
        assertEquals(5, player.currentRow); // The player should not have moved
    }

    @Test
    public void testMoveUpIntoBox() {
        tiles[4][5] = new Box(4, 5); // Make the tile above the player a Box
        player.moveUp(tiles);
        assertEquals(5, player.currentRow); // The player should not have moved
    }

    /**
     * Test of moveDown method, of class Player.
     */
    @org.junit.jupiter.api.Test
    public void testMoveDown() {
        player.moveDown(tiles);
        assertEquals(6, player.currentRow);
    }
    
    @Test
    public void testMoveDownIntoWall() {
        tiles[6][5] = new Wall(6, 5); // Make the tile above the player a Wall
        player.moveDown(tiles);
        assertEquals(5, player.currentRow); // The player should not have moved
    }

    @Test
    public void testMoveDownIntoBox() {
        tiles[6][5] = new Box(6, 5); // Make the tile above the player a Box
        player.moveDown(tiles);
        assertEquals(5, player.currentRow); // The player should not have moved
    }

    /**
     * Test of moveLeft method, of class Player.
     */
    @org.junit.jupiter.api.Test
    public void testMoveLeft() {
        player.moveLeft(tiles);
        assertEquals(4, player.currentCol);
    }
    
    @Test
    public void testMoveLeftIntoWall() {
        tiles[5][4] = new Wall(5, 4); // Make the tile above the player a Wall
        player.moveLeft(tiles);
        assertEquals(5, player.currentCol); // The player should not have moved
    }

    @Test
    public void testMoveLeftIntoBox() {
        tiles[5][4] = new Box(5, 4); // Make the tile above the player a Wall
        player.moveLeft(tiles);
        assertEquals(5, player.currentCol); // The player should not have moved
    }

    /**
     * Test of moveRight method, of class Player.
     */
    @org.junit.jupiter.api.Test
    public void testMoveRight() {
        player.moveRight(tiles);
        assertEquals(6, player.currentCol);
    }
    
    @Test
    public void testMoveRightIntoWall() {
        tiles[5][6] = new Wall(5, 6); // Make the tile above the player a Wall
        player.moveRight(tiles);
        assertEquals(5, player.currentCol); // The player should not have moved
    }

    @Test
    public void testMoveRightIntoBox() {
        tiles[5][6] = new Box(5, 6); // Make the tile above the player a Wall
        player.moveRight(tiles);
        assertEquals(5, player.currentCol); // The player should not have moved
    }

    /**
     * Test of dropBomb method, of class Player.
     */
    @org.junit.jupiter.api.Test
    public void testDropBombOnField() {
        player.dropBomb(tiles, bombs);
        assertEquals(1, bombs.size()); // The player should be able to drop a bomb
    }

    
}
