package data.bombermangame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AvatarComponents {

    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;

    public AvatarComponents() {
        try {
            // Load the first image and resize it
            BufferedImage originalImage1 = ImageIO.read(new File("assets/avatars/man (1).png"));
            image1 = resizeImage(originalImage1, 70, 70);

            // Load the second image and resize it
            BufferedImage originalImage2 = ImageIO.read(new File("assets/avatars/man (2).png"));
            image2 = resizeImage(originalImage2, 70, 70);

            // Load the third image and resize it
            BufferedImage originalImage3 = ImageIO.read(new File("assets/avatars/woman.png"));
            image3 = resizeImage(originalImage3, 70, 70);

        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle the exception appropriately (e.g., log it, show an error message, etc.)
        }
    }

    // Method to resize an image
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImage;
    }

    // Getter methods for accessing the images
    public BufferedImage getImage1() {
        return image1;
    }

    public BufferedImage getImage2() {
        return image2;
    }

    public BufferedImage getImage3() {
        return image3;
    }
}