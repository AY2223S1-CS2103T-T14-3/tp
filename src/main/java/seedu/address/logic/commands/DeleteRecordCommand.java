package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Record;

/**
 * Deletes a record identified using it's displayed index from the displayed record list.
 */
public class DeleteRecordCommand extends Command {

    public static final String COMMAND_WORD = "deleteR";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the record identified by the index number used in the displayed record list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Record: %1$s";

    private final Index targetIndex;

    public DeleteRecordCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Record> lastShownList = model.getFilteredRecordList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Record recordToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteRecord(recordToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, recordToDelete),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRecordCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteRecordCommand) other).targetIndex)); // state check
    }
}
