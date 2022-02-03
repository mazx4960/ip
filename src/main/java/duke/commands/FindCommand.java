package duke.commands;

import duke.DukeList;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor
     *
     * @param keyword index of the task
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Function to execute the command and get the result.
     *
     * @param dukeList dukeList object
     */
    @Override
    public String getResult(DukeList dukeList) {
        return dukeList.find(keyword);
    }
}
