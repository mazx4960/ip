package duke;

import duke.exceptions.InvalidTaskException;
import duke.tasks.Task;

import java.util.ArrayList;

public class DukeList {
    private static final int CAPACITY = 100;

    private final Storage storage;
    private final ArrayList<Task> taskList;

    /**
     * Constructor for Duke.DukeList
     */
    public DukeList() {
        this.storage = new Storage();

        if (storage.fileExist()) {
            this.taskList = storage.readData();
        } else {
            this.taskList = new ArrayList<>(CAPACITY);
        }

    }

    /**
     * Add a new item to the list.
     *
     * @param taskString string representation of the task
     */
    public String add(String taskString) throws InvalidTaskException {
        if (taskList.size() > CAPACITY) throw new InvalidTaskException("Capacity has been reached");

        Task task = Task.of(taskString);
        this.taskList.add(task);
        this.storage.writeData(taskList);

        return "Got it. I've added this task:\n"
                + String.format("\t%s\n", task)
                + String.format("Now you have %d tasks in the list.", this.taskList.size());
    }

    /**
     * Mark the task as completed given a task index.
     *
     * @param idx index of the task in the taskList
     * @return status string
     */
    public String markTask(int idx) throws IndexOutOfBoundsException {
        Task task = this.taskList.get(idx - 1);
        task.markAsCompleted();
        this.storage.writeData(taskList);

        return "Nice! I've marked this task as done:\n"
                + String.format("\t%s", task);
    }

    /**
     * Mark the task as uncompleted given a task index.
     *
     * @param idx idx index of the task in the taskList
     * @return status string
     */
    public String unmarkTask(int idx) throws IndexOutOfBoundsException {
        Task task = this.taskList.get(idx - 1);
        task.markAsUncompleted();
        this.storage.writeData(taskList);

        return "OK, I've marked this task as not done yet:\n"
                + String.format("\t%s", task);
    }

    /**
     * Delete the task specified.
     *
     * @param idx idx index of the task in the taskList
     * @return status string
     * @throws IndexOutOfBoundsException index given is out of bounds
     */
    public String delete(int idx) throws IndexOutOfBoundsException {
        Task task = this.taskList.remove(idx - 1);
        this.storage.writeData(taskList);

        return "Noted. I've removed this task:\n"
                + String.format("\t%s\n", task)
                + String.format("Now you have %d Duke.tasks in the list.", this.taskList.size());
    }

    @Override
    public String toString() {
        int size = this.taskList.size();
        StringBuilder result;

        if (size == 0) {
            result = new StringBuilder("List is empty. Add items to the list.");
        } else {
            result = new StringBuilder();
            for (int i = 0; i < size; i++) {
                result.append(String.format("%d. %s\n", i + 1, this.taskList.get(i)));
            }
        }

        return result.toString();
    }
}
