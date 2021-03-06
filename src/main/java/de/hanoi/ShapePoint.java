package de.hanoi;

enum ShapePoint {

    P_0_0(0, 0),
    P_0_1(0, 1),
    P_0_2(0, 2),
    P_0_3(0, 3),
    P_0_4(0, 4),
    P_1_0(1, 0),
    P_1_1(1, 1),
    P_1_2(1, 2),
    P_1_3(1, 3),
    P_1_4(1, 4),
    P_2_0(2, 0),
    P_2_1(2, 1),
    P_2_2(2, 2),
    P_2_3(2, 3),
    P_2_4(2, 4),
    P_3_0(3, 0),
    P_3_1(3, 1),
    P_3_2(3, 2),
    P_3_3(3, 3),
    P_3_4(3, 4),
    P_4_0(4, 0),
    P_4_1(4, 1),
    P_4_2(4, 2),
    P_4_3(4, 3),
    P_4_4(4, 4);

    final int x;
    final int y;

    static final int WIDTH = 5;
    static final int HEIGHT = 5;

    private static final ShapePoint[][] ALL = allPoints();

    ShapePoint(int y, int x) {
        this.x = x;
        this.y = y;
    }

    static ShapePoint[][] allPoints() {
        ShapePoint[][] result = new ShapePoint[HEIGHT][];
        for (int i = 0, resultLength = result.length; i < resultLength; i++) {
            result[i] = new ShapePoint[WIDTH];
        }
        for (ShapePoint value : values()) {
            result[value.y][value.x] = value;
        }
        return result;
    }

    ShapePoint invert() {
        return of(x, y);
    }

    ShapePoint rotate() {
        return of(x, WIDTH - y - 1);
    }

    ShapePoint left() {
        return of(y, x - 1);
    }

    boolean isLeftEdge() {
        return x == 0;
    }

    boolean isTopEdge() {
        return y == 0;
    }

    ShapePoint up() {
        return of(y - 1, x);
    }

    Point translate(Point base) {
        return Point.of(this.y + base.y, this.x + base.x);
    }

    static ShapePoint of(int y, int x) {
        return ALL[y][x];
    }
}
