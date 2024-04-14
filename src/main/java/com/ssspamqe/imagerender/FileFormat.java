package com.ssspamqe.imagerender;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FileFormat {
    PNG("png"),
    JPEG("jpeg"),
    JPG("jpg");

    private final String stringRepresentation;

    @Override
    public String toString() {
        return stringRepresentation;
    }
}
