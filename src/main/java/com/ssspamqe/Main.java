package com.ssspamqe;

import com.ssspamqe.fractalgeneration.fractalcreators.MultiThreadFractalCreator;
import com.ssspamqe.fractalgeneration.fractalcreators.SingleThreadFractalCreator;
import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import com.ssspamqe.fractalgeneration.pointmodifiers.AffineTransformation;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.DiscFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PolarFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.SinusoidalFunction;
import com.ssspamqe.imagerender.FileFormat;
import com.ssspamqe.imagerender.ImageRenderer;
import com.ssspamqe.imagerender.JavaGraphicsImageRenderer;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final int MAX_COLOR = 255;
    private static final int MAX_ABSOLUTE_SHIFT = 20;

    public static void main(String[] args) {
        List<AffineTransformation> affineTransformations = buildListOfAffineTransformations(70);
        var fractalGenerator = MultiThreadFractalCreator.builder()
                .samples(1_000_000)
                .offset(-20)
                .pointFunctions(List.of(new  DiscFunction(), new SinusoidalFunction()))
                .iterationsPerSample(100)
                .transformations(affineTransformations)
                .build();

        PixelCanvas canvas = new PixelCanvas(2000, 2000);
        fractalGenerator.fillCanvasWithThreads(canvas,6);


        ImageRenderer imageRenderer = new JavaGraphicsImageRenderer();
        imageRenderer.renderImage(canvas, Path.of("./generatedImages"), "fractalImage", FileFormat.PNG);
    }

    private static List<AffineTransformation> buildListOfAffineTransformations(int n) {
        List<AffineTransformation> transformations = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            var transformation = generateAffineTransformation(1_000_000);
            transformation.ifPresent(transformations::add);
        }
        return transformations;
    }


    private static Optional<AffineTransformation> generateAffineTransformation(int tryouts) {
        for (int tryout = 0; tryout < tryouts; tryout++) {
            double a = ThreadLocalRandom.current().nextDouble(-1, 1);
            double b = ThreadLocalRandom.current().nextDouble(-1, 1);
            double d = ThreadLocalRandom.current().nextDouble(-1, 1);
            double e = ThreadLocalRandom.current().nextDouble(-1, 1);

            double c = ThreadLocalRandom.current().nextDouble(-MAX_ABSOLUTE_SHIFT, MAX_ABSOLUTE_SHIFT);
            double f = ThreadLocalRandom.current().nextDouble(-MAX_ABSOLUTE_SHIFT, MAX_ABSOLUTE_SHIFT);

            int red = ThreadLocalRandom.current().nextInt(0, MAX_COLOR + 1);
            int green = ThreadLocalRandom.current().nextInt(0, MAX_COLOR + 1);
            int blue = ThreadLocalRandom.current().nextInt(0, MAX_COLOR + 1);
            Color color = new Color(red, green, blue);

            if (areCorrectAffineTransformationCoefficients(a, b, c, d)) {
                return Optional.of(new AffineTransformation(a, b, c, d, e, f, color));
            }
        }
        return Optional.empty();
    }

    private static boolean areCorrectAffineTransformationCoefficients(double a, double b, double d, double e) {
        return a * a + d * d < 1
                && b * b + e * e < 1
                && a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d);
    }
}