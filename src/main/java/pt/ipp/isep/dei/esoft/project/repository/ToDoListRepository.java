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

    private static List<ToDoList> toDoLists;

    /**
     * Constructs a new {@code ToDoListRepository} object.
     * Initializes the list of to-do lists.
     */
    public ToDoListRepository() {
        toDoLists = new ArrayList<>();
    }

    /**
     * Adds a new entry to the to-do list repository.
     *
     * @param toDoList The to-do list entry to add.
     * @return An optional containing the added to-do list entry if successful.
     */
    public Optional<ToDoList> add(ToDoList toDoList) {
        Optional<ToDoList> newToDoList = Optional.empty();
        newToDoList = Optional.of(toDoList.clone());
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
     * Retrieves a list of all to-do list entries in the repository.
     *
     * @return A list of to-do list entries.
     */
    public List<ToDoList> getAll() {
        return new ArrayList<>(toDoLists);
    }
}
