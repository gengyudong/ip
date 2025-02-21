package geng.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDosTest {

    @Test
    public void testTodoCreation() {
        ToDos todo = new ToDos("Read book");
        assertEquals("Read book", todo.getDescription());
    }

    @Test
    public void testTodoToString() {
        ToDos todo = new ToDos("Read book");
        assertEquals("T | 0 | Read book", todo.toString());
    }
}
