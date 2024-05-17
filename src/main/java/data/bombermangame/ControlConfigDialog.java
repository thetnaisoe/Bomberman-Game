//package data.bombermangame;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class ControlConfigDialog extends JDialog {
//    private Player player;
//
//    public ControlConfigDialog(Frame parent, Player player) {
//        super(parent, "Configure Controls for " + player.getName(), true);
//        this.player = player;
//        initializeUI();
//    }
//
//    private void initializeUI() {
//        setSize(400, 300);
//        setLocationRelativeTo(getParent());
//        setLayout(new BorderLayout(10, 10));
//
//        JPanel controlPanel = new JPanel(new GridLayout(0, 2, 10, 10));
//        addControlFields(controlPanel);
//        add(controlPanel, BorderLayout.CENTER);
//
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
//        addButtons(buttonPanel);
//        add(buttonPanel, BorderLayout.SOUTH);
//    }
//
//    private void addControlFields(JPanel panel) {
//        String[] actions = {"Move Up", "Move Down", "Move Left", "Move Right", "Drop Bomb"};
//        for (String action : actions) {
//            panel.add(new JLabel(action + ":"));
//            JTextField keyField = new JTextField(KeyEvent.getKeyText(player.getKey(action)), 10);
//            keyField.addKeyListener(new KeyAdapter() {
//                public void keyPressed(KeyEvent e) {
//                    keyField.setText(KeyEvent.getKeyText(e.getKeyCode()));
//                    player.setKey(action, e.getKeyCode());
//                }
//            });
//            panel.add(keyField);
//        }
//    }
//
//    private void addButtons(JPanel panel) {
//        JButton backButton = new JButton("Back");
//        backButton.addActionListener(e -> dispose());  // Closes this dialog to go back
//        panel.add(backButton);
//
//        JButton saveButton = new JButton("Save");
//        saveButton.addActionListener(e -> saveControls());
//        panel.add(saveButton);
//    }
//
//    private void saveControls() {
//        // Save settings and notify
//        JOptionPane.showMessageDialog(this, "Controls Saved Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
//        this.dispose();  // Close dialog after saving
//    }
//}
