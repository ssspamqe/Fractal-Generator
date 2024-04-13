package com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;

public class SinusoidalFunction implements PointFunction {
    @Override
    public Point applyOn(Point oldPoint) {
        double x = oldPoint.x();
        double y = oldPoint.y();

        double newX = Math.sin(x);
        double newY = Math.sin(y);

        return new Point(newX, newY);
    }
}
