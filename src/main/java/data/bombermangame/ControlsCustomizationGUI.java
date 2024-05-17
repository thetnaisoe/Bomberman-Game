///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class ControlCustomizationGUI extends JFrame {
//    private HashMap<String, Integer> keyBindings = new HashMap<>();
//    private Player player; // Assuming you have a Player class
//
//    public ControlCustomizationGUI(Player player) {
//        this.player = player;
//        setTitle("Customize Controls");
//        setSize(300, 200);
//        setLayout(new GridLayout(0, 2));
//        addControls();
//        setVisible(true);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    }
//
//    private void addControls() {
//        String[] controls = {"Move Up", "Move Down", "Move Left", "Move Right", "Drop Bomb"};
//        for (String control : controls) {
//            add(new JLabel(control));
//            JTextField keyField = new JTextField(KeyEvent.getKeyText(player.getKey(control)));
//            keyField.addKeyListener(new KeyAdapter() {
//                @Override
//                public void keyTyped(KeyEvent e) {
//                    keyBindings.put(control, e.getKeyCode());
//                    keyField.setText(KeyEvent.getKeyText(e.getKeyCode()));
//                }
//            });
//            add(keyField);
//        }
//
//        JButton saveButton = new JButton("Save");
//        saveButton.addActionListener(e -> {
//            saveKeyBindings();
//            dispose();
//        });
//        add(saveButton);
//    }
//
//    private void saveKeyBindings() {
//        keyBindings.forEach((action, keyCode) -> {
//            player.setKey(action, keyCode);
//        });
//        player.saveControls();
//    }
//}
