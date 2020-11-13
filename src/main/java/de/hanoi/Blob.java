package de.hanoi;

import java.util.ArrayList;
import java.util.List;

// shape + rotation + invert
class Blob {

    private final Shape shape;
    private final boolean[][] points;
    private final int width;
    private final int height;

    Blob(Shape shape, boolean[][] points) {
        this.shape = shape;
        this.points = points;
        this.width = points[0].length;
        this.height = points.length;
    }

    boolean occupies(int x, int y) {
        if (x >= width) {
            return false;
        }
        if (y >= height) {
            return false;
        }
        return points[y][x];
    }

    Shape getShape() {
        return shape;
    }

    boolean[][] points() {
        return points;
    }

    @Override
    public String toString() {
        List<String> result = print();
        return String.join(System.lineSeparator(), result);
    }

    List<String> print() {
        List<String> result = new ArrayList<>();
        for (boolean[] row : points) {
            StringBuilder sb = new StringBuilder();
            for (boolean b : row) {
                sb.append(b ? shape.signature() : ".");
            }
            result.add(sb.toString());
        }
        return result;
    }
}
