package com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;

public class SphericalFunction implements PointFunction {
    @Override
    public Point applyOn(Point oldPoint) {
        double x = oldPoint.x();
        double y = oldPoint.y();

        double newX = x / (x * x + y * y);
        double newY = y / (x * x + y * y);

        return new Point(newX, newY);
    }
}
