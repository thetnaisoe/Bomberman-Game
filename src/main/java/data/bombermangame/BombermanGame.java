package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.*;

import data.bombermangame.Monster;
import data.bombermangame.Player;

public class BombermanGame extends JFrame implements PlayerEventListener {
    private static final int ROWS = 15; // Number of rows in the table
    private static final int COLS = 15; // Number of columns in the table
    private JButton[][] buttons; // Array to hold game buttons
    private Tile[][] tiles;
    
    public static ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Monster> monsters = new ArrayList<>();
    private HashMap<Player, HashSet<Integer>> playerKeyMap = new HashMap<>();
    
  

    public BombermanGame() {
        setTitle("Bomberman Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Set layout to BorderLayout
        

       
        buttons = new JButton[ROWS][COLS]; // Initialize the buttons array
         tiles = new Tile[ROWS][COLS];
         setupGameUI();
  

        // Add buttons to the main panel
        
    }
    public void resetGame() {
       
    getContentPane().removeAll(); // Remove all UI components from the JFrame

    // Reinitialize game state variables if necessary
    buttons = new JButton[ROWS][COLS];
    tiles = new Tile[ROWS][COLS];
    players.clear();
    monsters.clear();
    playerKeyMap.clear();

    // Re-setup the game UI and state
    setupGameUI(); // You'll need to implement this based on your constructor logic

    // Revalidate and repaint to reflect changes
    revalidate();
    repaint();
}
    private void setupGameUI() {
    // Move your current constructor logic here to setup the game
    // This includes creating panels, setting up the game board, adding players, etc.
    // Essentially, refactor your constructor to call this method after initial setup
     // Create main game panel
        JPanel mainPanel = new JPanel(new GridLayout(15, 15));
        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();
    try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader("MapONE.txt")));
            for (int i = 0; i < ROWS; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < COLS; j++) {
                    char symbol = line.charAt(j);
                    JButton button = new JButton();
                    Tile tile;
                   // Tile tile = new Tile();
                    button.setPreferredSize(new Dimension(50, 50));
                    button.setOpaque(true);
                    button.setContentAreaFilled(true); 
                    button.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Add a border  
                    if (symbol == 'W') {
                        button.setBackground(Color.BLACK);
                          tile = new Wall(i,j);
                        

                    } else if(symbol=='B'){
                        button.setBackground(Color.GREEN);
                         tile = new Box(i,j,tiles);
                        

                    }
                    else {
                        button.setBackground(Color.GRAY);
                          tile = new Field(i,j);
                        
                    }
                    buttons[i][j] = button; // Add button to the buttons array
                    tiles[i][j]=tile;
                    //System.out.println(tile);
                    mainPanel.add(button);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        add(mainPanel, BorderLayout.CENTER); // Add main panel to the center

        // Create and add panel for game elements on top
        JPanel gameElementsPanel = new JPanel();
        gameElementsPanel.setPreferredSize(new Dimension(200, 100)); // Set size as needed
        gameElementsPanel.setBackground(Color.GREEN); // Set background color
        add(gameElementsPanel, BorderLayout.NORTH); // Add game elements panel to the top
        players.add(new Player("Abdelhamid",buttons,tiles, 1, 1,this));  

        players.add(new Player("Thet",buttons,tiles, 12, 12,this)); // Example positions
        monsters.add(new Monster(buttons, 5,5));
        monsters.add(new Monster(buttons, 8,8));
        monsters.add(new Monster(buttons, 6,6));
        
        setupKeyListeners(mainPanel);

        pack(); // Pack components tightly
        setLocationRelativeTo(null); // Center the frame
}
    
    public void setupKeyListeners(JPanel mainPanel) {
        // Example key assignments
        addPlayerKeys(players.get(0), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,KeyEvent.VK_SPACE);  
        addPlayerKeys(players.get(1), KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D,KeyEvent.VK_F); 

        // Create key listeners
        for (Player player : players) {
            mainPanel.addKeyListener(createKeyListenerForPlayer(player));
            System.out.println("Key Listener added for player");
        }
    }
    
        @Override
    public void onPlayerDeath(Player player) {
        // Logic to reset the game
        resetGame();
    }
    private void addPlayerKeys(Player player, int upKey, int downKey, int leftKey, int rightKey,int dropKey) {
        HashSet<Integer> keySet = new HashSet<>();
        keySet.add(upKey);
        keySet.add(downKey);
        keySet.add(leftKey);
        keySet.add(rightKey);
        keySet.add(dropKey);
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
                            player.moveUp();
                            System.out.println("moved up arrow");
                            break;
                        case KeyEvent.VK_DOWN: 
                            player.moveDown();
                            break;
                        case KeyEvent.VK_LEFT: 
                            player.moveLeft();
                            break;
                        case KeyEvent.VK_RIGHT: 
                            player.moveRight();
                            break;
                        case KeyEvent.VK_SPACE: 
                            player.dropBomb();
                            System.out.println("Key pressed: " + e.getKeyCode());
                            break;
                        // Add cases for player 2's keys:
                        case KeyEvent.VK_W: 
                            player.moveUp(); 
                            System.out.println("moved up w");
                            break;
                        case KeyEvent.VK_S: 
                            player.moveDown();
                            break;
                        case KeyEvent.VK_A: 
                            player.moveLeft();
                            break;
                        case KeyEvent.VK_D: 
                            player.moveRight();
                            break;
                        case KeyEvent.VK_F: 
                            player.dropBomb();
                            System.out.println("Key pressed: " + e.getKeyCode());
                            break;
                        
                    }
                }
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BombermanGame().setVisible(true);
            }
        });
    }
    
    //testing git if clone successful and committed
    //second timn
}

