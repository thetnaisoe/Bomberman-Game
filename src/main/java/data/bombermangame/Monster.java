package data.bombermangame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Monster {
    public JButton[][] buttons; // Reference to the game buttons grid
    public int currentRow; // Current row position of the monster
    public int currentCol; // Current column position of the monster
    public Timer timer;

    public Monster(JButton[][] buttons, int initialRow, int initialCol) {
        this.buttons = buttons;
        this.currentRow = initialRow;
        this.currentCol = initialCol;
        highlightCurrentPosition();
        startTimer();
    }

    private void startTimer() {
        timer = new Timer(500, new ActionListener() { // 0.5 seconds interval
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToRandomDirection();
            }
        });
        timer.start();
    }

    private void moveToRandomDirection() {
        Random random = new Random();
        int direction = random.nextInt(4); // Generate a random direction (0: up, 1: down, 2: left, 3: right)

        int newRow = currentRow;
        int newCol = currentCol;

        // Calculate new position based on the direction
        switch (direction) {
            case 0: // Move up
                newRow = Math.max(0, currentRow - 1);
                break;
            case 1: // Move down
                newRow = Math.min(buttons.length - 1, currentRow + 1);
                break;
            case 2: // Move left
                newCol = Math.max(0, currentCol - 1);
                break;
            case 3: // Move right
                newCol = Math.min(buttons[0].length - 1, currentCol + 1);
                break;
        }

        // Check if the new position is on a gray background
        if (buttons[newRow][newCol].getBackground().equals(Color.GRAY)) {
            clearCurrentPosition(); // Clear current position
            currentRow = newRow;
            currentCol = newCol;
            highlightCurrentPosition();
        }
    }

    private void highlightCurrentPosition() {
        buttons[currentRow][currentCol].setBackground(Color.RED); // Change the color to indicate the monster's position
    }
    
    private void clearCurrentPosition() {
        buttons[currentRow][currentCol].setBackground(Color.GRAY); // Clear the previous position
    }
}
