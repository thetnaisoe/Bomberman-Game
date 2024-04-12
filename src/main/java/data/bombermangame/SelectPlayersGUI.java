package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectPlayersGUI extends JFrame {
    private JButton twoPlayersButton;
    private JButton threePlayersButton;
    private JButton nextButton;
    private int numberOfPlayers;

    public SelectPlayersGUI() {
        setTitle("Select Number of Players");
        setSize(1024, 768); // Match the size of MainGUI
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); // Center the window on the screen
        initializeComponents();
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create a label asking for the number of players
        JLabel questionLabel = new JLabel("How Many Players?", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.insets = new Insets(20, 10, 20, 10); // Top padding
        add(questionLabel, gbc);

        // Initialize buttons for selecting the number of players
        twoPlayersButton = new JButton("2 players");
        twoPlayersButton.setFont(new Font("Arial", Font.PLAIN, 20));
        twoPlayersButton.setForeground(Color.BLACK); // Set text color to black
        gbc.insets = new Insets(10, 10, 10, 10);
        add(twoPlayersButton, gbc);

        threePlayersButton = new JButton("3 players");
        threePlayersButton.setFont(new Font("Arial", Font.PLAIN, 20));
        threePlayersButton.setForeground(Color.BLACK); // Set text color to black
        add(threePlayersButton, gbc);

        // Initialize "NEXT" button
        nextButton = new JButton("NEXT");
        nextButton.setFont(new Font("Arial", Font.BOLD, 20));
        nextButton.setForeground(Color.RED); // Set text color to red
        nextButton.setEnabled(false); // Disabled until a player number is selected
        add(nextButton, gbc);

        // Add action listeners to the buttons
        twoPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 2;
                nextButton.setEnabled(true); // Enable the next button after selection
            }
        });

        threePlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 3;
                nextButton.setEnabled(true); // Enable the next button after selection
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onNext();
            }
        });
    }

    private void onNext() {
        NamePlayersGUI namePlayersGUI = new NamePlayersGUI(numberOfPlayers);
        namePlayersGUI.setVisible(true);
        dispose(); // Close the SelectPlayersGUI window
    }

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SelectPlayersGUI().setVisible(true));
    }
}
