# US027 - List all green spaces managed by me

## 4.1 Domain Tests
**GreenSpace**

**Test 1:** This test verifies that the getName() method of the GreenSpace class returns the correct name.

	 @Test
    public void testGetName() {
        GreenSpace greenSpace = new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "test@example.com");
        String name = greenSpace.getName();
        assertEquals("Test", name);
    }



**Test 2:** Verifies that the getArea() method returns the correct area.

    @Test
    public void testGetArea() {
        GreenSpace greenSpace = new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "test@example.com");
        double area = greenSpace.getArea();
        assertEquals(100.0, area, 0.001);
    }


**Test 3:** Verifies that the getType() method returns the correct type.
  
    @Test
    public void testGetType() {
        GreenSpace greenSpace = new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "test@example.com");
        GreenSpaceType type = greenSpace.getType();
        assertEquals(GreenSpaceType.GARDEN, type);
    }

**Test 4:** Verifies that the getEmail() method returns the correct email.

    @Test
    public void testGetEmail() {
        GreenSpace greenSpace = new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "test@example.com");
        String email = greenSpace.getEmail();
        assertEquals("test@example.com", email);
    }

    
**Test 5:** Ensures that a GreenSpace object cannot be created with a negative area.
    
    @Test
    public void testNegativeArea() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace("Test", -100.0, GreenSpaceType.GARDEN, "test@example.com");
        });
    }

**Test 6:** Ensures that a GreenSpace object cannot be created with an area of zero.

    @Test
    public void testZeroArea() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace("Test", 0.0, GreenSpaceType.GARDEN, "test@example.com");
        });
    }

**Test 6:** Ensures that a GreenSpace object cannot be created with an empty name.

    @Test
    public void testEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace("", 100.0, GreenSpaceType.GARDEN, "test@example.com");
        });
    }

**Test 7:** Ensures that a GreenSpace object cannot be created with a null name.

    @Test
    public void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace(null, 100.0, GreenSpaceType.GARDEN, "test@example.com");
        });
    }

**Test 8:** Ensures that a GreenSpace object cannot be created with an invalid email address.


    @Test
    public void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "invalid-email");
        });
    }

**Test 9:** Ensures that a GreenSpace object cannot be created with a null email.

    @Test
    public void testNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, null);
        });
    }

**Test 10:** Ensures that a GreenSpace object cannot be created with a null type.

    @Test
    public void testNullType() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace("Test", 100.0, null, "test@example.com");
        });
    }

## 4.2 Repository tests
**GreenSpaceRepository**

**Test 1:** This test is checking the functionality of adding a GreenSpace object to the repository and then retrieving it.

    @Test
    public void testAddAndGetGreenSpaces() {
        GreenSpace greenSpace = new Garden("Central Park", 500.0, "user@example.com");
        GreenSpaceRepository.addGreenSpace(greenSpace);
        List<GreenSpace> greenSpaces = GreenSpaceRepository.getGreenSpaces();
        assertEquals(1, greenSpaces.size());
        assertEquals(greenSpace, greenSpaces.get(0));
    }


**Test 2:** This test ensures that when the repository is empty, calling getGreenSpaces() returns an empty list.
     
    @Test
    public void testGetGreenSpaces_EmptyList() {
        List<GreenSpace> greenSpaces = GreenSpaceRepository.getGreenSpaces();
        assertTrue(greenSpaces.isEmpty());
    }

    
**Test 3:** This test verifies that the repository throws an IllegalArgumentException when attempting to add a null GreenSpace object.
     
    @Test
    public void testAddGreenSpace_Null() {
        GreenSpace greenSpace = null;
        assertThrows(IllegalArgumentException.class, () -> {
            GreenSpaceRepository.addGreenSpace(greenSpace);
        });
    }

    
**Test 4:** This test checks the repository's behavior when attempting to add a duplicate GreenSpace object, throwing an IllegalArgumentException.

    @Test
    public void testAddGreenSpace_Duplicate() {
        GreenSpace greenSpace1 = new Garden("Central Park", 500.0, "user1@example.com");
        GreenSpaceRepository.addGreenSpace(greenSpace1);

        GreenSpace greenSpace2 = new Garden("Central Park", 500.0, "user1@example.com");
        assertThrows(IllegalArgumentException.class, () -> {
            GreenSpaceRepository.addGreenSpace(greenSpace2);
        });
    }

**Test 5:** This test checks the repository's behavior when attempting to add an invalid GreenSpace object. It expects the repository to throw an IllegalArgumentException for each case of invalid input.

    @Test
    public void testAddGreenSpace_InvalidGreenSpace() {
        assertThrows(IllegalArgumentException.class, () -> {
            GreenSpace greenSpace = new Garden("", -500.0, "invalid-email");
            GreenSpaceRepository.addGreenSpace(greenSpace);
        });
    }

## 5. Construction (Implementation)

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
        if (containsGreenSpace(greenSpace)) {
            throw new IllegalArgumentException("Duplicate GreenSpace cannot be added");
        }
        greenSpaces.add(greenSpace);
    }

    /**
     * Checks if the repository contains a duplicate of the provided GreenSpace.
     *
     * @param greenSpace the GreenSpace object to check for duplication
     * @return true if the repository contains a duplicate of the provided GreenSpace, false otherwise
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
     * Retrieves a list of all GreenSpace objects stored in the repository.
     *
     * @return a list of GreenSpace objects
     */
    public static List<GreenSpace> getGreenSpaces() {
        return new ArrayList<>(greenSpaces);
    }
}

```


## 6. Integration and Demo 

* A new option on the GSM menu options was added.

* For demo purposes some jobs are bootstrapped while system starts.


## 7. Observations

n/a