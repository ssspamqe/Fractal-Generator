package com.ssspamqe.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.SphericalFunction;
import com.ssspamqe.pointfunctions.customasserts.PointAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.withPrecision;

public class SphericalFunctionTest {
    private static final double DEFAULT_PRECISION = 0.01;

    private PointFunction sphericalFunction = new SphericalFunction();

    @Test
    public void should_performCorrectCalculations() {
        var point = new Point(1, 1);

        var actualPoint = sphericalFunction.applyOn(point);

        var expectedPoint = new Point(0.5, 0.5);
        PointAssert.assertThat(actualPoint).isCloseTo(expectedPoint, withPrecision(DEFAULT_PRECISION));
    }
}
