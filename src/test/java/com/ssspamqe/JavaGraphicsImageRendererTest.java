package com.ssspamqe;

import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;
import com.ssspamqe.imagerender.FileFormat;
import com.ssspamqe.imagerender.ImageRenderer;
import com.ssspamqe.imagerender.JavaGraphicsImageRenderer;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaGraphicsImageRendererTest {

    private static final Path DEFAULT_TEST_FILES_DIRECTORY =
            Path.of("./src/test/resources/images");
    private static final String DEFAULT_FILE_NAME = "img";
    private static final FileFormat DEFAULT_FILE_FORMAT = FileFormat.PNG;

    private final ImageRenderer imageRenderer = new JavaGraphicsImageRenderer();

    private PixelCanvas canvas = new PixelCanvas(10, 10);

    @BeforeEach
    void clearTestImages() throws IOException {
        FileUtils.cleanDirectory(new File((DEFAULT_TEST_FILES_DIRECTORY.toString())));
    }

    @Test
    public void should_renderAndSavePixelCanvas() {
        imageRenderer.renderImage(canvas, DEFAULT_TEST_FILES_DIRECTORY, DEFAULT_FILE_NAME, DEFAULT_FILE_FORMAT);

        var filePath = Path.of(DEFAULT_TEST_FILES_DIRECTORY + "\\" + DEFAULT_FILE_NAME + "." + DEFAULT_FILE_FORMAT);
        assertThat(Files.exists(filePath)).isTrue();
    }

    @Test
    public void should_enumerateFiles_when_fileWithSuchNameAlreadyExists() {
        imageRenderer.renderImage(canvas, DEFAULT_TEST_FILES_DIRECTORY, DEFAULT_FILE_NAME, DEFAULT_FILE_FORMAT);
        imageRenderer.renderImage(canvas, DEFAULT_TEST_FILES_DIRECTORY, DEFAULT_FILE_NAME, DEFAULT_FILE_FORMAT);

        var filePath = Path.of(DEFAULT_TEST_FILES_DIRECTORY + "\\" + DEFAULT_FILE_NAME + "1." + DEFAULT_FILE_FORMAT);
        assertThat(Files.exists(filePath)).isTrue();
    }

}
