package com.ssspamqe.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PolarFunction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class PolarFunctionTest {
    private static final double DEFAULT_PRECISION = 0.01;

    private PointFunction polarFunction = new PolarFunction();

    @Test
    public void should_performCorrectCalculations() {
        var point = new Point(1, 1);

        var calculatedPoint = polarFunction.applyOn(point);

        assertThat(calculatedPoint.x()).isCloseTo(0.25, withPrecision(DEFAULT_PRECISION));
        assertThat(calculatedPoint.y()).isCloseTo(0.41, withPrecision(DEFAULT_PRECISION));
    }
}
