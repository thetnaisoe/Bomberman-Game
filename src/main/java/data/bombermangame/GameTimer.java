package data.bombermangame;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a timer for the game.
 * This class is responsible for managing the game's time and formatting it as a string.
 * It uses a Swing Timer to increment the time every second.
 * 
 * @author Thet Naing Soe
 */
public class GameTimer {
    private int seconds = 0;

    /**
     * Starts the game timer.
     * This method schedules a task to increment the time every second.
     */
    public void start() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    seconds++;
                });
            }
        }, 0, 1000); // Run every second
    }

    /**
     * Returns the elapsed time formatted as "minutes:seconds".
     *
     * @return the elapsed time formatted as "minutes:seconds"
     */
    public String getFormattedTime() {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%d:%02d", minutes, remainingSeconds);
    }
}
