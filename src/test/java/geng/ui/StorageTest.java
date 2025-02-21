package geng.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geng.tasks.TaskList;
import geng.tasks.ToDos;

public class StorageTest {
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        storage = new Storage("data/test_storage.txt");
        taskList = new TaskList();
        taskList.addTask(new ToDos("Test task"));
    }

    @Test
    public void testSaveAndLoad() {
        try {
            storage.saveTasksToFile(taskList.getTaskList());
            TaskList loadedTaskList = new TaskList(storage.load());
            assertEquals(taskList.getTaskList().size(), loadedTaskList.getTaskList().size());
        } catch (GengException e) {
            fail("Exception should not be thrown");
        }
    }
}
