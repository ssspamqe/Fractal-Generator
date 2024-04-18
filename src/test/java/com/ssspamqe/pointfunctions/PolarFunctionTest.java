package com.ssspamqe.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PolarFunction;
import com.ssspamqe.pointfunctions.customasserts.PointAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.withPrecision;

public class PolarFunctionTest {
    private static final double DEFAULT_PRECISION = 0.01;

    private PointFunction polarFunction = new PolarFunction();

    @Test
    public void should_performCorrectCalculations() {
        var point = new Point(1, 1);

        var actualPoint = polarFunction.applyOn(point);

        var expectedPoint = new Point(0.25, 0.41);
        PointAssert.assertThat(actualPoint).isCloseTo(expectedPoint, withPrecision(DEFAULT_PRECISION));
    }
}
