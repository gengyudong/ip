package tasks;

import duke.tasks.Task;
import duke.tasks.ToDos;
import duke.tasks.TaskList;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
