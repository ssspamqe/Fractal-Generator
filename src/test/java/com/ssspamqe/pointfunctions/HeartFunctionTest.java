package com.ssspamqe.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.HeartFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import com.ssspamqe.pointfunctions.customasserts.PointAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class HeartFunctionTest {
    private static final double DEFAULT_PRECISION = 0.01;

    private PointFunction heartFunction = new HeartFunction();

    @Test
    public void should_performCorrectCalculations() {
        var point = new Point(1, 1);

        var actualPoint = heartFunction.applyOn(point);

        var expectedPoint = new Point(1.27, -0.63);
        PointAssert.assertThat(actualPoint).isCloseTo(expectedPoint, withPrecision(DEFAULT_PRECISION));
    }
}
