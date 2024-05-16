package data.bombermangame;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private int seconds = 0;

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

    // Method to format the elapsed time as "minutes:seconds"
    public String getFormattedTime() {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%d:%02d", minutes, remainingSeconds);
    }
}
