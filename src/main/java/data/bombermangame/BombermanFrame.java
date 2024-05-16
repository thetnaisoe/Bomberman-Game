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
    
    private BombermanComponent bombermanComponent;
    public Tile[][] tiles;
    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<Monster> monsters = new ArrayList<>();
    private boolean isRoundOver = false;
    
    public BombermanFrame() throws HeadlessException{
        
        this.setTitle("BombermanGame");
        bombermanComponent = new BombermanComponent();

        this.setLayout(new BorderLayout());
        this.add(bombermanComponent, BorderLayout.CENTER);
        setupGame();
        this.setVisible(true);
        bombermanComponent.requestFocusInWindow(); 
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
        this.pack();
        
        int delay = 500; // Update delay in milliseconds (e.g., 100ms for 10 updates per second) 
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateGame(); // Call your update logic aka game loop
            }
        };
        new Timer(delay, taskPerformer).start();
        
        addWindowListener(new WindowAdapter(){
            
            @Override
            public void windowClosing(WindowEvent e){
                showExitConfirmation();
            }
        });

    }
    
    private void updateGame() {
        // Update player positions (if you have movement logic outside of key presses)

        for (Monster monster : monsters) {
            monster.moveToRandomDirection();  
        }
        if (!isRoundOver) {
            checkGameStatus();
        }
        repaint(); // Trigger a repaint to reflect changes 
         bombermanComponent.repaint(); 
         for(Player p :players){
             p.updatePowerUpsAndCurses();
         }
    }
    
    private void setupGame() {
        // Load Map 
        Tile[][] tiles = bombermanComponent.loadMapFromFile("MapONE.txt"); 

        players.add(new Player("Abdelhamid", 3, 3, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png")); 
        players.add(new Player("Thet", 11,11, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png"));

        monsters.add(new Monster(tiles, 9, 9, players, "assets/monsters/ghostfrontgreen.png")); 
        System.out.println("Monster created!");
        monsters.add(new Monster(tiles, 5, 5, players, "assets/monsters/ghostfrontgreen.png"));
        System.out.println("Monster created!");
        System.out.println("Number of Monsters: " + monsters.size());

        // Pass data to component
        bombermanComponent.setTiles(tiles);
        bombermanComponent.setPlayers(players); 
        bombermanComponent.setMonsters(monsters);
        bombermanComponent.setupKeyListeners(bombermanComponent);
        bombermanComponent.borderLayer = 0;
        bombermanComponent.countdownTimeInSeconds  = 90;
    }
    
    public BombermanComponent getBombermanComponent() {
	return bombermanComponent;
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
            for (Monster monster : monsters) {
                if (player.getBounds().intersects(monster.getBounds())) {
                    player.isAlive = false;
                    determineRoundWinner();
                    break;
                }
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
                500 // Delay in milliseconds
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
            500 // Delay in milliseconds
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

        isRoundOver = false;
    }
    
    private void displayGameResult(String message) {
        JOptionPane.showMessageDialog(this, message); 
     }

    private void displayRoundResult(String message) {
       JOptionPane.showMessageDialog(this, message); 
    }
}
