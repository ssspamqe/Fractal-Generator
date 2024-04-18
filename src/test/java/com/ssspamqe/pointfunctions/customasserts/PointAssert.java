package com.ssspamqe.pointfunctions.customasserts;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.data.Offset;

public class PointAssert extends AbstractAssert<PointAssert, Point> {

    public PointAssert(Point point) {
        super(point, PointAssert.class);
    }

    public static PointAssert assertThat(Point actual) {
        return new PointAssert(actual);
    }

    public PointAssert isCloseTo(Point point, Offset<Double> precision) {
        isNotNull();
        double offset = precision.value;
        
        if (isNotCloseToWithOffset(actual.x(), point.x(), offset)) {
            failWithMessage("Expected point.x value to be close to %s with precision %s, but actual: %s", point.x(), offset, actual.x());
        }
        if (isNotCloseToWithOffset(actual.y(), point.y(), offset)) {
            failWithMessage("Expected point.y value to be close to %s with precision %s, but actual: %s", point.y(), offset, actual.y());
        }


        return this;
    }

    private boolean isNotCloseToWithOffset(double a, double b, double offset) {
        return Math.abs(a - b) >= offset;
    }
}
