package com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;

public class PolarFunction implements PointFunction {
    @Override
    public Point applyOn(Point oldPoint) {
        double x = oldPoint.x();
        double y = oldPoint.y();

        double newX = Math.atan(y / x) / Math.PI;
        double newY = Math.sqrt(x * x + y * y) - 1;

        return new Point(newX, newY);
    }
}
