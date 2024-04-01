package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import data.bombermangame.Monster;

public class Player {
    public JButton[][] buttons; // Reference to the game buttons grid
    public Tile[][] tiles;
    public int currentRow; // Current row position of the player
    public int currentCol; // Current column position of the player
    public boolean active;
    public ArrayList<Monster> monsters = new ArrayList<>();
    
    public Player(JButton[][] buttons,Tile[][] tiles, int initialRow, int initialCol) {
        this.buttons = buttons;
        this.tiles=tiles;
        this.currentRow = initialRow;
        this.currentCol = initialCol;
        this.active = true;

        highlightCurrentPosition();
    }

    public void moveUp() {
        if (currentRow > 0 && buttons[currentRow - 1][currentCol].getBackground().equals(Color.GRAY)) {
            clearCurrentPosition();
            currentRow--;
            highlightCurrentPosition();
        }


    }

    public void moveDown() {
        if (currentRow < buttons.length - 1 && buttons[currentRow + 1][currentCol].getBackground().equals(Color.GRAY)) {
            clearCurrentPosition();
            currentRow++;
            highlightCurrentPosition();
        }

    }

    public void moveLeft() {
        if (currentCol > 0 && buttons[currentRow][currentCol - 1].getBackground().equals(Color.GRAY)) {
            clearCurrentPosition();
            currentCol--;
            highlightCurrentPosition();
        }

    }
    public void dropBomb() {
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
    public void moveRight() {
        if (currentCol < buttons[0].length - 1 && buttons[currentRow][currentCol + 1].getBackground().equals(Color.GRAY)) {
            clearCurrentPosition();
            currentCol++;
            highlightCurrentPosition();
        }
    }



    private void highlightCurrentPosition() {
        buttons[currentRow][currentCol].setBackground(Color.BLUE);
    }

    private void clearCurrentPosition() {
        buttons[currentRow][currentCol].setBackground(Color.GRAY);
    }
    

    
    
}
