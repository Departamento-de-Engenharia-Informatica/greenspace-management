package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Collaborator.
 */
public class CollaboratorTest {

    /**
     * Tests if the constructor throws IllegalArgumentException when name is null.
     */
    @Test
    public void testConstructor_NullName_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator(null,
                birthdayDate, admissionDate,
                "123 Main St", "123456789",
                "john.doe@example.com",
                123456789, 12345678L, "Software Developer"));
    }

    /**
     * Tests if the constructor throws IllegalArgumentException when birthday date is null.
     */
    @Test
    public void testConstructor_NullBirthdayDate_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                null, admissionDate,
                "123 Main St", "123456789",
                "john.doe@example.com",
                123456789, 12345678L, "Software Developer"));
    }

    /**
     * Tests if the constructor throws IllegalArgumentException when admission date is null.
     */
    @Test
    public void testConstructor_NullAdmissionDate_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, null,
                "123 Main St", "123456789",
                "john.doe@example.com",
                123456789, 12345678L, "Software Developer"));
    }

    /**
     * Tests if the constructor throws IllegalArgumentException when address is null.
     */
    @Test
    public void testConstructor_NullAddress_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, admissionDate,
                null, "123456789",
                "john.doe@example.com",
                123456789, 12345678L, "Software Developer"));
    }

    /**
     * Tests if the constructor throws IllegalArgumentException when phone number is invalid.
     */
    @Test
    public void testConstructor_InvalidPhoneNumber_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, admissionDate,
                "123 Main St", "1234567", // Invalid phone number (less than 9 digits)
                "john.doe@example.com",
                123456789, 12345678L, "Software Developer"));
    }

    /**
     * Tests if the constructor throws IllegalArgumentException when email is invalid.
     */
    @Test
    public void testConstructor_InvalidEmail_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, admissionDate,
                "123 Main St", "123456789",
                "johndoeexample.com", // Invalid email (missing "@")
                123456789, 12345678L, "Software Developer"));
    }

    /**
     * Tests if the constructor throws IllegalArgumentException when taxpayer number is invalid.
     */
    @Test
    public void testConstructor_InvalidTaxpayerNumber_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, admissionDate,
                "123 Main St", "123456789",
                "john.doe@example.com",
                -123456789, // Invalid taxpayer number (negative)
                12345678L, "Software Developer"));
    }

    /**
     * Tests if the constructor throws IllegalArgumentException when BI number is invalid.
     */
    @Test
    public void testConstructor_InvalidBINumber_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, admissionDate,
                "123 Main St", "123456789",
                "john.doe@example.com",
                123456789, // Valid taxpayer number
                1234567L, // Invalid BI number (less than 8 digits)
                "Software Developer"));
    }

    /**
     * Tests if the constructor throws IllegalArgumentException when job is null.
     */
    @Test
    public void testConstructor_NullJob_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, admissionDate,
                "123 Main St", "123456789",
                "john.doe@example.com",
                123456789, 12345678L, null)); // Null job
    }
}
