package de.hanoi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShapeTest {

    @Test
    void testPrint() {
        assertEquals(8, Shape.LETTER_P.getBlobs().size());
        assertEquals(4, Shape.ANGLE.getBlobs().size());
    }

    @Test
    void testInvert() {
        boolean[][] points = Shape.invert(Shape.LETTER_P.points());
        assertEquals(3, points.length);
        assertArrayEquals(new boolean[]{true, true}, points[0]);
        assertArrayEquals(new boolean[]{true, true}, points[1]);
        assertArrayEquals(new boolean[]{true, false}, points[2]);
    }

    @Test
    void testRotate() {
        boolean[][] points = Shape.rotate(Shape.LETTER_P.points());
        assertEquals(3, points.length);
        assertArrayEquals(new boolean[]{true, true}, points[0]);
        assertArrayEquals(new boolean[]{true, true}, points[1]);
        assertArrayEquals(new boolean[]{false, true}, points[2]);
    }

    @Test
    void testSignature() {
        Set<Character> test = new HashSet<>();
        for (Shape shape : Shape.values()) {
            assertTrue(test.add(shape.signature()));
        }
    }

    @Test
    void testDistinct() {
        List<Blob> test = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            List<Blob> blobs = shape.getBlobs();
            for (Blob blob : blobs) {
                Assertions.assertFalse(Shape.contains(test, blob));
                test.add(blob);
            }
        }
    }

    @Test
    void testVolume() {
        for (Shape shape : Shape.values()) {
            List<Blob> blobs = shape.getBlobs();
            for (Blob blob : blobs) {
                assertEquals(5, getVolume(blob));
            }
        }
    }

    private static int getVolume(Blob blob) {
        boolean[][] points = blob.points();
        int count = 0;
        for (boolean[] row : points) {
            for (boolean point : row) {
                if (point) {
                    count++;
                }
            }
        }
        return count;
    }
}