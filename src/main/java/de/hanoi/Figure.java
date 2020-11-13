package de.hanoi;

// blob + position
class Figure {

    private final Blob blob;
    private final int x;
    private final int y;

    Figure(Blob blob, int x, int y) {
        this.blob = blob;
        this.x = x;
        this.y = y;
    }

    boolean occupies(int x, int y) {
        return blob.occupies(x - this.x, y - this.y);
    }

    Blob getBlob() {
        return blob;
    }
}
