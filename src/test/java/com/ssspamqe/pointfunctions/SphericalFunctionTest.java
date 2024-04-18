package com.ssspamqe.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.SphericalFunction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class SphericalFunctionTest {
    private static final double DEFAULT_PRECISION = 0.01;

    private PointFunction sphericalFunction = new SphericalFunction();

    @Test
    public void should_performCorrectCalculations() {
        var point = new Point(1, 1);

        var calculatedPoint = sphericalFunction.applyOn(point);

        assertThat(calculatedPoint.x()).isCloseTo(0.5, withPrecision(DEFAULT_PRECISION));
        assertThat(calculatedPoint.y()).isCloseTo(0.5, withPrecision(DEFAULT_PRECISION));
    }
}
