package de.hanoi;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PrintTest {

    private final Pentomino board = Pentomino.create();

    @Test
    @Disabled
    void testPrint() {
        board.addFigure(Shape.ANGLE.getBlobs().get(0), Point.P_0_1);
        System.out.println(board);
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