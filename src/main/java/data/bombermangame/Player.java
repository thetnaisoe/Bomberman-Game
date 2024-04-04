package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import data.bombermangame.Monster;

public class Player implements Explodable {
    public  JButton[][] buttons; // Reference to the game buttons grid
    public  Tile[][] tiles;
    public String name;
    public int currentRow; // Current row position of the player
    public int currentCol; // Current column position of the player
    public boolean active;
    public ArrayList<Monster> monsters = new ArrayList<>();
    public boolean isAlive = true;
    private PlayerEventListener eventListener;
     
    
    public Player(String name,JButton[][] butto,Tile[][] til, int initialRow, int initialCol,PlayerEventListener listener) {
        this.buttons = butto;
        this.name = name;
        this.tiles=til;
        this.currentRow = initialRow;
        this.currentCol = initialCol;
        this.active = true;
        this.eventListener = listener;

        highlightCurrentPosition();
    }
    public  void updateMap(JButton[][] bts,Tile[][] tls){
        this.buttons =bts;
        this.tiles=tls;
    }
    
    public void die(){
        isAlive=false;
            buttons[currentRow][currentCol].setBackground(Color.RED);
            if (eventListener != null) {
            eventListener.onPlayerDeath(this); // Notify the listener
        }
           
            if (BombermanGame.players.get(0).name == this.name){
                JOptionPane.showMessageDialog(null,name + " have died!" + BombermanGame.players.get(1).name +" has won the game!!!", "Game Over", JOptionPane.WARNING_MESSAGE);
                
            }
            else{
                 JOptionPane.showMessageDialog(null,name + " has died! " + BombermanGame.players.get(0).name +" has won the game!!!", "Game Over", JOptionPane.WARNING_MESSAGE);
                
            }


                
        
        
    }

    public void moveUp() {
        if (!isAlive) return;
        if (currentRow > 0  && tiles[currentRow - 1][currentCol].isPassable()) {
            clearCurrentPosition();
            currentRow--;
            highlightCurrentPosition();
        }


    }

    public void moveDown() {
        if (!isAlive) return;
        if (currentRow < buttons.length - 1  && tiles[currentRow +1][currentCol ].isPassable()) {
            clearCurrentPosition();
            currentRow++;
            highlightCurrentPosition();
        }

    }

    public void moveLeft() {
        if (!isAlive) return;
        if (currentCol > 0  && tiles[currentRow ][currentCol -1].isPassable()) {
            clearCurrentPosition();
            currentCol--;
            highlightCurrentPosition();
        }

    }
        public void moveRight() {
            if (!isAlive) return;
        if (currentCol < buttons[0].length - 1  && tiles[currentRow ][currentCol +1].isPassable()) {
            clearCurrentPosition();
            currentCol++;
            highlightCurrentPosition();
        }
    }
    public void dropBomb() {
        if (!isAlive) return;
    // Check if a bomb can be placed at the current position (no bomb already present, etc.)
     if (buttons[currentRow][currentCol].getBackground() != Color.PINK) {
        // Display the bomb
        buttons[currentRow][currentCol].setBackground(Color.PINK); 
    
    new Bomb(buttons,tiles,currentRow, currentCol, this, 2); // Assuming a default range of 2
    //highlightBombPosition(currentRow,currentCol);
     }
    
}
private void highlightBombPosition (int row,int col){
    buttons[row][col].setBackground(Color.PINK);
}




    private void highlightCurrentPosition() {
        buttons[currentRow][currentCol].setBackground(Color.BLUE);
    }

    private void clearCurrentPosition() {
        buttons[currentRow][currentCol].setBackground(Color.GRAY);
    }

    @Override
    public void explode(JButton[][] buttons, Tile[][] tiles) {
        this.die();
    }
    

    
    
}
