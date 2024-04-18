package com.ssspamqe.fractalcreators;

import com.ssspamqe.fractalgeneration.fractalcreators.MultiThreadFractalCreator;
import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiThreadFractalCreatorTest extends FractalCreatorTest {

    private static final int THREADS = 4;

    private MultiThreadFractalCreator fractalCreator;

    @BeforeEach
    void buildFractalCreator() {
        fractalCreator = MultiThreadFractalCreator.builder()
                .samples(SAMPLES)
                .offset(OFFSET)
                .pointFunctions(pointFunctions)
                .iterationsPerSample(ITERATIONS_PER_SAMPLE)
                .transformations(buildAffineTransformations())
                .build();
    }

    @Test
    void should_fillGivenPixelCanvas() {
        var canvas = new PixelCanvas(HEIGHT, WIDTH);

        fractalCreator.fillCanvasWithThreads(canvas, THREADS);

        int blackPixels = getAmountOfBlackPixels(canvas);
        assertThat(blackPixels).isNotEqualTo(HEIGHT * WIDTH);
    }

}
