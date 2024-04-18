package com.ssspamqe.properties;


import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import com.ssspamqe.imagerender.FileFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@ToString
public class Properties {
    @Getter
    private int affineTransformations;

    @Getter
    private int affineTransformationsGenerationTryouts;

    @Getter
    private int samples;

    @Getter
    private int offset;

    @Getter
    private int iterationsPerSample;

    private List<PointFunctionType> pointFunctionTypes;

    @Getter
    private int canvasHeight;

    @Getter
    private int canvasWidth;

    @Getter
    private int threads;

    @Getter
    private String generatedImagesPath;

    @Getter
    private String generatedImageName;

    @Getter
    private FileFormat fileFormat;


    public List<PointFunction> getPointFunctions() {
        return pointFunctionTypes.stream().map(PointFunctionType::getObject).toList();
    }
}