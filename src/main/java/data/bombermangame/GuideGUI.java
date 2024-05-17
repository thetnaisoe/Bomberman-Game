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

public class GuideGUI extends JFrame {
    private Map<String, Integer> keyBindingsPlayer1;
    private Map<String, Integer> keyBindingsPlayer2;

    public GuideGUI() {
        setTitle("Bomberman - Main Menu");
        setSize(300, 200);
        setLayout(new GridLayout(3, 1));

        keyBindingsPlayer1 = loadKeyBindingsPlayer1();
        keyBindingsPlayer2 = loadKeyBindingsPlayer2();

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

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 System.out.println("Start Game button clicked"); // Debugging
                BombermanFrame gameFrame = new BombermanFrame();
        gameFrame.setKeyBindings(keyBindingsPlayer1, keyBindingsPlayer2); // Set key bindings
        gameFrame.setupGame(); // Initialize game after setting key bindings
                dispose(); // Close the main menu
            }
        });

        add(player1Button);
        add(player2Button);
        add(startGameButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> loadKeyBindingsPlayer1() {
        return loadKeyBindingsFromFile("keybindings1.dat", getDefaultKeyBindingsPlayer1());
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> loadKeyBindingsPlayer2() {
        return loadKeyBindingsFromFile("keybindings2.dat", getDefaultKeyBindingsPlayer2());
    }

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

    private void saveKeyBindings(String filename, Map<String, Integer> keyBindings) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(keyBindings);
        } catch (IOException e) {
            e.printStackTrace();
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

    public static void main(String[] args) {
        new GuideGUI();
    }
}
