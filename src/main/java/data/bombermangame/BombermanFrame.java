package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import java.util.List; // Import only List from java.util
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * BombermanFrame is the main frame for the Bomberman game.
 */
public class BombermanFrame extends JFrame {
    private static BombermanComponent bombermanComponent;
    private AvatarComponents avatarComponents;
    private Map<String, Integer> keyBindingsPlayer1;
    private Map<String, Integer> keyBindingsPlayer2;
    private Map<String, Integer> keyBindingsPlayer3;
    private GameTimer gameTimer;

    public Tile[][] tiles;
    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<Monster> monsters = new ArrayList<>();
    private boolean isRoundOver = false;

    int numberOfPlayers = SelectPlayersGUI.numberOfPlayers;

    JLabel timer, scoreLabel1, scoreLabel2, scoreLabel3;

    /**
     * Constructor for BombermanFrame. Initializes the frame and game components.
     *
     * @throws HeadlessException if GraphicsEnvironment.isHeadless() returns true.
     */
    public BombermanFrame() throws HeadlessException {
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

            scoreLabel3 = new JLabel("Score: " + players.get(2).getGamesWon());

            imagePanel.add(label3);
            imagePanel.add(scoreLabel3);
        }

        this.add(imagePanel, BorderLayout.NORTH);
        this.add(bombermanComponent, BorderLayout.CENTER);
        this.setVisible(true);
        bombermanComponent.requestFocusInWindow();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(760, 850);
        this.setResizable(false);

        int delay = 500;
        ActionListener taskPerformer = evt -> updateGame();
        new Timer(delay, taskPerformer).start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }
        });
    }

    /**
     * Updates the timer label.
     */
    private void updateTimerLabel() {
        timer.setText("Time: " + gameTimer.getFormattedTime());
    }

    /**
     * Updates the score labels.
     */
    private void updateScoreLabels() {
        if (numberOfPlayers == 2) {
            scoreLabel1.setText("Score: " + players.get(0).getGamesWon());
            scoreLabel2.setText("Score: " + players.get(1).getGamesWon());
        } else if (numberOfPlayers == 3) {
            scoreLabel1.setText("Score: " + players.get(0).getGamesWon());
            scoreLabel2.setText("Score: " + players.get(1).getGamesWon());
            scoreLabel3.setText("Score: " + players.get(2).getGamesWon());
        }
    }

    public static void setBombermanComponent(BombermanComponent comp) {
        bombermanComponent = comp;
    }

    public static BombermanComponent getBombermanComponent() {
        return bombermanComponent;
    }

    /**
     * Updates the game state.
     */
    private void updateGame() {
        for (Monster monster : monsters) {
            monster.moveToRandomDirection();
        }
        if (!isRoundOver) {
            checkGameStatus();
        }
        updateTimerLabel();
        repaint();
        bombermanComponent.repaint();
        for (Player player : players) {
            player.updatePowerUpsAndCurses();
        }
        for (Player player : players) {
            if (player.getGamesWon() == 3) {
                String message = "Game Winner: " + player.getName() +
                        "\nGames Won: " + player.getGamesWon() +
                        "\nGames Time: " + gameTimer.getFormattedTime();
                displayGameResult(message);
                doUponExit();
                return;
            }
        }
    }

    /**
     * Sets up the game.
     */
    public void setupGame() {
        Tile[][] tiles = bombermanComponent.loadMapFromFile("MapONE.txt");

        players.clear();
        if (numberOfPlayers == 2) {
            players.add(new Player(NamePlayersGUI.allNames.get(0), 3, 3, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindingsPlayer1));
            players.add(new Player(NamePlayersGUI.allNames.get(1), 11, 11, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindingsPlayer2));
        } else if (numberOfPlayers == 3) {
            players.add(new Player(NamePlayersGUI.allNames.get(0), 3, 3, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindingsPlayer1));
            players.add(new Player(NamePlayersGUI.allNames.get(1), 11, 11, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindingsPlayer2));
            players.add(new Player(NamePlayersGUI.allNames.get(2), 5, 13, bombermanComponent, monsters, "assets/players/bombermanfrontgreen.png", keyBindingsPlayer3));
        }

        monsters.add(new Monster(tiles, 9, 9, players, "assets/monsters/ghostfrontgreen.png"));
        monsters.add(new Monster(tiles, 5, 5, players, "assets/monsters/ghostfrontgreen.png"));

        bombermanComponent.setTiles(tiles);
        bombermanComponent.setPlayers(players);
        bombermanComponent.setMonsters(monsters);
        bombermanComponent.setupKeyListeners();
        bombermanComponent.borderLayer = 0;
        bombermanComponent.countdownTimeInSeconds = 90;
    }

    /**
     * Initializes the game.
     */
    public void initializeGame() {
        setupGame();
    }

    /**
     * Sets the key bindings for the players.
     *
     * @param keyBindingsList a list of key bindings for the players
     */
    public void setKeyBindings(List<Map<String, Integer>> keyBindingsList) {
        bombermanComponent.setKeyBindings(keyBindingsList);
        bombermanComponent.setupKeyListeners();
    }

    /**
     * Shows the exit confirmation dialog.
     */
    private void showExitConfirmation() {
        int n = JOptionPane.showConfirmDialog(this, "Do you really want to quit?", "Really", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            doUponExit();
        }
    }

    /**
     * Performs actions upon exiting the game.
     */
    protected void doUponExit() {
        System.exit(0);
    }

    /**
     * Checks if all players are dead.
     *
     * @return true if all players are dead, false otherwise
     */
    private boolean allPlayersDead() {
        for (Player player : players) {
            if (player.isAlive) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks the game status.
     */
    private void checkGameStatus() {
        for (Player player : players) {
            if (!player.isAlive) {
                determineRoundWinner();
                break;
            }
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

    /**
     * Determines the round winner.
     */
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
            winner.gamesWon++;
            if (winner.gamesWon == 3) {
                String message = "Game Winner: " + winner.getName() + "\nRounds Won: " + winner.getGamesWon();
                isRoundOver = true;
                handleGameEnd(winner, message);
            } else {
                String message = "Round Winner: " + winner.getName() + "\nRounds Won: " + winner.getGamesWon();
                isRoundOver = true;
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                displayRoundResult(message);
                                resetGame();
                            }
                        },
                        200
                );
            }
        } else if (allPlayersDead()) {
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
                    200
            );
        }
    }

    /**
     * Handles the end of the game.
     *
     * @param winner the winner of the game
     * @param message the message to display
     */
    private void handleGameEnd(Player winner, String message) {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        displayGameResult(message);
                        winner.setGamesWon(0);
                        resetGame();
                    }
                },
                500
        );
    }

    /**
     * Resets the game.
     */
    private void resetGame() {
        Map<String, Integer> gamesWonCount = new HashMap<>();
        for (Player player : players) {
            gamesWonCount.put(player.getName(), player.getGamesWon());
        }

        players.clear();
        monsters.clear();

        setupGame();

        for (Player player : players) {
            Integer gamesWon = gamesWonCount.get(player.getName());
            if (gamesWon != null) {
                player.setGamesWon(gamesWon);
            }
        }
        resetPlayerPositions();
        isRoundOver = false;
    }

    /**
     * Resets the positions of the players.
     */
    private void resetPlayerPositions() {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (i == 0) {
                player.setCurrentRow(1);
                player.setCurrentCol(1);
            } else if (i == 1) {
                player.setCurrentRow(1);
                player.setCurrentCol(10);
            }
        }
    }

    /**
     * Displays the game result.
     *
     * @param message the message to display
     */
    private void displayGameResult(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Displays the round result.
     *
     * @param message the message to display
     */
    private void displayRoundResult(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
