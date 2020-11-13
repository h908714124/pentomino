package de.hanoi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeTest {

    @Test
    void testPrint() {
        assertEquals(8, Shape.NOSE.getBlobs().size());
        assertEquals(4, Shape.CORNER.getBlobs().size());
    }

    @Test
    void testInvert() {
        boolean[][] points = Shape.invert(Shape.NOSE.points());
        Assertions.assertEquals(2, points.length);
        assertArrayEquals(new boolean[]{true, true, true}, points[0]);
        assertArrayEquals(new boolean[]{true, true, false}, points[1]);
    }

    @Test
    void testRotate() {
        boolean[][] points = Shape.rotate(Shape.NOSE.points());
        Assertions.assertEquals(2, points.length);
        assertArrayEquals(new boolean[]{true, true, true}, points[0]);
        assertArrayEquals(new boolean[]{false, true, true}, points[1]);
    }
}