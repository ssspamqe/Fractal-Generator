package com.ssspamqe.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.DiskFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class DiscFunctionTest {

    private static final double DEFAULT_PRECISION = 0.01;

    private PointFunction diskFunction = new DiskFunction();

    @Test
    public void should_performCorrectCalculations() {
        var point = new Point(1, 1);

        var calculatedPoint = diskFunction.applyOn(point);

        assertThat(calculatedPoint.x()).isCloseTo(-0.24, withPrecision(DEFAULT_PRECISION));
        assertThat(calculatedPoint.y()).isCloseTo(-0.07, withPrecision(DEFAULT_PRECISION));
    }
}
