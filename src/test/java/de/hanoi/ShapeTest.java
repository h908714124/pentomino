package de.hanoi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShapeTest {

    @Test
    void testPrint() {
        assertEquals(8, Shape.LETTER_P.getBlobs().size());
        assertEquals(4, Shape.ANGLE.getBlobs().size());
    }

    @Test
    void testInvert() {
        Blob p = Shape.LETTER_P.getBlobs().get(0);
        Blob inverted = Shape.invert(p);
        assertEquals(EnumSet.of(
                ShapePoint.P_0_0,
                ShapePoint.P_0_1,
                ShapePoint.P_0_2,
                ShapePoint.P_1_0,
                ShapePoint.P_1_1), p.points());
        assertEquals(EnumSet.of(
                ShapePoint.P_0_0,
                ShapePoint.P_0_1,
                ShapePoint.P_1_0,
                ShapePoint.P_1_1,
                ShapePoint.P_2_0), inverted.points());
    }

    @Test
    void testRotate() {
        Blob p = Shape.LETTER_P.getBlobs().get(0);
        Blob rotated = Shape.rotate(p);
        assertEquals(EnumSet.of(
                ShapePoint.P_0_0,
                ShapePoint.P_0_1,
                ShapePoint.P_0_2,
                ShapePoint.P_1_0,
                ShapePoint.P_1_1), p.points());
        assertEquals(EnumSet.of(
                ShapePoint.P_0_0,
                ShapePoint.P_0_1,
                ShapePoint.P_1_0,
                ShapePoint.P_1_1,
                ShapePoint.P_2_1), rotated.points());
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
                assertFalse(Shape.contains(test, blob));
                test.add(blob);
            }
        }
    }

    @Test
    void testVolume() {
        for (Shape shape : Shape.values()) {
            List<Blob> blobs = shape.getBlobs();
            for (Blob blob : blobs) {
                assertEquals(5, blob.points().size());
            }
        }
    }
}