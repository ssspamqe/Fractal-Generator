package com.ssspamqe.fractalcreators;

import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import com.ssspamqe.fractalgeneration.pointmodifiers.AffineTransformation;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.DiskFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.HeartFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PolarFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.SinusoidalFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.SphericalFunction;

import java.awt.*;
import java.util.List;

public abstract class FractalCreatorTest {

    protected static final int SAMPLES = 1_000_000;
    protected static final int ITERATIONS_PER_SAMPLE = 100;
    protected static final int OFFSET = -20;

    protected static final int HEIGHT = 100;
    protected static final int WIDTH = 100;

    protected static final List<PointFunction> pointFunctions = List.of(
            new SinusoidalFunction(),
            new DiskFunction(),
            new HeartFunction(),
            new PolarFunction(),
            new SphericalFunction()
    );

    protected int getAmountOfBlackPixels(PixelCanvas canvas) {
        Color black = new Color(0, 0, 0);
        int count = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (canvas.getPixel(i, j).getColor().equals(black)) {
                    count++;
                }
            }
        }
        return count;
    }

    protected java.util.List<AffineTransformation> buildAffineTransformations() {
        double a = -0.57;
        double b = -0.5;
        double d = 0.15;
        double e = 0.08;
        double c = 0;
        double f = 0;
        var color = new Color(5, 17, 1);
        return List.of(new AffineTransformation(a, b, c, d, e, f, color));
    }
}
