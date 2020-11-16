package de.hanoi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

enum Shape {

    ROD('R', Collections.singletonList("@@@@@")),
    ANGLE('A', Arrays.asList(
            "@@@",
            "@  ",
            "@  "
    )),
    HOOK('H', Arrays.asList(
            "@  ",
            "@@@",
            "  @"
    )),
    LETTER_L('L', Arrays.asList(
            "@@@@",
            "@   "
    )),
    BATON('B', Arrays.asList(
            "@@@@",
            " @  "
    )),
    LETTER_T('T', Arrays.asList(
            "@@@",
            " @ ",
            " @ "
    )),
    LETTER_W('W', Arrays.asList(
            "@  ",
            "@@ ",
            " @@"
    )),
    STAR('+', Arrays.asList(
            " @ ",
            "@@@",
            " @ "
    )),
    LETTER_C('C', Arrays.asList(
            "@@@",
            "@ @"
    )),
    NUMBER_4('4', Arrays.asList(
            "@  ",
            "@@@",
            " @ "
    )),
    SNAKE('S', Arrays.asList(
            "  @@",
            "@@@ "
    )),
    LETTER_P('P', Arrays.asList(
            "@@@",
            "@@ "
    ));

    private final char signature;
    private final EnumSet<ShapePoint> points;
    private final List<Blob> blobs;
    final int width;
    final int height;

    Shape(char signature, List<String> strings) {
        this.signature = signature;
        this.points = EnumSet.noneOf(ShapePoint.class);
        this.width = strings.get(0).length();
        this.height = strings.size();
        for (int y = 0, stringsSize = strings.size(); y < stringsSize; y++) {
            String string = strings.get(y);
            for (int x = 0; x < string.length(); x++) {
                if (!Character.toString(string.charAt(x)).isBlank()) {
                    points.add(ShapePoint.of(x, y));
                }
            }
        }
        this.blobs = getBlobs(this, points);
    }

    static Blob getBlob(Shape shape, EnumSet<ShapePoint> points) {
        return new Blob(shape, points);
    }

    Blob getBlob() {
        return new Blob(this, points);
    }

    List<Blob> getBlobs() {
        return blobs;
    }

    static List<Blob> getBlobs(Shape shape, EnumSet<ShapePoint> points) {
        List<Blob> result = new ArrayList<>();
        for (Blob variant : getInversions(getBlob(shape, points))) {
            Blob current = variant;
            for (int rot = 0; rot < 4; rot++) {
                Blob rotated = new Blob(shape, rotate(current.pointSet()));
                if (!contains(result, rotated)) {
                    result.add(rotated);
                }
                current = rotated;
            }
        }
        return result;
    }

    static List<Blob> getInversions(Blob original) {
        boolean[][] inverted = invert(original.points());
        if (Arrays.deepEquals(inverted, original.points())) {
            return Collections.singletonList(original);
        }
        Blob inversion = Blob.create(original.getShape(), inverted);
        return Arrays.asList(original, inversion);

    }

    static boolean[][] invert(boolean[][] points) {
        boolean[][] result = new boolean[points[0].length][];
        for (int i = 0; i < points[0].length; i++) {
            result[i] = new boolean[points.length];
        }
        for (int y = 0; y < points.length; y++) {
            for (int x = 0; x < points[y].length; x++) {
                result[x][y] = points[y][x];
            }
        }
        return result;
    }

    static EnumSet<ShapePoint> rotate(EnumSet<ShapePoint> points) {
        EnumSet<ShapePoint> result = EnumSet.noneOf(ShapePoint.class);
        for (ShapePoint point : points) {
            result.add(point.rotate());
        }
        while (canShiftLeft(result)) {
            result = shiftLeft(result);
        }
        while (canShiftUp(result)) {
            result = shiftUp(result);
        }
        return result;
    }

    private static EnumSet<ShapePoint> shiftLeft(EnumSet<ShapePoint> points) {
        EnumSet<ShapePoint> result = EnumSet.noneOf(ShapePoint.class);
        for (ShapePoint point : points) {
            result.add(point.shiftLeft());
        }
        return result;
    }

    private static boolean canShiftLeft(EnumSet<ShapePoint> points) {
        for (ShapePoint point : points) {
            if (!point.canShiftLeft()) {
                return false;
            }
        }
        return true;
    }

    private static EnumSet<ShapePoint> shiftUp(EnumSet<ShapePoint> points) {
        EnumSet<ShapePoint> result = EnumSet.noneOf(ShapePoint.class);
        for (ShapePoint point : points) {
            result.add(point.shiftUp());
        }
        return result;

    }

    private static boolean canShiftUp(EnumSet<ShapePoint> points) {
        for (ShapePoint point : points) {
            if (!point.canShiftUp()) {
                return false;
            }
        }
        return true;
    }

    static boolean contains(List<Blob> blobs, Blob test) {
        for (Blob blob : blobs) {
            if (test.pointSet().equals(blob.pointSet())) {
                return true;
            }
        }
        return false;
    }

    char signature() {
        return signature;
    }

    EnumSet<ShapePoint> points() {
        return points;
    }
}
