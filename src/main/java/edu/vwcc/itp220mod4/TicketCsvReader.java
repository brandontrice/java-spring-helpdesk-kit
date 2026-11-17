package edu.vwcc.itp220mod4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 1: read help desk tickets from a CSV file.
 */
public class TicketCsvReader {

    /**
     * Instructions:
     * Read all lines from csvPath. The first line is the header, so skip it.
     * Skip blank lines. Split every other line into exactly five fields using
     * split(",", -1), trim each field, and build a Ticket. Convert priority
     * and status with their enum valueOf methods. If a data row does not have
     * exactly five fields, throw IllegalArgumentException. Return the tickets
     * in the same order as the file.
     */
    public static List<Ticket> read(Path csvPath) throws IOException {
        List<String> lines = Files.readAllLines(csvPath);
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] fields = line.split(",", -1);
            if (fields.length != 5) {
                throw new IllegalArgumentException(
                        "Expected 5 fields but found " + fields.length);
            }
            String id = fields[0].trim();
            String category = fields[1].trim();
            Priority priority = Priority.valueOf(fields[2].trim());
            TicketStatus status = TicketStatus.valueOf(fields[3].trim());
            String technician = fields[4].trim();
            tickets.add(new Ticket(id, category, priority, status, technician));
        }
        return tickets;
    }
}
