package com.ssspamqe;

import com.ssspamqe.fractalgeneration.fractalcreators.MultiThreadFractalCreator;
import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import com.ssspamqe.helpers.affinetranformationsgenerator.AffineTransformationGenerator;
import com.ssspamqe.helpers.affinetranformationsgenerator.RandomAffineTransformationGenerator;
import com.ssspamqe.imagerender.ImageRenderer;
import com.ssspamqe.imagerender.JavaGraphicsImageRenderer;
import com.ssspamqe.properties.Properties;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import java.nio.file.Path;

public class Main {

    private static final String PROPERTIES_FILE_PATH = "generation.yaml";

    private static final AffineTransformationGenerator affineTransformationGenerator =
            new RandomAffineTransformationGenerator();

    private static final ImageRenderer imageRenderer = new JavaGraphicsImageRenderer();

    private static Properties properties;

    public void main(String[] args) {
        properties = getProperties();

        var fractalGenerator = buildFractalGeneratorFromProperties();
        PixelCanvas canvas = new PixelCanvas(properties.getCanvasHeight(), properties.getCanvasWidth());
        fractalGenerator.fillCanvasWithThreads(canvas, properties.getThreads());

        renderAndSaveCanvas(canvas);
    }

    private static MultiThreadFractalCreator buildFractalGeneratorFromProperties() {
        var affineTransformations = affineTransformationGenerator
                .generateListOfAffineTransformations(properties.getAffineTransformationsAmount());

        return MultiThreadFractalCreator.builder()
                .samples(properties.getSamples())
                .offset(properties.getOffset())
                .pointFunctions(properties.getPointFunctions())
                .iterationsPerSample(properties.getIterationsPerSample())
                .transformations(affineTransformations)
                .build();

    }

    private static void renderAndSaveCanvas(PixelCanvas canvas) {
        var imagePath = Path.of(properties.getGeneratedImagesPath());
        var fileName = properties.getGeneratedImageName();
        var format = properties.getFileFormat();

        imageRenderer.renderImage(canvas, imagePath, fileName, format);
    }

    private Properties getProperties() {
        var loaderoptions = new LoaderOptions();
        TagInspector taginspector =
                tag -> tag.getClassName().equals(Properties.class.getName());
        loaderoptions.setTagInspector(taginspector);
        Yaml yaml = new Yaml(new Constructor(Properties.class, loaderoptions));

        var inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_PATH);

        return yaml.load(inputStream);
    }
}