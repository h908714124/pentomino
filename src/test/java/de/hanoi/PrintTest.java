package de.hanoi;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

@Disabled
class PrintTest {

    private final Pentomino pentomino = Pentomino.create();

    @Test
    void testPrint() {
        pentomino.addFigure(Shape.ANGLE.getBlob(), 0, 0);
        List<String> strings = pentomino.print();
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    void printAllShapes() {
        for (Shape shape : Shape.values()) {
            for (Blob blob : shape.getBlobs()) {
                System.out.println(blob);
                System.out.println();
            }
        }
    }
}