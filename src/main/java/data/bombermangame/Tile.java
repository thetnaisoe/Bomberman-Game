/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.bombermangame;

import java.awt.Image;

public abstract class Tile {
    protected int rowIndex;
    protected int colIndex;
    protected boolean canDrop;
    protected boolean destroyed;
    protected TileType tileType;
    private Image image;
    
    public Tile(int row,int col){
        this.rowIndex=row;
        this.colIndex =col;
        this.canDrop=false;
        this.destroyed=false;
    }
    
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public boolean canDrop(){
        return canDrop;
    }
    public boolean destroyed(){
        return destroyed;
    }
    public int getRow(){
        return rowIndex;
                }
     public int getCol(){
        return colIndex;
                }
     public void setCanDrop(boolean a){
         this.canDrop=a;
         
         
     }
     public void setDestroyed(boolean a){
         this.destroyed=a;
         
         
     }
    public boolean isPassable() {
        switch(tileType) {
            case WALL: return false;
            case BOX: return false;
            default: return true; // Example: Field is passable
        }
    }
    
}
