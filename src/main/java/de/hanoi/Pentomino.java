package de.hanoi;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Pentomino {

    private static final EnumSet<Point> EMPTY_SET = EnumSet.noneOf(Point.class);

    private final List<Figure> figures = new ArrayList<>();
    private final EnumSet<Point> occupiedArea = EnumSet.noneOf(Point.class);

    private final EnumSet<Point> hull = EnumSet.noneOf(Point.class);
    private final EnumSet<Point> hullDead = EnumSet.noneOf(Point.class);
    private final EnumSet<Point> connected = EnumSet.noneOf(Point.class);
    private final EnumSet<Point> connectedDead = EnumSet.noneOf(Point.class);

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

    Figure addFigure(Blob blob, Point point) {
        Figure figure = Figure.create(blob, point);
        figures.add(figure);
        updateOccupiedArea();
        return figure;
    }

    EnumSet<Point> hull(Figure figure) {
        hull.clear();
        hullDead.clear();
        while (true) {
            int size = hull.size();
            for (Point point : figure.points()) {
                if (hullDead.contains(point)) {
                    continue;
                }
                Point free = point.expand(occupiedArea, hull);
                if (free == null) {
                    hullDead.add(point);
                } else {
                    hull.add(free);
                }
            }
            if (hull.size() == size) {
                break;
            }
        }
        return hull;
    }

    EnumSet<Point> findConnectedArea(Point seed) {
        if (occupiedArea.contains(seed)) {
            return EMPTY_SET;
        }
        connectedDead.clear();
        connected.clear();
        connected.add(seed);
        while (true) {
            int size = connected.size();
            for (Point point : connected) {
                if (connectedDead.contains(point)) {
                    continue;
                }
                Point free = point.expand(occupiedArea, connected);
                if (free == null) {
                    connectedDead.add(point);
                } else {
                    connected.add(free);
                }
            }
            if (connected.size() == size) {
                break;
            }
        }
        return connected;
    }

    void updateOccupiedArea() {
        occupiedArea.clear();
        for (Figure figure : figures) {
            occupiedArea.addAll(figure.points());
        }
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

    @Override
    public String toString() {
        List<String> result = print();
        return String.join(System.lineSeparator(), result);
    }
}
