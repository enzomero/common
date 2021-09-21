package api.wpm.common.api;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public interface CsvTool {
    default List<String> getLines(final BufferedReader csvReader) {
        return csvReader.lines().collect(Collectors.toList());
    }

    @SneakyThrows
    default void writeLines(final FileWriter writer, List<String> strings) {
        strings.forEach(s -> writeLine(writer, s));
    }

    default boolean isNotTitleLine(final String s) {
        return !s.contains("Quantity");
    }

    default void writeLine(final FileWriter writer, final String line) {
        try {
            writer.write(line);
        } catch (IOException e) {}
    }
}
