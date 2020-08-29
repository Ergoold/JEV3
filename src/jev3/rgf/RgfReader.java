package jev3.rgf;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A class containing static convenience methods for decoding image files in RGF format.
 */
public class RgfReader {

    /**
     * Constructor is private to prevent instantiation.
     */
    private RgfReader() {
    }

    /**
     * Decodes the array of bytes as an RGF image into a {@code BufferedImage}.
     *
     * @param image array of bytes representing an RGF image
     * @return a {@code BufferedImage} decoded from the byte array
     */
    public static BufferedImage read(byte[] image) {
        BufferedImage bufferedImage = new BufferedImage(Byte.toUnsignedInt(image[0]), Byte.toUnsignedInt(image[1]),
                BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < bufferedImage.getHeight(); row++)
            for (int col = 0; col < bufferedImage.getWidth(); col++) {
                int rowSize = (bufferedImage.getWidth() + Byte.SIZE - 1) / Byte.SIZE * Byte.SIZE;
                int bitIndex = row * rowSize + col + Byte.SIZE * 2;
                int color = image[bitIndex / Byte.SIZE] & (1 << (bitIndex % Byte.SIZE));

                bufferedImage.setRGB(col, row, color > 0 ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }

        return bufferedImage;
    }

    /**
     * Decodes the RGF image file at the specified path into a {@code BufferedImage}.
     *
     * @param path the path to the RGF image file
     * @return a {@code BufferedImage} decoded from the file
     * @throws IOException if an error occurs during reading
     */
    public static BufferedImage read(Path path) throws IOException {
        return read(Files.readAllBytes(path));
    }

    /**
     * Decodes the RGF image file into a {@code BufferedImage}.
     *
     * @param file the RGF image file
     * @return a {@code BufferedImage} decoded from the file
     * @throws IOException if an error occurs during reading
     */
    public static BufferedImage read(File file) throws IOException {
        return read(file.toPath());
    }
}
