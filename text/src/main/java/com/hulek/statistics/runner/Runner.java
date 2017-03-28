package com.hulek.statistics.runner;

import com.hulek.statistics.text.structure.services.VowelStatisticsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * @author: Paweł Hulek
 * @created: 2017-03-28.
 */
public class Runner {
    public static final String SRC_MAIN_RESOURCES = "text/src/main/resources/";
    //should be logged by slf4j
    private final static Logger log = Logger.getLogger("Runner");

    public static void main(String[] args) {
        String inputFileName = SRC_MAIN_RESOURCES + "INPUT.txt";
        String outputFileName = SRC_MAIN_RESOURCES + "OUTPUT.txt";
        String text = "";
        try (Stream<String> stream = Files.lines(Paths.get(inputFileName))) {
            text = stream.reduce("", (s, s2) -> s + " " + s2);
        } catch (IOException e) {
            log.log(Level.WARNING, "Error while reading from file", e);
        }
        List<String> stats = new VowelStatisticsService().generateStatistics(text);
        try {
            Files.write(Paths.get(outputFileName), stats);
        } catch (IOException e) {
            log.log(Level.WARNING, "Error while writing to file", e);
        }

    }
}
