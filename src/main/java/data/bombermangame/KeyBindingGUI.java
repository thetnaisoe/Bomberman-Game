/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * Represents the GUI for setting key bindings in the Bomberman game.
 * This class extends JFrame and provides a user interface for setting the key bindings for a player.
 * The key bindings are saved to a file when the user clicks the "Save" button.
 *
 * @author lenovo
 */

public class KeyBindingGUI extends JFrame {
    private Map<String, Integer> keyBindings;
    private JTextField upField, downField, leftField, rightField, bombField, obstacleField;
    
    /**
     * Constructs a new KeyBindingGUI with the specified parent frame, player number, and key bindings.
     *
     * @param parent the parent frame of this KeyBindingGUI
     * @param playerNumber the number of the player for whom the key bindings are being set
     * @param keyBindings the current key bindings
     */
    public KeyBindingGUI(JFrame parent, int playerNumber, Map<String, Integer> keyBindings) {
        this.keyBindings = keyBindings;

        setTitle("Player " + playerNumber + " Key Bindings");
        setSize(400, 300);
        setLayout(new GridLayout(7, 2));

        JLabel upLabel = new JLabel("Move Up: ");
        JLabel downLabel = new JLabel("Move Down: ");
        JLabel leftLabel = new JLabel("Move Left: ");
        JLabel rightLabel = new JLabel("Move Right: ");
        JLabel bombLabel = new JLabel("Drop Bomb: ");
        JLabel obstacleLabel = new JLabel("Place Obstacle: ");

        upField = createKeyField("UP");
        downField = createKeyField("DOWN");
        leftField = createKeyField("LEFT");
        rightField = createKeyField("RIGHT");
        bombField = createKeyField("BOMB");
        obstacleField = createKeyField("OBSTACLE");

        add(upLabel);
        add(upField);
        add(downLabel);
        add(downField);
        add(leftLabel);
        add(leftField);
        add(rightLabel);
        add(rightField);
        add(bombLabel);
        add(bombField);
        add(obstacleLabel);
        add(obstacleField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            saveKeyBindings(playerNumber);
            parent.setVisible(true);
            dispose();
        });

        add(new JLabel());
        add(saveButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
    
    /**
     * Creates a JTextField for the specified action.
     * The JTextField displays the current key binding for the action and updates the key binding when a key is pressed.
     *
     * @param action the action for which to create a JTextField
     * @return the created JTextField
     */
    private JTextField createKeyField(String action) {
        JTextField keyField = new JTextField(10);
        keyField.setText(KeyEvent.getKeyText(keyBindings.get(action)));
        keyField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyBindings.put(action, e.getKeyCode());
                keyField.setText(KeyEvent.getKeyText(e.getKeyCode()));
            }
        });
        return keyField;
    }
    
    /**
     * Saves the key bindings to a file.
     * The file is named "keybindings" followed by the player number and ".dat".
     * If an error occurs while saving the key bindings, the error is printed to the standard error stream.
     *
     * @param playerNumber the number of the player for whom the key bindings are being saved
     */
    private void saveKeyBindings(int playerNumber) {
        String filename = "keybindings" + playerNumber + ".dat";
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(keyBindings);
            JOptionPane.showMessageDialog(this, "Key bindings saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

