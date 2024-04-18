package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JButton startButton;
    private JButton quitButton;
    private JLabel backgroundLabel;

    public MainGUI() {
        setTitle("Bomberman Game");
        setSize(1024, 768); // Adjust if needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeComponents();
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void initializeComponents() {
        // Initialize buttons
        startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setForeground(Color.GREEN);
        startButton.setBackground(Color.YELLOW);
        startButton.setFocusPainted(false);

        quitButton = new JButton("QUIT");
        quitButton.setFont(new Font("Arial", Font.BOLD, 24));
        quitButton.setForeground(Color.RED);
        quitButton.setBackground(Color.YELLOW);
        quitButton.setFocusPainted(false);

        // Set up the background label and load the background image
        backgroundLabel = new JLabel();
        ImageIcon backgroundImage = new ImageIcon("M64IQe.jpeg");
        Image image = backgroundImage.getImage(); // Transform it
        Image newimg = image.getScaledInstance(1024, 768, Image.SCALE_SMOOTH); // Scale it the smooth way
        backgroundImage = new ImageIcon(newimg); // Transform it back
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout()); // Use GridBagLayout for better control over positioning

        // Create a transparent panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 10)); // 2 rows, 1 col, vertical gap 10
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        // Constraints for the button panel placement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 1;

        // Add the button panel to the background label
        backgroundLabel.add(buttonPanel, gbc);

        // Add action listeners to the buttons
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onStart();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onQuit();
            }
        });

        // Add the background label to the frame
        add(backgroundLabel, BorderLayout.CENTER);
    }

    private void onStart() {
        // Hide the main menu
        setVisible(false);

        // Open the SelectPlayersGUI window
        SelectPlayersGUI selectPlayersGUI = new SelectPlayersGUI();
        selectPlayersGUI.setVisible(true);
    }

    private void onQuit() {
        // Quit button logic goes here
        System.out.println("Quit game...");
        dispose(); // Close the window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}
