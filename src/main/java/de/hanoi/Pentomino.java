package de.hanoi;

import java.util.ArrayList;
import java.util.EnumSet;
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
                sb.append(pointSignature(Point.of(y, x)));
            }
            result.add(sb.toString());
        }
        return result;
    }

    private char pointSignature(Point point) {
        for (Figure figure : figures) {
            if (figure.points().contains(point)) {
                return figure.shape().signature();
            }
        }
        return '.';
    }

    void addFigure(Blob blob, Point point) {
        Figure figure = Figure.create(blob, point);
        figures.add(figure);
    }

    EnumSet<Point> findConnectedArea(Point seed) {
        EnumSet<Point> dead = EnumSet.noneOf(Point.class);
        EnumSet<Point> forbidden = occupiedArea();
        EnumSet<Point> result = EnumSet.of(seed);
        while (true) {
            int size = result.size();
            for (Point point : result) {
                if (dead.contains(point)) {
                    continue;
                }
                Point free = point.expand(forbidden, result);
                if (free == null) {
                    dead.add(point);
                } else {
                    result.add(free);
                }
            }
            if (result.size() == size) {
                break;
            }
        }
        return result;
    }

    EnumSet<Point> occupiedArea() {
        EnumSet<Point> result = EnumSet.noneOf(Point.class);
        for (Figure figure : figures) {
            result.addAll(figure.points());
        }
        return result;
    }

    boolean overlaps(Blob blob, Point point) {
        Figure figure = Figure.create(blob, point);
        for (Figure f : figures) {
            if (f.overlaps(figure)) {
                return true;
            }
        }
        return false;
    }
}
