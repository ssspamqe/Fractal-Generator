package com.ssspamqe.helpers.affinetranformationsgenerator;

import com.ssspamqe.fractalgeneration.pointmodifiers.AffineTransformation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomAffineTransformationGenerator implements AffineTransformationGenerator {

    @Override
    public List<AffineTransformation> generateListOfAffineTransformations(int n) {
        List<AffineTransformation> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            var newTransformation = generateAffineTransformation();
            result.add(newTransformation);
        }
        return result;
    }

    @Override
    public AffineTransformation generateAffineTransformation() {
        while (true) {
            double a = generateRotationCoefficient();
            double b = generateRotationCoefficient();
            double d = generateRotationCoefficient();
            double e = generateRotationCoefficient();

            double c = generateShiftCoefficient();
            double f = generateShiftCoefficient();

            var color = generateColor();

            if (areCorrectAffineTransformationCoefficients(a, b, c, d)) {
                return AffineTransformation.builder()
                        .a(a)
                        .b(b)
                        .d(d)
                        .e(e)
                        .c(c)
                        .f(f)
                        .color(color)
                        .build();
            }
        }
    }

    private double generateRotationCoefficient() {
        return ThreadLocalRandom.current().nextDouble(-1, 1);
    }

    private double generateShiftCoefficient() {
        return ThreadLocalRandom.current().nextDouble(-20, 20);
    }

    private Color generateColor() {
        int r = generateColorCoefficient();
        int g = generateColorCoefficient();
        int b = generateColorCoefficient();

        return new Color(r, g, b);
    }

    private int generateColorCoefficient() {
        return ThreadLocalRandom.current().nextInt(0, 256);
    }


    private boolean areCorrectAffineTransformationCoefficients(double a, double b, double d, double e) {
        return a * a + d * d < 1
                && b * b + e * e < 1
                && a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d);
    }
}
