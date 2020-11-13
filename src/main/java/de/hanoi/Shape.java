package de.hanoi;

import java.util.Arrays;
import java.util.List;

enum Shape {

    CORNER('c', Arrays.asList(
            "@@@",
            "@  ",
            "@  "
    ));

    private final char signature;
    private final boolean[][] points;
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
    }

    Blob getBlob() {
        return new Blob(this, points);
    }

    char signature() {
        return signature;
    }
}
