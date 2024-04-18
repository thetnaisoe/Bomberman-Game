package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NamePlayersGUI extends JFrame {
    private List<JTextField> nameFields;
    private JButton submitButton;
    private final int numberOfPlayers;

    public NamePlayersGUI(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        setTitle("Enter Players Name");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        initializeComponents();
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 50, 10, 50);
        gbc.anchor = GridBagConstraints.CENTER;

        // Create a label asking for the players' names
        JLabel instructionLabel = new JLabel("Enter Players Name");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 50, 20, 50);
        add(instructionLabel, gbc);

        // Initialize text fields for entering names
        nameFields = new ArrayList<>();
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        for (int i = 1; i <= numberOfPlayers; i++) {
            gbc.gridy++;
            gbc.insets = new Insets(10, 50, 10, 5);
            JLabel playerLabel = new JLabel("Player " + i + ": ");
            playerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            add(playerLabel, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(10, 5, 10, 50);
            JTextField nameField = new JTextField(20);
            nameField.setFont(new Font("Arial", Font.PLAIN, 18));
            nameFields.add(nameField);
            add(nameField, gbc);
            gbc.gridx = 0; // Reset to first column for next label
        }

        // Initialize "Submit" button
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.RED);
        submitButton.setPreferredSize(new Dimension(200, 50)); // Set a fixed size for the button
        gbc.insets = new Insets(20, 50, 20, 50);
        add(submitButton, gbc);

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmit();
            }
        });
    }

    private void onSubmit() {
        ArrayList<String> playerNames = new ArrayList<>();
        for (JTextField nameField : nameFields) {
            playerNames.add(nameField.getText().trim());
        }
        // Print out the player names for debugging purposes
        System.out.println("Player names: " + playerNames);

        // Dispose the current window
        dispose();

        // Open the SelectMapGUI
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SelectMapGUI selectMapGUI = new SelectMapGUI();
                    selectMapGUI.setVisible(true);
                    System.out.println("SelectMapGUI should be visible now.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NamePlayersGUI(2).setVisible(true)); // Example for 2 players
    }

}
