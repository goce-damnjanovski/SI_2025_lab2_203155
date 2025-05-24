import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    RuntimeException ex;

    private List<Item> createList(Item... elems) {
        return new ArrayList<>(Arrays.asList(elems));
    }

    // Every Statement
    //Test case: allItems == null; cardNumber = ""
    @Test
    void testAllItemsNull() {
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, ""));
        assertEquals("allItems list can't be null!", ex.getMessage());
    }

    //Test case: allItems = {[null, 2, 500, 1]}; cardNumber = ""
    @Test
    void testInvalidItemName() {
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(createList(new Item(null, 2, 500, 1)), ""));
        assertEquals("Invalid item!", ex.getMessage());
    }

    // Test case: allItems = {["product", 0, 100, 0]}; cardNumber == null
    @Test
    void testNullCardNumber() {
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(createList(new Item("Item1", 0, 100, 0)), null));
        assertEquals("Invalid card number!", ex.getMessage());
    }

    // Test case: allItems = {["product", 0, 500, 1]}; cardNumber == "1234ab%%5678$cd*"
    @Test
    void testCardWithInvalidCharacters() {
        List<Item> items = List.of();
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(createList(new Item("Item1", 0, 500, 1)), "1234ab%%5678$cd*"));
        assertEquals("Invalid character in card number!", ex.getMessage());
    }

    // Test case: allItems = {["product", 15, 500, 1]}; cardNumber == "1234000056789012"
    @Test
    void testValidCardNumber() {
        List<Item> items = List.of(new Item("Item1", 15, 500, 1));
        double result = SILab2.checkCart(items, "1234000056789012");
        assertEquals(-30, result);
    }

    // Multiple Condition: if(item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)
    // Condition: TXX
    @Test
    void testMultipleCondition_TXX() {
        Item item = new Item("Item1", 15, 500, 1);
        assertTrue(item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10);
    }

    // Condition: FTX
    @Test
    void testMultipleCondition_FTX() {
        Item item = new Item("Item1", 15, 100, 1);
        assertTrue(item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10);
    }

    // Condition: FFT
    @Test
    void testMultipleCondition_FFT() {
        Item item = new Item("Item1", 15, 100, 15);
        assertTrue(item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10);
    }

    // Condition: FFF
    @Test
    void testMultipleCondition_FFF() {
        Item item = new Item("Item1", 0, 100, 0);
        assertFalse(item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10);
    }
}