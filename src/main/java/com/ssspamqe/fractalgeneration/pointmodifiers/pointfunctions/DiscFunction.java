package com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;

public class DiscFunction implements PointFunction {

    @Override
    public Point applyOn(Point oldPoint) {
        double x = oldPoint.x();
        double y = oldPoint.y();

        double trigonometryArgument = Math.PI * Math.sqrt(x * x + y * y);
        double coefficient = Math.atan(y / x) / Math.PI;

        double newX = coefficient * Math.sin(trigonometryArgument);
        double newY = coefficient * Math.cos(trigonometryArgument);

        return new Point(newX, newY);
    }
}
