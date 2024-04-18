package com.ssspamqe.fractalgeneration.fractalcreators;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import com.ssspamqe.fractalgeneration.pointmodifiers.AffineTransformation;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import lombok.Builder;

import java.util.List;

public class SingleThreadFractalCreator extends FractalCreator {

    @Builder
    public SingleThreadFractalCreator(
            int samples,
            int iterationsPerSample,
            int offset,
            List<AffineTransformation> transformations,
            List<PointFunction> pointFunctions
    ) {
        super(samples, iterationsPerSample, offset, transformations, pointFunctions);
    }

    @Override
    public void fillCanvas(PixelCanvas canvas) {
        for (int sample = 0; sample < samples; sample++) {
            Point startPoint = getRandomInitialPoint();
            iteratePoint(startPoint, canvas);
        }
    }

    private void iteratePoint(
            Point point,
            PixelCanvas canvas
    ) {
        Point newPoint = point;
        for (int iteration = offset; iteration < iterationsPerSample; iteration++) {
            var transformation = getRandomTransformation();

            newPoint = transformation.applyOn(newPoint);
            newPoint = applyAllPointFunctions(newPoint);

            if (pixelAtPointMustBePainted(newPoint, iteration)) {
                var dot = compressToDot(newPoint, canvas.getHeight(), canvas.getHeight());

                if (dotInCanvas(dot, canvas)) {
                    var pixel = canvas.getPixel(dot);
                    paintPixel(pixel, transformation.color());
                }
            }
        }
    }
}
