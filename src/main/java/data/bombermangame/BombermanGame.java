/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package data.bombermangame;

import javax.swing.*;
/**
 *
 * @author ThetNaingSoe!
 */
public final class BombermanGame
{

    private BombermanGame() {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BombermanFrame().setVisible(true); // Create and show your BombermanFrame
            }
        });
    }


}
