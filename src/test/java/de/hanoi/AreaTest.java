package de.hanoi;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AreaTest {

    private final Pentomino board = Pentomino.create();

    @Test
    void testFits() {
        board.addFigure(Shape.ANGLE.getBlobs().get(0), Point.P_0_0);
        assertFalse(board.overlaps(Shape.STAR.getBlobs().get(0), Point.P_2_0));
        assertTrue(board.overlaps(Shape.STAR.getBlobs().get(0), Point.P_1_0));
    }

    @Test
    void testConnectedArea1() {
        board.addFigure(Shape.STAR.getBlobs().get(0), Point.P_0_0);
        EnumSet<Point> area = board.findConnectedArea(Point.P_0_0);
        assertEquals(EnumSet.of(Point.P_0_0), area);
    }

    @Test
    void testConnectedArea2() {
        board.addFigure(Shape.STAR.getBlobs().get(0), Point.P_0_0);
        EnumSet<Point> area = board.findConnectedArea(Point.P_0_4);
        assertEquals(54, area.size());
    }

    @Test
    void testConnectedArea3() {
        board.addFigure(Shape.STAR.getBlobs().get(0), Point.P_0_0);
        board.addFigure(Shape.STAR.getBlobs().get(0), Point.P_0_3);
        EnumSet<Point> area = board.findConnectedArea(Point.P_0_2);
        assertEquals(EnumSet.of(Point.P_0_2, Point.P_0_3), area);
    }
}