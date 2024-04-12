package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SelectMapGUI extends JFrame {
    private JButton seaButton;
    private JButton desertButton;
    private JButton hellButton;
    private String selectedMap;

    public SelectMapGUI() {
        setTitle("Choose Map");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); // Center the window on the screen
        initializeComponents();
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(20, 0, 5, 0);

        // Title Label
        JLabel titleLabel = new JLabel("Choose Map");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(titleLabel, gbc);

        gbc.insets = new Insets(5, 0, 20, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Map buttons with images
        seaButton = createMapButton("Sea", new ImageIcon("sea.jpeg"));
        desertButton = createMapButton("Desert", new ImageIcon("desert.jpeg"));
        hellButton = createMapButton("Hell", new ImageIcon("hell.jpeg"));

        // Map Selection Panel
        JPanel mapPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        mapPanel.add(seaButton);
        mapPanel.add(desertButton);
        mapPanel.add(hellButton);

        gbc.gridy = 1;
        add(mapPanel, gbc);

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 20));
        nextButton.setForeground(Color.RED); // Set font color to red
        nextButton.setBackground(Color.WHITE); // Set background color to blue
        nextButton.setOpaque(true); // For MacOS, ensure that the background color is visible
        nextButton.setBorderPainted(false); // Optional: to remove the border if desired
        gbc.gridy = 2; // Increment Y position
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0);
        add(nextButton, gbc);

        // Action listeners for map buttons
        seaButton.addActionListener(e -> selectMap("Sea"));
        desertButton.addActionListener(e -> selectMap("Desert"));
        hellButton.addActionListener(e -> selectMap("Hell"));

        // Action listener for the Next button
        nextButton.addActionListener(e -> onNext());
    }

    private JButton createMapButton(String mapName, ImageIcon icon) {
        JButton button = new JButton();
        button.setIcon(resizeIcon(icon, 150, 150));
        button.setActionCommand(mapName);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setText(mapName);

        return button;
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private void selectMap(String mapName) {
        this.selectedMap = mapName;
        System.out.println("Map selected: " + selectedMap);
        // Additional logic for when a map is selected can be added here
    }

    private void onNext() {
        System.out.println("Selected map: " + selectedMap);

        // Dispose the current window
        dispose();

        // Open the GuideGUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GuideGUI guideGUI = new GuideGUI();
                guideGUI.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SelectMapGUI().setVisible(true));
    }
}
