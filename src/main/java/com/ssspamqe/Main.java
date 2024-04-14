package com.ssspamqe;

import com.ssspamqe.fractalgeneration.fractalcreators.MultiThreadFractalCreator;
import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import com.ssspamqe.fractalgeneration.pointmodifiers.AffineTransformation;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.DiscFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.SinusoidalFunction;
import com.ssspamqe.imagerender.ImageRenderer;
import com.ssspamqe.imagerender.JavaGraphicsImageRenderer;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final int MAX_COLOR = 255;
    private static final int MAX_ABSOLUTE_SHIFT = 20;
    private static final String PROPERTIES_FILE_PATH = "generation.yaml";

    public void main(String[] args) {
        var properties = getProperties();

        List<AffineTransformation> affineTransformations =
                buildListOfAffineTransformations(
                        properties.getAffineTransformations(),
                        properties.getAffineTransformationsGenerationTryouts()
                );

        var fractalGenerator = MultiThreadFractalCreator.builder()
                .samples(properties.getSamples())
                .offset(properties.getOffset())
                .pointFunctions(List.of(new DiscFunction(), new SinusoidalFunction()))
                .iterationsPerSample(properties.getIterationsPerSample())
                .transformations(affineTransformations)
                .build();

        PixelCanvas canvas = new PixelCanvas(properties.getCanvasHeight(), properties.getCanvasWidth());
        fractalGenerator.fillCanvasWithThreads(canvas, properties.getThreads());


        ImageRenderer imageRenderer = new JavaGraphicsImageRenderer();
        var imagePath = Path.of(properties.getGeneratedImagesPath());
        String fileName = properties.getGeneratedImageName();
        var format = properties.getFileFormat();
        imageRenderer.renderImage(canvas, imagePath, fileName, format);
    }

    Properties getProperties() {
        var loaderoptions = new LoaderOptions();
        TagInspector taginspector =
                tag -> tag.getClassName().equals(Properties.class.getName());
        loaderoptions.setTagInspector(taginspector);
        Yaml yaml = new Yaml(new Constructor(Properties.class, loaderoptions));
        var inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_PATH);
        return yaml.load(inputStream);
    }

    private List<AffineTransformation> buildListOfAffineTransformations(int n, int tryouts) {
        List<AffineTransformation> transformations = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            var transformation = generateAffineTransformation(tryouts);
            transformation.ifPresent(transformations::add);
        }
        return transformations;
    }


    private Optional<AffineTransformation> generateAffineTransformation(int tryouts) {
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

    private boolean areCorrectAffineTransformationCoefficients(double a, double b, double d, double e) {
        return a * a + d * d < 1
                && b * b + e * e < 1
                && a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d);
    }
}