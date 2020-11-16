package de.hanoi;

import java.util.EnumSet;

// blob + base
class Figure {

    private final Shape shape;
    private final EnumSet<Point> points;

    Figure(Shape shape, EnumSet<Point> points) {
        this.shape = shape;
        this.points = points;
    }

    static Figure create(Blob blob, Point base) {
        EnumSet<Point> result = EnumSet.noneOf(Point.class);
        for (ShapePoint point : blob.points()) {
            result.add(point.translate(base));
        }
        return new Figure(blob.shape(), result);
    }

    boolean occupies(int x, int y) {
        return points.contains(Point.of(x, y));
    }

    boolean overlaps(Figure other) {
        for (Point point : points) {
            for (Point o : other.points) {
                if (point == o) {
                    return true;
                }
            }
        }
        return false;
    }

    Shape shape() {
        return shape;
    }
}
