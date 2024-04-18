package com.ssspamqe;

import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import com.ssspamqe.fractalgeneration.postprocessor.GammaCorrection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GammaCorrectionTest {

    private static final int WIDTH = 2;
    private static final int HEIGHT = 2;

    private final GammaCorrection correction = new GammaCorrection(3.0);

    @Test
    void should_repaintPixels_when_theyWereHit() {
        //Arrange
        PixelCanvas canvas = new PixelCanvas(HEIGHT, WIDTH);

        var oldColor = new Color(100,100,100);

        var pixel = canvas.getPixel(0,1);
        pixel.setColor(oldColor);
        pixel.setHits(100);

        //Act
        correction.process(canvas);

        //Assert
        var actualColor = canvas.getPixel(0,1).getColor();
        assertThat(actualColor).isNotEqualTo(oldColor);
    }
}
