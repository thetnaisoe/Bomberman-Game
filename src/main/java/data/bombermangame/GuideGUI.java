package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GuideGUI extends JFrame {

    public GuideGUI() {
        setTitle("How To Play?");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); // Center the window on the screen
        initializeComponents();
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 50, 20, 50);

        // Title Label
        JLabel titleLabel = new JLabel("How To Play?");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        // Game Instruction Image
        ImageIcon originalIcon = new ImageIcon("guide.jpg"); // Load the original image
        Image originalImage = originalIcon.getImage(); // Transform it
        Image scaledImage = originalImage.getScaledInstance(500, 500, Image.SCALE_SMOOTH); // Scale it to fit
        ImageIcon guideIcon = new ImageIcon(scaledImage); // Convert it back to an ImageIcon
        JLabel imageLabel = new JLabel(guideIcon);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 50, 10, 50);
        add(imageLabel, gbc);

        // Start Game Button
        JButton startButton = new JButton("Start The Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.BLACK);
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 50, 20, 50);
        gbc.anchor = GridBagConstraints.CENTER;
        add(startButton, gbc);

        // Action listener for the Start Game button
        startButton.addActionListener((ActionEvent e) -> startGame());
    }

    private void startGame() {
        System.out.println("Game is starting...");
        dispose(); // Close the GuideGUI
        // Open the BombermanGame JFrame
        EventQueue.invokeLater(() -> {
            BombermanFrame game = new BombermanFrame();
            game.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuideGUI().setVisible(true));
    }
}
