 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.Timer;  
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 

import java.util.*;

/**
 *
 * @author ThetNaingSoe
 */
public class BombermanFrame extends JFrame{
    
    private static BombermanComponent bombermanComponent;
    private AvatarComponents avatarComponents; 
    
    private Map<String, Integer>keyBindingsPlayer1;
    private Map<String, Integer>keyBindingsPlayer2;
     private Map<String, Integer>keyBindingsPlayer3;
    
    private GameTimer gameTimer;

    public Tile[][] tiles;
    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<Monster> monsters = new ArrayList<>();
    private boolean isRoundOver = false;
    
    int numberOfPlayers = SelectPlayersGUI.numberOfPlayers;

    JLabel timer, scoreLabel1, scoreLabel2, scoreLabel3;
    
    public BombermanFrame() throws HeadlessException{
        
        this.setTitle("BombermanGame");
        bombermanComponent = new BombermanComponent();

        avatarComponents = new AvatarComponents();

        gameTimer = new GameTimer();
        gameTimer.start(); // Start the timer

        timer = new JLabel("Time: " + gameTimer.getFormattedTime());
        timer.setPreferredSize(new Dimension(150, 50)); // Adjust width and height as needed
        timer.setFont(timer.getFont().deriveFont(Font.BOLD, 18)); // Adjust the font size as needed

        // Call setupGame() to populate players list
        setupGame();

        this.setLayout(new BorderLayout());

        // Create a panel to hold the images
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));


        // Add images to the panel based on the number of players
        if (numberOfPlayers >= 2) {

            ImageIcon image1 = new ImageIcon(avatarComponents.getImage1());
            ImageIcon image2 = new ImageIcon(avatarComponents.getImage2());
            JLabel label1 = new JLabel(image1);
            JLabel label2 = new JLabel(image2);

            // Add score labels next to the avatars



            scoreLabel1 = new JLabel("Score: " + players.get(0).getGamesWon());
            scoreLabel2 = new JLabel("Score: " + players.get(1).getGamesWon());


            imagePanel.add(timer);
            imagePanel.add(label1);
            imagePanel.add(scoreLabel1);
            imagePanel.add(label2);
            imagePanel.add(scoreLabel2);
        }
        if (numberOfPlayers >= 3) {
            ImageIcon image3 = new ImageIcon(avatarComponents.getImage3());
            JLabel label3 = new JLabel(image3);

            // Add score label next to the avatar
            scoreLabel3 = new JLabel("Score: " + players.get(2).getGamesWon());

            imagePanel.add(label3);
            imagePanel.add(scoreLabel3);
        }


        // Add the image panel to the top of the frame
        this.add(imagePanel, BorderLayout.NORTH);

        this.add(bombermanComponent, BorderLayout.CENTER);
        this.setVisible(true);
        bombermanComponent.requestFocusInWindow();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // Set the initial size of the frame
        this.setSize(760, 850);
        this.setResizable(false);

        int delay = 500;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateGame();
            }
        };
        new Timer(delay, taskPerformer).start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }
        });
    }
    
    private void updateTimerLabel() {
        timer.setText("Time: " + gameTimer.getFormattedTime());
    }
    private void updateScoreLabels() {

        if (numberOfPlayers == 2){
            scoreLabel1.setText("Score: " + players.get(0).getGamesWon());
            scoreLabel2.setText("Score: " + players.get(1).getGamesWon());
        }
        else if (numberOfPlayers == 3) {
            scoreLabel1.setText("Score: " + players.get(0).getGamesWon());
            scoreLabel2.setText("Score: " + players.get(1).getGamesWon());
            scoreLabel3.setText("Score: " + players.get(2).getGamesWon());
        }
    }
    //  private static BombermanComponent bombermanComponent;

    public static void setBombermanComponent(BombermanComponent comp) {
        bombermanComponent = comp;
    }

    public static BombermanComponent getBombermanComponent() {
        return bombermanComponent;
    }
    
    private void updateGame() {
        // Update player positions (if you have movement logic outside of key presses)
        for (Monster monster : monsters) {
            monster.moveToRandomDirection();
        }
        if (!isRoundOver) {
            checkGameStatus();
        }
        updateTimerLabel(); // Update the timer label
        repaint(); // Trigger a repaint to reflect changes
         bombermanComponent.repaint();
         for(Player p :players){
             p.updatePowerUpsAndCurses();
         }

        for (Player player : players) {
            if (player.getGamesWon() == 3) {
                String message = "Game Winner: " + player.getName() +
                        "\nGames Won: " + player.getGamesWon() +
                        "\nGames Time: " + gameTimer.getFormattedTime();
                displayGameResult(message);
                doUponExit();
                return; // Exit the method early if game winner is found
            }
        }
    }
    
    public void setupGame() {
        // Load Map 
        Tile[][] tiles = bombermanComponent.loadMapFromFile("MapONE.txt"); 

//<<<<<<< HEAD
//        players.add(new Player("Abdelhamid", 3, 3, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png")); 
//        players.add(new Player("Thet", 11,11, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png"));
//=======
    if (numberOfPlayers == 2){
            players.add(new Player(NamePlayersGUI.allNames.get(0), 3, 3, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindingsPlayer1));
            players.add(new Player(NamePlayersGUI.allNames.get(1), 11, 11, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindingsPlayer2));
            players.add(new Player(NamePlayersGUI.allNames.get(1), 5, 13, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindingsPlayer3));
        }//    public Player(String name, int initialRow, int initialCol, BombermanComponent bombermanComponent, ArrayList<Monster> monsters, String imagePath,Map<String, Integer> keyBindings ) {

        else if (numberOfPlayers == 3) {
            players.add(new Player(NamePlayersGUI.allNames.get(0), 3, 3, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindingsPlayer1));
            players.add(new Player(NamePlayersGUI.allNames.get(1), 11, 11, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindingsPlayer2));
            players.add(new Player(NamePlayersGUI.allNames.get(2), 5, 13, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindingsPlayer3));
        }

        monsters.add(new Monster(tiles, 9, 9, players, "assets/monsters/ghostfrontgreen.png")); 
        System.out.println("Monster created!");
        monsters.add(new Monster(tiles, 5, 5, players, "assets/monsters/ghostfrontgreen.png"));
        System.out.println("Monster created!");
        System.out.println("Number of Monsters: " + monsters.size());

        // Pass data to component
        bombermanComponent.setTiles(tiles);
        bombermanComponent.setPlayers(players); 
        bombermanComponent.setMonsters(monsters);
        bombermanComponent.setupKeyListeners();
        bombermanComponent.borderLayer = 0;
        bombermanComponent.countdownTimeInSeconds  = 90;
    }
    
    public void inisetKeyBtializeGame() {
        setupGame();
    }
    
    public void setKeyBindings(java.util.List<Map<String, Integer>> keyBindingsList){

       // BombermanComponent bombermanComponent = new BombermanComponent();
        bombermanComponent.setKeyBindings(keyBindingsList); // Pass key bindings to the component
        bombermanComponent.setupKeyListeners(); // Setup key listeners with new bindings
        
        // Initialize players with default images and positions
       // ArrayList<Player> players = new ArrayList<>();
       // players.add(new Player("Player1", 0, 0, bombermanComponent, "assets/players/bombermanfrontgreen.png", keyBindingsPlayer1));
       // players.add(new Player("Player2", 10, 10, bombermanComponent, "assets/bombermanfrontgreen.png", keyBindingsPlayer2));

        bombermanComponent.setPlayers(players);
        //bombermanComponent.setTiles(/* Your method to load the map */);

        add(bombermanComponent);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void showExitConfirmation(){
        int n = JOptionPane.showConfirmDialog(this, "Do you really want to quit?", "Really", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION){
            doUponExit();
        }
    }
        
    protected void doUponExit(){
        System.exit(0);
    }
    
        private boolean allPlayersDead() {
        for (Player player : players) {
            if (player.isAlive) {
                return false; // At least one player is still alive
            }
        }
        return true; // All players are dead
    }
        
    private void checkGameStatus() {
        for (Player player : players) {
            // Check if player is already dead
            if (!player.isAlive) {
                determineRoundWinner();
                break;
            }

            // Check for collision with monsters
            for (Monster monster : monsters) {
                if (player.getBounds().intersects(monster.getBounds())) {
                    player.isAlive = false;
                    determineRoundWinner();
                    break;
                }
            }

            if (bombermanComponent.isPlayerInExplosion(player)) {
                player.isAlive = false;
                determineRoundWinner();
                break;
            }
        }
    }

    private void determineRoundWinner() {
        Player winner = null;
        int aliveCount = 0;

        for (Player player : players) {
            if (player.isAlive) {
                winner = player;
                aliveCount++;
            }
        }

        if (aliveCount == 1) {
        // Single winner
        winner.gamesWon++; // Update win count

        // Check if the player has won the game
        if (winner.gamesWon == 3) {
            // The player has won the game
            String message = "Game Winner: " + winner.getName() + 
                             "\nRounds Won: " + winner.getGamesWon(); // Include total game wins
            isRoundOver = true;
            handleGameEnd(winner, message);
        } else {
            // The player has won the round but not the game
            String message = "Round Winner: " + winner.getName() + 
                             "\nRounds Won: " + winner.getGamesWon(); // Include current game wins
            isRoundOver = true;
            new java.util.Timer().schedule( 
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        displayRoundResult(message); 
                        resetGame();
                    }
                }, 
                200 // Delay in milliseconds
            );
        }
            } else if (allPlayersDead()) {
        // All players dead - it's a draw
        String message = "Round Result: Draw!";
        isRoundOver = true;
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    displayRoundResult(message);
                    resetGame();
                }
            }, 
            200 // Delay in milliseconds
        );
    }
    }
    
    
    private void handleGameEnd(Player winner, String message) {
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    displayGameResult(message);
                    winner.setGamesWon(0); // Reset gamesWon count to 0
                    resetGame();
                }
            }, 
            500 // Delay in milliseconds
        );
    }
    
    private void resetGame() {
        // Save the gamesWon count
        Map<String, Integer> gamesWonCount = new HashMap<>();
        for (Player player : players) {
            gamesWonCount.put(player.getName(), player.getGamesWon());
        }

        // Clear the players and monsters
    //
    players.clear();
        monsters.clear();

        // Setup the game again
        setupGame();

        // Transfer the gamesWon count to the new Player objects
        for (Player player : players) {
            Integer gamesWon = gamesWonCount.get(player.getName());
            if (gamesWon != null) {
                player.setGamesWon(gamesWon);
            }
        }
         resetPlayerPositions();
        isRoundOver = false;
    }
    
    private void resetPlayerPositions() {
    for (int i = 0; i < players.size(); i++) {
        Player player = players.get(i);
        if (i == 0) {
            player.setCurrentRow(1); // Reset position for Player 1
            player.setCurrentCol(1);
        } else if (i == 1) {
            player.setCurrentRow(1); // Reset position for Player 2
            player.setCurrentCol(10);
        }
        // Add more conditions if there are more players
    }
}
    private void displayGameResult(String message) {
        JOptionPane.showMessageDialog(this, message); 
     }

    private void displayRoundResult(String message) {
       JOptionPane.showMessageDialog(this, message); 
    }
}
