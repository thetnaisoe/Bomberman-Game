
package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectPlayersGUI extends JFrame {
    private JButton twoPlayersButton;
    private JButton threePlayersButton;
    private JButton nextButton;
    // Declare numberOfPlayers as public static
    public static int numberOfPlayers;


    public SelectPlayersGUI() {
        setTitle("Select Number of Players");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        initializeComponents();
        System.out.println("1");


    }


    private void initializeComponents() {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel questionLabel = new JLabel("How Many Players?", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.insets = new Insets(20, 10, 20, 10); // Top padding
        add(questionLabel, gbc);

        twoPlayersButton = new JButton("2 players");
        twoPlayersButton.setFont(new Font("Arial", Font.PLAIN, 20));
        twoPlayersButton.setForeground(Color.BLACK); // Set text color to black
        gbc.insets = new Insets(10, 10, 10, 10);
        add(twoPlayersButton, gbc);

        threePlayersButton = new JButton("3 players");
        threePlayersButton.setFont(new Font("Arial", Font.PLAIN, 20));
        threePlayersButton.setForeground(Color.BLACK); // Set text color to black
        add(threePlayersButton, gbc);

        nextButton = new JButton("NEXT");
        nextButton.setFont(new Font("Arial", Font.BOLD, 20));
        nextButton.setForeground(Color.RED);
        nextButton.setEnabled(false);
        add(nextButton, gbc);

        twoPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 2;
                nextButton.setEnabled(true);
            }
        });

        threePlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 3;
                nextButton.setEnabled(true);
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
        dispose();
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SelectPlayersGUI().setVisible(true));
    }
}