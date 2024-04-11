/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package data.bombermangame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
