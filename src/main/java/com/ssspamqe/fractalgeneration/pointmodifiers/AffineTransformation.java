package com.ssspamqe.fractalgeneration.pointmodifiers;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;

import java.awt.*;

public record AffineTransformation(
        double a,
        double b,
        double c,
        double d,
        double e,
        double f,
        Color color) implements PointModifier {

    @Override
    public Point applyOn(Point point) {
        double newX = a * point.x() + b * point.y() + c;
        double newY = d * point.x() + e * point.y() + f;

        return new Point(newX, newY);
    }
}
