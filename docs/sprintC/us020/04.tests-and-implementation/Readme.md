Claro, aqui está o template atualizado com todas as classes e testes fornecidos:

# US020 - Register a GreenSpace

## 4. Tests

### GreenSpaceRepositoryTest

**Test 1:** Clear the list of GreenSpace objects in the repository before each test.

```java
@Before
public void setUp() {
    GreenSpaceRepository.getGreenSpaces().clear();
}
```

**Test 2:** Clear the list of GreenSpace objects in the repository after each test.

```java
@After
public void tearDown() {
    GreenSpaceRepository.getGreenSpaces().clear();
}
```

**Test 3:** Add a GreenSpace object to the repository and retrieve it.

```java
@Test
public void testAddAndGetGreenSpaces() {
    GreenSpace greenSpace = new Garden("Central Park", 500.0, "user@example.com");
    GreenSpaceRepository.addGreenSpace(greenSpace);
    List<GreenSpace> greenSpaces = GreenSpaceRepository.getGreenSpaces();
    assertEquals(1, greenSpaces.size());
    assertEquals(greenSpace, greenSpaces.get(0));
}
```

**Test 4:** Retrieve GreenSpace objects when the repository is empty.

```java
@Test
public void testGetGreenSpaces_EmptyList() {
    List<GreenSpace> greenSpaces = GreenSpaceRepository.getGreenSpaces();
    assertTrue(greenSpaces.isEmpty());
}
```

**Test 5:** Add a null GreenSpace object to the repository.

```java
@Test
public void testAddGreenSpace_Null() {
    GreenSpace greenSpace = null;
    assertThrows(IllegalArgumentException.class, () -> {
        GreenSpaceRepository.addGreenSpace(greenSpace);
    });
}
```

**Test 6:** Add a duplicate GreenSpace object to the repository.

```java
@Test
public void testAddGreenSpace_Duplicate() {
    GreenSpace greenSpace1 = new Garden("Central Park", 500.0, "user1@example.com");
    GreenSpaceRepository.addGreenSpace(greenSpace1);

    GreenSpace greenSpace2 = new Garden("Central Park", 500.0, "user1@example.com");
    assertThrows(IllegalArgumentException.class, () -> {
        GreenSpaceRepository.addGreenSpace(greenSpace2);
    });
}
```

**Test 7:** Add an invalid GreenSpace object to the repository.

```java
@Test
public void testAddGreenSpace_InvalidGreenSpace() {
    assertThrows(IllegalArgumentException.class, () -> {
        GreenSpace greenSpace = new Garden("", -500.0, "invalid-email");
        GreenSpaceRepository.addGreenSpace(greenSpace);
    });
}
```

### GreenSpaceTest

**Test 1:** Get the name of the GreenSpace.

```java
@Test
public void testGetName() {
    GreenSpace greenSpace = new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "test@example.com");
    String name = greenSpace.getName();
    assertEquals("Test", name);
}
```

**Test 2:** Get the area of the GreenSpace.

```java
@Test
public void testGetArea() {
    GreenSpace greenSpace = new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "test@example.com");
    double area = greenSpace.getArea();
    assertEquals(100.0, area, 0.001);
}
```

**Test 3:** Get the type of the GreenSpace.

```java
@Test
public void testGetType() {
    GreenSpace greenSpace = new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "test@example.com");
    GreenSpaceType type = greenSpace.getType();
    assertEquals(GreenSpaceType.GARDEN, type);
}
```

**Test 4:** Get the email of the GreenSpace.

```java
@Test
public void testGetEmail() {
    GreenSpace greenSpace = new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "test@example.com");
    String email = greenSpace.getEmail();
    assertEquals("test@example.com", email);
}
```

**Test 5:** Create a GreenSpace with a negative area.

```java
@Test
public void testNegativeArea() {
    assertThrows(IllegalArgumentException.class, () -> {
        new TestGreenSpace("Test", -100.0, GreenSpaceType.GARDEN, "test@example.com");
    });
}
```

**Test 6:** Create a GreenSpace with zero area.

```java
@Test
public void testZeroArea() {
    assertThrows(IllegalArgumentException.class, () -> {
        new TestGreenSpace("Test", 0.0, GreenSpaceType.GARDEN, "test@example.com");
    });
}
```

**Test 7:** Create a GreenSpace with an empty name.

```java
@Test
public void testEmptyName() {
    assertThrows(IllegalArgumentException.class, () -> {
        new TestGreenSpace("", 100.0, GreenSpaceType.GARDEN, "test@example.com");
    });
}
```

**Test 8:** Create a GreenSpace with a null name.

```java
@Test
public void testNullName() {
    assertThrows(IllegalArgumentException.class, () -> {
        new TestGreenSpace(null, 100.0, GreenSpaceType.GARDEN, "test@example.com");
    });
}
```

**Test 9:** Create a GreenSpace with an invalid email.

```java
@Test
public void testInvalidEmail() {
    assertThrows(IllegalArgumentException.class, () -> {
        new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "invalid-email");
    });
}
```

**Test 10:** Create a GreenSpace with a null email.

```java
@Test
public void testNullEmail() {
    assertThrows(IllegalArgumentException.class, () -> {
        new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, null);
    });
}
```

**Test 11:** Create a GreenSpace with a null type.

```java
@Test
public void testNullType() {
    assertThrows(IllegalArgumentException.class, () -> {
        new TestGreenSpace("Test", 100.0, null, "test@example.com");
    });
}
```

## 5. Construction (Implementation)

### Class RegisterGreenSpaceUI

```java
package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

import java.util.List;
import java.util.Scanner;

/**
 * User interface for registering green spaces.
 */
public class RegisterGreenSpaceUI implements Runnable {

    private final Scanner scanner;
    private final String userEmail;

    /**
     * Constructs a RegisterGreenSpaceUI object with the specified user email.
     *
     * @param userEmail the email of the logged-in user
     */
    public RegisterGreenSpaceUI(String userEmail) {
        this.scanner = new Scanner(System.in);
        this.userEmail = userEmail;
    }

    /**
     * Runs the green space registration user interface.
     */
    public void run() {
        System.out.println("\n--- Green Space Management ---");
        System.out.println("Logged in as: " + userEmail); // Print the logged-in user's email
        System.out.print("Enter name of the green space: ");
        String name = scanner.nextLine();

        System.out.print("Enter area of the green space in square meters: ");
        double area = Double.parseDouble(scanner.nextLine());

        System.out.println("Select type of green space:");
        System.out.println("1. Garden");
        System.out.println("2. Medium-Sized Park");
        System.out.println("3. Large-Sized Park");
        int typeChoice = Integer.parseInt(scanner.nextLine());
        GreenSpaceType type;

        switch (typeChoice) {
            case 1:
                type = GreenSpaceType.GARDEN;
                break;
            case 2:
                type = GreenSpaceType.MEDIUM_SIZED_PARK;
                break;
            case 3:
                type = GreenSpaceType.LARGE_SIZED_PARK;
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        RegisterGreenSpaceController.registerGreenSpace(name, area, type, userEmail);
        System.out.println("Green space registered successfully.");

        // Display all registered green spaces
        System.out.println("\n--- Registered Green Spaces ---");
        List<GreenSpace> greenSpaces = RegisterGreenSpaceController.getAllGreenSpaces();
        for (GreenSpace greenSpace : greenSpaces) {
            greenSpace.displayDetails();
        }
    }
}
```

### Class GreenSpaceRepository

```java
package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing GreenSpace objects.
 */
public class GreenSpaceRepository {
    private static List<GreenSpace> greenSpaces = new ArrayList<>();

    /**
     * Adds a GreenSpace object to the repository.
     *
     * @param greenSpace the GreenSpace object to add
     * @throws IllegalArgumentException if the provided GreenSpace is null or if a duplicate GreenSpace is being added
     */
    public static void addGreenSpace(GreenSpace greenSpace) {
        if (greenSpace == null) {
            throw new IllegalArgumentException("GreenSpace cannot be null");
        }
        if (containsGreenSpace

(greenSpace)) {
            throw new IllegalArgumentException("Duplicate GreenSpace cannot be added");
        }
        greenSpaces.add(greenSpace);
    }

    /**
     * Checks if the repository contains a GreenSpace object with the same name and email.
     *
     * @param greenSpace the GreenSpace object to check
     * @return true if a GreenSpace with the same name and email exists, false otherwise
     */
    private static boolean containsGreenSpace(GreenSpace greenSpace) {
        for (GreenSpace gs : greenSpaces) {
            if (gs.getName().equals(greenSpace.getName()) && gs.getEmail().equals(greenSpace.getEmail())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the list of GreenSpace objects in the repository.
     *
     * @return a list of GreenSpace objects
     */
    public static List<GreenSpace> getGreenSpaces() {
        return new ArrayList<>(greenSpaces);
    }
}
```

### Class RegisterGreenSpaceController

```java
package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.domain.Garden;
import pt.ipp.isep.dei.esoft.project.domain.LargeSizedPark;
import pt.ipp.isep.dei.esoft.project.domain.MediumSizedPark;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.util.List;

/**
 * Controller class for registering green spaces.
 */
public class RegisterGreenSpaceController {

    /**
     * Registers a new green space.
     *
     * @param name the name of the green space
     * @param area the area of the green space in square meters
     * @param type the type of the green space
     * @param userEmail the email of the user registering the green space
     * @throws IllegalArgumentException if the provided GreenSpaceType is invalid
     */
    public static void registerGreenSpace(String name, double area, GreenSpaceType type, String userEmail) {
        GreenSpace greenSpace;
        switch (type) {
            case GARDEN:
                greenSpace = new Garden(name, area, userEmail);
                break;
            case MEDIUM_SIZED_PARK:
                greenSpace = new MediumSizedPark(name, area, userEmail);
                break;
            case LARGE_SIZED_PARK:
                greenSpace = new LargeSizedPark(name, area, userEmail);
                break;
            default:
                throw new IllegalArgumentException("Invalid GreenSpaceType");
        }
        GreenSpaceRepository.addGreenSpace(greenSpace);
    }

    /**
     * Retrieves all registered green spaces.
     *
     * @return a list of GreenSpace objects
     */
    public static List<GreenSpace> getAllGreenSpaces() {
        return GreenSpaceRepository.getGreenSpaces();
    }
}
```

### Class GreenSpace
```java
package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Represents a type of green space.
 */
public abstract class GreenSpace {
    private String name;
    private double area;
    private GreenSpaceType type;
    private String email;

    /**
     * Constructs a GreenSpace object with the specified name, area, type, and email.
     *
     * @param name  the name of the green space
     * @param area  the area of the green space in square meters
     * @param type  the type of the green space
     * @param email the email associated with the green space
     */
    public GreenSpace(String name, double area, GreenSpaceType type, String email) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (area <= 0) {
            throw new IllegalArgumentException("Area must be positive");
        }
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }

        this.name = name;
        this.area = area;
        this.type = type;
        this.email = email;
    }


    /**
     * Gets the name of the green space.
     *
     * @return the name of the green space
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the area of the green space.
     *
     * @return the area of the green space in square meters
     */
    public double getArea() {
        return area;
    }

    /**
     * Gets the type of the green space.
     *
     * @return the type of the green space
     */
    public GreenSpaceType getType() {
        return type;
    }

    /**
     * Gets the email associated with the green space.
     *
     * @return the email associated with the green space
     */
    public String getEmail() {
        return email;
    }

    /**
     * Displays the details of the green space.
     */
    public abstract void displayDetails();
}

```

## 6. Integration and Demo

* Added a new option on the Employee menu to register a green space.
* Bootstrapped some green spaces when the system starts for demo purposes.

## 7. Observations

n/a