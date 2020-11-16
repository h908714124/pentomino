package de.hanoi;

import java.util.EnumSet;

enum Point {

    P_0_0(0, 0),
    P_0_1(0, 1),
    P_0_2(0, 2),
    P_0_3(0, 3),
    P_0_4(0, 4),
    P_0_5(0, 5),
    P_0_6(0, 6),
    P_0_7(0, 7),
    P_0_8(0, 8),
    P_0_9(0, 9),
    P_1_0(1, 0),
    P_1_1(1, 1),
    P_1_2(1, 2),
    P_1_3(1, 3),
    P_1_4(1, 4),
    P_1_5(1, 5),
    P_1_6(1, 6),
    P_1_7(1, 7),
    P_1_8(1, 8),
    P_1_9(1, 9),
    P_2_0(2, 0),
    P_2_1(2, 1),
    P_2_2(2, 2),
    P_2_3(2, 3),
    P_2_4(2, 4),
    P_2_5(2, 5),
    P_2_6(2, 6),
    P_2_7(2, 7),
    P_2_8(2, 8),
    P_2_9(2, 9),
    P_3_0(3, 0),
    P_3_1(3, 1),
    P_3_2(3, 2),
    P_3_3(3, 3),
    P_3_4(3, 4),
    P_3_5(3, 5),
    P_3_6(3, 6),
    P_3_7(3, 7),
    P_3_8(3, 8),
    P_3_9(3, 9),
    P_4_0(4, 0),
    P_4_1(4, 1),
    P_4_2(4, 2),
    P_4_3(4, 3),
    P_4_4(4, 4),
    P_4_5(4, 5),
    P_4_6(4, 6),
    P_4_7(4, 7),
    P_4_8(4, 8),
    P_4_9(4, 9),
    P_5_0(5, 0),
    P_5_1(5, 1),
    P_5_2(5, 2),
    P_5_3(5, 3),
    P_5_4(5, 4),
    P_5_5(5, 5),
    P_5_6(5, 6),
    P_5_7(5, 7),
    P_5_8(5, 8),
    P_5_9(5, 9);

    final int x;
    final int y;

    static final int WIDTH = 10;
    static final int HEIGHT = 6;

    private static final Point[][] ALL = allPoints();

    Point(int y, int x) {
        this.x = x;
        this.y = y;
    }

    static Point[][] allPoints() {
        Point[][] result = new Point[6][];
        for (int i = 0, resultLength = result.length; i < resultLength; i++) {
            result[i] = new Point[10];
        }
        for (Point value : values()) {
            result[value.y][value.x] = value;
        }
        return result;
    }

    Point expand(EnumSet<Point> forbidden, EnumSet<Point> alreadyDone) {
        if (!isLeftEdge()) {
            Point point = left();
            if (!forbidden.contains(point) && !alreadyDone.contains(point)) {
                return point;
            }
        }
        if (!isRightEdge()) {
            Point point = right();
            if (!forbidden.contains(point) && !alreadyDone.contains(point)) {
                return point;
            }
        }
        if (!isTopEdge()) {
            Point point = up();
            if (!forbidden.contains(point) && !alreadyDone.contains(point)) {
                return point;
            }
        }
        if (!isBottomEdge()) {
            Point point = down();
            if (!forbidden.contains(point) && !alreadyDone.contains(point)) {
                return point;
            }
        }
        return null;
    }

    private boolean isLeftEdge() {
        return x == 0;
    }

    private boolean isRightEdge() {
        return x == 9;
    }

    private boolean isTopEdge() {
        return y == 0;
    }

    private boolean isBottomEdge() {
        return y == 5;
    }

    private Point left() {
        return of(y, x - 1);
    }

    private Point right() {
        return of(y, x + 1);
    }

    private Point up() {
        return of(y - 1, x);
    }

    private Point down() {
        return of(y + 1, x);
    }

    static Point of(int y, int x) {
        return ALL[y][x];
    }
}
