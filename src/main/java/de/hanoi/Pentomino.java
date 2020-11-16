package de.hanoi;

import java.util.ArrayList;
import java.util.List;

public class Pentomino {

    private final List<Figure> blobs = new ArrayList<>();

    private Pentomino() {
    }

    static Pentomino create() {
        return new Pentomino();
    }

    List<String> print() {
        List<String> result = new ArrayList<>();
        for (int y = 0; y < Point.HEIGHT; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < Point.WIDTH; x++) {
                sb.append(occupied(x, y));
            }
            result.add(sb.toString());
        }
        return result;
    }

    char occupied(int x, int y) {
        for (Figure figure : blobs) {
            if (figure.occupies(x, y)) {
                return figure.shape().signature();
            }
        }
        return '.';
    }

    void addFigure(Blob blob, int x, int y) {
        blobs.add(Figure.create(blob, Point.of(x, y)));
    }
}
