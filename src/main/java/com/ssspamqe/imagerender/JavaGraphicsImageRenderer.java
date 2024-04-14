package com.ssspamqe.imagerender;

import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class JavaGraphicsImageRenderer implements ImageRenderer {
    @Override
    public void renderImage(PixelCanvas canvas, Path directoryPath, String fileName, FileFormat format) {
        if (!Files.isDirectory(directoryPath)) {
            throw new IllegalArgumentException("directoryPath argument must be a directory");
        }

        int height = canvas.getHeight();
        int width = canvas.getWidth();

        var image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        fillGraphics(graphics, canvas);

        saveFile(image, directoryPath, fileName, format);
    }

    private void fillGraphics(Graphics graphics, PixelCanvas canvas) {
        for (int row = 0; row < canvas.getHeight(); row++) {
            for (int column = 0; column < canvas.getWidth(); column++) {
                var pixel = canvas.getPixel(row, column);
                graphics.setColor(pixel.getColor());
                graphics.fillRect(row, column, 1, 1);
            }
        }
    }

    private void saveFile(BufferedImage image, Path directoryPath, String fileName, FileFormat format) {
        var fullFileName = buildFullFileName(directoryPath, fileName, format);

        try {
            ImageIO.write(image, format.toString(), new File(fullFileName));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String buildFullFileName(Path directoryPath, String fileName, FileFormat fileFormat) {
        String fullFileName = STR."\{directoryPath}\\\{fileName}.\{fileFormat}";
        int fileId = 0;
        while (fileWithSuchNameAlreadyExists(fullFileName)) {
            fileId++;
            fullFileName = STR."\{directoryPath}\\\{fileName}\{fileId}.\{fileFormat}";
        }
        return fullFileName;
    }

    private boolean fileWithSuchNameAlreadyExists(String fullFileName) {
        return Files.exists(Path.of(fullFileName));
    }
}
