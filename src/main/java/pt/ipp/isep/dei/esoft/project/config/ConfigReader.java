package pt.ipp.isep.dei.esoft.project.config;

import pt.ipp.isep.dei.esoft.project.ui.console.GreenSpaceSorter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileOutputStream;

import java.io.OutputStream;

/**
 * Utility class for reading configuration properties.
 */
public class ConfigReader {

    /**
     * Reads the sorting algorithm class from the configuration file and instantiates it.
     *
     * @return the instantiated GreenSpaceSorter object representing the chosen sorting algorithm,
     *         or null if an error occurs during reading or instantiation
     */
    public static GreenSpaceSorter readSortingAlgorithm() {
        Properties prop = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(input);
            String sortingAlgorithmClass = prop.getProperty("sortingAlgorithm");
            return (GreenSpaceSorter) Class.forName(sortingAlgorithmClass).newInstance();
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Updates the sorting algorithm class in the configuration file.
     *
     * @param sortingAlgorithmClass the fully qualified class name of the sorting algorithm
     */
    public static void updateSortingAlgorithm(String sortingAlgorithmClass) {
        Properties prop = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(input);
            prop.setProperty("sortingAlgorithm", sortingAlgorithmClass);
            try (OutputStream output = new FileOutputStream("config.properties")) {
                prop.store(output, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
