package tetris.model;

import java.awt.Color;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.model.bricks.OBrick;

public class LayerTest {

    public LayerTest() {
    }

    @Test
    public void testGetRowsCount() {
        Layer instance = new Layer(3, 5);
        int result = instance.getRowsCount();
        assertEquals(3, result);
    }

    @Test
    public void testGetColumnsCount() {
        Layer instance = new Layer(3, 5);
        int result = instance.getColumnsCount();
        assertEquals(5, result);
    }

    @Test
    public void testSetField() {
        Layer instance = new Layer(10, 5);
        instance.setField(0, 0, Color.green);
        assertTrue(instance.getField(0, 0) == Color.green);
        instance.setField(9, 4, Color.yellow);
        assertTrue(instance.getField(9, 4) == Color.yellow);
    }

    @Test
    public void testSetFieldWithTooHighValue() {
        Layer instance = new Layer(10, 5);
        instance.setField(100, 200, Color.green);
        Layer expResult = new Layer(10, 5);
        assertEquals(instance, expResult);
    }

    @Test
    public void testSetFieldWithTooLowValue() {
        Layer instance = new Layer(10, 5);
        instance.setField(-100, -200, Color.green);
        Layer expResult = new Layer(10, 5);
        assertEquals(instance, expResult);
    }

    @Test
    public void testGetFieldWithCorrectData() {
        Layer instance = new Layer(4, 4);
        Color result = instance.getField(0, 0);
        assertEquals(Layer.getDefaultColor(), result);
    }

    @Test
    public void testGetFieldWithExcessiveData() {
        Layer instance = new Layer(4, 4);
        Color result = instance.getField(4, 4);
        assertNull(result);
    }

    @Test
    public void testGetFieldWithInsufficientData() {
        Layer instance = new Layer(4, 4);
        Color result = instance.getField(-1, -1);
        assertNull(result);
    }

    @Test
    public void testGetMovedDownLayer() {
        Layer instance = new Layer(3, 5);
        instance.setField(0, 2, Color.yellow);
        Layer result = instance.getMovedDown();

        Layer expResult = new Layer(3, 5);
        expResult.setField(1, 2, Color.yellow);

        assertEquals(expResult, result);
    }

    @Test
    public void testEquals() {
        Object obj = new Layer(3, 5);
        Layer instance = new Layer(3, 5);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testReset() {
        Layer actual = new Layer(2, 2);
        actual.setField(0, 0, Color.yellow);
        actual.reset();

        Layer expected = new Layer(2, 2);

        assertEquals(expected, actual);
    }

    @Test
    public void testPut() {
        boolean[][] blocks = {{false, false, false}, {false, true, true}};
        Color blockColor = Color.yellow;
        Layer actual = new Layer(4, 4);
        actual.put(blocks, blockColor);

        Layer expected = new Layer(4, 4);
        expected.setField(1, 1, blockColor);
        expected.setField(1, 2, blockColor);

        assertEquals(expected, actual);
    }

    @Test
    public void testTryToDestroyLine_NothingToDestroy() {
        Layer instance = new Layer(4, 4);
        boolean result = instance.tryToDestroyLine();
        assertFalse(result);
    }

    @Test
    public void testTryToDestroyLine_LineToDestroyExists() {
        Layer instance = new Layer(3, 3);
        boolean[][] blocks = {{true, false, false}, {true, true, true}};
        instance.put(blocks, Color.black);
        boolean result = instance.tryToDestroyLine();
        assertTrue(result);
    }

    @Test
    public void testTryToDestroyLine() {
        Layer actual = new Layer(3, 3);
        actual.setField(0, 0, Color.yellow);
        actual.setField(1, 0, Color.yellow);
        actual.setField(1, 1, Color.yellow);
        actual.setField(1, 2, Color.yellow);
        actual.setField(2, 1, Color.yellow);
        actual.tryToDestroyLine();
        
        Layer expected = new Layer(3,3);
        expected.setField(1, 0, Color.yellow);
        expected.setField(2, 1, Color.yellow);
        
        assertEquals(expected, actual);
    }

    @Test
    public void testOverlapsWith_isOverlapping() {
        Layer actual = new Layer(3, 3);
        actual.setField(1, 1, Color.yellow);
        FallingBrick brick = new FallingBrick();
        brick.setBrick(new OBrick(), 1);
        boolean result = actual.overlapsWith(brick);
        System.out.println(actual.getField(1, 1));
        System.out.println(brick.getColor(1, 1));
        System.out.println(brick.getColor(2, 2));
        assertTrue(result);
    }

    public void testIsFullField_fieldIsFull() {
        Layer instance = new Layer(2, 2);
        instance.setField(1, 1, Color.yellow);
        boolean result = instance.isOccupied(1, 1);
        assertTrue(result);
    }

    public void testIsFullField_fieldIsEmpty() {
        Layer instance = new Layer(2, 2);
        boolean result = instance.isOccupied(1, 1);
        assertFalse(result);
    }

    @Test
    public void testIsOnBottom_isOnBottom() {
        Layer instance = new Layer(2, 2);
        instance.setField(1, 1, Color.yellow);
        boolean result = instance.isOnBottom();
        assertTrue(result);
    }

    @Test
    public void testIsOnBottom_nothingIsOnBottom() {
        Layer instance = new Layer(2, 2);
        boolean result = instance.isOnBottom();
        assertFalse(result);
    }

    @Test
    public void testIsOnTop_isOnTop() {
        Layer instance = new Layer(2, 2);
        instance.setField(0, 0, Color.yellow);
        boolean result = instance.isOnTop();
        assertTrue(result);
    }

    @Test
    public void testIsOnTop_nothingIsOnTop() {
        Layer instance = new Layer(2, 2);
        boolean result = instance.isOnTop();
        assertFalse(result);
    }

    @Test
    public void testMoveRight_canMove() {
        Layer actual = new Layer(2, 3);
        actual.setField(0, 0, Color.yellow);
        Layer result = actual.getMovedRight();
        Layer expected = new Layer(2, 3);
        expected.setField(0, 1, Color.yellow);
        assertEquals(expected, result);
    }

    @Test
    public void testMoveLeft_canMove() {
        Layer actual = new Layer(2, 3);
        actual.setField(0, 2, Color.yellow);
        Layer result = actual.getMovedLeft();
        Layer expected = new Layer(2, 3);
        expected.setField(0, 1, Color.yellow);
        assertEquals(expected, result);
    }

    @Test
    public void testMoveRight_cannotMove() {
        Layer actual = new Layer(2, 3);
        actual.setField(0, 2, Color.yellow);
        actual.getMovedRight();
        Layer expected = new Layer(2, 3);
        expected.setField(0, 2, Color.yellow);
        assertEquals(expected, actual);
    }

}
