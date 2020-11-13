package de.hanoi;

// shape + rotation + invert
class Blob {

    private final Shape shape;
    private final boolean[][] points;
    private final int width;
    private final int height;

    Blob(Shape shape, boolean[][] points) {
        this.shape = shape;
        this.points = points;
        this.width = shape.width;
        this.height = shape.height;
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
}
