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
public class ExplosionTest {
    private Explosion explosion;
    private Tile[][] tiles;
    private Player player;
    private Monster monster;
    BombermanComponent bombermanComponent;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    Map<String, Integer> keyBindings;

    @BeforeEach
    void setUp() {
        bombermanComponent = new BombermanComponent();
        explosion = new Explosion(1, 1, 2);
        tiles = new Tile[3][3];

        // Initialize the tiles array with some dummy data
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Field(i,j);
            }
        }

        // Add a wall and a box to the tiles array
        tiles[1][2] = new Wall(1,2);
        //tiles[2][1] = new Box(2, 1);

        players = new ArrayList<>();
        monsters = new ArrayList<>();
        player = new Player("TestPlayer", 2, 1, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindings); // Set the player's position to be within the blast radius
        players.add(player);
        monster = new Monster(tiles, 1, 0, players, "assets/monsters/ghostfrontgreen.png"); // Set the monster's position to be within the blast radius
        monsters.add(monster);

        // Set the players and monsters lists in the BombermanComponent class
        bombermanComponent.players = players;
        bombermanComponent.monsters = monsters;
    }
    
    @Test
    void testBlastRadius() {
        assertEquals(2, explosion.getBlastRadius());
    }
    
    @Test
    void testPlayerAffectedByExplosion() {
        explosion.detonate(tiles);

        assertFalse(player.isAlive); // The player should be dead
    }

    @Test
    void testMonsterAffectedByExplosion() {
        explosion.detonate(tiles);

        assertFalse(monster.isAlive); // The monster should be dead
    }
    
    @Test
    void testWallNotAffectedByExplosion() {
        explosion.detonate(tiles);

        // Check that the wall has not been affected
        assertTrue(tiles[1][2] instanceof Wall); // The wall should still be there
    }

    
}
