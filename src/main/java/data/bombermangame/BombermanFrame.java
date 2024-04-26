/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.Timer;  
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 

import java.util.*;

/**
 *
 * @author ThetNaingSoe
 */
public class BombermanFrame extends JFrame{
    
    private BombermanComponent bombermanComponent;
    public Tile[][] tiles;
    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<Monster> monsters = new ArrayList<>();
    
    public BombermanFrame() throws HeadlessException{
        
        this.setTitle("BombermanGame");
        bombermanComponent = new BombermanComponent();

        this.setLayout(new BorderLayout());
        this.add(bombermanComponent, BorderLayout.CENTER);
        setupGame();
        this.setVisible(true);
        bombermanComponent.requestFocusInWindow(); 
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
        this.pack();
        
        int delay = 500; // Update delay in milliseconds (e.g., 100ms for 10 updates per second) 
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateGame(); // Call your update logic aka game loop
            }
        };
        new Timer(delay, taskPerformer).start();
        
        addWindowListener(new WindowAdapter(){
            
            @Override
            public void windowClosing(WindowEvent e){
                showExitConfirmation();
            }
        });

    }
    
    private void updateGame() {
        // Update player positions (if you have movement logic outside of key presses)

        for (Monster monster : monsters) {
            monster.moveToRandomDirection();  
        }

        repaint(); // Trigger a repaint to reflect changes 
         bombermanComponent.repaint(); 
         for(Player p :players){
             p.updatePowerUpsAndCurses();
         }
    }
    
    private void setupGame() {
        // Load Map 
        Tile[][] tiles = bombermanComponent.loadMapFromFile("MapONE.txt"); 

        players.add(new Player("Abdelhamid", 3, 3, bombermanComponent, "assets/players/bombermanfrontgreen.png")); 
        players.add(new Player("Thet", 11,11, bombermanComponent, "assets/players/bombermanfrontgreen.png"));

        monsters.add(new Monster(tiles, 9, 9, players, "assets/monsters/ghostfrontgreen.png")); 
        System.out.println("Monster created!");
        monsters.add(new Monster(tiles, 5, 5, players, "assets/monsters/ghostfrontgreen.png"));
        System.out.println("Monster created!");
        System.out.println("Number of Monsters: " + monsters.size());

        // Pass data to component
        bombermanComponent.setTiles(tiles);
        bombermanComponent.setPlayers(players); 
        bombermanComponent.setMonsters(monsters);
        bombermanComponent.setupKeyListeners(bombermanComponent);
    }
    
    public BombermanComponent getBombermanComponent() {
	return bombermanComponent;
    }
        
    private void showExitConfirmation(){
        int n = JOptionPane.showConfirmDialog(this, "Do you really want to quit?", "Really", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION){
            doUponExit();
        }
    }
        
    protected void doUponExit(){
        System.exit(0);
    }
}
