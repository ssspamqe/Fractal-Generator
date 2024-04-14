package com.ssspamqe.imagerender;

import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;

import java.nio.file.Path;

public interface ImageRenderer {
    void renderImage(PixelCanvas canvas, Path imagePath, String fileName, FileFormat format);
}
