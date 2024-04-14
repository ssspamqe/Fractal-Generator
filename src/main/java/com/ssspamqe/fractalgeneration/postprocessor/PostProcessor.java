package com.ssspamqe.fractalgeneration.postprocessor;

import com.ssspamqe.fractalgeneration.graphics.PixelCanvas;

public interface PostProcessor {
    void process(PixelCanvas canvas);
}
