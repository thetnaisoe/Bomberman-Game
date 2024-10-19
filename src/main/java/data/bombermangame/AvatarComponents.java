package data.bombermangame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents the avatar components in the game.
 * This class is responsible for loading and resizing the avatar images.
 * It provides getter methods to access the images.
 * 
 * @author Thet Naing Soe
 */
public class AvatarComponents {

    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;

    /**
     * Constructs a new AvatarComponents object.
     * This constructor loads and resizes the avatar images.
     */
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

    /**
     * Resizes the given image to the specified width and height.
     *
     * @param originalImage the original image to resize
     * @param width the desired width of the resized image
     * @param height the desired height of the resized image
     * @return the resized image
     */
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImage;
    }

    /**
     * Returns the first avatar image.
     *
     * @return the first avatar image
     */
    public BufferedImage getImage1() {
        return image1;
    }

    /**
     * Returns the second avatar image.
     *
     * @return the second avatar image
     */
    public BufferedImage getImage2() {
        return image2;
    }

    /**
     * Returns the third avatar image.
     *
     * @return the third avatar image
     */
    public BufferedImage getImage3() {
        return image3;
    }
}