package com.ssspamqe.fractalgeneration.fractalcreators;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.graphics.Pixel;
import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import com.ssspamqe.fractalgeneration.pointmodifiers.AffineTransformation;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import lombok.Builder;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultiThreadFractalCreator extends FractalCreator {

    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    @Builder
    public MultiThreadFractalCreator(int samples, int iterationsPerSample, int offset, List<AffineTransformation> transformations, List<PointFunction> pointFunctions) {
        super(samples, iterationsPerSample, offset, transformations, pointFunctions);
    }

    @Override
    public void fillCanvas(PixelCanvas canvas) {
        fillCanvasWithThreads(canvas, 1);
    }

    public void fillCanvasWithThreads(PixelCanvas canvas, int threads) {
        int samplesPerThread = samples / threads;

        try (ExecutorService executorService = Executors.newFixedThreadPool(threads)) {
            executorService.execute(() -> generateFractalInSingleThread(canvas, samplesPerThread));
        }
    }

    private void generateFractalInSingleThread(PixelCanvas canvas, int samplesInThread) {
        for (int sample = 0; sample < samplesInThread; sample++) {
            Point startPoint = getRandomInitialPoint();
            iteratePoint(startPoint, canvas);
        }
    }

    private void iteratePoint(Point startPoint, PixelCanvas canvas) {
        Point newPoint = startPoint;
        for (int iteration = offset; iteration < iterationsPerSample; iteration++) {
            var transformation = getRandomTransformation();

            Point transformedPoint = transformation.applyOn(newPoint);
            newPoint = applyAllPointFunctions(transformedPoint);

            if (pixelAtPointMustBePainted(newPoint, iteration)) {
                var dot = compressToDot(newPoint, canvas.getHeight(), canvas.getWidth());

                if (dotInCanvas(dot, canvas)) {
                    var pixel = canvas.getPixel(dot);

                    paintPixelWithLock(pixel, transformation.color());
                }
            }
        }
    }

    private void paintPixelWithLock(Pixel pixel, Color color) {
        READ_WRITE_LOCK.writeLock().lock();
        try {
            paintPixel(pixel, color);
        } finally {
            READ_WRITE_LOCK.writeLock().unlock();
        }
    }
}
