package de.hanoi;

import java.util.ArrayList;
import java.util.List;

public class Pentomino {

    private final List<Figure> figures = new ArrayList<>();

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
        for (Figure figure : figures) {
            if (figure.occupies(x, y)) {
                return figure.shape().signature();
            }
        }
        return '.';
    }

    void addFigure(Blob blob, int x, int y) {
        Figure figure = Figure.create(blob, Point.of(x, y));
        figures.add(figure);
    }

    boolean overlaps(Blob blob, int x, int y) {
        Figure figure = Figure.create(blob, Point.of(x, y));
        for (Figure f : figures) {
            if (f.overlaps(figure)) {
                return true;
            }
        }
        return false;
    }
}
