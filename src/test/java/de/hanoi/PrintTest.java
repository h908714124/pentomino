package de.hanoi;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrintTest {

    private final Pentomino board = Pentomino.create();

    @Test
    @Disabled
    void testPrint() {
        board.addFigure(Shape.ANGLE.getBlobs().get(0), 0, 0);
        List<String> strings = board.print();
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    void testFits() {
        board.addFigure(Shape.ANGLE.getBlobs().get(0), 0, 0);
        assertFalse(board.overlaps(Shape.STAR.getBlobs().get(0), 2, 0));
        assertTrue(board.overlaps(Shape.STAR.getBlobs().get(0), 1, 0));
    }

    @Test
    @Disabled
    void printAllShapes() {
        for (Shape shape : Shape.values()) {
            for (Blob blob : shape.getBlobs()) {
                System.out.println(blob);
                System.out.println();
            }
        }
    }
}