package de.hanoi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

enum Shape {

    ROD('R', Collections.singletonList("@@@@@")),
    ANGLE('A', List.of(
            "@@@",
            "@  ",
            "@  "
    )),
    HOOK('H', List.of(
            "@  ",
            "@@@",
            "  @"
    )),
    LETTER_L('L', List.of(
            "@@@@",
            "@   "
    )),
    BATON('B', List.of(
            "@@@@",
            " @  "
    )),
    LETTER_T('T', List.of(
            "@@@",
            " @ ",
            " @ "
    )),
    LETTER_W('W', List.of(
            "@  ",
            "@@ ",
            " @@"
    )),
    STAR('+', List.of(
            " @ ",
            "@@@",
            " @ "
    )),
    LETTER_C('C', List.of(
            "@@@",
            "@ @"
    )),
    NUMBER_4('4', List.of(
            "@  ",
            "@@@",
            " @ "
    )),
    SNAKE('S', List.of(
            "  @@",
            "@@@ "
    )),
    LETTER_P('P', List.of(
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
        this.blobs = allBlobs(this, points);
    }

    List<Blob> getBlobs() {
        return blobs;
    }

    private static List<Blob> allBlobs(Shape shape, EnumSet<ShapePoint> points) {
        Blob original = new Blob(shape, points);
        List<Blob> result = new ArrayList<>();
        result.add(original);
        for (Blob variant : getInversions(original)) {
            Blob current = variant;
            for (int rot = 0; rot < 4; rot++) {
                Blob rotated = new Blob(shape, rotate(current.points()));
                if (!contains(result, rotated)) {
                    result.add(rotated);
                }
                current = rotated;
            }
        }
        return result;
    }

    static List<Blob> getInversions(Blob original) {
        EnumSet<ShapePoint> inverted = invert(original.points());
        if (inverted.equals(original.points())) {
            return Collections.singletonList(original);
        }
        Blob inversion = new Blob(original.shape(), inverted);
        return List.of(original, inversion);
    }

    static EnumSet<ShapePoint> invert(EnumSet<ShapePoint> points) {
        EnumSet<ShapePoint> result = EnumSet.noneOf(ShapePoint.class);
        for (ShapePoint point : points) {
            result.add(point.invert());
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
            if (test.points().equals(blob.points())) {
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
