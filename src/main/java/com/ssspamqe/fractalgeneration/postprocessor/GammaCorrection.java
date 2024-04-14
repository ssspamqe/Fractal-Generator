package com.ssspamqe.fractalgeneration.postprocessor;

import com.ssspamqe.fractalgeneration.graphics.Pixel;
import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class GammaCorrection implements PostProcessor {
    private final double gamma;

    @Override
    public void process(PixelCanvas canvas) {
        var pixelNormals = buildPixelNormals(canvas);
        repaintCanvas(canvas, pixelNormals);
    }

    private HashMap<Pixel, Double> buildPixelNormals(PixelCanvas canvas) {
        var result = new HashMap<Pixel, Double>();

        for (int row = 0; row < canvas.getHeight(); row++) {
            for (int column = 0; column < canvas.getWidth(); column++) {
                var pixel = canvas.getPixel(row, column);
                if (pixel.getHits() != 0) {
                    result.put(pixel, Math.log10(pixel.getHits()));
                } else {
                    result.put(pixel, 0.0);
                }
            }
        }

        return result;
    }


    private void repaintCanvas(PixelCanvas canvas, Map<Pixel, Double> pixelNormals) {
        double maxGamma = buildMaxGamma(canvas, pixelNormals);

        Set<Pixel> correctedPixels = new HashSet<>();

        for (int row = 0; row < canvas.getHeight(); row++) {
            for (int column = 0; column < canvas.getWidth(); column++) {
                var pixel = canvas.getPixel(row, column);
                if (!correctedPixels.contains(pixel)) {
                    repaintPixel(pixel, maxGamma, pixelNormals.get(pixel));
                    correctedPixels.add(pixel);
                }
            }
        }
    }

    private double buildMaxGamma(PixelCanvas canvas, Map<Pixel, Double> pixelNormals) {
        double maxGamma = Double.MAX_VALUE;

        for (int row = 0; row < canvas.getHeight(); row++) {
            for (int column = 0; column < canvas.getWidth(); column++) {
                var pixel = canvas.getPixel(row, column);
                if (pixel.getHits() != 0) {
                    maxGamma = Math.max(maxGamma, pixelNormals.get(pixel));
                }
            }
        }

        return maxGamma;
    }

    private void repaintPixel(Pixel pixel, double maxGamma, double pixelNormal) {
        double normal = pixelNormal / maxGamma;

        double coefficient = Math.pow(normal, 1 / gamma);
        var newColor = getProcessedColor(pixel.getColor(), coefficient);

        pixel.setColor(newColor);

    }

    private Color getProcessedColor(Color oldColor, double coefficient) {
        int red = (int) (oldColor.getRed() * coefficient);
        int green = (int) (oldColor.getGreen() * coefficient);
        int blue = (int) (oldColor.getBlue() * coefficient);

        return new Color(red, green, blue);
    }


}
