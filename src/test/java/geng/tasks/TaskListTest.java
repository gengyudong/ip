package geng.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTask_validTask_taskAdded() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new ToDos("Read book");

        taskList.addTask(task);

        assertEquals(1, taskList.size());
        assertEquals("Read book", taskList.getTask(0).getDescription());
    }

    @Test
    public void deleteTask_existingIndex_taskRemoved() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new ToDos("Write report");
        taskList.addTask(task);

        Task removedTask = taskList.getTask(0);
        taskList.removeTask(0);

        assertEquals(0, taskList.size());
        assertEquals("Write report", removedTask.getDescription());
    }
}
