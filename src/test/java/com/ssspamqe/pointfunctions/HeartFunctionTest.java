package com.ssspamqe.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.HeartFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class HeartFunctionTest {
    private static final double DEFAULT_PRECISION = 0.01;

    private PointFunction heartFunction = new HeartFunction();

    @Test
    public void should_performCorrectCalculations() {
        var point = new Point(1, 1);

        var calculatedPoint = heartFunction.applyOn(point);

        assertThat(calculatedPoint.x()).isCloseTo(1.27, withPrecision(DEFAULT_PRECISION));
        assertThat(calculatedPoint.y()).isCloseTo(-0.63, withPrecision(DEFAULT_PRECISION));
    }
}
