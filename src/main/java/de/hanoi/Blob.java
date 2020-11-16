package de.hanoi;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

// oriented shape
class Blob {

    private final Shape shape;
    private final EnumSet<ShapePoint> points;

    Blob(Shape shape, EnumSet<ShapePoint> points) {
        this.shape = shape;
        this.points = points;
    }

    Shape shape() {
        return shape;
    }

    EnumSet<ShapePoint> points() {
        return points;
    }

    @Override
    public String toString() {
        List<String> result = print();
        return String.join(System.lineSeparator(), result);
    }

    List<String> print() {
        List<String> result = new ArrayList<>();
        for (int y = 0; y < ShapePoint.HEIGHT; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < ShapePoint.WIDTH; x++) {
                sb.append(!points.contains(ShapePoint.of(y, x)) ? "." : shape.signature());
            }
            result.add(sb.toString());
        }
        return result;
    }
}
