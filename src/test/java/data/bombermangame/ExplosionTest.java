/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package data.bombermangame;

import java.util.ArrayList;
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
        tiles[2][1] = new Box(2, 1);

        players = new ArrayList<>();
        players.add(new Player("TestPlayer", 1, 0, bombermanComponent, "assets/players/bombermanfrontgreen.png")); 
        player = new Player("TestPlayer", 1, 0, bombermanComponent, "assets/players/bombermanfrontgreen.png");
        monster = new Monster(tiles, 0, 0, players, "assets/monsters/ghostfrontgreen.png"); 

    }
    
    @Test
    void testBlastRadius() {
        assertEquals(2, explosion.getBlastRadius());
    }

    @Test
    void testDetonate() {
        explosion.detonate(tiles);

        //Check that the tiles around the explosion have been affected
        assertTrue(tiles[1][0] instanceof Field); // The player's tile should be empty
        assertTrue(tiles[1][2] instanceof Wall); // The wall should still be there
        //assertTrue(tiles[2][1] instanceof Field); // The box should have been replaced with an empty tile

        //Check that the player and monster have been affected
        //assertFalse(player.isAlive); // The player should be dead
        assertTrue(monster.isAlive); // The monster should still be alive
    }

    
}
