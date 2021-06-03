package course.advance.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * Utility class
 */
public class DirectoryUtils {

    /**
     * Disabled
     */
    private DirectoryUtils() {
    }

    /**
     * @param path path to directory
     * @return internal files and directories separated by '\n'
     * @throws IOException IOException
     */
    public static String getDirectoryContent(Path path) throws IOException {
        return getInternalDirectories(path) + getInternalFiles(path);
    }

    /**
     * @param path path to directory
     * @return only internal directories separated by '\n'
     * @throws IOException IOException
     */
    public static String getInternalDirectories(Path path) throws IOException {
        return Files.list(path)
                .filter(Files::isDirectory)
                .map(Path::getFileName)
                .map(Path::toString)
                .map(p -> p.concat("/"))
                .collect(Collectors.joining("\n", "", "\n"));
    }

    /**
     * @param path path to directory
     * @return only internal files separated by '\n'
     * @throws IOException IOException
     */
    public static String getInternalFiles(Path path) throws IOException {
        return Files.list(path)
                .filter(f -> !Files.isDirectory(f))
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.joining("\n"));
    }
}
