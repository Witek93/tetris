package tetris.model;

import java.awt.Color;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wit
 */
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
        assertTrue(instance.getField(0,0) == Color.green);
        instance.setField(9, 4, Color.yellow);
        assertTrue(instance.getField(9,4) == Color.yellow);
    }

    @Test
    public void testOverlapsWithEmptyLayers() {
        Layer layer = new Layer(3, 5);
        Layer instance = new Layer(3, 5);
        boolean expResult = false;
        boolean result = instance.overlapsWith(layer);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testOverlapsWithExactLayers() {
        Layer layer = new Layer(3, 5);
        layer.setField(1, 2, Color.yellow);
        layer.setField(2, 4, Color.yellow);
        
        Layer instance = new Layer(3, 5);
        instance.setField(1, 2, Color.yellow);
        instance.setField(2, 4, Color.yellow);
        
        boolean expResult = true;
        boolean result = instance.overlapsWith(layer);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetField() {
        Layer instance = new Layer(10, 5);
        Color color = Color.yellow;
        instance.setField(2, 3, color);
        Color expResult = color;
        Color result = instance.getField(2, 3);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovedDownLayer() {
        Layer instance = new Layer(3, 5);
        instance.setField(0, 2, Color.yellow);
        Layer result = instance.getMovedDownLayer();
        
        Layer expResult = new Layer(3, 5);
        expResult.setField(1, 2, Color.yellow);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals() {
        Object obj = new Layer(3,5);
        Layer instance = new Layer(3,5);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

}
