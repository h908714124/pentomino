package de.hanoi;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

// shape + rotation + invert
class Blob {

    private final Shape shape;
    private final EnumSet<ShapePoint> points;

    Blob(Shape shape, EnumSet<ShapePoint> points) {
        this.shape = shape;
        this.points = points;
    }

    static Blob create(Shape shape, boolean[][] points) {
        EnumSet<ShapePoint> enumPoints = EnumSet.noneOf(ShapePoint.class);
        for (int y = 0, pointsLength = points.length; y < pointsLength; y++) {
            boolean[] row = points[y];
            for (int x = 0, rowLength = row.length; x < rowLength; x++) {
                boolean b = row[x];
                if (b) {
                    enumPoints.add(ShapePoint.of(x, y));
                }
            }
        }
        return new Blob(shape, enumPoints);
    }

    boolean occupies(int x, int y) {
        for (ShapePoint point : points) {
            if (point.x == x && point.y == y) {
                return true;
            }
        }
        return false;
    }

    Shape getShape() {
        return shape;
    }

    boolean[][] points() {
        boolean[][] result = new boolean[ShapePoint.HEIGHT][];
        for (int i = 0, resultLength = result.length; i < resultLength; i++) {
            result[i] = new boolean[ShapePoint.WIDTH];
        }
        for (ShapePoint point : this.points) {
            result[point.y][point.x] = true;
        }
        return result;
    }

    public EnumSet<ShapePoint> pointSet() {
        return points;
    }

    @Override
    public String toString() {
        List<String> result = print();
        return String.join(System.lineSeparator(), result);
    }

    List<String> print() {
        List<String> result = new ArrayList<>();
        for (boolean[] row : points()) {
            StringBuilder sb = new StringBuilder();
            for (boolean b : row) {
                sb.append(b ? shape.signature() : ".");
            }
            result.add(sb.toString());
        }
        return result;
    }
}
