package pt.ipp.isep.dei.esoft.project.application.controller;



import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.repository.ToDoListRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Optional;

/**
 * The {@code ToDoListController} class represents a controller responsible for managing
 * the creation and retrieval of to-do list entries in the application.
 * It interacts with the to-do list repository to add new entries and retrieve existing ones.
 */
public class ToDoListController {

    private final ToDoListRepository toDoListRepository;

    /**
     * Constructs a new {@code ToDoListController} object.
     * Initializes the to-do list repository.
     */
    public ToDoListController() {
        Repositories repositories = Repositories.getInstance();
        this.toDoListRepository = repositories.getToDoListRepository();
    }

    /**
     * Creates a new to-do list entry with the provided information.
     * Adds the entry to the repository if it is valid and does not already exist.
     *
     * @param taskDescription  The description of the task.
     * @param urgency          The urgency level of the task.
     * @param expectedDuration The expected duration of the task.
     * @return An optional containing the newly created to-do list entry, if successful.
     */
    public Optional<ToDoList> createToDoListEntry(String taskDescription, String urgency, int expectedDuration, String greenspaceName, String status) {
        ToDoList toDoList = new ToDoList(taskDescription, urgency, expectedDuration, greenspaceName, status);
        return toDoListRepository.add(toDoList);
    }

    /**
     * Retrieves a list of all to-do list entries.
     *
     * @return A list of to-do list entries.
     */

    public void updateToDoListStatus(String taskDescription, String newStatus) {
        List<ToDoList> toDoListEntries = ToDoListRepository.getAll();
        for (ToDoList toDoListEntry : toDoListEntries) {
            if (toDoListEntry.getTaskDescription().equals(taskDescription)) {
                toDoListEntry.setStatus(newStatus);
                ToDoListRepository.updateToDoListEntry(toDoListEntry);
                break;
            }
        }
    }

    public List<ToDoList> getAllToDoListEntries() {
        return ToDoListRepository.getAllToDoLists();
    }

}

