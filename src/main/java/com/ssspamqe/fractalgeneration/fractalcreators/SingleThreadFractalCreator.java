package com.ssspamqe.fractalgeneration.fractalcreators;

import com.ssspamqe.fractalgeneration.coordinateobjects.Point;
import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import lombok.Builder;

@Builder
public class SingleThreadFractalCreator extends FractalCreator {
    
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
