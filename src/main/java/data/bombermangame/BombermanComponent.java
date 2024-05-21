package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.io.*;
import java.util.List; // Import only List from java.util
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyListener;
import javax.swing.Timer;

/**
 * BombermanComponent is a custom component that handles rendering and game logic for the Bomberman game.
 */
public class BombermanComponent extends JComponent {
    private List<Map<String, Integer>> keyBindingsList;

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

    /**
     * Constructor for BombermanComponent. Initializes the component and loads necessary images.
     */
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
        
        setFocusable(true);
        
        File file1 = new File("keybindings1.dat");
        File file2 = new File("keybindings2.dat");

        if (!file1.exists()) {
            saveDefaultKeyBindings("keybindings1.dat", getDefaultKeyBindingsPlayer1());
        }
        if (!file2.exists()) {
            saveDefaultKeyBindings("keybindings2.dat", getDefaultKeyBindingsPlayer2());
        }
    }

    /**
     * Saves the default key bindings to a file.
     *
     * @param filename the name of the file to save the key bindings to
     * @param defaultBindings the default key bindings
     */
    private void saveDefaultKeyBindings(String filename, Map<String, Integer> defaultBindings) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(defaultBindings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> loadKeyBindingsPlayer1() {
        return loadKeyBindingsFromFile("keybindings1.dat", getDefaultKeyBindingsPlayer1());
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> loadKeyBindingsPlayer2() {
        return loadKeyBindingsFromFile("keybindings2.dat", getDefaultKeyBindingsPlayer2());
    }

    /**
     * Loads key bindings from a file.
     *
     * @param filename the name of the file to load the key bindings from
     * @param defaultBindings the default key bindings
     * @return the loaded key bindings
     */
    private Map<String, Integer> loadKeyBindingsFromFile(String filename, Map<String, Integer> defaultBindings) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Map<String, Integer>) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            return defaultBindings;
        }
    }

    /**
     * Returns the default key bindings for Player 1.
     *
     * @return the default key bindings for Player 1
     */
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

    /**
     * Returns the default key bindings for Player 2.
     *
     * @return the default key bindings for Player 2
     */
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

    /**
     * Sets the tiles for the game.
     *
     * @param tiles the tiles to set
     */
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    /**
     * Sets the players for the game.
     *
     * @param players the players to set
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Sets the monsters for the game.
     *
     * @param monsters the monsters to set
     */
    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    /**
     * Loads the map from a file.
     *
     * @param filename the name of the file to load the map from
     * @return the loaded tiles
     */
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

    /**
     * Creates a tile from a symbol.
     *
     * @param symbol the symbol representing the tile
     * @param row the row of the tile
     * @param col the column of the tile
     * @return the created tile
     */
    private Tile createTileFromSymbol(char symbol, int row, int col) {
        switch (symbol) {
            case 'W': return new Wall(row, col);
            case 'B':
                Box box = new Box(row, col);
                box.maybeDropItem(); // Decide if this box will have an item when destroyed
                return box;
            case '0': return new Field(row, col);
            default: return null;
        }
    }

    /**
     * Sets up the key listeners for each player.
     */
    public void setupKeyListeners() {
        // Remove existing key listeners to avoid duplicates
        for (KeyListener listener : getKeyListeners()) {
            removeKeyListener(listener);
        }

        // Add key listeners for each player
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            addKeyListener(createKeyListenerForPlayer(player, i));
        }
        setFocusable(true); // Ensure the component can gain focus
    }

    /**
     * Sets the key bindings for the players.
     *
     * @param keyBindingsList a list of key bindings for the players
     */
    public void setKeyBindings(List<Map<String, Integer>> keyBindingsList) {
        this.keyBindingsList = keyBindingsList;
    }

    /**
     * Updates the key bindings for a player.
     *
     * @param player the player to update the key bindings for
     * @param upKey the key for moving up
     * @param downKey the key for moving down
     * @param leftKey the key for moving left
     * @param rightKey the key for moving right
     * @param bombKey the key for dropping a bomb
     * @param obstacleKey the key for placing an obstacle
     */
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

    /**
     * Sets up the controls for the players.
     */
    public void setupPlayerControls() {
        for (Player player : players) {
            int playerIndex = players.indexOf(player);
            if (playerIndex >= 0 && playerIndex < keyBindingsList.size()) {
                Map<String, Integer> keyBindings = keyBindingsList.get(playerIndex);
                updatePlayerKeys(player, keyBindings.get("UP"), keyBindings.get("DOWN"), keyBindings.get("LEFT"),
                        keyBindings.get("RIGHT"), keyBindings.get("BOMB"), keyBindings.get("OBSTACLE"));
            }
        }
    }

    /**
     * Creates a key listener for a player.
     *
     * @param player the player to create the key listener for
     * @param playerIndex the index of the player
     * @return the created key listener
     */
    private KeyAdapter createKeyListenerForPlayer(Player player, int playerIndex) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Map<String, Integer> keyBindings = keyBindingsList.get(playerIndex);
                int keyCode = e.getKeyCode();

                if (keyBindings != null && keyBindings.containsValue(keyCode)) {
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

    /**
     * Adds the key listener to the players.
     */
    public void initializePlayers() {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            addKeyListener(createKeyListenerForPlayer(player, i));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int mapWidth = tiles[0].length * SQUARE_SIZE;  // Calculate based on your tiles
        int mapHeight = tiles.length * SQUARE_SIZE;
        return new Dimension(mapWidth, mapHeight);
    }

    /**
     * Returns the image for an item.
     *
     * @param item the item to get the image for
     * @return the image for the item
     */
    private Image getItemImage(Item item) {
        if (item instanceof RangeIncrease) {
            return blastRangeImage;
        } else if (item instanceof AddBomb) {
            return addBombImage;
        } else if (item instanceof RollerSkate) {
            return rollerSkateImage;
        } else if (item instanceof Invincibility) {
            return invincableImage;
        } else if (item instanceof Detonator) {
            return detonatorImage;
        } else if (item instanceof BombBlock) {
            return bombBlockImage;
        } else if (item instanceof RangeDecrease) {
            return rangeDecreaseImage;
        } else if (item instanceof ForcedBombDrop) {
            return dropAllImage;
        } else if (item instanceof Ghost) {
            return ghostImage;
        } else if (item instanceof ObstaclePowerUp) {
            return obstaclePImage;
        }
        return null;
    }

    /**
     * Checks for power-up collisions and applies effects if necessary.
     *
     * @param player the player to check for collisions
     */
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
                        Item item = ((Box) tile).getItem();
                        if (item != null) {
                            Image itemImage = getItemImage(item);
                            g.drawImage(itemImage, x, y, null); // Draw item image before the box
                        }
                        // Draw box image last so it's on top
                        g.drawImage(boxImage, x, y, null);
                    } else if (tile instanceof Obstacle) {
                        // Draw the obstacle image
                        g.drawImage(boxImage, x, y, null);
                    } else if (tile instanceof Field) {
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

    /**
     * Draws an explosion on the component.
     *
     * @param g the graphics object
     * @param x the x-coordinate of the explosion
     * @param y the y-coordinate of the explosion
     * @param dx the change in x direction
     * @param dy the change in y direction
     * @param range the range of the explosion
     */
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
                    if (item != null) {
                        Image itemImage = getItemImage(item);
                        if (itemImage != null) {
                            g.drawImage(itemImage, newX, newY, null); // Draw item image if present
                        }
                    }
                    g.drawImage(explosionImage, newX, newY, null); // Draw explosion image on top
                    break; // Stop further explosion propagation in this direction
                } else if ((tile instanceof Obstacle)) {
                    Field field = new Field(row, col);
                    tiles[row][col] = field;
                    g.drawImage(fieldImage, newX, newY, null);
                    g.drawImage(explosionImage, newX, newY, null); // Draw explosion image on top
                    break; //
                } else if (!(tile instanceof Wall)) {
                    g.drawImage(explosionImage, newX, newY, null);
                } else {
                    break; // Stop the explosion at a wall
                }
            }
        }
    }

    /**
     * Shrinks the borders of the game area.
     */
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

    /**
     * Checks if a tile is on the border of the map.
     *
     * @param row the row of the tile
     * @param col the column of the tile
     * @return true if the tile is on the border, false otherwise
     */
    private boolean isBorderTile(int row, int col) {
        return row == borderLayer || row == tiles.length - 1 - borderLayer ||
               col == borderLayer || col == tiles[0].length - 1 - borderLayer;
    }

    /**
     * Checks if a position is valid.
     *
     * @param x the x-coordinate of the position
     * @param y the y-coordinate of the position
     * @return true if the position is valid, false otherwise
     */
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }

    /**
     * Checks if a player is in an explosion.
     *
     * @param player the player to check
     * @return true if the player is in an explosion, false otherwise
     */
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
