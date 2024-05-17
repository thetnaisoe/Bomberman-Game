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

public class KeyBindingGUI extends JFrame {
    private Map<String, Integer> keyBindings;
    private JTextField upField, downField, leftField, rightField, bombField, obstacleField;

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

    private void saveKeyBindings(int playerNumber) {
        String filename = playerNumber == 1 ? "keybindings1.dat" : "keybindings2.dat";
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(keyBindings);
            JOptionPane.showMessageDialog(this, "Key bindings saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

