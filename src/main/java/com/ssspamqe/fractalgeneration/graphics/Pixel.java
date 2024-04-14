package com.ssspamqe.fractalgeneration.graphics;

import lombok.Data;

import java.awt.*;

@Data
public class Pixel {
    private Color color = Color.BLACK;
    private int hits;

    public void incrementHits() {
        hits++;
    }

    public void mixColorWith(Color newColor) {
        color = new Color(
                (color.getRed() + newColor.getRed()) / 2,
                (color.getGreen() + newColor.getGreen()) / 2,
                (color.getBlue() + newColor.getBlue()) / 2
        );
    }
}
