package de.hanoi;

import java.util.ArrayList;
import java.util.List;

public class Pentomino {

    private final int width;
    private final int height;

    private final List<Figure> blobs = new ArrayList<>();

    Pentomino(int width, int height) {
        this.width = width;
        this.height = height;
    }

    static Pentomino create() {
        return new Pentomino(10, 6);
    }

    List<String> print() {
        List<String> result = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                sb.append(occupied(x, y));
            }
            result.add(sb.toString());
        }
        return result;
    }

    char occupied(int x, int y) {
        for (Figure figure : blobs) {
            if (figure.occupies(x, y)) {
                return figure.getBlob().getShape().signature();
            }
        }
        return '.';
    }

    void addFigure(Blob blob, int x, int y) {
        blobs.add(new Figure(blob, x, y));
    }
}
