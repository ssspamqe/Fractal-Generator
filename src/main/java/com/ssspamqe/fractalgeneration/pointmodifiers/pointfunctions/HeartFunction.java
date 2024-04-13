package com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;

public class HeartFunction implements PointFunction {

    @Override
    public Point applyOn(Point oldPoint) {
        double x = oldPoint.x();
        double y = oldPoint.y();

        double sqrt = Math.sqrt(x * x + y * y);
        double trigonometryArgument = sqrt * Math.atan(y / x);

        double newX = sqrt * Math.sin(trigonometryArgument);
        double newY = -sqrt * Math.cos(trigonometryArgument);

        return new Point(newX, newY);
    }
}
