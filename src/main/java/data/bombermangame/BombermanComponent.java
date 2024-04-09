/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.io.*;
import java.util.List; // Import only List from java.util
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image; 
/**
 *
 * @author ThetNaingSoe
 */
public class BombermanComponent extends JComponent {

    private static final int SQUARE_SIZE = 50; // Example size for rendering
    private Tile[][] tiles; 
    private Player player;
    public static ArrayList<Player> players = new ArrayList<>();
    public ArrayList<Monster> monsters = new ArrayList<>();
    public Monster monster;
    private HashMap<Player, HashSet<Integer>> playerKeyMap = new HashMap<>();
    private Image fieldImage;
    private Image wallImage;
    private Image boxImage;

    public BombermanComponent() {
        
        try {
            fieldImage = ImageIO.read(new File("field.png")); // Replace with your image path 
            fieldImage = fieldImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            wallImage = ImageIO.read(new File("wall.png")); // Replace with your image path 
            wallImage = wallImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            boxImage = ImageIO.read(new File("box.png")); // Replace with your image path 
            boxImage = boxImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    
    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    public Tile[][] loadMapFromFile(String filename) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            List<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            tiles = new Tile[lines.size()][lines.get(0).length()]; 

            for (int row = 0; row < lines.size(); row++) {
                for (int col = 0; col < lines.get(0).length(); col++) {
                    char symbol = lines.get(row).charAt(col);
                    tiles[row][col] = createTileFromSymbol(symbol, row, col); 
                }
            }

            return tiles; // Add this line to return the tiles array
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null; // Return null in case of an exception 
        }
    }

    private Tile createTileFromSymbol(char symbol, int row, int col) {
        switch (symbol) {
            case 'W': return new Wall(row, col);
            case 'B': return new Box(row, col, tiles); 
            case '0': return new Field(row, col);
            default:  return null; 
        }
    }
    
    public void setupKeyListeners(JComponent component) {
        // Example key assignments
        addPlayerKeys(players.get(0), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);  
        addPlayerKeys(players.get(1), KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D); 

        // Create key listeners
        for (Player player : players) {
            component.addKeyListener(createKeyListenerForPlayer(player));
            System.out.println("Key Listener added for player");
        }
    }
    
    private void addPlayerKeys(Player player, int upKey, int downKey, int leftKey, int rightKey) {
        HashSet<Integer> keySet = new HashSet<>();
        keySet.add(upKey);
        keySet.add(downKey);
        keySet.add(leftKey);
        keySet.add(rightKey);
        playerKeyMap.put(player, keySet);
    }
    
    private KeyAdapter createKeyListenerForPlayer(Player player) {
        //player.updateMap();
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (playerKeyMap.get(player).contains(e.getKeyCode())) { // Check for valid key
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP: 
                            player.moveUp(tiles);
                            System.out.println("moved up arrow");
                            break;
                        case KeyEvent.VK_DOWN: 
                            player.moveDown(tiles);
                            break;
                        case KeyEvent.VK_LEFT: 
                            player.moveLeft(tiles);
                            break;
                        case KeyEvent.VK_RIGHT: 
                            player.moveRight(tiles);
                            break;
//                        case KeyEvent.VK_SPACE: 
//                            player.dropBomb(tiles);
//                            System.out.println("Key pressed: " + e.getKeyCode());
//                            break;
                        // Add cases for player 2's keys:
                        case KeyEvent.VK_W: 
                            player.moveUp(tiles); 
                            System.out.println("moved up w");
                            break;
                        case KeyEvent.VK_S: 
                            player.moveDown(tiles);
                            break;
                        case KeyEvent.VK_A: 
                            player.moveLeft(tiles);
                            break;
                        case KeyEvent.VK_D: 
                            player.moveRight(tiles);
                            break;
//                        case KeyEvent.VK_F: 
//                            player.dropBomb();
//                            System.out.println("Key pressed: " + e.getKeyCode());
//                            break;
                        
                    }
                }
            }
        };
    }
    
    @Override
    public Dimension getPreferredSize() {
        int mapWidth = tiles[0].length * SQUARE_SIZE;  // Calculate based on your tiles
        int mapHeight = tiles.length * SQUARE_SIZE;
        return new Dimension(mapWidth, mapHeight); 
    }

   @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Monsters in paintComponent: " + monsters.size()); // Add this line 
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                Tile tile = tiles[row][col];
                if (tile != null) { 
                    int x = col * SQUARE_SIZE; 
                    int y = row * SQUARE_SIZE;

                    if (tile instanceof Wall) {
                        g.drawImage(wallImage, x, y, null); 
                    } else if (tile instanceof Box) {
                        g.drawImage(boxImage, x, y, null); 
                    } else { // Likely Field 
                        g.drawImage(fieldImage, x, y, null); 
                    }

                }
            }
        }
        
            // Draw all players 
        for (Player player : players) { 
            if (player.isAlive) { // Only draw if the player is alive
                int playerX = player.currentCol * SQUARE_SIZE;
                int playerY = player.currentRow * SQUARE_SIZE;

                // Assuming each player has its unique image
                g.drawImage(player.getPlayerImage(), playerX, playerY, null); 
            }
        }
        
        for (Monster monster : monsters) { 
            if (monster != null) { 
                System.out.println("Drawing monster at: " + monster.currentCol * SQUARE_SIZE + ", " + monster.currentRow * SQUARE_SIZE);
                System.out.println(monster.monsterImage); // Debugging line 
                int monsterX = monster.currentCol * SQUARE_SIZE;
                int monsterY = monster.currentRow * SQUARE_SIZE;
                g.drawImage(monster.monsterImage, monsterX, monsterY, null); 
            }
        } 
    }
}
