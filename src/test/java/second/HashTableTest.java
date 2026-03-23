package second;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static second.HashTable.Point.*;
import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    private HashTable<Integer> table;

    @BeforeEach
    void setUp() {
        table = new HashTable<>();
    }

    @Test
    @DisplayName("Вставка в пустую корзину")
    void testInsertEmpty() {
        table.insert(5);
        List<HashTable.Point> expected = Arrays.asList(INSERT_START, HASH_CALCULATED, NODE_ADDED, INSERT_END);
        assertEquals(expected, table.getTrace());
    }

    @Test
    @DisplayName("Вставка с коллизией")
    void testInsertCollision() {
        table.insert(5);
        table.clearTrace();

        table.insert(18);
        List<HashTable.Point> expected = Arrays.asList(INSERT_START, HASH_CALCULATED, BUCKET_NOT_EMPTY, NODE_ADDED, INSERT_END);
        assertEquals(expected, table.getTrace());
    }

    @Test
    @DisplayName("Успешный поиск (голова цепочки)")
    void testSearchFoundHead() {
        table.insert(5);
        table.clearTrace();

        assertTrue(table.search(5));
        List<HashTable.Point> expected = Arrays.asList(SEARCH_START, HASH_CALCULATED, NODE_FOUND, SEARCH_END);
        assertEquals(expected, table.getTrace());
    }

    @Test
    @DisplayName("Успешный поиск (внутри цепочки)")
    void testSearchFoundInternal() {
        table.insert(5);
        table.insert(18);
        table.clearTrace();

        assertTrue(table.search(5));
        List<HashTable.Point> expected = Arrays.asList(SEARCH_START, HASH_CALCULATED, NODE_FOUND, SEARCH_END);
        assertEquals(expected, table.getTrace());
    }

    @Test
    @DisplayName("Поиск несуществующего элемента (пустая корзина)")
    void testSearchNotFoundEmpty() {
        assertFalse(table.search(6));
        List<HashTable.Point> expected = Arrays.asList(SEARCH_START, HASH_CALCULATED, NODE_NOT_FOUND, SEARCH_END);
        assertEquals(expected, table.getTrace());
    }

    @Test
    @DisplayName("Поиск несуществующего элемента")
    void testSearchNotFoundCollision() {
        table.insert(5);
        table.clearTrace();

        assertFalse(table.search(18));
        List<HashTable.Point> expected = Arrays.asList(SEARCH_START, HASH_CALCULATED, NODE_NOT_FOUND, SEARCH_END);
        assertEquals(expected, table.getTrace());
    }

    @Test
    @DisplayName("Удаление головы цепочки")
    void testDeleteHead() {
        table.insert(5);
        table.insert(18);
        table.clearTrace();

        table.delete(18);
        List<HashTable.Point> expected = Arrays.asList(DELETE_START, HASH_CALCULATED, NODE_DELETED_HEAD, DELETE_END);
        assertEquals(expected, table.getTrace());
    }

    @Test
    @DisplayName("Удаление внутреннего элемента цепочки")
    void testDeleteInternal() {
        table.insert(5);
        table.insert(18);
        table.clearTrace();

        table.delete(5);
        List<HashTable.Point> expected = Arrays.asList(DELETE_START, HASH_CALCULATED, NODE_DELETED_INTERNAL, DELETE_END);
        assertEquals(expected, table.getTrace());
    }

    @Test
    @DisplayName("Удаление несуществующего элемента")
    void testDeleteNotFoundEmpty() {
        table.delete(6);
        List<HashTable.Point> expected = Arrays.asList(DELETE_START, HASH_CALCULATED, NODE_NOT_FOUND_FOR_DELETE, DELETE_END);
        assertEquals(expected, table.getTrace());
    }





}