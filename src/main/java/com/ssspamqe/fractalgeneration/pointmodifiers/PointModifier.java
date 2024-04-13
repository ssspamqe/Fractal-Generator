package com.ssspamqe.fractalgeneration.pointmodifiers;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;

public interface PointModifier {
    Point applyOn(Point point);
}
