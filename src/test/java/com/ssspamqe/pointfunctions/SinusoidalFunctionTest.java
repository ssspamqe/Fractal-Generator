package com.ssspamqe.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.SinusoidalFunction;
import com.ssspamqe.pointfunctions.customasserts.PointAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class SinusoidalFunctionTest {
    private static final double DEFAULT_PRECISION = 0.01;

    private PointFunction sinusoidalFunction = new SinusoidalFunction();

    @Test
    public void should_performCorrectCalculations() {
        var point = new Point(1, 1);

        var actualPoint = sinusoidalFunction.applyOn(point);

        assertThat(actualPoint.x()).isCloseTo(0.84, withPrecision(DEFAULT_PRECISION));
        assertThat(actualPoint.y()).isCloseTo(0.84, withPrecision(DEFAULT_PRECISION));

        var expectedPoint = new Point(0.84, 0.84);
        PointAssert.assertThat(actualPoint).isCloseTo(expectedPoint, withPrecision(DEFAULT_PRECISION));
    }
}
