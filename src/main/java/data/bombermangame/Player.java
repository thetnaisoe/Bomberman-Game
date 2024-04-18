/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image; 
import java.util.ArrayList;
/**
 *
 * @author ThetNaingSoe
 */
public class Player {
    public String name;
    public int currentRow;
    public int currentCol;
    public boolean isAlive = true;
    private BombermanComponent bombermanComponent; 
    private Image playerImage;
    private static final int SQUARE_SIZE = 50;

    // Constructor
    public Player(String name, int initialRow, int initialCol, BombermanComponent bombermanComponent, String imagePath ) {
        this.name = name;
        this.currentRow = initialRow;
        this.currentCol = initialCol;
        this.bombermanComponent = bombermanComponent;
        try {
            Image loadedImage = ImageIO.read(new File(imagePath));
            // Resize the image
            this.playerImage = loadedImage.getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void moveUp(Tile[][] tiles) {
        if (!isAlive) return;
        int targetRow = currentRow - 1;
        if (targetRow >= 0 && tiles[targetRow][currentCol].isPassable()) {
            currentRow = targetRow; 
            requestRepaint();
        }

    }

    public void moveDown(Tile[][] tiles) {
        if (!isAlive) return;
        int targetRow = currentRow + 1;
        if (targetRow < tiles.length && tiles[targetRow][currentCol].isPassable()) {
            currentRow = targetRow; 
            requestRepaint(); 
        }

    }

    public void moveLeft(Tile[][] tiles) {
        if (!isAlive) return;
        int targetCol = currentCol - 1;
        if (targetCol >= 0 && tiles[currentRow][targetCol].isPassable()) {
            currentCol = targetCol;
            requestRepaint(); 
        }
 
    }

    public void moveRight(Tile[][] tiles) {
        if (!isAlive) return;
        int targetCol = currentCol + 1;
        if (targetCol < tiles[0].length && tiles[currentRow][targetCol].isPassable()) {
            currentCol = targetCol;
            requestRepaint(); 
        }

    }
    
    public void dropBomb(Tile[][] tiles, ArrayList<Bomb> bombs) {
        if (!isAlive) return; // Check if the player is alive

        Bomb bomb = new Bomb(currentRow, currentCol);
        bombs.add(bomb); // Add the bomb to the list of bombs

        bomb.detonate(tiles);
    }




    
    private void requestRepaint() {
        bombermanComponent.repaint();
    }

    public Image getPlayerImage() {
        return playerImage;
    }
}
