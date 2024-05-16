/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package data.bombermangame;

import java.util.*;
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
public class MonsterTest {
    
    private Monster monster;
    private Tile[][] tiles;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    BombermanComponent bombermanComponent;

    @BeforeEach
    public void setUp() {
        bombermanComponent = new BombermanComponent();
        tiles = new Tile[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = new Field(i, j); 
            }
        }
        players = new ArrayList<>();
        monsters = new ArrayList<>();
        players.add(new Player("TestPlayer", 5, 5, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png")); 
        monster = new Monster(tiles, 0, 0, players, "assets/monsters/ghostfrontgreen.png"); 
        monsters.add(monster);
    }
    
    @Test
    public void testMoveIntoWall() {
        tiles[1][0] = new Wall(1, 0); // Make the tile above the monster a Wall
        monster.moveToRandomDirection();
        assertNotEquals(1, monster.currentRow); // The monster should not have moved into the Wall
    }

    @Test
    public void testMoveIntoBox() {
        tiles[1][0] = new Box(1, 0); // Make the tile above the monster a Box
        monster.moveToRandomDirection();
        assertNotEquals(1, monster.currentRow); // The monster should not have moved into the Box
    }

    @Test
    public void testCollidesWithPlayer() {
        // Move player to monster's position
        Player player = players.get(0);
        player.currentRow = monster.currentRow;
        player.currentCol = monster.currentCol;
        assertTrue(monster.collidesWithPlayer(monster.currentRow, monster.currentCol));
    }

    @Test
    public void testHandlePlayerCollision() {
        // Move player to monster's position and handle collision
        Player player = players.get(0);
        player.currentRow = monster.currentRow;
        player.currentCol = monster.currentCol;
        monster.handlePlayerCollision(player);
        assertFalse(player.isAlive);
    }
    
}
