package de.hanoi;

// blob + position
class Figure {

    private final Blob blob;
    private final Point point;

    Figure(Blob blob, Point point) {
        this.blob = blob;
        this.point = point;
    }

    boolean occupies(int x, int y) {
        return blob.occupies(x - this.point.x, y - this.point.y);
    }

    Blob getBlob() {
        return blob;
    }
}
