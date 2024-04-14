package com.ssspamqe;


import com.ssspamqe.imagerender.FileFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Properties {
    private int affineTransformations;
    private int affineTransformationsGenerationTryouts;
    private int samples;
    private int offset;
    private int iterationsPerSample;
    private int canvasHeight;
    private int canvasWidth;
    private int threads;
    private String generatedImagesPath;
    private String generatedImageName;
    private FileFormat fileFormat;
}