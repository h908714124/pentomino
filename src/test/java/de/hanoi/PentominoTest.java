package de.hanoi;

import org.junit.jupiter.api.Test;

import java.util.List;

class PentominoTest {

    private final Pentomino pentomino = Pentomino.create();

    @Test
    void testPrint() {
        pentomino.addFigure(Shape.CORNER.getBlob(), 0, 0);
        List<String> strings = pentomino.print();
        for (String string : strings) {
            System.out.println(string);
        }
    }
}