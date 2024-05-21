package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the GUI for entering player names in the Bomberman game.
 * This class extends JFrame and provides a user interface for entering the names of a specified number of players.
 * The names are stored in a static list and can be retrieved using the getAllNames method.
 * The GUI includes a submit button that checks if all names have been entered and then closes the window and opens the SelectMapGUI.
 *
 */
public class NamePlayersGUI extends JFrame {
    private List<JTextField> nameFields;
    private JButton submitButton;
    private final int numberOfPlayers;
    public static List<String> allNames = new ArrayList<>(); // Static list to store all names
    
    /**
     * Constructs a new NamePlayersGUI with the specified number of players.
     *
     * @param numberOfPlayers the number of players for whom to enter names
     */
    public NamePlayersGUI(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        setTitle("Enter Players Name");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        initializeComponents();
    }
    
    /**
     * Initializes the components of the GUI.
     * This method is called in the constructor to set up the GUI.
     */
    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 50, 10, 50);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel instructionLabel = new JLabel("Enter Players Name");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 50, 20, 50);
        add(instructionLabel, gbc);

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
            gbc.gridx = 0;
        }

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.RED);
        submitButton.setPreferredSize(new Dimension(200, 50));
        gbc.insets = new Insets(20, 50, 20, 50);
        add(submitButton, gbc);

        submitButton.addActionListener(this::onSubmit);
    }
    
    /**
     * Handles the action of clicking the submit button.
     * This method checks if all names have been entered. If not, it displays an error message.
     * If all names have been entered, it closes the window and opens the SelectMapGUI.
     *
     * @param e the action event
     */
    private void onSubmit(ActionEvent e) {
        StringBuilder missingNames = new StringBuilder();
        allNames.clear(); // Clear the list before adding new names
        for (int i = 0; i < nameFields.size(); i++) {
            String name = nameFields.get(i).getText().trim();
            if (name.isEmpty()) {
                if (missingNames.length() > 0) {
                    missingNames.append(", ");
                }
                missingNames.append("Player ").append(i + 1);
            } else {
                allNames.add(name); // Add the name to the static list
            }
        }

        if (missingNames.length() > 0) {
            JOptionPane.showMessageDialog(this, "Please write names for: " + missingNames.toString() + ".",
                    "Missing Names", JOptionPane.ERROR_MESSAGE);
        } else {
            dispose(); // Close this window
            EventQueue.invokeLater(() -> {
                SelectMapGUI selectMapGUI = new SelectMapGUI();
                selectMapGUI.setVisible(true);
            });
        }
    }
    
    /**
     * Returns the list of all entered player names.
     *
     * @return the list of all entered player names
     */
    public static List<String> getAllNames() {
        return allNames;
    }
    
    /**
     * The main method for testing the NamePlayersGUI.
     * This method creates a new NamePlayersGUI for 2 players and makes it visible.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NamePlayersGUI(2).setVisible(true)); // Example for 2 players
    }
}
