package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

    /**
    * Represents the main menu of the Bomberman game.
    * This class is responsible for managing the game's main menu, which includes options to customize controls for each player and start the game.
    * It also handles loading and saving of key bindings for each player.
    * 
    * @author lenovo
    */

public class GuideGUI extends JFrame {
    private Map<String, Integer> keyBindingsPlayer1;
    private Map<String, Integer> keyBindingsPlayer2;
    private Map<String, Integer> keyBindingsPlayer3;
    private int playerCount = 2; // Default to 2 players
    
    /**
     * Constructs a new GuideGUI object.
     * This method initializes the main menu and its components.
     */
    public GuideGUI() {
        setTitle("Bomberman - Main Menu");
        setSize(400, 200);
        setLayout(new GridLayout(4, 1));

        keyBindingsPlayer1 = loadKeyBindingsPlayer1();
        keyBindingsPlayer2 = loadKeyBindingsPlayer2();
        keyBindingsPlayer3 = loadKeyBindingsPlayer3();

        JButton player1Button = new JButton("Customize Player 1 Controls");
        player1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new KeyBindingGUI(GuideGUI.this, 1, keyBindingsPlayer1);
            }
        });

        JButton player2Button = new JButton("Customize Player 2 Controls");
        player2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new KeyBindingGUI(GuideGUI.this, 2, keyBindingsPlayer2);
            }
        });
        
        add(player1Button);
        add(player2Button);
        
        if (SelectPlayersGUI.numberOfPlayers >= 3) {
            JButton player3Button = new JButton("Customize Player 3 Controls");
            player3Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new KeyBindingGUI(GuideGUI.this, 3, keyBindingsPlayer3);
                }
            });
            add(player3Button);
        }

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Start Game button clicked"); // Debugging
            BombermanFrame gameFrame = new BombermanFrame();
            java.util.List<Map<String, Integer>> keyBindingsList = new java.util.ArrayList<>();
                keyBindingsList.add(keyBindingsPlayer1);
                keyBindingsList.add(keyBindingsPlayer2);
                if (SelectPlayersGUI.numberOfPlayers >= 3) {
                    keyBindingsList.add(keyBindingsPlayer3);
                }
            gameFrame.setKeyBindings(keyBindingsList); // Set key bindings
            gameFrame.setupGame(); // Initialize game after setting key bindings
            dispose(); // Close the main menu
        }
    });

        add(startGameButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> loadKeyBindingsPlayer1() {
        return loadKeyBindingsFromFile("keybindings1.dat", getDefaultKeyBindingsPlayer1());
    }
     private Map<String, Integer> loadKeyBindingsPlayer3() {
        return loadKeyBindingsFromFile("keybindings3.dat", getDefaultKeyBindingsPlayer3());
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> loadKeyBindingsPlayer2() {
        return loadKeyBindingsFromFile("keybindings2.dat", getDefaultKeyBindingsPlayer2());
    }
    
    /**
     * Loads the key bindings for player 1 from a file.
     * If the file does not exist, it creates a new file with the default key bindings.
     *
     * @return the key bindings for player 1
     */
    private Map<String, Integer> loadKeyBindingsFromFile(String filename, Map<String, Integer> defaultBindings) {
        File file = new File(filename);
        if (!file.exists()) {
            saveKeyBindings(filename, defaultBindings);
        }

        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Map<String, Integer>) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            return defaultBindings;
        }
    }

    /**
     * Saves the specified key bindings to a file.
     * This method uses Java's serialization API to write the key bindings to a file.
     *
     * @param filename the name of the file to save the key bindings to
     * @param keyBindings the key bindings to save
     */
    private void saveKeyBindings(String filename, Map<String, Integer> keyBindings) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(keyBindings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the default key bindings for player 1.
     * The default key bindings are W for up, S for down, A for left, D for right, Space for bomb, and O for obstacle.
     *
     * @return the default key bindings for player 1
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
     * Returns the default key bindings for player 2.
     * The default key bindings are Up arrow for up, Down arrow for down, Left arrow for left, Right arrow for right, Enter for bomb, and L for obstacle.
     *
     * @return the default key bindings for player 2
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
     * Returns the default key bindings for player 3.
     * The default key bindings are T for up, G for down, F for left, H for right, Y for bomb, and U for obstacle.
     *
     * @return the default key bindings for player 3
     */
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

    /**
     * The main method that starts the game's main menu.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        new GuideGUI();
    }
}
