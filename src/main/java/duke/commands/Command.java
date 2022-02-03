package duke.commands;

import duke.DukeList;
import duke.TextUI;
import duke.exceptions.InvalidTaskException;

public abstract class Command {
    /**
     * Checks if the command is a terminating one.
     *
     * @return boolean whether to terminate
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Function to execute the command.
     *
     * @param dukeList dukeList object
     * @param ui ui object
     * @throws InvalidTaskException invalid task
     */
    public void execute(DukeList dukeList, TextUI ui) throws InvalidTaskException {
        ui.printMsg(getResult(dukeList));
    }

    /**
     * Function to execute the command and get the result.
     * @param dukeList dukeList object
     * @throws InvalidTaskException invalid task
     */
    public abstract String getResult(DukeList dukeList) throws InvalidTaskException;
}
