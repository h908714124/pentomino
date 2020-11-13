package de.hanoi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private final boolean[][] points;
    private final List<Blob> blobs;
    final int width;
    final int height;

    Shape(char signature, List<String> strings) {
        this.signature = signature;
        this.points = new boolean[strings.size()][];
        this.width = strings.get(0).length();
        this.height = strings.size();
        for (int i = 0, stringsSize = strings.size(); i < stringsSize; i++) {
            String string = strings.get(i);
            points[i] = new boolean[string.length()];
            for (int j = 0; j < string.length(); j++) {
                points[i][j] = !Character.toString(string.charAt(j)).isBlank();
            }
        }
        this.blobs = getBlobs(this, points);
    }

    static Blob getBlob(Shape shape, boolean[][] points) {
        return new Blob(shape, points);
    }

    Blob getBlob() {
        return new Blob(this, points);
    }

    List<Blob> getBlobs() {
        return blobs;
    }

    static List<Blob> getBlobs(Shape shape, boolean[][] points) {
        List<Blob> result = new ArrayList<>();
        for (Blob variant : getInversions(getBlob(shape, points))) {
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
        boolean[][] inverted = invert(original.points());
        if (Arrays.deepEquals(inverted, original.points())) {
            return Collections.singletonList(original);
        }
        Blob inversion = new Blob(original.getShape(), inverted);
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

    static boolean[][] rotate(boolean[][] points) {
        boolean[][] result = new boolean[points[0].length][];
        for (int i = 0; i < points[0].length; i++) {
            result[i] = new boolean[points.length];
        }
        for (int y = points.length - 1; y >= 0; y--) {
            for (int x = 0; x < points[y].length; x++) {
                result[x][points.length - y - 1] = points[y][x];
            }
        }
        return result;
    }

    static boolean contains(List<Blob> blobs, Blob test) {
        for (Blob blob : blobs) {
            if (Arrays.deepEquals(blob.points(), test.points())) {
                return true;
            }
        }
        return false;
    }

    char signature() {
        return signature;
    }

    boolean[][] points() {
        return points;
    }
}
