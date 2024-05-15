package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for CollaboratorRepository.
 */
public class CollaboratorRepositoryTest {

    private CollaboratorRepository collaboratorRepository;

    @BeforeEach
    public void setUp() {
        collaboratorRepository = new CollaboratorRepository();
    }

    /**
     * Test if adding a collaborator to an empty repository returns the collaborator.
     */
    @Test
    public void testAdd_CollaboratorToEmptyRepository_ReturnsCollaborator() {
        // Arrange
        Collaborator collaborator = new Collaborator("John Doe", LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1), "123 Main St", "123456789",
                "john.doe@example.com", 123456789, 12345678L, "Software Developer");

        // Act
        Optional<Collaborator> addedCollaborator = collaboratorRepository.add(collaborator);

        // Assert
        assertTrue(addedCollaborator.isPresent());
        assertEquals(collaborator, addedCollaborator.get());
    }

    /**
     * Test if adding a collaborator with unique taxpayer and BI numbers returns the collaborator.
     */
    @Test
    public void testAdd_CollaboratorWithUniqueTaxpayerBINumbers_ReturnsCollaborator() {
        // Arrange
        collaboratorRepository.add(new Collaborator("John Doe", LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1), "123 Main St", "123456789",
                "john.doe@example.com", 123456789, 12345678L, "Software Developer"));

        // Act
        Collaborator newCollaborator = new Collaborator("Jane Smith", LocalDate.of(1995, 8, 20),
                LocalDate.of(2021, 2, 10), "456 Oak St", "987654321",
                "jane.smith@example.com", 987654321, 87654321L, "Data Analyst");
        Optional<Collaborator> addedCollaborator = collaboratorRepository.add(newCollaborator);

        // Assert
        assertTrue(addedCollaborator.isPresent());
        assertEquals(newCollaborator, addedCollaborator.get());
    }

    /**
     * Test if adding a collaborator with a duplicate taxpayer number returns empty optional.
     */
    @Test
    public void testAdd_CollaboratorWithDuplicateTaxpayerNumber_ReturnsEmptyOptional() {
        // Arrange
        Collaborator firstCollaborator = new Collaborator("John Doe", LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1), "123 Main St", "123456789",
                "john.doe@example.com", 123456789, 12345678L, "Software Developer");
        collaboratorRepository.add(firstCollaborator);

        // Act
        Collaborator duplicateTaxpayerCollaborator = new Collaborator("Jane Smith", LocalDate.of(1995, 8, 20),
                LocalDate.of(2021, 2, 10), "456 Oak St", "987654321",
                "jane.smith@example.com", 123456789, 87654321L, "Data Analyst");
        Optional<Collaborator> addedCollaborator = collaboratorRepository.add(duplicateTaxpayerCollaborator);

        // Assert
        assertTrue(addedCollaborator.isEmpty());
    }

    /**
     * Test if adding a collaborator with a duplicate BI number returns empty optional.
     */
    @Test
    public void testAdd_CollaboratorWithDuplicateBINumber_ReturnsEmptyOptional() {
        // Arrange
        Collaborator firstCollaborator = new Collaborator("John Doe", LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1), "123 Main St", "123456789",
                "john.doe@example.com", 123456789, 12345678L, "Software Developer");
        collaboratorRepository.add(firstCollaborator);

        // Act
        Collaborator duplicateBICollaborator = new Collaborator("Jane Smith", LocalDate.of(1995, 8, 20),
                LocalDate.of(2021, 2, 10), "456 Oak St", "987654321",
                "jane.smith@example.com", 987654321, 12345678L, "Data Analyst");
        Optional<Collaborator> addedCollaborator = collaboratorRepository.add(duplicateBICollaborator);

        // Assert
        assertTrue(addedCollaborator.isEmpty());
    }

    /**
     * Test if getting the list of collaborators returns an empty list for an empty repository.
     */
    @Test
    public void testGetCollaborators_EmptyRepository_ReturnsEmptyList() {
        // Act
        List<Collaborator> collaboratorList = CollaboratorRepository.getCollaborators();

        // Assert
        assertTrue(collaboratorList.isEmpty());
    }

    /**
     * Test if getting the list of collaborators returns a list containing all collaborators.
     */
    @Test
    public void testGetCollaborators_NonEmptyRepository_ReturnsAllCollaborators() {
        // Arrange
        Collaborator collaborator1 = new Collaborator("John Doe", LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1), "123 Main St", "123456789",
                "john.doe@example.com", 123456789, 12345678L, "Software Developer");
        Collaborator collaborator2 = new Collaborator("Jane Smith", LocalDate.of(1995, 8, 20),
                LocalDate.of(2021, 2, 10), "456 Oak St", "987654321",
                "jane.smith@example.com", 987654321, 87654321L, "Data Analyst");
        collaboratorRepository.add(collaborator1);
        collaboratorRepository.add(collaborator2);

        // Act
        List<Collaborator> collaboratorList = CollaboratorRepository.getCollaborators();

        // Assert
        assertEquals(2, collaboratorList.size());
        assertTrue(collaboratorList.contains(collaborator1));
        assertTrue(collaboratorList.contains(collaborator2));
    }

    /**
     * Test if the isTaxpayerNumberDuplicate method returns true for a duplicate taxpayer number.
     */
    @Test
    public void testIsTaxpayerNumberDuplicate_DuplicateTaxpayerNumber_ReturnsTrue() {
        // Arrange
        collaboratorRepository.add(new Collaborator("John Doe", LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1), "123 Main St", "123456789",
                "john.doe@example.com", 123456789, 12345678L, "Software Developer"));

        // Act
        boolean isDuplicate = collaboratorRepository.isTaxpayerNumberDuplicate(123456789);

        // Assert
        assertTrue(isDuplicate);
    }

    /**
     * Test if the isTaxpayerNumberDuplicate method returns false for a unique taxpayer number.
     */
    @Test
    public void testIsTaxpayerNumberDuplicate_UniqueTaxpayerNumber_ReturnsFalse() {
        // Arrange
        collaboratorRepository.add(new Collaborator("John Doe", LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1), "123 Main St", "123456789",
                "john.doe@example.com", 123456789, 12345678L, "Software Developer"));

        // Act
        boolean isDuplicate = collaboratorRepository.isTaxpayerNumberDuplicate(987654321);

        // Assert
        assertFalse(isDuplicate);
    }

    /**
     * Test if the isBINumberDuplicate method returns true for a duplicate BI number.
     */
    @Test
    public void testIsBINumberDuplicate_DuplicateBINumber_ReturnsTrue() {
        // Arrange
        collaboratorRepository.add(new Collaborator("John Doe", LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1), "123 Main St", "123456789",
                "john.doe@example.com", 123456789, 12345678L, "Software Developer"));

        // Act
        boolean isDuplicate = collaboratorRepository.isBINumberDuplicate(12345678L);

        // Assert
        assertTrue(isDuplicate);
    }

    /**
     * Test if the isBINumberDuplicate method returns false for a unique BI number.
     */
    @Test
    public void testIsBINumberDuplicate_UniqueBINumber_ReturnsFalse() {
        // Arrange
        collaboratorRepository.add(new Collaborator("John Doe", LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1), "123 Main St", "123456789",
                "john.doe@example.com", 123456789, 12345678L, "Software Developer"));

        // Act
        boolean isDuplicate = collaboratorRepository.isBINumberDuplicate(87654321L);

        // Assert
        assertFalse(isDuplicate);
    }
}
