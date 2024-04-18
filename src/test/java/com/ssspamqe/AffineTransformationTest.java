package com.ssspamqe;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.pointmodifiers.AffineTransformation;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;


public class AffineTransformationTest {

    private static final double DEFAULT_PRECISION = 0.01;

    @Test
    public void should_performCorrectCalculations() {
        var transformation = AffineTransformation.builder()
                .a(-0.57)
                .b(-0.5)
                .d(0.15)
                .e(0.08)
                .c(0)
                .f(0)
                .color(new Color(5, 17, 1))
                .build();
        var point = new Point(1, 1);

        var actualPoint = transformation.applyOn(point);

        assertThat(actualPoint.x()).isCloseTo(-1.07, withPrecision(DEFAULT_PRECISION));
        assertThat(actualPoint.y()).isCloseTo(0.23, withPrecision(DEFAULT_PRECISION));
    }
}
