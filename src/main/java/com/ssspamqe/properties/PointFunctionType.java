package com.ssspamqe.properties;

import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.DiskFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.HeartFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PointFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.PolarFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.SinusoidalFunction;
import com.ssspamqe.fractalgeneration.pointmodifiers.pointfunctions.SphericalFunction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PointFunctionType {

    DISK(new DiskFunction()),
    HEART(new HeartFunction()),
    POLAR(new PolarFunction()),
    SINUSOIDAL(new SinusoidalFunction()),
    SPHERICAL(new SphericalFunction());

    private final PointFunction object;
}
