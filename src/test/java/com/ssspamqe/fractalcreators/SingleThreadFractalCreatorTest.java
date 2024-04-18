package com.ssspamqe.fractalcreators;

import com.ssspamqe.fractalgeneration.fractalcreators.SingleThreadFractalCreator;
import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleThreadFractalCreatorTest extends FractalCreatorTest {

    private SingleThreadFractalCreator fractalCreator;

    @BeforeEach
    void buildFractalCreator() {
        fractalCreator = SingleThreadFractalCreator.builder()
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

        fractalCreator.fillCanvas(canvas);

        int blackPixels = getAmountOfBlackPixels(canvas);
        assertThat(blackPixels).isNotEqualTo(HEIGHT * WIDTH);
    }
}
