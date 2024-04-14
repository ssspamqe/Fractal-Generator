package com.ssspamqe.fractalgeneration.graphics;

import com.ssspamqe.fractalgeneration.coordinateobjects.Dot;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PixelCanvas {

    private final int width;
    private final int height;

    private final boolean verticalSymmetry;
    private final boolean horizontalSymmetry;

    private List<List<Pixel>> canvas;

    public PixelCanvas(int height, int width) {
        this(height, width, false, false);
    }

    public PixelCanvas(int height, int width, boolean verticalSymmetry, boolean horizontalSymmetry) {
        this.height = height;
        this.width = width;

        this.verticalSymmetry = verticalSymmetry;
        this.horizontalSymmetry = horizontalSymmetry;

        fillCanvas();
    }

    private void fillCanvas() {
        fillCanvasWithNullValues();
        if (verticalSymmetry && horizontalSymmetry) {
            fillCanvasWithBothSymmetry();
        } else if (verticalSymmetry) {
            fillCanvasWithVerticalSymmetry();
        } else if (horizontalSymmetry) {
            fillCanvasWithHorizontalSymmetry();
        } else {
            fillCanvasWithNoSymmetry();
        }
    }

    private void fillCanvasWithNoSymmetry() {
        canvas = new ArrayList<>(height);

        for (int row = 0; row < height; row++) {
            List<Pixel> pixelRow = new ArrayList<>(width);
            for (int column = 0; column < width; column++) {
                pixelRow.add(new Pixel());
            }
            canvas.add(pixelRow);
        }
    }

    private void fillCanvasWithBothSymmetry() {
        int maxX = getHalfHeight();
        int maxY = getHalfWidth();

        for (int row = 0; row < maxX; row++) {
            for (int column = 0; column < maxY; column++) {
                var pixel = new Pixel();

                canvas.get(row).set(column, pixel);
                canvas.get(row).set(mirrorYVertically(column), pixel);
                canvas.get(mirrorXHorizontally(row)).set(column, pixel);
                canvas.get(mirrorXHorizontally(row)).set(mirrorYVertically(column), pixel);
            }
        }
    }

    private void fillCanvasWithHorizontalSymmetry() {
        int maxX = getHalfHeight();

        for (int row = 0; row < maxX; row++) {
            for (int column = 0; column < width; column++) {
                var pixel = new Pixel();
                canvas.get(row).set(column, pixel);
                canvas.get(mirrorXHorizontally(row)).set(column, pixel);
            }
        }

    }

    private void fillCanvasWithVerticalSymmetry() {
        int maxY = getHalfWidth();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < maxY; column++) {
                var pixel = new Pixel();
                canvas.get(row).set(column, pixel);
                canvas.get(row).set(mirrorYVertically(column), pixel);
            }
        }

    }

    private void fillCanvasWithNullValues() {
        canvas = new ArrayList<>(height);

        for (int row = 0; row < height; row++) {
            List<Pixel> pixelRow = new ArrayList<>(width);
            for (int column = 0; column < width; column++) {
                pixelRow.add(null);
            }
            canvas.add(pixelRow);
        }
    }

    private int mirrorYVertically(int y) {
        return width - y - 1;
    }

    private int mirrorXHorizontally(int x) {
        return height - x - 1;
    }

    private int getHalfHeight() {
        return getHalfCanvasDimension(height);
    }

    private int getHalfWidth() {
        return getHalfCanvasDimension(width);
    }

    private int getHalfCanvasDimension(int dimensionValue) {
        return dimensionValue / 2 + (1 - dimensionValue % 2);
    }

    public Pixel getPixel(Dot dot) {
        return getPixel(dot.x(), dot.y());
    }

    public Pixel getPixel(int row, int column) {
        return canvas.get(row).get(column);
    }

}
