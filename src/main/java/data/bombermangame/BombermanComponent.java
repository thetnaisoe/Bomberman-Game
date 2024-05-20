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
import java.awt.event.KeyListener;
import javax.swing.Timer;

/**
 *
 * @author ThetNaingSoe
 */
public class BombermanComponent extends JComponent {
     private Map<String, Integer> keyBindingsPlayer1;
    private Map<String, Integer> keyBindingsPlayer2;
    private Map<String, Integer> keyBindingsPlayer3;

    public int countdownTimeInSeconds = 90; // Example countdown time
    private Timer countdownTimer;
    private boolean isPainted = false;
    private static final int SQUARE_SIZE = 50; // Example size for rendering
    public int borderLayer = 0;
    public static Tile[][] tiles; 
    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<Monster> monsters = new ArrayList<>();
    public Monster monster;
    private HashMap<Player, HashSet<Integer>> playerKeyMap = new HashMap<>();
    public static ArrayList<Bomb> bombs = new ArrayList<>();
    public boolean isTreePlayers;
    int numberOfPlayers = SelectPlayersGUI.numberOfPlayers;
    private Image fieldImage;
    private Image wallImage;
    private Image boxImage;
    private Image bombImage;
    private Image explosionImage;
    public Image blastRangeImage;
    public Image detonatorImage;
    public Image rollerSkateImage;
    public Image invincableImage;
     public Image addBombImage;
     public Image rangeDecreaseImage;
     public Image bombBlockImage;
     public Image dropAllImage;
     public Image ghostImage;
     public Image obstaclePImage;
     
    
    public BombermanComponent() {
        try {
            fieldImage = ImageIO.read(new File("assets/fields/field.png")); // Replace with your image path 
            fieldImage = fieldImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            wallImage = ImageIO.read(new File("assets/fields/wall.png")); // Replace with your image path 
            wallImage = wallImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            boxImage = ImageIO.read(new File("assets/fields/block.png")); // Replace with your image path 
            boxImage = boxImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bombImage = ImageIO.read(new File("assets/explosion/bombgreen.png")); // Replace with your image path 
            bombImage = bombImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            explosionImage = ImageIO.read(new File("assets/explosion/blastcenter.png")); // Replace with your image path 
            explosionImage = explosionImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            blastRangeImage = ImageIO.read(new File("assets/powerups/RangeP.png")); // Replace with your image path 
            blastRangeImage = blastRangeImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            detonatorImage = ImageIO.read(new File("assets/powerups/DetonatorP.png")); // Replace with your image path 
            detonatorImage = detonatorImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            addBombImage = ImageIO.read(new File("assets/powerups/addBombP.png")); // Replace with your image path 
            addBombImage = addBombImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            rollerSkateImage = ImageIO.read(new File("assets/powerups/RollerP.png")); // Replace with your image path 
            rollerSkateImage = rollerSkateImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
         try {
            invincableImage = ImageIO.read(new File("assets/powerups/InvisibleP.png")); // Replace with your image path 
            invincableImage = invincableImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
           try {
            rangeDecreaseImage = ImageIO.read(new File("assets/Curses/rangeDecreaseC.png")); // Replace with your image path 
            rangeDecreaseImage = rangeDecreaseImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
            try {
            bombBlockImage = ImageIO.read(new File("assets/Curses/bombBlockC.png")); // Replace with your image path 
            bombBlockImage = bombBlockImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
            try {
            dropAllImage = ImageIO.read(new File("assets/Curses/dropAllC.png")); // Replace with your image path 
            dropAllImage = dropAllImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
              try {
            ghostImage = ImageIO.read(new File("assets/Powerups/GhostP.png")); // Replace with your image path 
            ghostImage = ghostImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
                 try {
            obstaclePImage = ImageIO.read(new File("assets/Powerups/ObsticaleP.png")); // Replace with your image path 
            obstaclePImage = obstaclePImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
          keyBindingsPlayer1 = loadKeyBindingsPlayer1();
        keyBindingsPlayer2 = loadKeyBindingsPlayer2();
        keyBindingsPlayer3 = loadKeyBindingsPlayer3();
        //setupKeyListeners();
        setFocusable(true);
        
         File file1 = new File("keybindings1.dat");
    File file2 = new File("keybindings2.dat");
    File file3 = new File("keybindings3.dat");
    

    if (!file1.exists()) {
        saveDefaultKeyBindings("keybindings1.dat", getDefaultKeyBindingsPlayer1());
    }
    if (!file2.exists()) {
        saveDefaultKeyBindings("keybindings2.dat", getDefaultKeyBindingsPlayer2());
    }
    if (!file2.exists()) {
        saveDefaultKeyBindings("keybindings3.dat", getDefaultKeyBindingsPlayer3());
    }


        
    }
    private void saveDefaultKeyBindings(String filename, Map<String, Integer> defaultBindings) {
    try (FileOutputStream fos = new FileOutputStream(filename);
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(defaultBindings);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
   @SuppressWarnings("unchecked")
    private Map<String, Integer>loadKeyBindingsPlayer1() {
        return loadKeyBindingsFromFile("keybindings1.dat", getDefaultKeyBindingsPlayer1());
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> loadKeyBindingsPlayer2() {
        return loadKeyBindingsFromFile("keybindings2.dat", getDefaultKeyBindingsPlayer2());
    }
    @SuppressWarnings("unchecked")
    private Map<String, Integer> loadKeyBindingsPlayer3() {
        return loadKeyBindingsFromFile("keybindings3.dat", getDefaultKeyBindingsPlayer3());
    }

    private Map<String, Integer> loadKeyBindingsFromFile(String filename, Map<String, Integer> defaultBindings) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Map<String, Integer>) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            return defaultBindings;
        }
    }

    private Map<String, Integer> getDefaultKeyBindingsPlayer1() {
        Map<String, Integer> defaultBindings = new HashMap<>();
        defaultBindings.put("UP", KeyEvent.VK_W);
        defaultBindings.put("DOWN", KeyEvent.VK_S);
        defaultBindings.put("LEFT", KeyEvent.VK_A);
        defaultBindings.put("RIGHT", KeyEvent.VK_D);
        defaultBindings.put("BOMB", KeyEvent.VK_SPACE);
        defaultBindings.put("OBSTACLE", KeyEvent.VK_O);
        return defaultBindings;
    }

    private Map<String, Integer> getDefaultKeyBindingsPlayer2() {
        Map<String, Integer> defaultBindings = new HashMap<>();
        defaultBindings.put("UP", KeyEvent.VK_UP);
        defaultBindings.put("DOWN", KeyEvent.VK_DOWN);
        defaultBindings.put("LEFT", KeyEvent.VK_LEFT);
        defaultBindings.put("RIGHT", KeyEvent.VK_RIGHT);
        defaultBindings.put("BOMB", KeyEvent.VK_ENTER);
        defaultBindings.put("OBSTACLE", KeyEvent.VK_L);
        return defaultBindings;
    }
    private Map<String, Integer> getDefaultKeyBindingsPlayer3() {
        Map<String, Integer> defaultBindings = new HashMap<>();
        defaultBindings.put("UP", KeyEvent.VK_T);
        defaultBindings.put("DOWN", KeyEvent.VK_G);
        defaultBindings.put("LEFT", KeyEvent.VK_F);
        defaultBindings.put("RIGHT", KeyEvent.VK_H);
        defaultBindings.put("BOMB", KeyEvent.VK_Y);
        defaultBindings.put("OBSTACLE", KeyEvent.VK_U);
        return defaultBindings;
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
            case 'B': 
           //     System.out.println("Creating Box at row: " + row + ", col: " + col);
                Box box = new Box(row, col);
            box.maybeDropItem(); // Decide if this box will have an item when destroyed
            return box; 
            case '0': return new Field(row, col);
            default: return null; 
        }
    }

    
    public void setupKeyListeners() {
        // Remove existing key listeners to avoid duplicates
        for (KeyListener listener : getKeyListeners()) {
            removeKeyListener(listener);
        }

        // Add key listeners for each player
        for (Player player : players) {
            addKeyListener(createKeyListenerForPlayer(player));
        }
        setFocusable(true); // Ensure the component can gain focus
    }

public void setKeyBindings(List<Map<String, Integer>> keyBindingsList) {
    // Initialize players with default images and positions
    ArrayList<Player> players = new ArrayList<>();
    for (int i = 0; i < keyBindingsList.size(); i++) {
        Player player = new Player("Player" + (i + 1), i * 10, i * 10, this, monsters,"assets/players/bombermanfrontgreen.png", keyBindingsList.get(i));
        players.add(player);
        updatePlayerKeys(player, player.getKeyBindings().get("UP"), player.getKeyBindings().get("DOWN"), player.getKeyBindings().get("LEFT"), player.getKeyBindings().get("RIGHT"), player.getKeyBindings().get("BOMB"), player.getKeyBindings().get("OBSTACLE"));
    }

    setPlayers(players);
    setupPlayerControls();
    setupKeyListeners(); // Setup key listeners with new bindings
}


    
    // Method to update key bindings for a player
public void updatePlayerKeys(Player player, int upKey, int downKey, int leftKey, int rightKey, int bombKey, int obstacleKey) {
    HashSet<Integer> keySet = new HashSet<>();
    keySet.add(upKey);
    keySet.add(downKey);
    keySet.add(leftKey);
    keySet.add(rightKey);
    keySet.add(bombKey);
    keySet.add(obstacleKey);
    playerKeyMap.put(player, keySet); // Store the keys in a map where Player is the key
}

// Call this method when initializing or when key settings change
public void setupPlayerControls() {
    for (Player player : players) {
        Map<String, Integer> keyBindings = player.getKeyBindings();
        updatePlayerKeys(player, keyBindings.get("UP"), keyBindings.get("DOWN"), keyBindings.get("LEFT"), keyBindings.get("RIGHT"), keyBindings.get("BOMB"), keyBindings.get("OBSTACLE"));
    }
}


    private void addPlayerKeys(Player player, int upKey, int downKey, int leftKey, int rightKey, int bombKey, int obstacleKey) {
        HashSet<Integer> keySet = new HashSet<>();
        keySet.add(upKey);
        keySet.add(downKey);
        keySet.add(leftKey);
        keySet.add(rightKey);
        keySet.add(bombKey); // Add bomb key to the set of player keys
        keySet.add(obstacleKey);
        playerKeyMap.put(player, keySet);
    }

private KeyAdapter createKeyListenerForPlayer(Player player) {
    return new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            Map<String, Integer> keyBindings = player.getKeyBindings();
            int keyCode = e.getKeyCode();

            if (playerKeyMap.get(player).contains(keyCode)) {
                if (keyCode == keyBindings.get("UP")) {
                    player.moveUp(tiles);
                } else if (keyCode == keyBindings.get("DOWN")) {
                    player.moveDown(tiles);
                } else if (keyCode == keyBindings.get("LEFT")) {
                    player.moveLeft(tiles);
                } else if (keyCode == keyBindings.get("RIGHT")) {
                    player.moveRight(tiles);
                } else if (keyCode == keyBindings.get("BOMB")) {
                    player.dropBomb(tiles, bombs);
                } else if (keyCode == keyBindings.get("OBSTACLE")) {
                    player.placeObstacle(tiles);
                }
                repaint(); // Repaint the component to reflect the movement
            }
        }
    };
}

// Example of how to add the key listener to players
public void initializePlayers() {
    for (Player player : players) {
        addKeyListener(createKeyListenerForPlayer(player));
    }
}

    @Override
    public Dimension getPreferredSize() {
        int mapWidth = tiles[0].length * SQUARE_SIZE;  // Calculate based on your tiles
        int mapHeight = tiles.length * SQUARE_SIZE;
        return new Dimension(mapWidth, mapHeight); 
    }
    private Image getItemImage(Item item) {
    // You need to load these images in the constructor or elsewhere

         if (item instanceof RangeIncrease) {
        return blastRangeImage;
    } else if (item instanceof AddBomb) {
        return addBombImage;
    } else if (item instanceof RollerSkate) {
        return rollerSkateImage;
    } else if (item instanceof Invincibility) {
        return invincableImage;
    }
          else if (item instanceof Detonator) {
        return detonatorImage;
    }
           else if (item instanceof BombBlock) {
        return bombBlockImage;
    }
              else if (item instanceof RangeDecrease) {
        return rangeDecreaseImage;
    }
              else if (item instanceof ForcedBombDrop) {
        return dropAllImage;
    }
                   else if (item instanceof Ghost) {
        return ghostImage;
    }
                else if (item instanceof ObstaclePowerUp) {
        return obstaclePImage;
    }
        
        
    return null;
}
    private void checkPowerUpCollision(Player player) {
    int row = player.currentRow;
    int col = player.currentCol;
    Tile tile = tiles[row][col];
    if (tile instanceof Field) {
        Item item = ((Field) tile).getItem();
        if (item instanceof PowerUp) {
            player.pickUpPowerUp((PowerUp) item);
            ((Field) tile).setItem(null); // Remove the power-up from the field
        }
        if (item instanceof Curse) {
            player.pickUpCurse((Curse) item);
            ((Field) tile).setItem(null); // Remove the power-up from the field
        }
    }
    }

   @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                Tile tile = tiles[row][col];
                if (tile != null) { 
                    int x = col * SQUARE_SIZE; 
                    int y = row * SQUARE_SIZE;

                    if (tile instanceof Wall) {
                        g.drawImage(wallImage, x, y, null); 
                        tile.setImage(wallImage);
                    } else if (tile instanceof Box) {
                     Item item = ((Box)tile).getItem();
                    if (item != null) {
                        Image itemImage = getItemImage(item);
                        g.drawImage(itemImage, x, y, null); // Draw item image before the box
                    }
                    // Draw box image last so it's on top
                    g.drawImage(boxImage, x, y, null);
                   
                    } else if (tile instanceof Obstacle) {
                    // Draw the obstacle image
                    g.drawImage(boxImage, x, y, null);
                }else if (tile instanceof Field) {
                    // Draw explosion image if it's set
                    if (tile.getImage() == explosionImage) {
                        g.drawImage(explosionImage, x, y, null);
                    } else {
                        // Draw field image
                        g.drawImage(fieldImage, x, y, null); 
                    }
                    // Draw item image if present
                    Item item = ((Field) tile).getItem();
                    if (item != null) {
                        Image itemImage = getItemImage(item);
                        if (itemImage != null) {
                            g.drawImage(itemImage, x, y, null);
                        }
                    }
                }
                

                }
            }
        }
        
            // Draw all players 
        for (Player player : players) {
            if (player.isAlive) {
                checkPowerUpCollision(player);
                int playerX = player.currentCol * SQUARE_SIZE;
                int playerY = player.currentRow * SQUARE_SIZE;
                // Check if the player is on a tile with an explosion image
                if (tiles[player.currentRow][player.currentCol].getImage() == explosionImage) {
                    // Player dies if on an explosion tile
                    
                    player.isAlive = false;
                    // You may want to perform additional actions here, such as removing the player from the game
                } else {
                    // Draw the player if they are alive and not on an explosion tile
                    g.drawImage(player.getPlayerImage(), playerX, playerY, null);
                }
            }
        }
    
    // Iterate through monsters
        for (Monster monster : monsters) {
            if (monster.isAlive) {
                int monsterX = monster.currentCol * SQUARE_SIZE;
                int monsterY = monster.currentRow * SQUARE_SIZE;
                // Check if the monster is on a tile with an explosion image
                if (tiles[monster.currentRow][monster.currentCol].getImage() == explosionImage) {
                    // Monster dies if on an explosion tile
                    monster.isAlive = false;
                    // You may want to perform additional actions here, such as removing the monster from the game
                } else {
                    // Draw the monster if they are alive and not on an explosion tile
                    g.drawImage(monster.monsterImage, monsterX, monsterY, null);
                }
            }
        }
        Iterator<Bomb> iterator = bombs.iterator();
        while (iterator.hasNext()) {
            Bomb bomb = iterator.next();
            if (!bomb.isExploded()) {
                // Draw bomb if it hasn't exploded yet
                int bombX = bomb.getCol() * SQUARE_SIZE;
                int bombY = bomb.getRow() * SQUARE_SIZE;
                g.drawImage(bombImage, bombX, bombY, null);
            } else {
                // Draw the explosion
                drawExplosion(g, bomb.getCol() * SQUARE_SIZE, bomb.getRow() * SQUARE_SIZE, 1, 0, bomb.getExplosionRadius());
                drawExplosion(g, bomb.getCol() * SQUARE_SIZE, bomb.getRow() * SQUARE_SIZE, -1, 0, bomb.getExplosionRadius());
                drawExplosion(g, bomb.getCol() * SQUARE_SIZE, bomb.getRow() * SQUARE_SIZE, 0, 1, bomb.getExplosionRadius());
                drawExplosion(g, bomb.getCol() * SQUARE_SIZE, bomb.getRow() * SQUARE_SIZE, 0, -1, bomb.getExplosionRadius());
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                iterator.remove();
                bombs.remove(bomb);
            }
        }
        if (!isPainted) {
            countdownTimer = new Timer(1000, e -> {
                countdownTimeInSeconds--; // Decrement countdown value every second
                if (countdownTimeInSeconds % 15 == 0 && countdownTimeInSeconds > 0) {
                    // Call shrinkBorders() when countdownValue is divisible by 5
                    shrinkBorders();
                    repaint(); // Request a repaint after the borders shrink
                }
                if (countdownTimeInSeconds <= 0) {
                    // Countdown finished, perform any final actions
                    countdownTimer.stop();
                    // Add any additional logic you want to execute when countdown finishes
                    repaint(); // Request a repaint when countdown finishes
                }
            });
            countdownTimer.start();
            isPainted = true; // Set the flag to true after the first paintComponent call
        }
    }

    private void drawExplosion(Graphics g, int x, int y, int dx, int dy, int range) {
    g.drawImage(explosionImage, x, y, null); // Draw the initial explosion at the bomb's location

    for (int i = 1; i <= range; i++) {
        int newX = x + i * SQUARE_SIZE * dx;
        int newY = y + i * SQUARE_SIZE * dy;
        if (isValidPosition(newX, newY)) {
            int col = newX / SQUARE_SIZE;
            int row = newY / SQUARE_SIZE;
            Tile tile = tiles[row][col];

            // Ensure only to process if it's a Box
            if (tile instanceof Box) {
                  Box box = (Box) tile;
                Item item = box.destroyAndGetItem(); // Get item from the destroyed box
                Field field = new Field(row, col);  // Create a new Field where the Box was
                field.setItem(item);  // Set the item to the field if any
                tiles[row][col] = field;  // Replace the box with a field in the tiles array

                g.drawImage(fieldImage, newX, newY, null); // Draw the field image first
//               if(((Field)(tiles[row][col])).getItem()!=null){
//                   System.out.println("Filed GET NOT NULLLLLLLLL");
//               }
               
                if (item != null) {
                   // System.out.println("iten NOT NULLLLL");
                    Image itemImage = getItemImage(item);
                    if (itemImage != null) {
                        g.drawImage(itemImage, newX, newY, null); // Draw item image if present
                    }
                }
                g.drawImage(explosionImage, newX, newY, null); // Draw explosion image on top
                break; // Stop further explosion propagation in this direction
            }
            else if ((tile instanceof Obstacle)) {
                 Field field = new Field(row, col); 
                tiles[row][col] = field;
                g.drawImage(fieldImage, newX, newY, null);
                  g.drawImage(explosionImage, newX, newY, null); // Draw explosion image on top
                break; // 
            }
            
            else if (!(tile instanceof Wall)) {
                g.drawImage(explosionImage, newX, newY, null);
            }
            
            else {
                break; // Stop the explosion at a wall
            }
        }
    }
} 
    private void shrinkBorders() {
        for (int row = borderLayer; row < tiles.length - borderLayer; row++) {
            for (int col = borderLayer; col < tiles[0].length - borderLayer; col++) {
                if (isBorderTile(row, col)) {
                    tiles[row][col] = new Field(row, col); // Change border tiles to Field tiles
                    tiles[row][col].setImage(explosionImage); // Set explosion image for border tiles
                }
            }
        }
        borderLayer++; // Increment the border layer for the next shrinkage
    }

    // Method to check if a tile is on the border of the map
    private boolean isBorderTile(int row, int col) {
        return row == borderLayer || row == tiles.length - 1 - borderLayer ||
               col == borderLayer || col == tiles[0].length - 1 - borderLayer;
    }



    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }
    
    public boolean isPlayerInExplosion(Player player) {
        int playerX = player.currentCol * SQUARE_SIZE;
        int playerY = player.currentRow * SQUARE_SIZE;
        // Check if the player is on a tile with an explosion image
        if (tiles[player.currentRow][player.currentCol].getImage() == explosionImage) {
            // Player is on an explosion tile
            return true;
        }
        return false;
    }
}
