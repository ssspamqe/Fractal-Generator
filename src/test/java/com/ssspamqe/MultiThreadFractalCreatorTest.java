package com.ssspamqe;

import com.ssspamqe.fractalgeneration.fractalcreators.MultiThreadFractalCreator;
import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import com.ssspamqe.fractalgeneration.pointmodifiers.AffineTransformation;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.DiskFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.HeartFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PolarFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.SinusoidalFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.SphericalFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiThreadFractalCreatorTest {
    private static final int SAMPLES = 1_000_000;
    private static final int ITERATIONS_PER_SAMPLE = 100;
    private static final int OFFSET = -20;

    private static final int HEIGHT = 100;
    private static final int WIDTH = 100;

    private static final int THREADS = 4;

    private static final List<PointFunction> pointFunctions = List.of(
            new SinusoidalFunction(),
            new DiskFunction(),
            new HeartFunction(),
            new PolarFunction(),
            new SphericalFunction()
    );

    private MultiThreadFractalCreator fractalCreator;

    @BeforeEach
    void buildFractalCreator() {
        fractalCreator = MultiThreadFractalCreator.builder()
                .samples(SAMPLES)
                .offset(OFFSET)
                .pointFunctions(pointFunctions)
                .iterationsPerSample(ITERATIONS_PER_SAMPLE)
                .transformations(buildTransformations())
                .build();

    }

    private List<AffineTransformation> buildTransformations() {
        double a = -0.57;
        double b = -0.5;
        double d = 0.15;
        double e = 0.08;
        double c = 0;
        double f = 0;
        var color = new Color(5, 17, 1);
        return List.of(new AffineTransformation(a, b, c, d, e, f, color));
    }

    @Test
    void should_fillGivenPixelCanvas() {
        var canvas = new PixelCanvas(HEIGHT, WIDTH);

        fractalCreator.fillCanvasWithThreads(canvas, THREADS);

        int blackPixels = getAmountOfBlackPixels(canvas);
        assertThat(blackPixels).isNotEqualTo(HEIGHT * WIDTH);
    }

    private int getAmountOfBlackPixels(PixelCanvas canvas) {
        Color black = new Color(0, 0, 0);
        int count = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (canvas.getPixel(i, j).getColor().equals(black)) {
                    count++;
                }
            }
        }
        return count;
    }
}
