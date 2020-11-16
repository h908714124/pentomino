package de.hanoi;

// blob + position
class Figure {

    private final Blob blob;
    private final Point base;

    Figure(Blob blob, Point base) {
        this.blob = blob;
        this.base = base;
    }

    boolean occupies(int x, int y) {
        return blob.occupies(x - this.base.x, y - this.base.y);
    }

    Blob getBlob() {
        return blob;
    }
}
