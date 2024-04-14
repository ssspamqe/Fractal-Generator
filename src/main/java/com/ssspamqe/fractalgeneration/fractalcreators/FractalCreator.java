package com.ssspamqe.fractalgeneration.fractalcreators;

import com.ssspamqe.fractalgeneration.coordinateobjects.Dot;
import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.graphics.Pixel;
import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import com.ssspamqe.fractalgeneration.pointmodifiers.AffineTransformation;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@RequiredArgsConstructor
public abstract class FractalCreator {
    protected static final int X_MIN = -1;
    protected static final int X_MAX = 1;
    protected static final int Y_MIN = -1;
    protected static final int Y_MAX = 1;

    protected final int samples;
    protected final int iterationsPerSample;
    protected final int offset;
    protected final List<AffineTransformation> transformations;
    protected final List<PointFunction> pointFunctions;

    public abstract void fillCanvas(PixelCanvas canvas);

    protected Point getRandomInitialPoint() {
        double randomX = ThreadLocalRandom.current().nextDouble(X_MIN, X_MAX);
        double randomY = ThreadLocalRandom.current().nextDouble(Y_MIN, Y_MAX);
        return new Point(randomX, randomY);
    }

    protected Point applyAllPointFunctions(Point point) {
        Point result = point;
        for (PointFunction function : pointFunctions) {
            result = function.applyOn(result);
        }
        return result;
    }

    protected Dot compressToDot(Point point, int height, int width) {
        int x = height - (int) (((X_MAX - point.x()) / (X_MAX - X_MIN)) * height);
        int y = width - (int) (((Y_MAX - point.y()) / (Y_MAX - Y_MIN)) * width);

        return new Dot(x, y);
    }

    protected void paintPixel(Pixel pixel, Color color) {
        if (pixel.getHits() == 0) {
            pixel.setColor(color);
        } else {
            pixel.mixColorWith(color);
        }
        pixel.incrementHits();
    }

    protected AffineTransformation getRandomTransformation() {
        return getRandomElement(transformations);
    }

    protected <T> T getRandomElement(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
    }

    protected boolean pixelAtPointMustBePainted(Point point, int iteration) {
        return iteration >= 0
                && (point.x() >= X_MIN && point.x() <= X_MAX)
                && (point.y() >= Y_MIN && point.y() <= Y_MAX);
    }

    protected boolean dotInCanvas(Dot dot, PixelCanvas canvas) {
        return dot.x() >= 0 & dot.x() < canvas.getHeight() & dot.y() >= 0 & dot.y() < canvas.getHeight();
    }

}
