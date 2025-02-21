package geng.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        Task task = new ToDos("Test task");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskList().size());
    }

    @Test
    public void testRemoveTask() {
        Task task = new ToDos("Test task");
        taskList.addTask(task);
        taskList.removeTask(0);
        assertEquals(0, taskList.getTaskList().size());
    }

    @Test
    public void testGetTask() {
        Task task = new ToDos("Test task");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(0));
    }
}
