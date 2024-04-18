package com.ssspamqe.helpers.affinetranformationsgenerator;

import com.ssspamqe.fractalgeneration.pointmodifiers.AffineTransformation;

import java.util.List;

public interface AffineTransformationGenerator {
    AffineTransformation generateAffineTransformation();

    List<AffineTransformation> generateListOfAffineTransformations(int n);

}
