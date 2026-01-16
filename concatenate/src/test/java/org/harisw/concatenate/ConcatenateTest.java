package org.harisw.concatenate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


public class ConcatenateTest {

    @Test
    void testExtractCommonElementsWithCommonElements() {
        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> list2 = List.of(3, 4, 5, 6);
        List<Integer> result = Concatenate.extractCommonElements(list1, list2);
        
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertEquals(2, result.size());
    }

    @Test
    void testExtractCommonElementsNoCommon() {
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(4, 5, 6);
        List<Integer> result = Concatenate.extractCommonElements(list1, list2);
        
        assertTrue(result.isEmpty());
    }

    @Test
    void testExtractCommonElementsFirstAndLastMatch() {
        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> list2 = List.of(1, 5, 6, 4);
        List<Integer> result = Concatenate.extractCommonElements(list1, list2);
        
        assertTrue(result.contains(1));
        assertTrue(result.contains(4));
    }

    @Test
    void testMainWithValidArguments() {
        assertDoesNotThrow(() -> Concatenate.main(new String[]{"1,2,3", "2,3,4"}));
    }

    @Test
    void testMainWithInsufficientArguments() {
        assertThrows(UnsupportedOperationException.class, () -> Concatenate.main(new String[]{"1,2,3"}));
    }

    @Test
    void testMainWithNoArguments() {
        assertThrows(UnsupportedOperationException.class, () -> Concatenate.main(new String[]{}));
    }
}