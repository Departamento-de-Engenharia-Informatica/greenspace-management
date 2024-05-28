package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code ToDoListRepository} class represents a repository for managing to-do lists.
 * It stores to-do list objects and provides methods to add new entries and perform validation checks.
 */
public class ToDoListRepository {

    private static List<ToDoList> toDoLists = new ArrayList<>();

    /**
     * Constructs a new {@code ToDoListRepository} object.
     */
    public ToDoListRepository() {
        // No need to initialize the list here, it's already done in the declaration
    }

    /**
     * Adds a new entry to the to-do list repository.
     *
     * @param toDoList The to-do list entry to add.
     * @return An optional containing the added to-do list entry if successful.
     */
    public Optional<ToDoList> add(ToDoList toDoList) {
        Optional<ToDoList> newToDoList = Optional.of(toDoList.clone());
        toDoLists.add(newToDoList.get());
        return newToDoList;
    }

    /**
     * Retrieves a list of all to-do list entries in the repository.
     *
     * @return A list of to-do list entries.
     */
    public static List<ToDoList> getAllToDoLists() {
        // Defensive copy to prevent modification from outside
        return List.copyOf(toDoLists);
    }

    /**
     * Updates an existing to-do list entry in the repository.
     *
     * @param updatedEntry The updated to-do list entry.
     */
    public static void updateToDoListEntry(ToDoList updatedEntry) {
        for (int i = 0; i < toDoLists.size(); i++) {
            if (toDoLists.get(i).getTaskDescription().equals(updatedEntry.getTaskDescription())) {
                toDoLists.set(i, updatedEntry);
                break;
            }
        }
    }

    /**
     * Retrieves a list of all to-do list entries in the repository.
     *
     * @return A list of to-do list entries.
     */
    public static List<ToDoList> getAll() {
        return new ArrayList<>(toDoLists);
    }

    /**
     * Finds a to-do list entry by task description.
     *
     * @param taskDescription The task description.
     * @return An optional containing the to-do list entry if found.
     */
    public Optional<ToDoList> findByTaskDescription(String taskDescription) {
        return toDoLists.stream()
                .filter(toDoList -> toDoList.getTaskDescription().equals(taskDescription))
                .findFirst();
    }

    /**
     * Retrieves a list of to-do list entries by user email and green space name.
     *
     * @param userEmail The user's email.
     * @param greenSpaceName The green space name.
     * @return A list of to-do list entries.
     */
    public List<ToDoList> findByUserEmailAndGreenSpaceName(String userEmail, String greenSpaceName) {
        List<ToDoList> filteredToDoLists = new ArrayList<>();
        for (ToDoList toDoList : toDoLists) {
            if (toDoList.getUserEmail().equals(userEmail) && toDoList.getGreenspaceName().equals(greenSpaceName)) {
                filteredToDoLists.add(toDoList);
            }
        }
        return filteredToDoLists;
    }
}
